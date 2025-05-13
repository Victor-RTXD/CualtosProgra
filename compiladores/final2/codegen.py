# codegen.py

class CodeGenerator:
    def __init__(self):
        self.temp_count = 0
        self.code = []

    def new_temp(self):
        self.temp_count += 1
        return f"t{self.temp_count}"

    def generate(self, ast):
        for node in ast:
            if node.nodetype == 'DECL':
                t = self.new_temp()
                self.code.append(f"{t} = {node.value['value']}  // {node.value['type']} {node.value['id']}")
            elif node.nodetype == 'PRINTF':
                args = ', '.join(node.value)
                self.code.append(f"call printf, {args}")
            elif node.nodetype == 'RETURN':
                self.code.append(f"return {node.value}")
            elif node.nodetype == 'DEFINE':
                self.code.append(f"// macro: {node.value}")
        return self.code
