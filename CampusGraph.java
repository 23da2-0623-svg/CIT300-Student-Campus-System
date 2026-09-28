import java.util.*;

public class CampusGraph {
    private Map<String, List<String>> adjacencyList;

    public CampusGraph() {
        this.adjacencyList = new HashMap<>();
    }

    // Add a new Campus Location (Vertex)
    public void addLocation(String location) {
        adjacencyList.putIfAbsent(location, new ArrayList<>());
    }

    // Add a Path between two locations (Undirected Edge)
    public void addPath(String source, String destination) {
        addLocation(source);
        addLocation(destination);
        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source);
    }

    // Breadth-First Search (BFS) Traversal
    public void bfsTraversal(String startLocation) {
        if (!adjacencyList.containsKey(startLocation)) {
            System.out.println("Error: Start location '" + startLocation + "' not found on campus map.");
            return;
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(startLocation);
        queue.add(startLocation);

        System.out.println("\n--- Campus Traversal (BFS) starting from: " + startLocation + " ---");
        while (!queue.isEmpty()) {
            String current = queue.poll();
            System.out.print(current + " -> ");

            for (String neighbor : adjacencyList.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println("END");
    }

    // Depth-First Search (DFS) Traversal
    public void dfsTraversal(String startLocation) {
        if (!adjacencyList.containsKey(startLocation)) {
            System.out.println("Error: Start location '" + startLocation + "' not found on campus map.");
            return;
        }

        Set<String> visited = new HashSet<>();
        System.out.println("\n--- Campus Traversal (DFS) starting from: " + startLocation + " ---");
        dfsHelper(startLocation, visited);
        System.out.println("END");
    }

    private void dfsHelper(String current, Set<String> visited) {
        visited.add(current);
        System.out.print(current + " -> ");

        for (String neighbor : adjacencyList.get(current)) {
            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited);
            }
        }
    }

    // Display all available locations and paths
    public void displayNetwork() {
        if (adjacencyList.isEmpty()) {
            System.out.println("Campus network is currently empty.");
            return;
        }
        System.out.println("\n--- Campus Route Network ---");
        for (String location : adjacencyList.keySet()) {
            System.out.println(location + " connected to: " + adjacencyList.get(location));
        }
    }
}