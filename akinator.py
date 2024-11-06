"""
En primer lugar, se define una lista llamada features que contiene diccionarios con características de varios personajes, donde cada diccionario 
incluye el nombre del personaje y valores booleanos que describen atributos como si el personaje es humano, femenino, youtuber, si está en una película, 
si es ficticio, inventor o indio. A continuación, el juego muestra la lista de personajes disponibles y solicita al usuario que ingrese su personaje. 
Luego, el programa hace preguntas sobre las características del personaje, y el usuario debe responder "y" (sí) o "n" (no). La función adivinar_personaje() 
es responsable de filtrar los personajes en la lista según las respuestas del usuario. Si el valor de una característica en un personaje no coincide con la 
respuesta del usuario, ese personaje se elimina de la lista. Después de cada respuesta, el programa va actualizando la lista de personajes posibles y, si al 
final solo queda un personaje en la lista, el programa adivina y muestra su nombre.

"""

features = [ 
    {"name": "Shahruk Khan", "human": True, "Female": False, "youtuber": False, "movie": True , "fictional": False, "inventor": False, "indian": True},
    {"name": "Priyanka Chopra", "human": True, "Female": True, "youtuber": False, "movie": True , "fictional": False, "inventor": False, "indian": True},
    {"name": "Chris Hemsworth", "human": True, "Female": False, "youtuber": False, "movie": True , "fictional": False, "inventor": False, "indian": False},
    {"name": "Ariana Grande", "human": True, "Female": True, "youtuber": False, "movie": True , "fictional": False, "inventor": False, "indian": False},
    {"name": "CV Raman", "human": True, "Female": False, "youtuber": False, "movie": False, "fictional": False, "inventor": True, "indian": True},
    {"name": "Janaki Ammal", "human": True, "Female": True, "youtuber": False, "movie": False, "fictional": False, "inventor": True, "indian": True},
    {"name": "Isaac Newton", "human": True, "Female": False, "youtuber": False, "movie": False, "fictional": False, "inventor": True, "indian": False},
    {"name": "Marie Curie", "human": True, "Female": True, "youtuber": False, "movie": False, "fictional": False,"inventor": True, "indian": False},
    {"name": "Carry Minati (Ajey Nagar)", "human": True, "Female": False, "youtuber": True, "movie": False, "fictional": False, "inventor": False, "indian": True},
    {"name": "Mostly Sane (Prajakta Koli)", "human": True, "Female": True, "youtuber": True, "movie": False, "fictional": False, "inventor": False, "indian": True},
    {"name": "PewDiePie", "human": True, "Female": False, "youtuber": True, "movie": False, "fictional": False, "inventor": False, "indian": False},
    {"name": "JENNA MARBLES", "human": True, "Female": True, "youtuber": True, "movie": False, "fictional": False, "inventor": False, "indian": False},
    {"name": "Mogli", "human": False, "Female": False, "youtuber": False, "movie": True, "fictional": True, "inventor": False, "indian": True},
    {"name": "Jasmin", "human": True, "Female": True, "youtuber": False, "movie": True, "fictional": True, "inventor": False, "indian": True},
    {"name": "Hiccup Horrendous Haddock", "human": False, "Female": False, "youtuber": False, "movie": True, "fictional": True, "inventor": False, "indian": False},
    {"name": "Ariel", "human": False, "Female": True, "youtuber": False, "movie": True, "fictional": True, "inventor": False, "indian": False},
    {"name": "Lal Bahadur Shastri", "human": True, "Female": False, "youtuber": False, "movie": False, "fictional": False, "inventor": False, "indian": True},
    {"name": "Indira Gandhi", "human": True, "Female": True, "youtuber": False, "movie": False, "fictional": False, "inventor": False, "indian": True},
    {"name": "Barack Obama", "human": True, "Female": False, "youtuber": False, "movie": False, "fictional": False, "inventor": False, "indian": False},
    {"name": "Angela Merkel", "human": True, "Female": True, "youtuber": False, "movie": False, "fictional": False, "inventor": False, "indian": False},
]

def adivinar_personaje(respuesta, clave):
    if respuesta == "y":
        respuesta_final = True
    else:
        respuesta_final = False

    filtrados = []

    for j in features:
        if j[clave] != respuesta_final:
            filtrados.append(j)

    for i in filtrados:
        features.remove(i)

    if len(features) == 1:
        print("\n\nTu personaje es " + features[0]["name"])
        quit()

print("¡Hola Mundo, Bienvenido a Akinator! Intentaré leer tu mente.")

print("Por favor, adivina cualquier personaje de la lista que se muestra a continuación:")

for i in range(len(features)):
    print(f"{i+1}). {features[i]['name']}")

input("\n\nPresiona enter cuando estés listo...")

print("Por favor, responde las preguntas y trataré de adivinar tu personaje.")

print("¿Tu personaje es humano? (y/n): ")
respuesta = input()
adivinar_personaje(respuesta, "human")

print("¿Tu personaje es mujer? (y/n): ")
respuesta = input()
adivinar_personaje(respuesta, "Female")

print("¿Tu personaje es youtuber? (y/n): ")
respuesta = input()
adivinar_personaje(respuesta, "youtuber")

print("¿Tu personaje está en una película? (y/n): ")
respuesta = input()
adivinar_personaje(respuesta, "movie")

print("¿Tu personaje es ficticio? (y/n): ")
respuesta = input()
adivinar_personaje(respuesta, "fictional")

print("¿Tu personaje es inventor? (y/n): ")
respuesta = input()
adivinar_personaje(respuesta, "inventor")

print("¿Tu personaje es indio? (y/n): ")
respuesta = input()
adivinar_personaje(respuesta, "indian")
