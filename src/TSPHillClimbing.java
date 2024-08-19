import java.util.*;

public class TSPHillClimbing {

    static class City {
        int x, y;

        public City(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // Euclidean distance between two cities
        public double distanceTo(City other) {
            int dx = this.x - other.x;
            int dy = this.y - other.y;
            return Math.sqrt(dx * dx + dy * dy);
        }
    }

    // Calculating the total distance of a tour
    static double tourDistance(List<City> tour) {
        double totalDistance = 0.0;
        for (int i = 0; i < tour.size() - 1; i++) {
            totalDistance += tour.get(i).distanceTo(tour.get(i + 1));
        }
        // Returning to the starting city
        totalDistance += tour.get(tour.size() - 1).distanceTo(tour.get(0));
        return totalDistance;
    }

    // Hill climbing algorithm to find a near-optimal solution
    static List<City> hillClimbingTSP(List<City> cities) {
        List<City> currentTour = new ArrayList<>(cities);
        double currentDistance = tourDistance(currentTour);

        // Defining a neighborhood: swap each pair of cities
        boolean improved;
        do {
            improved = false;
            for (int i = 0; i < currentTour.size() - 1; i++) {
                for (int j = i + 1; j < currentTour.size(); j++) {
                    // Swap cities i and j
                    Collections.swap(currentTour, i, j);
                    double newDistance = tourDistance(currentTour);

                    // If the new tour is shorter, accept it
                    if (newDistance < currentDistance) {
                        currentDistance = newDistance;
                        improved = true;
                    } else {
                        // Swapping back if the new tour is not better
                        Collections.swap(currentTour, i, j);
                    }
                }
            }
        } while (improved);

        return currentTour;
    }

    public static void main(String[] args) {
        List<City> cities = new ArrayList<>();
        cities.add(new City(0, 0));
        cities.add(new City(1, 2));
        cities.add(new City(3, 1));
        cities.add(new City(4, 3));

        List<City> solution = hillClimbingTSP(cities);

        System.out.println("Best tour found:");
        for (City city : solution) {
            System.out.println(city.x + ", " + city.y);
        }
        System.out.println("Total distance: " + tourDistance(solution));
    }
}
