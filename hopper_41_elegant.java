import java.util.Arrays;
import java.util.HashMap;

import java.util.Scanner;

public class hopper_41_elegant {

    static int maxLength = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); //Size array
        int d = scanner.nextInt(); //Max step size
        int m = scanner.nextInt(); //Max difference per step
        maxLength = n;

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.println(solve(arr,m,d));

    }


    private static int solve(int[] arr, int m, int d) {
        //if (arr.length == 0) return 0;
        int n = arr.length; int longestPath;


        boolean[] usedArray = new boolean[n];

        HashMap<Integer, int[]> memory = new HashMap<>();
        int[] endpoint = solve(arr,m,d,n,memory,0, usedArray, 0);
        int endIndex = endpoint[1];
        int[] longestP = solve(arr,m,d,n,memory,endIndex, usedArray, 0);
        longestPath = longestP[0];
        /*
        for (int i = 0; i < n; i++) { //start from every position
            int[] result = solve(arr,m,d,n,memory,i, usedArray, 0);
            int longest = result[0];
            longestPath = Math.max(longest,longestPath);

        }*/

        return longestPath;
    }

    private static int[] solve(int[] arr, int m, int d, int n, HashMap<Integer, int[]> memory, int i, boolean[] used, int penalty){
        used[i] = true;

        Integer hash = Arrays.hashCode(used);
        if (memory.get(hash) != null) {
            used[i] = false;
            return memory.get(hash);
        }

        int act = arr[i];
        int max = 0;
        for (int j = -d; j <= d; j++) {

            int x = i + j;
            if (j==0 || x < 0 || x >= n) continue;
            boolean xx = !used[x];
            boolean condM = Math.abs(act-arr[x]) <= m;
            if (condM && xx) {
                int[] result = solve(arr, m, d, n, memory, x,used, penalty +j);
                int longest = result[0];
                //int longest = solve(arr, m, d, n, memory, x,used, penalty +j);

                max = Math.max(longest, max);
                result[0] = max;
                memory.put(Arrays.hashCode(used), result);
            }
        }

        used[i] = false;

        return new int[]{max+1 , i};

    }

}

