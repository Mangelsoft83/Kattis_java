import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class balanceddiet {
    static List<Integer> nums;
    static List<Integer> extended;

    static List<Integer> set;


    static HashSet<List<Integer>> uniek;


    static int half,sum;
    static int best = Integer.MAX_VALUE;
    static List<Integer> bestPath;

    public static void main(String[] args) throws IOException {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            //Read line
            String[] parse = scan.readLine().split(" ");
            int N = Integer.parseInt(parse[0]);
            if(N==0)break;

            // make sorted num list
            nums = new ArrayList<>();
            for (int i = 1; i <= N; i++) {
                nums.add(Integer.parseInt(parse[i]));
            }
            
            //Knapsack problem https://www.youtube.com/watch?v=nLmhmB6NzcM
            int sum = 0;
            for(int n:nums) sum+=n;
            int sizeSack = sum / 2;

            //Not needed for this problem to split profits and weights
            List<Integer> weights = new ArrayList<>(nums);
            List<Integer> profits = new ArrayList<>(nums);

            int maxProfit = knapsack(sizeSack, weights, profits);
            
            
            System.out.println(sum - maxProfit + " " + maxProfit);

        }
    }

    private static int knapsack(int sizeSack, List<Integer> weights, List<Integer> profits) {

        int[][] profitMatrix = new int[weights.size()+1][sizeSack + 1];

        for (int i = 0; i < profitMatrix.length; i++) {
            for (int j = 0; j < profitMatrix[0].length; j++) {

                if(i==0 || j==0){
                    profitMatrix[i][j] = 0;
                }else {
                    int volume = weights.get(i-1);
                    int profit = profits.get(i-1);
                    int profitPrefRow =  profitMatrix[i-1][j];
                    int profitPrefRowOffset = 0;
                    if(j - volume >= 0)
                        profitPrefRowOffset =  profitMatrix[i-1][j-volume] + profit;


                    profitMatrix[i][j] = Math.max(profitPrefRow,profitPrefRowOffset);
                }
            }
        }

        return profitMatrix[profitMatrix.length-1][profitMatrix[0].length-1];
    }

    private static void solveBalance(int sum, int index) {

        if(best == 0)return;
        if(Math.abs(sum - half) < Math.abs(best)) {// store best path
            best = sum - half;
            bestPath = new ArrayList<>(extended);
        }

        for (int i = index; i < nums.size(); i++) {
            int newSum = sum + nums.get(i);

            if (newSum <= half){
                extended.add(i);
                solveBalance(newSum,i+1);
                extended.remove(extended.size()-1);//remove last
            }
        }
    }

}
