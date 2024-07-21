import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class hopper_41 {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); //Size array
        int d = scanner.nextInt(); //Max step size
        int m = scanner.nextInt(); //Max difference per step

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        HashMap<Integer, Integer> history = new HashMap<>();

        boolean[][] policy = makePolicy(m, d, n, arr);
        boolean[] visited = new boolean[n];

        int max = 0;
        for (int i = 0; i < n; i++) {
            int result = solve(n, policy, visited, history, 1, i);
            max = Math.max(result, max);
        }

        System.out.println(max);

    }

    private static int solve(int n, boolean[][] policy, boolean[] visited, HashMap<Integer, Integer> history, int depth, int act) {
        //Admin
        visited[act] = true;

        //Early return statements
        Integer hash = Arrays.hashCode(visited);
        if (history.get(hash) != null) {
            visited[act] = false;
            return history.get(hash);
        }
        if (depth == n) {
            return n;
        }

        int maxLength = depth;

        for (int i = 0; i < n; i++) {
            boolean extend = policy[act][i] && !visited[i];
            if (extend) {

                //put in history;
                int result = solve(n, policy, visited, history, depth + 1, i);

                maxLength = Math.max(maxLength, result);
                history.put(Arrays.hashCode(visited), maxLength);
            }
        }

        visited[act] = false;
        return maxLength;
    }


    private static boolean[][] makePolicy(int m, int d, int n, int[] array) {
        boolean[][] policy = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                if (i == j) {
                    policy[i][j] = false;
                } else {
                    boolean a = Math.abs(i - j) <= d;
                    boolean b = Math.abs(array[i] - array[j]) <= m;

                    policy[i][j] = a && b;
                }
        }

        return policy;
    }
}

