import math
import numpy as np
import matplotlib.pyplot as plt
from matplotlib import cm
from mpl_toolkits.mplot3d import Axes3D

# Set random seed for reproducibility
np.random.seed(42)

# 1. Generate synthetic data
def generate_data(n=100, noise=10):
    """Generate synthetic data with complex patterns."""
    # Independent variable
    X = np.random.uniform(-5, 5, n)
    
    # True parameters (ground truth)
    true_slope = 2.5
    true_intercept = 3.7
    
    # Add non-linear components to make it more complex
    y = (true_intercept + 
         true_slope * X + 
         1.5 * np.sin(X) + 
         0.5 * X**2 + 
         np.random.normal(0, noise, n))
    
    return X, y, true_slope, true_intercept

# 2. Cost function (Mean Squared Error)
def compute_cost(X, y, slope, intercept):
    """Compute a more complex cost function with regularization terms and oscillations."""
    n = len(X)
    predictions = intercept + slope * X
    
    # Basic MSE term
    mse_cost = (1/(2*n)) * np.sum((predictions - y)**2)
    
    # Add regularization with oscillating penalty (creates multiple local minima)
    regularization = (0.1 * (slope**2) + 0.05 * (intercept**2))
    oscillation_penalty = 0.2 * math.sin(5 * slope) + 0.2 * math.cos(5 * intercept)
    
    # Barrier terms to create steep walls in the cost landscape
    barrier_left = 0.5 * np.exp(-5 * (slope + 3.5)**2) if slope < -3.5 else 0
    barrier_right = 0.5 * np.exp(-5 * (slope - 3.5)**2) if slope > 3.5 else 0
    
    # Combine all terms
    total_cost = mse_cost + regularization + oscillation_penalty + barrier_left + barrier_right
    
    return total_cost

# 3. Gradient descent function
def gradient_descent(X, y, learning_rate=0.01, iterations=1000):
    """Perform gradient descent to find optimal slope and intercept."""
    n = len(X)
    
    # Initialize parameters with random values
    slope = np.random.randn()
    intercept = np.random.randn()
    
    # Lists to store parameters and costs
    slope_history = [slope]
    intercept_history = [intercept]
    cost_history = [compute_cost(X, y, slope, intercept)]
    
    # Perform gradient descent
    for i in range(iterations):
        # Predictions with current parameters
        predictions = intercept + slope * X
        
        # Compute gradients
        gradient_slope = -(1/n) * np.sum(X * (y - predictions))
        gradient_intercept = -(1/n) * np.sum(y - predictions)
        
        # Update parameters
        slope = slope - learning_rate * gradient_slope
        intercept = intercept - learning_rate * gradient_intercept
        
        # Store parameters and cost
        slope_history.append(slope)
        intercept_history.append(intercept)
        cost_history.append(compute_cost(X, y, slope, intercept))
    
    return slope, intercept, slope_history, intercept_history, cost_history

# 4. Visualize the cost function in 3D
def visualize_cost_function(X, y, slope_history, intercept_history, cost_history):
    """Create a 3D surface plot of the cost function."""
    # Create meshgrid for slope and intercept
    slope_range = np.linspace(min(slope_history)-1, max(slope_history)+1, 200)  # 200 en lugar de 100
    intercept_range = np.linspace(min(intercept_history)-1, max(intercept_history)+1, 200)
    slope_grid, intercept_grid = np.meshgrid(slope_range, intercept_range)
    custom_cmap = plt.cm.coolwarm
    
    # Compute cost for each combination of slope and intercept
    cost_grid = np.zeros(slope_grid.shape)
    for i in range(len(intercept_range)):
        for j in range(len(slope_range)):
            cost_grid[i, j] = compute_cost(X, y, slope_grid[i, j], intercept_grid[i, j])
    
    # Create 3D plot
    fig = plt.figure(figsize=(12, 10))
    ax = fig.add_subplot(111, projection='3d')
    surface = ax.plot_surface(
    slope_grid, intercept_grid, cost_grid,
    cmap=custom_cmap,
    linewidth=0.1,
    rstride=2, cstride=2,  # Esto crea más líneas en la superficie
    alpha=0.9,
    antialiased=True,
    shade=True
)
    
    # Plot the path of gradient descent
    ax.plot(slope_history, intercept_history, cost_history, 'r-', 
            linewidth=2, label='Gradient Descent Path')
    ax.plot(slope_history, intercept_history, cost_history, 'ko', markersize=4)
    
    # Highlight start and end points
    ax.plot([slope_history[0]], [intercept_history[0]], [cost_history[0]], 
            'go', markersize=10, label='Start')
    ax.plot([slope_history[-1]], [intercept_history[-1]], [cost_history[-1]], 
            'ro', markersize=10, label='End')
    
    # Set labels and title
    ax.set_xlabel('Slope (m)')
    ax.set_ylabel('Intercept (b)')
    ax.set_zlabel('Cost (MSE)')
    ax.set_title('Cost Function Surface and Gradient Descent Path', fontsize=15)
    fig.colorbar(surface, ax=ax, shrink=0.5, aspect=10)
    ax.legend()
    ax.view_init(elev=25, azim=-50)
    
    plt.tight_layout()
    plt.show()

