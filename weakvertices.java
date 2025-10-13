import java.util.ArrayList;
import java.util.Scanner;

public class weakvertices {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int n = scanner.nextInt();

        while (n!=-1)
        {
            ArrayList<Integer> weak = new ArrayList<>();

            //read adj matrix
            int[][] adjMatrix = getAdjMatrix(n, scanner);


            //Test for weakness
            for (int i = 0; i < n; i++) {
                boolean isWeak = !isTriange(i,adjMatrix,n);
                if(isWeak) weak.add(i);
            }

            //create a string
            printString(weak);

            n = scanner.nextInt();
        }


    }

    private static void printString(ArrayList<Integer> weak) {
        if (!weak.isEmpty()) {
            String answer = "";

            for (Integer w: weak) {
                answer = answer + w + " ";
            }

            if (!answer.isEmpty())
                System.out.println(answer.substring(0, answer.length() - 1));
            else
                System.out.println("");
        }
    }

    private static int[][] getAdjMatrix(int n, Scanner scanner) {
        int[][] adjMatrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adjMatrix[i][j] = scanner.nextInt();
            }
        }
        return adjMatrix;
    }

    private static boolean isTriange(int i, int[][] adjMatrix,int n) {

        //Loop trough index neighbours
        for (int j = 0; j < n; j++) {
            if(adjMatrix[i][j] == 1){ //neighbour is found
                for (int k = 0; k < n; k++) { //in neighbour Vertex find the way to the start index. Is there than trangle
                    if (adjMatrix[j][k] == 1){
                        if(adjMatrix[k][i] == 1) return true;
                    }
                }
            }
        }
        return false;
    }
}
