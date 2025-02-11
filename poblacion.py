import random

# poblacion_baja_diversidad = [60, 61, 62, 60, 61, 60, 62, 60, 61, 60]
# poblacion_alta_diversidad = [10, 20, 30, 50, 70, 90, 100, 85, 40, 60]

# print("Poca diversidad:", poblacion_baja_diversidad)
# print("Mucha diversidad:", poblacion_alta_diversidad)

# poblacion = [random.randint(50, 100) for _ in range(10)] 
poblacion = [10, 20, 30, 50, 70, 90, 100, 85, 40, 60]
print("Población más rápida:", poblacion)

def torneo(poblacion): 
    a, b = random.sample(poblacion, 2) 
    return max(a, b)


for _ in range(5): 
    ganador = torneo(poblacion)
    print("Ganador del torneo:", ganador)

#ruleta 
print("*****************")
def ruleta(poblacion):
    suma = sum(poblacion)
    # Se selecciona un valor aleatorio entre 0 y la suma total de la población
    valor = random.uniform(0, suma)
    acumulado = 0
    for individuo in poblacion:
        acumulado += individuo
        if acumulado > valor:
            return individuo
        
for _ in range(5):
    ganador = ruleta(poblacion)
    print("Ganador por ruleta:", ganador)

#elitismo
print("*****************")
maximos = sorted(poblacion, reverse=True)[:5]
print(maximos)