# 5. Visualize contour plot
def visualize_contour(X, y, slope_history, intercept_history):
    """Create a contour plot of the cost function with gradient descent path."""
    # Create meshgrid for slope and intercept
    slope_range = np.linspace(min(slope_history)-1, max(slope_history)+1, 100)
    intercept_range = np.linspace(min(intercept_history)-1, max(intercept_history)+1, 100)
    slope_grid, intercept_grid = np.meshgrid(slope_range, intercept_range)
    
    # Compute cost for each combination of slope and intercept
    cost_grid = np.zeros(slope_grid.shape)
    for i in range(len(intercept_range)):
        for j in range(len(slope_range)):
            cost_grid[i, j] = compute_cost(X, y, slope_grid[i, j], intercept_grid[i, j])
    
    # Create contour plot
    plt.figure(figsize=(10, 8))
    contour = plt.contour(slope_grid, intercept_grid, cost_grid, 
                          levels=50, cmap='viridis')
    plt.colorbar(contour)
    
    # Plot the path of gradient descent
    plt.plot(slope_history, intercept_history, 'r-', linewidth=2, label='Gradient Descent Path')
    plt.plot(slope_history, intercept_history, 'ko', markersize=4)
    
    # Highlight start and end points
    plt.plot(slope_history[0], intercept_history[0], 'go', markersize=10, label='Start')
    plt.plot(slope_history[-1], intercept_history[-1], 'ro', markersize=10, label='End')
    
    # Set labels and title
    plt.xlabel('Slope (m)')
    plt.ylabel('Intercept (b)')
    plt.title('Contour Plot of Cost Function with Gradient Descent Path', fontsize=15)
    plt.legend()
    plt.grid(True)
    
    plt.tight_layout()
    plt.show()

# 6. Visualize data and regression line
def visualize_regression(X, y, slope, intercept, true_slope, true_intercept):
    """Plot the data and the regression line."""
    plt.figure(figsize=(10, 6))
    
    # Plot data points
    plt.scatter(X, y, color='blue', alpha=0.5, label='Data Points')
    
    # Sort X for line plotting
    X_sorted = np.sort(X)
    
    # Plot estimated regression line
    plt.plot(X_sorted, intercept + slope * X_sorted, 'r-', 
             linewidth=2, label=f'Estimated: y = {intercept:.2f} + {slope:.2f}x')
    
    # Plot true regression line
    plt.plot(X_sorted, true_intercept + true_slope * X_sorted, 'g--', 
             linewidth=2, label=f'True: y = {true_intercept:.2f} + {true_slope:.2f}x')
    
    plt.xlabel('X')
    plt.ylabel('y')
    plt.title('Linear Regression with Gradient Descent', fontsize=15)
    plt.legend()
    plt.grid(True)
    
    plt.tight_layout()
    plt.show()

# 7. Plot cost over iterations
def plot_cost_history(cost_history):
    """Plot the cost over iterations."""
    plt.figure(figsize=(10, 6))
    plt.plot(range(len(cost_history)), cost_history, 'b-')
    plt.xlabel('Iteration')
    plt.ylabel('Cost (MSE)')
    plt.title('Cost vs Iteration', fontsize=15)
    plt.grid(True)
    
    plt.tight_layout()
    plt.show()

# Main execution
if __name__ == "__main__":
    # Generate data
    X, y, true_slope, true_intercept = generate_data(n=100, noise=5)
    
    # Set hyperparameters
    learning_rate = 0.01
    iterations = 200
    
    # Perform gradient descent
    final_slope, final_intercept, slope_history, intercept_history, cost_history = gradient_descent(
        X, y, learning_rate=learning_rate, iterations=iterations
    )
    
    print(f"True parameters: slope = {true_slope}, intercept = {true_intercept}")
    print(f"Estimated parameters: slope = {final_slope:.4f}, intercept = {final_intercept:.4f}")
    print(f"Final cost (MSE): {cost_history[-1]:.4f}")
    
    # Visualize results
    visualize_regression(X, y, final_slope, final_intercept, true_slope, true_intercept)
    plot_cost_history(cost_history)
    visualize_contour(X, y, slope_history, intercept_history)
    visualize_cost_function(X, y, slope_history, intercept_history, cost_history)