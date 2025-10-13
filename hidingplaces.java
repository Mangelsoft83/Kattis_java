
import java.util.Scanner;

public class hidingplaces {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        String alphabet = "abcdefgh";
        String numbers = "12345678";



        for (int i = 0; i < n; i++) { //loop trough init positions
            String pos = scanner.next();
            char[] initPosition = pos.toCharArray();
            int xStart = Integer.parseInt(String.valueOf(numbers.indexOf(initPosition[1])));
            int yStart = Integer.parseInt(String.valueOf(alphabet.indexOf(initPosition[0])));

            int[][] board = new int[8][8];

            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    board[x][y] = -1;
                }
            }

            int depth = 0;
            board[xStart][yStart] = depth;
            int maxMoves = 0;

            for (int j = 0; j < 10; j++) {
                if (maxMoves < j) break;
                maxMoves = waterdruppel(board,j,maxMoves);
            }

            //Print results



            StringBuilder print = new StringBuilder(maxMoves + " ");
            for (int x = 7; x >= 0; x--) {
                for (int y = 0; y < 8; y++) {
                    char[] a = alphabet.toCharArray();
                    char[] b = numbers.toCharArray();

                    int nMoves = board[x][y];

                    if (nMoves == maxMoves){
                        print.append(" ").append(a[y]).append(b[x]);
                    }

                }

            }
            System.out.println(print);

        }
    }

    private static int waterdruppel(int[][] board, int d,int maxMoves) {
        int[] xNewArray = {-2,-1,1,2,2,1,-1,-2}; //dx
        int[] yNewArray = {1,2,2,1,-1,-2,-2,-1}; //dy

        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                if (board[x][y] == d){
                    for (int i = 0; i < xNewArray.length; i++) {
                        int xNew = x + xNewArray[i];
                        int yNew = y + yNewArray[i];

                        if(xNew >= 8 || xNew < 0 || yNew >= 8 || yNew < 0 || board[xNew][yNew] != -1) continue;
                        maxMoves = d + 1;
                        board[xNew][yNew] = maxMoves;
                    }
                }

            }

        }

        return maxMoves;
    }

}
