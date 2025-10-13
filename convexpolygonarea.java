import java.text.DecimalFormat;
import java.util.Scanner;

public class convexpolygonarea {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {

            int m = scanner.nextInt();
            int[] xx = new int[m+1];
            int[] yy = new int[m+1];

            for (int j = 0; j < m; j++) {
                xx[j] = scanner.nextInt();
                yy[j] = scanner.nextInt();
            }

            xx[m] = xx[0];
            yy[m] = yy[0];

            int sum = 0;
            for (int j = 1; j < m+1; j++) {
                int d = xx[j-1] * yy[j] - yy[j-1] * xx[j];
                sum += d;
            }

            DecimalFormat df = new DecimalFormat("#.##");

            System.out.println(df.format((double)sum / 2));
        }
    }


}
