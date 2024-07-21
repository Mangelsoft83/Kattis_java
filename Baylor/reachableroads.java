import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class reachableroads {
    static boolean[] visited;
    static List<List<Integer>> adjList;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            int m = scanner.nextInt();
            int r = scanner.nextInt();


            adjList= new ArrayList<>(m);
            for (int j = 0; j < m; j++) {
                adjList.add(new ArrayList<>());
            }

            for (int j = 0; j < r; j++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                adjList.get(a).add(b);
                adjList.get(b).add(a);
            }

            visited = new boolean[m];
            int newRoads = 0;
            for (int j = 0; j < m; j++) {
                if(!visited[j]){
                    newRoads++;
                    dfs(j);
                }
            }

            System.out.println(newRoads-1);

        }


    }

    private static void dfs(int j) {
        visited[j] = true;
        for (int i:adjList.get(j)) {
            if(!visited[i]) dfs(i);
        }

    }
}
