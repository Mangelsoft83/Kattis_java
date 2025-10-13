import java.text.DecimalFormat;
import java.util.Scanner;

public class differentdistances {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextDouble()){
            double x1 = scanner.nextDouble();
            if (x1==0)break;

            double y1 = scanner.nextDouble();
            double x2 = scanner.nextDouble();
            double y2 = scanner.nextDouble();
            double p = scanner.nextDouble();

            double d = Math.pow(Math.pow(Math.abs(x1-x2),p) + Math.pow(Math.abs(y1-y2),p),1/p);
            DecimalFormat df = new DecimalFormat("0.0000000000");
            System.out.println(df.format(d));
        }
    }
}
