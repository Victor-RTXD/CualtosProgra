import random

poblacion = [10, 20, 30, 50, 70, 90, 100, 85, 40, 60]

def torneoAl(poblacion): 
    a, b = random.sample(poblacion, 2)  # Selecciona dos individuos al azar
    return max(a, b)  # Elige el mejor de los dos

def torneo(iteraciones):
    global poblacion  # Declarar global ANTES de usar poblacion
    
    auxiliar = []  # Lista local para la nueva población
    for _ in range(iteraciones): 
        auxiliar.append(torneoAl(poblacion))  # Usar torneoAl en lugar de torneo
        print("Ganador del torneo:", auxiliar[-1])  # Mostrar el último ganador

    poblacion = auxiliar  # Actualizar la población global
    print("Nueva población:", poblacion)

# Ejecutar los torneos
torneo(10) #1
print("*******************")
torneo(10) #2
print("*******************")

#3 mejores
poblacion = sorted(poblacion, reverse=True)
tres_mejores = poblacion[:3]
    
auxiliar = []  # Lista local para la nueva población
for _ in range(3): 
    auxiliar.append(torneoAl(tres_mejores))  # Usar torneoAl en lugar de torneo
    print("Ganador del torneo:", auxiliar)  # Mostrar el último ganador

poblacion[0] = auxiliar[0] 
poblacion[1] = auxiliar[1]
poblacion[2] = auxiliar[2]
print("Nueva población:", poblacion)
print("*******************")

# 4 peores
poblacion = sorted(poblacion)
cuatro_peores = poblacion[:4]
    
auxiliar = []  # Lista local para la nueva población
for _ in range(4): 
    auxiliar.append(torneoAl(cuatro_peores))  # Usar torneoAl en lugar de torneo
    print("Ganador del torneo:", auxiliar)  # Mostrar el último ganadorpoblacion = auxiliar  # Actualizar la población global

poblacion[0] = auxiliar[0] 
poblacion[1] = auxiliar[1]
poblacion[2] = auxiliar[2]
poblacion[3] = auxiliar[3]
print("Nueva población:", poblacion)