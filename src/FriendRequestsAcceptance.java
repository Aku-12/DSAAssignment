import java.util.*;

public class FriendRequestsAcceptance {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Reading number of houses
        System.out.print("Number of houses: ");
        int n = scanner.nextInt();

        // Reading restrictions
        System.out.print("Number of restrictions: ");
        int numRestrictions = scanner.nextInt();
        List<int[]> restrictions = new ArrayList<>();
        System.out.println("Enter restrictions as pairs (0-indexed):");
        for (int i = 0; i < numRestrictions; i++) {
            int house1 = scanner.nextInt();
            int house2 = scanner.nextInt();
            restrictions.add(new int[]{house1, house2});
        }

        // Reading friend requests
        System.out.print("Number of friend requests: ");
        int numRequests = scanner.nextInt();
        List<int[]> requests = new ArrayList<>();
        System.out.println("Enter requests as pairs (0-indexed):");
        for (int i = 0; i < numRequests; i++) {
            int house1 = scanner.nextInt();
            int house2 = scanner.nextInt();
            requests.add(new int[]{house1, house2});
        }

        // Solution logic
        List<String> results = processRequests(n, restrictions, requests);

        // Output results
        System.out.println("Output:");
        for (String result : results) {
            System.out.println(result);
        }

        scanner.close();
    }

    // Method to process the friend requests
    private static List<String> processRequests(int n, List<int[]> restrictions, List<int[]> requests) {
        // Build adjacency list for restrictions
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] restriction : restrictions) {
            int house1 = restriction[0];
            int house2 = restriction[1];
            graph.get(house1).add(house2);
            graph.get(house2).add(house1);
        }

        // Processing each request
        List<String> results = new ArrayList<>();
        for (int[] request : requests) {
            int house1 = request[0];
            int house2 = request[1];

            // Check if adding direct edge violates restrictions
            if (canBeFriends(graph, n, house1, house2)) {
                results.add("Approved");
            } else {
                results.add("Denied");
            }
        }
        return results;
    }

    // Helper method to check if adding an edge between house1 and house2 violates restrictions
    private static boolean canBeFriends(List<List<Integer>> graph, int n, int house1, int house2) {
        // Use BFS to check if there's a path from house1 to house2 through restricted houses
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];

        queue.add(house1);
        visited[house1] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int neighbor : graph.get(current)) {
                if (!visited[neighbor]) {
                    if (neighbor == house2) {
                        return false; // Found a path, so they can't be friends
                    }
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        return true; // No path found, so they can be friends
    }
}
