import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class islandhopping {

    public static void main(String[] args) throws IOException {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(scanner.readLine());

        for (int n = 0; n < N; n++) {
            int M = Integer.parseInt(scanner.readLine());

            List<double[]> isLands = new ArrayList<>();
            for (int m = 0; m < M; m++) {
                String[] s = scanner.readLine().split(" ");
                double x = Double.parseDouble(s[0]);double y = Double.parseDouble(s[1]);
                isLands.add(new double[]{x,y});
            }

            //double totalDistance = MST_kat(isLands, M);
            List<KruskalsMST.Edge> graphEdges = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                for (int j = i+1; j < M; j++) {
                    double[] island1 = isLands.get(i);
                    double[] island2 = isLands.get(j);
                    double dist = Math.sqrt(Math.pow(island1[0]-island2[0],2) + Math.pow(island1[1]-island2[1],2));
                    graphEdges.add(new KruskalsMST.Edge(i,j,dist));

                }

            }

            // Sort the edges in non-decreasing order
            // (increasing with repetition allowed)
            graphEdges.sort(Comparator.comparingDouble(o -> o.weight));

            //KruskalsMST MST = new KruskalsMST();
            double totalDistance = KruskalsMST.getCost(isLands.size(), graphEdges);

            System.out.println(totalDistance);

        }
    }

    private static double MST_kat(List<double[]> isLands, int M) {
        Bridge bridges = new Bridge(isLands);

        double totalDistance = 0;
        int totalConnected = 1;
        boolean[] visited = new boolean[M];

        double[] min = new double[M];

        visited[0] = true;

        for (int i = 0; i < M; i++) {
            min[i] = bridges.getDist(0,i);
        }

        while (totalConnected < M){
            int minInd = Integer.MAX_VALUE;
            double minValue = Double.MAX_VALUE;
            for (int i = 0; i < min.length; i++) {
                if(!visited[i] && min[i] < minValue){
                    minInd = i;
                    minValue = min[i];
                }
            }

            totalDistance += Math.sqrt(minValue);
            visited[minInd] = true;
            totalConnected++;

            for (int i = 0; i < M; i++) {
                boolean mirror = i > minInd;
                int x = mirror ? minInd : i;
                int y = mirror ? i : minInd;

                if (min[i] == 0.0) continue;
                if (min[i] > bridges.getDist(x,y)) {
                    min[i] = bridges.getDist(x,y);
                }
            }
        }
        return totalDistance;
    }
}

class Bridge{
    final private Double[][] dist;
    private final List<double[]> isLands;

    public Bridge(List<double[]> isLands) {
        this.isLands = isLands;
        dist = new Double[isLands.size()][isLands.size()];
    }

    public double getDist(int i, int j){
        double[] island1 = isLands.get(i);
        double[] island2 = isLands.get(j);

        if(dist[i][j] == null){
            dist[i][j] = Math.pow(island1[0]-island2[0],2) + Math.pow(island1[1]-island2[1],2);
            dist[j][i] = dist[i][j];
        }

        return dist[i][j];
    }
}

class KruskalsMST
{

    // Defines edge structure
    static class Edge {
        int src, dest;
        Double weight;

        public Edge(int src, int dest, double weight)
        {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "src=" + src +
                    ", dest=" + dest +
                    ", weight=" + weight +
                    '}';
        }
    }

    // Defines subset element structure
    static class Subset {
        int parent, rank;

        public Subset(int parent, int rank)
        {
            this.parent = parent;
            this.rank = rank;
        }

        @Override
        public String toString() {
            return "Subset{" +
                    "parent=" + parent +
                    ", rank=" + rank +
                    '}';
        }
    }

    // Starting point of program execution
    public static void main(String[] args)
    {
        int V = 4;
        List<Edge> graphEdges = new ArrayList<>(
                List.of(new Edge(0, 1, 10), new Edge(0, 2, 6),
                        new Edge(0, 3, 5), new Edge(1, 3, 15),
                        new Edge(2, 3, 4)));

        // Sort the edges in non-decreasing order
        // (increasing with repetition allowed)
        graphEdges.sort(Comparator.comparingDouble(o -> o.weight));

        kruskals(V, graphEdges);
    }

    public static double getCost(int V, List<Edge> E){
        return kruskals(V,E);
    }

    // Function to find the MST
    private static double kruskals(int V, List<Edge> edges)
    {
        int j = 0;
        int noOfEdges = 0;

        // Allocate memory for creating V subsets
        Subset[] subsets = new Subset[V];

        // Allocate memory for results
        Edge[] results = new Edge[V];

        // Create V subsets with single elements
        for (int i = 0; i < V; i++) {
            subsets[i] = new Subset(i, 0);
        }

        // Number of edges to be taken is equal to V-1
        while (noOfEdges < V - 1) {

            // Pick the smallest edge. And increment
            // the index for next iteration
            Edge nextEdge = edges.get(j);
            int x = findRoot(subsets, nextEdge.src);
            int y = findRoot(subsets, nextEdge.dest);

            // If including this edge doesn't cause cycle,
            // include it in result and increment the index
            // of result for next edge
            if (x != y) {
                results[noOfEdges] = nextEdge;
                union(subsets, x, y);
                noOfEdges++;
            }

            j++;
        }

        // Print the contents of result[] to display the
        // built MST

        //System.out.println(
                //"Following are the edges of the constructed MST:");
        double minCost = 0;
        for (int i = 0; i < noOfEdges; i++) {

            minCost += results[i].weight;
        }
        //System.out.println("Total cost of MST: " + minCost);


        return minCost;
    }

    // Function to unite two disjoint sets
    private static void union(Subset[] subsets, int x,
                              int y)
    {
        int rootX = findRoot(subsets, x);
        int rootY = findRoot(subsets, y);

        if (subsets[rootY].rank < subsets[rootX].rank) {
            subsets[rootY].parent = rootX;
        }
        else if (subsets[rootX].rank
                < subsets[rootY].rank) {
            subsets[rootX].parent = rootY;
        }
        else {
            subsets[rootY].parent = rootX;
            subsets[rootX].rank++;
        }
    }

    // Function to find parent of a set
    private static int findRoot(Subset[] subsets, int i)
    {
        if (subsets[i].parent == i)
            return subsets[i].parent;

        subsets[i].parent
                = findRoot(subsets, subsets[i].parent);
        return subsets[i].parent;
    }
}
// This code is contributed by Salvino D'sa
