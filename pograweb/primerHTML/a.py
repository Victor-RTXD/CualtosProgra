def encontrar_maximo(array):
    maximo = array[0]
    for elemento in array[1:]:
        if elemento > maximo:
            maximo = elemento
    return maximo

# Inicialización del array desordenado
array_desordenado = [3, 7, 1, 9, 4, 6, 8, 2, 5]

# Llamada a la función y mostrar el resultado
resultado = encontrar_maximo(array_desordenado)
print("El número más grande en el array es:", resultado)
