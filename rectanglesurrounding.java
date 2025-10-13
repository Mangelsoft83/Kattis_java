import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class rectanglesurrounding {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int N = scanner.nextInt();
            if (N==0)break;

            List<int[]> recList = new ArrayList<>();

            int xStart = Integer.MAX_VALUE,xStop = Integer.MIN_VALUE,yStart = Integer.MAX_VALUE,yStop = Integer.MIN_VALUE;

            for (int n = 0; n < N; n++) {
                int[] rec = new int[4];
                rec[0] = scanner.nextInt();
                rec[1] = scanner.nextInt();
                rec[2] = scanner.nextInt();
                rec[3] = scanner.nextInt();

                xStart = Math.min(xStart,rec[0]);
                xStop = Math.max(xStop,rec[2]);
                yStart = Math.min(yStart,rec[1]);
                yStop = Math.max(yStop,rec[3]);

                recList.add(rec);
            }

            //apply offsets in rectangels
            for (int i = 0; i < N; i++) {
                int[] rectangle = recList.get(i);
                rectangle[0] -= xStart;
                rectangle[2] -= xStart;
                rectangle[1] -= yStart;
                rectangle[3] -= yStart;
            }

            xStop -= xStart;
            yStop -= yStart;


            boolean[][] grid = new boolean[xStop][yStop];

            for (int[] rec:recList){
                for (int i = rec[0]; i < rec[2]; i++) {
                    for (int j = rec[1]; j < rec[3]; j++) {
                        grid[i][j] = true;
                    }
                }
            }

            int count=0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    count += grid[i][j] ? 1:0;
                }
            }

            System.out.println(count);
        }
    }
}
