inventario = dict()
inventario["manzana"] = 5
inventario["leche"] = 3
print(inventario)

#encontrar duplicados
def check_voter(name):
    if voted.get(name):
        print("kick them out")
    else:
        voted[name] = True
        print("let them vote")

voted = {}
check_voter("roy")
check_voter("roy")
check_voter("pepe")

#lista telefonica
phone = {}
phone["victor"] = 65656565
