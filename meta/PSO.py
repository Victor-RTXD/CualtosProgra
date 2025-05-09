import numpy as np
import random

# Parámetros
num_particles = 30
num_iterations = 100
w = 0.7  # Inercia
c1 = 1.4  # Coeficiente cognitivo
c2 = 1.4  # Coeficiente social
x_min, x_max = -10, 10  # Rango de búsqueda

# Función objetivo
def objective_function(x):
    return x**2

# Inicialización de partículas
particles = np.random.uniform(x_min, x_max, num_particles)
velocities = np.random.uniform(-1, 1, num_particles)
personal_best_positions = np.copy(particles)
personal_best_values = np.array([objective_function(x) for x in particles])

# Mejor posición global
global_best_position = personal_best_positions[np.argmin(personal_best_values)]
global_best_value = min(personal_best_values)

# Iteraciones del algoritmo PSO
for _ in range(num_iterations):
    for i in range(num_particles):
        # Actualización de velocidad
        r1, r2 = random.random(), random.random()
        velocities[i] = (w * velocities[i] + 
                         c1 * r1 * (personal_best_positions[i] - particles[i]) + 
                         c2 * r2 * (global_best_position - particles[i]))
        
        # Actualización de posición
        particles[i] += velocities[i]
        particles[i] = np.clip(particles[i], x_min, x_max)  # Limitar al rango permitido
        
        # Evaluar función objetivo
        fitness = objective_function(particles[i])
        
        # Actualizar mejor posición personal
        if fitness < personal_best_values[i]:
            personal_best_values[i] = fitness
            personal_best_positions[i] = particles[i]
        
    # Actualizar mejor posición global
    current_best_index = np.argmin(personal_best_values)
    if personal_best_values[current_best_index] < global_best_value:
        global_best_value = personal_best_values[current_best_index]
        global_best_position = personal_best_positions[current_best_index]

print(f"Mejor posición encontrada: {global_best_position}")
print(f"Mejor valor encontrado: {global_best_value}")
