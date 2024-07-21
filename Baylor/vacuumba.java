import java.text.DecimalFormat;
import java.util.Scanner;

public class vacuumba {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) { // n of paths
            int m = scanner.nextInt();
            double x =0; double y= 0;
            double angle = 0;
            for (int j = 0; j < m; j++) { // m of lines per path
                angle += scanner.nextDouble();
                double len = scanner.nextDouble();

                x+= len * Math.cos(angle * Math.PI/180);
                y+= -len * Math.sin(angle * Math.PI/180);
            }

            DecimalFormat df = new DecimalFormat("0.000000");
            System.out.println(df.format(y) + " " + df.format(x));
        }
    }
}
