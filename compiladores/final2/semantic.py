def semantic_analysis(ast):
    symbol_table = {}

    def check_node(node, scope_vars=None):
        if scope_vars is None:
            scope_vars = {}

        if node.type == "Function":
            symbol_table[node.value] = "function"
            local_vars = {}
            for child in node.children:
                check_node(child, local_vars)

        elif node.type == "Declaration":
            varname = node.value
            if varname in scope_vars:
                raise Exception(f"Variable '{varname}' ya declarada")
            if node.children:
                value_node = node.children[0]
                # Inferir tipo: flotante, entero, carácter
                if value_node.value.replace('.', '', 1).isdigit():
                    scope_vars[varname] = "float" if '.' in value_node.value else "int"
                elif len(value_node.value) == 3 and value_node.value.startswith("'"):
                    scope_vars[varname] = "char"
                else:
                    scope_vars[varname] = "unknown"
            else:
                scope_vars[varname] = "unknown"

        elif node.type == "Return":
            pass  # Ignorado para este ejemplo

        elif node.type == "Program":
            for child in node.children:
                check_node(child, scope_vars)

        # Agrega más validaciones según el AST generado por el parser

    check_node(ast)
