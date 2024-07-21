import java.util.Scanner;

public class shatteredcake {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int W = scanner.nextInt();
        int n = scanner.nextInt();

        int opp = 0;
        for (int i = 0; i < n; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            opp += a*b;
        }

        System.out.println(opp/W);

    }
}
