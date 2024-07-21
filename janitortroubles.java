import java.util.Scanner;

public class janitortroubles {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        int d = scanner.nextInt();

        double s = (double) (a + b + c + d) / 2;

        double area = (s-a)*(s-b)*(s-c)*(s-d);
        area = Math.sqrt(area);

        System.out.println(area);
    }
}
