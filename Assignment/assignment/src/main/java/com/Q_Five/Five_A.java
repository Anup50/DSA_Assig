package com.Q_Five;
import java.util.Arrays;
import java.util.Random;



class AntColony {
    //variables to store the distance matrix, number of ants, pheromone matrix, 
    //probabilities, number of cities, best tour and its length, and parameters 

    private int[][] distanceMatrix;
    private int numAnts;
    private double[][] pheromoneMatrix;
    private double[][] probabilities;
    private int numCities;
    private int[] bestTour;
    private int bestTourLength;
    private double evaporationRate;
    private double alpha;
    private double beta;

    // Constructor to initialize the AntColony object with parameters
    public AntColony(int[][] distanceMatrix, int numAnts, double evaporationRate, double alpha, double beta) {
        this.distanceMatrix = distanceMatrix;
        this.numAnts = numAnts; // number of ants
        this.evaporationRate = evaporationRate; // Set the evaporation rate
        this.alpha = alpha; // Set the alpha parameter
        this.beta = beta; // Set the beta parameter
        this.numCities = distanceMatrix.length; // Determine the number of cities from the distance matrix
        this.pheromoneMatrix = new double[numCities][numCities]; // Initialize the pheromone matrix
        this.probabilities = new double[numCities][numCities]; // Initialize the probabilities matrix
        initializePheromones(); // Initialize the pheromone matrix with initial values
    }
    
    // Method to initialize the pheromone matrix with initial values
    private void initializePheromones() {
        double initialPheromone = 1.0 / numCities; // Set initial pheromone level for each edge
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                if (i != j) {
                    pheromoneMatrix[i][j] = initialPheromone; // Assign initial pheromone level to each edge
                }
            }
        }
    }

    // Method to solve the TSP using ant colony optimization with the specified number of iterations
    public void solve(int maxIterations) {
        bestTourLength = Integer.MAX_VALUE; // Set initial best tour length to a very large value
        bestTour = new int[numCities]; // Initialize best tour array
        Random random = new Random(); // Create a random number generator

        for (int iteration = 0; iteration < maxIterations; iteration++) {
            for (int ant = 0; ant < numAnts; ant++) {
                boolean[] visited = new boolean[numCities];
                int[] tour = new int[numCities];
                int currentCity = random.nextInt(numCities); // Randomly choose a starting city
                tour[0] = currentCity; // Add the starting city to the tour
                visited[currentCity] = true; // Mark the starting city as visited

                for (int i = 1; i < numCities; i++) {
                    calculateProbabilities(currentCity, visited); // Calculate probabilities for selecting the next city
                    int nextCity = selectNextCity(currentCity); // Select the next city based on probabilities
                    tour[i] = nextCity; // Add the selected city to the tour
                    visited[nextCity] = true; // Mark the selected city as visited
                    currentCity = nextCity; // Update the current city
                }

                int tourLength = calculateTourLength(tour); // Calculate the length of the tour
                if (tourLength < bestTourLength) {
                    bestTourLength = tourLength; // Update the best tour length if a shorter tour is found
                    bestTour = tour.clone(); // Update the best tour
                }
            }

            updatePheromones(); // Update the pheromone levels
        }
    }

    // Method to calculate the probabilities of selecting each unvisited city as the next city to visit
    private void calculateProbabilities(int city, boolean[] visited) {
        double total = 0.0;
        for (int i = 0; i < numCities; i++) {
            if (!visited[i]) {
                probabilities[city][i] = Math.pow(pheromoneMatrix[city][i], alpha) * Math.pow(1.0 / distanceMatrix[city][i], beta);
                total += probabilities[city][i];
            } else {
                probabilities[city][i] = 0.0;
            }
        }

        for (int i = 0; i < numCities; i++) {
            probabilities[city][i] /= total;
        }
    }

    // Method to select the next city based on the calculated probabilities
    private int selectNextCity(int city) {
        double[] probabilities = this.probabilities[city];
        double r = Math.random();
        double cumulativeProbability = 0.0;
        for (int i = 0; i < numCities; i++) {
            cumulativeProbability += probabilities[i];
            if (r <= cumulativeProbability) {
                return i;
            }
        }
        return -1;
    }

    // Method to update the pheromone levels
    private void updatePheromones() {
        // Evaporation: Reduce pheromone levels on all edges
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                pheromoneMatrix[i][j] *= (1.0 - evaporationRate);
            }
        }
        // Add new pheromones: Deposit pheromones on edges of the best tour found by all ants
        for (int i = 0; i < numCities - 1; i++) {
            int city1 = bestTour[i];
            int city2 = bestTour[i + 1];
            pheromoneMatrix[city1][city2] += (1.0 / bestTourLength);
            pheromoneMatrix[city2][city1] += (1.0 / bestTourLength);
        }
    }

    // Method to calculate the total length of a tour
    private int calculateTourLength(int[] tour) {
        int length = 0;
        for (int i = 0; i < tour.length - 1; i++) {
            length += distanceMatrix[tour[i]][tour[i + 1]]; // Add the distance between consecutive cities
        }
        length += distanceMatrix[tour[tour.length - 1]][tour[0]]; // Add the distance from the last city back to the starting city
        return length;
    }

    // Getter method to retrieve the length of the best tour found
    public int getBestTourLength() {
        return bestTourLength;
    }

    // Getter method to retrieve the sequence of cities in the best tour found
    public int[] getBestTour() {
        return bestTour;
    }
}

// Main class for executing the Ant Colony Optimization algorithm for the TSP
public class Five_A{
   

    public static void main(String[] args) {
        int[][] distanceMatrix = {
                {0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {20, 25, 30, 0}
        };
        int numAnts = 5;
        double evaporationRate = 0.5;
        double alpha = 1.0;
        double beta = 2.0;

        AntColony colony = new AntColony(distanceMatrix, numAnts, evaporationRate, alpha, beta);
        colony.solve(1000); // Solve TSP with 1000 iterations

        int[] bestTour = colony.getBestTour();
        int bestTourLength = colony.getBestTourLength();

        System.out.println("Best tour: " + Arrays.toString(bestTour));
        System.out.println("Best tour length: " + bestTourLength);
    }
}