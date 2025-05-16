from parser import ASTNode, Program, Function, VarDeclaration, BinaryExpression, UnaryExpression
from parser import Identifier, Literal, CallExpression, AssignmentExpression, ReturnStatement
from parser import IfStatement, WhileStatement, ForStatement, CompoundStatement, ExpressionStatement
from parser import Parameter

class SemanticError:
    def __init__(self, message, line, column):
        self.message = message
        self.line = line
        self.column = column
    
    def __str__(self):
        return f"Error semántico  {self.message}"

class Symbol:
    def __init__(self, name, type_spec, line, column):
        self.name = name
        self.type = type_spec
        self.line = line
        self.column = column

class FunctionSymbol(Symbol):
    def __init__(self, name, return_type, params, line, column):
        super().__init__(name, "function", line, column)
        self.return_type = return_type
        self.params = params  # Lista de tipos de parámetros

class SymbolTable:
    def __init__(self, parent=None):
        self.symbols = {}
        self.parent = parent
    
    def define(self, symbol):
        """Añade un símbolo a la tabla."""
        self.symbols[symbol.name] = symbol
    
    def resolve(self, name):
        """Busca un símbolo en esta tabla o en las tablas padres."""
        if name in self.symbols:
            return self.symbols[name]
        elif self.parent:
            return self.parent.resolve(name)
        return None

