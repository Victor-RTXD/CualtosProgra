def generate_code(ast):
    code_lines = []

    def emit(node, indent=0):
        space = "    " * indent
        if node.type == "Function":
            code_lines.append(f"{space}func {node.value}() {{")
            for child in node.children:
                emit(child, indent + 1)
            code_lines.append(f"{space}}}")
        elif node.type == "Declaration":
            line = f"{space}let {node.value}"
            if node.children:
                line += f" = {node.children[0].value}"
            code_lines.append(line + ";")
        elif node.type == "Return":
            code_lines.append(f"{space}return {node.value};")
        elif node.type == "Program":
            for child in node.children:
                emit(child, indent)

    emit(ast)
    return "\n".join(code_lines)
