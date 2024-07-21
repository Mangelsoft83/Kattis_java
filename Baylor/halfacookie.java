import java.util.Scanner;

public class halfacookie {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){
            double r = scanner.nextDouble();
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();

            if(x*x + y*y > r*r) System.out.println("miss");
            else {
                double d = Math.sqrt(x*x+y*y);

                double h = r-d;
                double segment_a = r * r * Math.acos((r - h)/r) - (r - h) * Math.sqrt(2 * r * h - h * h);
                double circle_a = 3.1415926535897932384626433832795028841971693993751 * r*r;
                double segment_b = circle_a - segment_a;

                System.out.println(segment_b + " " + segment_a);


            }

        }
    }
}
