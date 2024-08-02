import java.util.*;

class CityPlanning {
    public static List<int[]> modifyRoadTimes(int n, int[][] roads, int source, int destination, int targetTime) {
        // Step 1: Represent the road network
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int weight = road[2];
            graph[u].add(new int[]{v, weight});
            graph[v].add(new int[]{u, weight});
        }

        // Step 2: Perform BFS to find shortest paths
        int[] shortestDistances = new int[n];
        Arrays.fill(shortestDistances, Integer.MAX_VALUE);
        shortestDistances[source] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int[] neighbor : graph[u]) {
                int v = neighbor[0];
                int weight = neighbor[1];

                if (weight != -1 && shortestDistances[u] + weight < shortestDistances[v]) {
                    shortestDistances[v] = shortestDistances[u] + weight;
                    queue.offer(v);
                }
            }
        }

        // Step 3: Adjust the road construction times to achieve the target time
        List<int[]> result = new ArrayList<>();

        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int originalWeight = road[2];
            int newWeight = originalWeight;

            if (originalWeight == -1) {
                int currentDistance = shortestDistances[u];
                int requiredDistance = shortestDistances[v];
                int diff = targetTime - (requiredDistance - currentDistance);

                if (diff > 0) {
                    newWeight = diff;
                } else {
                    newWeight = 1; // Adjust to any positive integer within the range
                }
            }

            result.add(new int[]{u, v, newWeight});
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of nodes:");
        int n = scanner.nextInt();

        System.out.println("Enter the number of roads:");
        int roadCount = scanner.nextInt();
        int[][] roads = new int[roadCount][3];

        System.out.println("Enter the roads (u v weight) (use -1 for unknown weights):");
        for (int i = 0; i < roadCount; i++) {
            roads[i][0] = scanner.nextInt();
            roads[i][1] = scanner.nextInt();
            roads[i][2] = scanner.nextInt();
        }

        System.out.println("Enter the source node:");
        int source = scanner.nextInt();

        System.out.println("Enter the destination node:");
        int destination = scanner.nextInt();

        System.out.println("Enter the target time:");
        int targetTime = scanner.nextInt();

        List<int[]> modifiedRoads = modifyRoadTimes(n, roads, source, destination, targetTime);

        System.out.println("Modified roads:");
        for (int[] road : modifiedRoads) {
            System.out.println(Arrays.toString(road));
        }

        scanner.close();
    }
}
