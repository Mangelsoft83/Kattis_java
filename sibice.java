import java.util.Scanner;

public class sibice {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double N = scanner.nextDouble();
        double W = scanner.nextDouble();
        double H = scanner.nextDouble();

        double dim = Math.sqrt(W*W + H*H);

        for (int i = 0; i < N; i++) {

            System.out.println(dim >= scanner.nextDouble() ? "DA" : "NE");
        }

    }
}