class SemanticAnalyzer:
    def __init__(self):
        self.errors = []
        self.current_function = None
    
    def analyze(self, ast):
        """Punto de entrada principal para el análisis semántico."""
        if not isinstance(ast, Program):
            self.errors.append(SemanticError("Se esperaba un programa", 0, 0))
            return self.errors
        
        # Tabla de símbolos global
        global_scope = SymbolTable()
        
        # Predefinir algunas funciones comunes de C
        global_scope.define(FunctionSymbol("printf", "int", ["string"], 0, 0))
        global_scope.define(FunctionSymbol("scanf", "int", ["string"], 0, 0))
        global_scope.define(FunctionSymbol("malloc", "void*", ["int"], 0, 0))
        global_scope.define(FunctionSymbol("free", "void", ["void*"], 0, 0))
        
        # Primera pasada: registrar todas las declaraciones de funciones
        for function in ast.functions:
            self.register_function(function, global_scope)
        
        # Primera pasada: registrar todas las declaraciones globales
        for declaration in ast.declarations:
            self.analyze_declaration(declaration, global_scope)
        
        # Segunda pasada: analizar cuerpos de funciones
        for function in ast.functions:
            self.analyze_function(function, global_scope)
        
        return self.errors
    
    def register_function(self, function, scope):
        """Registra una función en la tabla de símbolos."""
        # Obtener tipos de parámetros
        param_types = [param.type for param in function.params]
        
        # Verificar si la función ya existe
        existing = scope.resolve(function.name)
        if existing:
            self.errors.append(SemanticError(
                f"Redefinición de la función '{function.name}'", 
                function.line if hasattr(function, 'line') else 0, 
                function.column if hasattr(function, 'column') else 0
            ))
        else:
            # Añadir la función a la tabla de símbolos
            scope.define(FunctionSymbol(
                function.name, 
                function.return_type, 
                param_types,
                function.line if hasattr(function, 'line') else 0,
                function.column if hasattr(function, 'column') else 0
            ))
    
    def analyze_function(self, function, global_scope):
        """Analiza una función completa."""
        # Crear nueva tabla de símbolos para la función
        function_scope = SymbolTable(global_scope)
        
        # Establecer la función actual (para verificar returns)
        self.current_function = function
        
        # Registrar parámetros en la tabla de símbolos
        for param in function.params:
            function_scope.define(Symbol(
                param.name, 
                param.type,
                param.line if hasattr(param, 'line') else 0,
                param.column if hasattr(param, 'column') else 0
            ))
        
        # Analizar el cuerpo de la función
        self.analyze_compound_statement(function.body, function_scope)
        
        # Restablecer la función actual
        self.current_function = None
    
    def analyze_compound_statement(self, compound, scope):
        """Analiza un bloque de código compuesto."""
        # Crear nueva tabla de símbolos para este bloque
        block_scope = SymbolTable(scope)
        
        # Analizar declaraciones en este bloque
        for decl in compound.declarations:
            self.analyze_declaration(decl, block_scope)
        
        # Analizar sentencias en este bloque
        for stmt in compound.statements:
            self.analyze_statement(stmt, block_scope)
    
    def analyze_declaration(self, declaration, scope):
        """Analiza una declaración de variable."""
        # Verificar si la variable ya está declarada en este ámbito
        existing = scope.symbols.get(declaration.name)
        if existing:
            self.errors.append(SemanticError(
                f"Redefinición de la variable '{declaration.name}'",
                declaration.line if hasattr(declaration, 'line') else 0,
                declaration.column if hasattr(declaration, 'column') else 0
            ))
        else:
            # Agregar la variable a la tabla de símbolos
            scope.define(Symbol(
                declaration.name,
                declaration.type,
                declaration.line if hasattr(declaration, 'line') else 0,
                declaration.column if hasattr(declaration, 'column') else 0
            ))
            
            # Si hay un inicializador, analizarlo
            if declaration.initializer:
                init_type = self.analyze_expression(declaration.initializer, scope)
                self.check_types_compatible(
                    declaration.type, 
                    init_type, 
                    f"Tipos incompatibles en la inicialización de '{declaration.name}'",
                    declaration.line if hasattr(declaration, 'line') else 0,
                    declaration.column if hasattr(declaration, 'column') else 0
                )
    
    def analyze_statement(self, statement, scope):
        """Analiza una sentencia."""
        if isinstance(statement, ExpressionStatement):
            if statement.expression:
                self.analyze_expression(statement.expression, scope)
        
        elif isinstance(statement, CompoundStatement):
            self.analyze_compound_statement(statement, scope)
        
        elif isinstance(statement, IfStatement):
            cond_type = self.analyze_expression(statement.condition, scope)
            self.check_condition_type(cond_type, statement.condition)
            
            self.analyze_statement(statement.then_stmt, scope)
            if statement.else_stmt:
                self.analyze_statement(statement.else_stmt, scope)
        
        elif isinstance(statement, WhileStatement):
            cond_type = self.analyze_expression(statement.condition, scope)
            self.check_condition_type(cond_type, statement.condition)
            
            self.analyze_statement(statement.body, scope)
        
        elif isinstance(statement, ForStatement):
            # Crear ámbito para el for
            for_scope = SymbolTable(scope)
            
            # Analizar las tres partes del for
            if statement.init:
                self.analyze_expression(statement.init, for_scope)
            
            if statement.condition:
                cond_type = self.analyze_expression(statement.condition, for_scope)
                self.check_condition_type(cond_type, statement.condition)
            
            if statement.update:
                self.analyze_expression(statement.update, for_scope)
            
            # Analizar el cuerpo
            self.analyze_statement(statement.body, for_scope)
        
        elif isinstance(statement, ReturnStatement):
            # Verificar si estamos en una función
            if not self.current_function:
                self.errors.append(SemanticError(
                    "Sentencia 'return' fuera de función",
                    statement.line if hasattr(statement, 'line') else 0,
                    statement.column if hasattr(statement, 'column') else 0
                ))
                return
            
            # Si hay una expresión, verificar que su tipo coincida con el de retorno
            if statement.expression:
                expr_type = self.analyze_expression(statement.expression, scope)
                self.check_types_compatible(
                    self.current_function.return_type,
                    expr_type,
                    "Tipo de retorno incompatible",
                    statement.line if hasattr(statement, 'line') else 0,
                    statement.column if hasattr(statement, 'column') else 0
                )
            elif self.current_function.return_type != "void":
                self.errors.append(SemanticError(
                    f"Función '{self.current_function.name}' de tipo '{self.current_function.return_type}' debe retornar un valor",
                    statement.line if hasattr(statement, 'line') else 0,
                    statement.column if hasattr(statement, 'column') else 0
                ))
    
    def analyze_expression(self, expr, scope):
        """
        Analiza una expresión y devuelve su tipo.
        """
        if isinstance(expr, BinaryExpression):
            return self.analyze_binary_expression(expr, scope)
        
        elif isinstance(expr, UnaryExpression):
            return self.analyze_unary_expression(expr, scope)
        
        elif isinstance(expr, Identifier):
            return self.analyze_identifier(expr, scope)
        
        elif isinstance(expr, Literal):
            return expr.type
        
        elif isinstance(expr, CallExpression):
            return self.analyze_call_expression(expr, scope)
        
        elif isinstance(expr, AssignmentExpression):
            return self.analyze_assignment_expression(expr, scope)
        
        # Tipo por defecto si no se puede determinar
        return "unknown"
    
    def analyze_binary_expression(self, expr, scope):
        """Analiza una expresión binaria."""
        left_type = self.analyze_expression(expr.left, scope)
        right_type = self.analyze_expression(expr.right, scope)
        
        # Verificar compatibilidad de operadores
        if expr.operator in ['+', '-', '*', '/', '%']:
            # Operadores aritméticos
            if left_type not in ['int', 'float'] or right_type not in ['int', 'float']:
                self.errors.append(SemanticError(
                    f"Operador '{expr.operator}' requiere operandos numéricos",
                    expr.line if hasattr(expr, 'line') else 0,
                    expr.column if hasattr(expr, 'column') else 0
                ))
            
            # Determinar tipo resultante
            if left_type == 'float' or right_type == 'float':
                return 'float'
            return 'int'
        
        elif expr.operator in ['&&', '||']:
            # Operadores lógicos
            self.check_condition_type(left_type, expr.left)
            self.check_condition_type(right_type, expr.right)
            return 'int'  # Los operadores lógicos devuelven int en C
        
        elif expr.operator in ['==', '!=', '<', '>', '<=', '>=']:
            # Operadores de comparación
            self.check_types_compatible(
                left_type, 
                right_type, 
                f"Tipos incompatibles en comparación '{expr.operator}'",
                expr.line if hasattr(expr, 'line') else 0,
                expr.column if hasattr(expr, 'column') else 0
            )
            return 'int'  # Las comparaciones devuelven int en C
        
        elif expr.operator in ['&', '|', '^', '<<', '>>']:
            # Operadores bit a bit
            if left_type != 'int' or right_type != 'int':
                self.errors.append(SemanticError(
                    f"Operador '{expr.operator}' requiere operandos enteros",
                    expr.line if hasattr(expr, 'line') else 0,
                    expr.column if hasattr(expr, 'column') else 0
                ))
            return 'int'
        
        return "unknown"
    
    def analyze_unary_expression(self, expr, scope):
        """Analiza una expresión unaria."""
        operand_type = self.analyze_expression(expr.expression, scope)
        
        if expr.operator in ['-', '~']:
            # Operadores numéricos
            if operand_type not in ['int', 'float']:
                self.errors.append(SemanticError(
                    f"Operador '{expr.operator}' requiere operando numérico",
                    expr.line if hasattr(expr, 'line') else 0,
                    expr.column if hasattr(expr, 'column') else 0
                ))
            return operand_type
        
        elif expr.operator == '!':
            # Operador lógico
            return 'int'
        
        elif expr.operator in ['++', '--', '++_post', '--_post']:
            # Operadores de incremento/decremento
            if operand_type not in ['int', 'float']:
                self.errors.append(SemanticError(
                    f"Operador '{expr.operator.replace('_post', '')}' requiere operando numérico",
                    expr.line if hasattr(expr, 'line') else 0,
                    expr.column if hasattr(expr, 'column') else 0
                ))
            
            # Verificar que el operando sea una variable (lvalue)
            if not isinstance(expr.expression, Identifier):
                self.errors.append(SemanticError(
                    f"Operador '{expr.operator.replace('_post', '')}' requiere un lvalue",
                    expr.line if hasattr(expr, 'line') else 0,
                    expr.column if hasattr(expr, 'column') else 0
                ))
            
            return operand_type
        
        return "unknown"
    
    def analyze_identifier(self, identifier, scope):
        """Analiza un identificador."""
        symbol = scope.resolve(identifier.name)
        if not symbol:
            self.errors.append(SemanticError(
                f"Variable '{identifier.name}' no declarada",
                identifier.line if hasattr(identifier, 'line') else 0,
                identifier.column if hasattr(identifier, 'column') else 0
            ))
            return "unknown"
        
        return symbol.type
    
    def analyze_call_expression(self, call, scope):
        """Analiza una llamada a función."""
        # Verificar si la función existe
        if not isinstance(call.callee, Identifier):
            self.errors.append(SemanticError(
                "Expresión de llamada inválida",
                call.line if hasattr(call, 'line') else 0,
                call.column if hasattr(call, 'column') else 0
            ))
            return "unknown"
        
        function_name = call.callee.name
        function = scope.resolve(function_name)
        
        if not function:
            self.errors.append(SemanticError(
                f"Función '{function_name}' no declarada",
                call.line if hasattr(call, 'line') else 0,
                call.column if hasattr(call, 'column') else 0
            ))
            return "unknown"
        
        if not isinstance(function, FunctionSymbol):
            self.errors.append(SemanticError(
                f"'{function_name}' no es una función",
                call.line if hasattr(call, 'line') else 0,
                call.column if hasattr(call, 'column') else 0
            ))
            return "unknown"
        
        # Verificar número de argumentos
        if len(call.arguments) != len(function.params):
            self.errors.append(SemanticError(
                f"Función '{function_name}' espera {len(function.params)} argumentos, pero se proporcionaron {len(call.arguments)}",
                call.line if hasattr(call, 'line') else 0,
                call.column if hasattr(call, 'column') else 0
            ))
        else:
            # Verificar tipos de argumentos
            for i, (arg, param_type) in enumerate(zip(call.arguments, function.params)):
                arg_type = self.analyze_expression(arg, scope)
                self.check_types_compatible(
                    param_type, 
                    arg_type, 
                    f"Tipo incompatible para el argumento {i+1} de la función '{function_name}'",
                    arg.line if hasattr(arg, 'line') else 0,
                    arg.column if hasattr(arg, 'column') else 0
                )
        
        return function.return_type
    
    def analyze_assignment_expression(self, expr, scope):
        """Analiza una expresión de asignación."""
        # Verificar que el lado izquierdo sea un lvalue
        if not isinstance(expr.left, Identifier):
            self.errors.append(SemanticError(
                "El lado izquierdo de una asignación debe ser un lvalue",
                expr.line if hasattr(expr, 'line') else 0,
                expr.column if hasattr(expr, 'column') else 0
            ))
            return "unknown"
        
        left_type = self.analyze_expression(expr.left, scope)
        right_type = self.analyze_expression(expr.right, scope)
        
        # Verificar compatibilidad de tipos
        if expr.operator == '=':
            self.check_types_compatible(
                left_type, 
                right_type, 
                "Tipos incompatibles en asignación",
                expr.line if hasattr(expr, 'line') else 0,
                expr.column if hasattr(expr, 'column') else 0
            )
        else:
            # Operadores compuestos (+=, -=, etc.)
            base_op = expr.operator[0]
            if base_op in ['+', '-', '*', '/', '%', '&', '|', '^', '<', '>']:
                # Simular operación binaria para verificación
                op_expr = BinaryExpression(expr.left, base_op, expr.right)
                op_type = self.analyze_binary_expression(op_expr, scope)
                
                # Verificar que el resultado sea compatible con el lado izquierdo
                self.check_types_compatible(
                    left_type, 
                    op_type, 
                    f"Tipos incompatibles en operador compuesto '{expr.operator}'",
                    expr.line if hasattr(expr, 'line') else 0,
                    expr.column if hasattr(expr, 'column') else 0
                )
        
        return left_type
    
    def check_condition_type(self, type_name, expr):
        """Verifica que un tipo sea válido para una condición."""
        if type_name not in ['int', 'float', 'char', 'unknown']:
            self.errors.append(SemanticError(
                "La condición debe ser de tipo numérico o puntero",
                expr.line if hasattr(expr, 'line') else 0,
                expr.column if hasattr(expr, 'column') else 0
            ))
    
    def check_types_compatible(self, expected, actual, message, line, column):
        """Verifica que dos tipos sean compatibles."""
        # Ignorar verificación si alguno es desconocido
        if expected == "unknown" or actual == "unknown":
            return
        
        # Conversión implícita int -> float permitida
        if expected == 'float' and actual == 'int':
            return
        
        # Conversión implícita char -> int permitida
        if expected == 'int' and actual == 'char':
            return
        
        # Tipos básicos deben coincidir
        if expected != actual:
            self.errors.append(SemanticError(message, line, column))