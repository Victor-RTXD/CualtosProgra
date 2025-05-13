#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""
Implementación interactiva del Algoritmo de Colonia de Hormigas (ACO)
para resolver el Problema del Viajante (TSP) con visualización del
depósito de feromonas y controles interactivos.
"""

import numpy as np
import matplotlib.pyplot as plt
from matplotlib.widgets import Slider, Button
import time
import random
from matplotlib.animation import FuncAnimation

class ACO_TSP:
    """
    Clase que implementa el algoritmo de Colonia de Hormigas para el TSP
    """
    
    def __init__(self, n_cities=20, n_ants=20, alpha=1, beta=5, rho=0.5, Q=100, max_iter=100):
        """
        Inicializa el algoritmo ACO para el TSP
        
        Parámetros:
        -----------
        n_cities : int
            Número de ciudades (nodos)
        n_ants : int
            Número de hormigas en la colonia
        alpha : float
            Importancia de las feromonas (pondera la influencia del rastro de feromonas)
        beta : float
            Importancia de la información heurística (pondera la influencia de la distancia)
        rho : float
            Tasa de evaporación de feromonas (0 a 1)
        Q : float
            Constante que regula la cantidad de feromonas depositadas
        max_iter : int
            Número máximo de iteraciones
        """
        self.n_cities = n_cities
        self.n_ants = n_ants
        self.alpha = alpha
        self.beta = beta
        self.rho = rho
        self.Q = Q
        self.max_iter = max_iter
        
        # Generamos ciudades aleatorias en un espacio 2D
        self.cities = np.random.rand(n_cities, 2)
        
        # Calculamos la matriz de distancias
        self.distances = self._calculate_distances()
        
        # Inicializamos la matriz de feromonas
        self.pheromones = np.ones((n_cities, n_cities))
        
        # Mejor ruta y su longitud
        self.best_path = None
        self.best_path_length = float('inf')
        
        # Historial para fines de visualización
        self.history = {
            'best_path_length': [],
            'avg_path_length': [],
            'pheromone_history': []
        }
    
    def _calculate_distances(self):
        """Calcula la matriz de distancias entre todas las ciudades"""
        distances = np.zeros((self.n_cities, self.n_cities))
        for i in range(self.n_cities):
            for j in range(self.n_cities):
                if i != j:
                    # Distancia euclidiana
                    distances[i, j] = np.sqrt(np.sum((self.cities[i] - self.cities[j])**2))
                else:
                    distances[i, j] = 1e-10  # Evitar división por cero
        return distances
    
    def _select_next_city(self, ant, visited):
        """Selecciona la siguiente ciudad para una hormiga basada en feromonas y distancias"""
        current = ant[-1]
        unvisited = [city for city in range(self.n_cities) if city not in visited]
        
        if not unvisited:
            return None
            
        # Calculamos las probabilidades de selección
        probabilities = []
        total = 0
        
        for city in unvisited:
            # Atractivo = (feromonas ^ alpha) * (1/distancia ^ beta)
            p = (self.pheromones[current, city]**self.alpha) * ((1.0/self.distances[current, city])**self.beta)
            probabilities.append(p)
            total += p
        
        # Normalizamos las probabilidades
        probabilities = [p/total for p in probabilities]
        
        # Selección basada en ruleta
        r = random.random()
        cum_prob = 0
        for i, prob in enumerate(probabilities):
            cum_prob += prob
            if r <= cum_prob:
                return unvisited[i]
        
        return unvisited[-1]  # Por si hay algún error numérico
    
    def _construct_solutions(self):
        """Construye las soluciones para todas las hormigas"""
        ants_paths = []
        ants_path_lengths = []
        
        for _ in range(self.n_ants):
            # Cada hormiga comienza en una ciudad aleatoria
            start_city = random.randint(0, self.n_cities - 1)
            ant_path = [start_city]
            visited = {start_city}
            
            # Construimos el camino completo
            while len(ant_path) < self.n_cities:
                next_city = self._select_next_city(ant_path, visited)
                if next_city is None:
                    break
                ant_path.append(next_city)
                visited.add(next_city)
            
            # Añadimos la vuelta a la ciudad inicial para completar el ciclo
            ant_path.append(ant_path[0])
            
            # Calculamos la longitud del camino
            path_length = sum(self.distances[ant_path[i], ant_path[i+1]] for i in range(len(ant_path)-1))
            
            ants_paths.append(ant_path)
            ants_path_lengths.append(path_length)
            
            # Actualizamos la mejor solución
            if path_length < self.best_path_length:
                self.best_path = ant_path.copy()
                self.best_path_length = path_length
        
        return ants_paths, ants_path_lengths
    
    def _update_pheromones(self, ants_paths, ants_path_lengths):
        """Actualiza la matriz de feromonas basada en los caminos de las hormigas"""
        # Evaporación de feromonas
        self.pheromones *= (1 - self.rho)
        
        # Depósito de feromonas
        for ant_path, path_length in zip(ants_paths, ants_path_lengths):
            for i in range(len(ant_path) - 1):
                self.pheromones[ant_path[i], ant_path[i+1]] += self.Q / path_length
                self.pheromones[ant_path[i+1], ant_path[i]] += self.Q / path_length  # TSP simétrico
    
    def run_iteration(self):
        """Ejecuta una iteración del algoritmo"""
        ants_paths, ants_path_lengths = self._construct_solutions()
        self._update_pheromones(ants_paths, ants_path_lengths)
        
        # Guardamos datos para visualización
        self.history['best_path_length'].append(self.best_path_length)
        self.history['avg_path_length'].append(np.mean(ants_path_lengths))
        self.history['pheromone_history'].append(self.pheromones.copy())
        
        return self.best_path, self.best_path_length
    
    def run(self):
        """Ejecuta el algoritmo completo"""
        for _ in range(self.max_iter):
            self.run_iteration()
        
        return self.best_path, self.best_path_length

class ACOVisualizer:
    """Clase para visualizar el algoritmo ACO en tiempo real"""
    
    def __init__(self, aco):
        """
        Inicializa el visualizador
        
        Parámetros:
        -----------
        aco : ACO_TSP
            Instancia del algoritmo ACO a visualizar
        """
        self.aco = aco
        self.iteration = 0
        self.running = False
        self.speed = 0.5  # velocidad de la animación
        
        # Configuración de la figura
        self.fig = plt.figure(figsize=(14, 10))
        self.fig.canvas.manager.set_window_title('Visualización de Colonia de Hormigas (ACO) para TSP')
        
        # Layout de la figura
        gs = self.fig.add_gridspec(3, 2)
        
        # Área para mostrar las ciudades y caminos
        self.ax_cities = self.fig.add_subplot(gs[0:2, 0])
        self.ax_cities.set_title('Problema del Viajante (TSP)')
        self.ax_cities.set_xlabel('X')
        self.ax_cities.set_ylabel('Y')
        
        # Área para mostrar las feromonas
        self.ax_pheromones = self.fig.add_subplot(gs[0:2, 1])
        self.ax_pheromones.set_title('Matriz de Feromonas')
        
        # Área para mostrar la convergencia
        self.ax_convergence = self.fig.add_subplot(gs[2, :])
        self.ax_convergence.set_title('Convergencia')
        self.ax_convergence.set_xlabel('Iteración')
        self.ax_convergence.set_ylabel('Longitud del Camino')
        
        # Añadimos sliders para los parámetros
        self.slider_ax_alpha = plt.axes([0.2, 0.02, 0.65, 0.03])
        self.slider_alpha = Slider(
            self.slider_ax_alpha, 'Alpha (Importancia de feromonas)', 0.1, 5.0, 
            valinit=self.aco.alpha, valstep=0.1
        )
        
        self.slider_ax_beta = plt.axes([0.2, 0.06, 0.65, 0.03])
        self.slider_beta = Slider(
            self.slider_ax_beta, 'Beta (Peso de distancias)', 0.1, 10.0, 
            valinit=self.aco.beta, valstep=0.1
        )
        
        self.slider_ax_rho = plt.axes([0.2, 0.10, 0.65, 0.03])
        self.slider_rho = Slider(
            self.slider_ax_rho, 'Rho (Tasa de evaporación)', 0.0, 1.0,
            valinit=self.aco.rho, valstep=0.01
        )
        
        # Botones para controlar la simulación
        self.button_ax_start = plt.axes([0.2, 0.16, 0.15, 0.04])
        self.button_start = Button(self.button_ax_start, 'Iniciar')
        
        self.button_ax_pause = plt.axes([0.45, 0.16, 0.15, 0.04])
        self.button_pause = Button(self.button_ax_pause, 'Pausar')
        
        # Eliminamos el botón de reiniciar
        # self.button_ax_reset = plt.axes([0.5, 0.16, 0.1, 0.04])
        # self.button_reset = Button(self.button_ax_reset, 'Reiniciar')
        
        self.button_ax_step = plt.axes([0.7, 0.16, 0.15, 0.04])
        self.button_step = Button(self.button_ax_step, 'Paso')
        
        # Conectamos eventos
        self.slider_alpha.on_changed(self.update_params)
        self.slider_beta.on_changed(self.update_params)
        self.slider_rho.on_changed(self.update_params)
        
        self.button_start.on_clicked(self.start_simulation)
        self.button_pause.on_clicked(self.pause_simulation)
        # Eliminamos la conexión al botón de reiniciar
        # self.button_reset.on_clicked(self.reset_simulation)
        self.button_step.on_clicked(self.step_simulation)
        
        # Inicializamos la visualización
        self.initialize_plots()
        
        # Configuración de la animación
        self.anim = FuncAnimation(
            self.fig, self.update, interval=100,
            blit=False, save_count=100
        )
        
    def initialize_plots(self):
        """Inicializa las gráficas"""
        # Plotear las ciudades
        self.ax_cities.clear()
        self.ax_cities.scatter(
            self.aco.cities[:, 0], self.aco.cities[:, 1],
            s=100, c='blue', edgecolors='black'
        )
        
        # Numerar las ciudades
        for i, (x, y) in enumerate(self.aco.cities):
            self.ax_cities.annotate(str(i), (x, y), fontsize=12)
            
        self.ax_cities.set_xlim(-0.1, 1.1)
        self.ax_cities.set_ylim(-0.1, 1.1)
        self.ax_cities.set_title('Problema del Viajante (TSP)')
        
        # Inicializar matriz de feromonas
        self.ax_pheromones.clear()
        self.pheromone_img = self.ax_pheromones.imshow(
            self.aco.pheromones, cmap='viridis',
            vmin=0, vmax=2
        )
        self.fig.colorbar(self.pheromone_img, ax=self.ax_pheromones)
        self.ax_pheromones.set_title('Matriz de Feromonas')
        
        # Inicializar gráfico de convergencia
        self.ax_convergence.clear()
        self.line_best, = self.ax_convergence.plot([], [], 'r-', linewidth=2, label='Mejor')
        self.line_avg, = self.ax_convergence.plot([], [], 'b-', linewidth=1, label='Promedio')
        self.ax_convergence.set_xlim(0, self.aco.max_iter)
        self.ax_convergence.set_ylim(0, 10)  # Se ajustará automáticamente
        self.ax_convergence.legend()
        self.ax_convergence.set_title('Convergencia')
        
        # Mejor camino (inicialmente vacío)
        self.path_line, = self.ax_cities.plot([], [], 'r-', linewidth=2)
        
        # Texto para mostrar información
        self.info_text = self.ax_cities.text(
            0.02, 0.02, '', transform=self.ax_cities.transAxes,
            fontsize=10, bbox=dict(facecolor='white', alpha=0.7)
        )
        
        self.fig.tight_layout(rect=[0, 0.22, 1, 0.98])
        
    def update_params(self, val):
        """Actualiza los parámetros del algoritmo"""
        self.aco.alpha = self.slider_alpha.val
        self.aco.beta = self.slider_beta.val
        self.aco.rho = self.slider_rho.val
    
    def start_simulation(self, event):
        """Inicia la simulación"""
        self.running = True
    
    def pause_simulation(self, event):
        """Pausa la simulación"""
        self.running = False
    
    def reset_simulation(self, event):
        """Reinicia la simulación"""
        self.running = False
        self.iteration = 0
        
        # Reiniciar el algoritmo
        self.aco = ACO_TSP(
            n_cities=self.aco.n_cities,
            n_ants=self.aco.n_ants,
            alpha=self.slider_alpha.val,
            beta=self.slider_beta.val,
            rho=self.slider_rho.val,
            Q=self.aco.Q,
            max_iter=self.aco.max_iter
        )
        
        # Reiniciar visualización
        self.initialize_plots()
    
    def step_simulation(self, event):
        """Avanza un paso en la simulación"""
        if self.iteration < self.aco.max_iter:
            self.aco.run_iteration()
            self.iteration += 1
            self.update_visualization()
    
    def update_visualization(self):
        """Actualiza la visualización"""
        # Actualizar mejor camino
        if self.aco.best_path is not None:
            x = [self.aco.cities[i, 0] for i in self.aco.best_path]
            y = [self.aco.cities[i, 1] for i in self.aco.best_path]
            self.path_line.set_data(x, y)
        
        # Actualizar matriz de feromonas
        self.pheromone_img.set_array(self.aco.pheromones)
        
        # Ajustar límites para colorbar
        vmax = max(2, np.max(self.aco.pheromones))
        self.pheromone_img.set_clim(0, vmax)
        
        # Actualizar gráfico de convergencia
        x = list(range(len(self.aco.history['best_path_length'])))
        self.line_best.set_data(x, self.aco.history['best_path_length'])
        self.line_avg.set_data(x, self.aco.history['avg_path_length'])
        
        if len(x) > 0:
            ymax = max(
                max(self.aco.history['best_path_length']),
                max(self.aco.history['avg_path_length'])
            ) * 1.1
            ymin = min(
                min(self.aco.history['best_path_length']),
                min(self.aco.history['avg_path_length'])
            ) * 0.9
            self.ax_convergence.set_ylim(ymin, ymax)
        
        # Actualizar texto informativo
        info = f"Iteración: {self.iteration}/{self.aco.max_iter}\n"
        info += f"Mejor distancia: {self.aco.best_path_length:.4f}\n"
        info += f"Alpha (Importancia feromonas): {self.aco.alpha:.2f}\n"
        info += f"Beta (Peso distancias): {self.aco.beta:.2f}\n"
        info += f"Rho (Evaporación): {self.aco.rho:.2f}"
        self.info_text.set_text(info)
    
    def update(self, frame):
        """Función de actualización para la animación"""
        if self.running and self.iteration < self.aco.max_iter:
            self.aco.run_iteration()
            self.iteration += 1
            self.update_visualization()
            
            # Si llegamos al máximo de iteraciones, detenemos la simulación
            if self.iteration >= self.aco.max_iter:
                self.running = False
        
        return []
    
    def show(self):
        """Muestra la visualización"""
        plt.show()

def main():
    """Función principal"""
    # Creamos la instancia ACO
    aco = ACO_TSP(n_cities=20, n_ants=30, alpha=1.0, beta=5.0, rho=0.5, Q=100, max_iter=100)
    
    # Creamos y mostramos el visualizador
    visualizer = ACOVisualizer(aco)
    visualizer.show()

if __name__ == "__main__":
    main()