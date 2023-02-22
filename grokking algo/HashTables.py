inventario = dict()
inventario["manzana"] = 5
inventario["leche"] = 3
print(inventario)

def votaciones(name):
    if votacion.get(name):
        print("kick them out")
    else:
        print("let them vote")
        votacion[name] = True

votacion = {}
votacion["pepe"] = False
votaciones(votacion["pepe"])
votaciones(votacion["pepe"])