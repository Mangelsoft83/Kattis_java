import java.text.DecimalFormat;
import java.util.Scanner;

public class estimatingtheareaofacircle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double r = scanner.nextDouble();
        double m = scanner.nextInt();
        double c = scanner.nextInt();
        DecimalFormat df = new DecimalFormat("#.#####");
        while(r!=0)
        {
            double opp1 = 3.141592654 * r*r;
            double opp2 = 4*r*r * c/m;

            String result = df.format(opp1) + " " + df.format(opp2);
            System.out.println(result);

            r = scanner.nextDouble();
            m = scanner.nextInt();
            c = scanner.nextInt();
        }

    }
}
