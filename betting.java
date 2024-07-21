import java.util.Scanner;

public class betting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double pct = scanner.nextDouble();
        System.out.println(100/pct);
        System.out.println(100/(100 -pct));
    }
}
