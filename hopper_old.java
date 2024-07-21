import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class hopper_old {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        //int[] arr = new int[n];
        int D = scanner.nextInt();
        int M = scanner.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        HashMap<Integer,Integer> history = new HashMap<>();

        int[] solutionArray = new int[n];
        Arrays.fill(solutionArray, 1);

        for (int i = 0; i < solutionArray.length; i++) {
            boolean[] usedArray = new boolean[arr.length];
            if(i>0)solutionArray[i] = solutionArray[i-1];
            solutionArray[i] = solve(arr, M, D, i,usedArray,1,solutionArray,i,history);
        }

        int max = 0;
        for(int value:solutionArray)
            max = Math.max(max,value);

        System.out.println(max);

    }

    private static int solve(int[] arr, int m, int d, int actualPosition, boolean[] usedArray, int depth, int[] solutionArray, int pos, HashMap<Integer, Integer> history) {

        usedArray[actualPosition] = true;
        int hash = Arrays.hashCode(usedArray);
        Integer xx = history.get(Arrays.hashCode(usedArray));
        if (xx != null){
            usedArray[actualPosition] = false;
            return xx;
        }
        /*
        //Break if better solution cant be better tan actual result
        int potential = 0;
        for(boolean used:usedArray) if(!used) potential++;

        if(potential + depth <= solutionArray[pos]){
            usedArray[actualPosition] = false;
            history.put(hash,depth);
            return solutionArray[pos];
        }
        */

        int startPos = Math.max(actualPosition - d,0);
        int endPos = Math.min(actualPosition + d+1,arr.length);
        for (int newPosition = startPos; newPosition < endPos; newPosition++) {

                if (actualPosition == newPosition) continue;

            if (possible(actualPosition,newPosition,arr,m,d,usedArray)){
                depth += 1;
                if(depth > solutionArray[pos]) solutionArray[pos] = depth;
                if(arr.length == depth) return depth;
                solve(arr,m,d,newPosition, usedArray, depth, solutionArray,pos, history);
                history.put(hash,depth);
                depth -= 1;
            }

        }
        usedArray[actualPosition] = false;
        return solutionArray[pos];
    }


    private static boolean possible(int actualPosition, int newPosition, int[] arr, int m, int d, boolean[] checked) {
        if (checked[newPosition]) return false;
        else if (Math.abs(newPosition-actualPosition) > d) return false;
        else return Math.abs((arr[newPosition] - arr[actualPosition])) <= m;
    }


}
