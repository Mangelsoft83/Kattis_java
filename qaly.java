import java.util.Scanner;

public class qaly {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        double qaly =0;
        for (int i = 0; i < N; i++) {
            double f = scanner.nextDouble();
            double y = scanner.nextDouble();
            qaly += f*y;
        }
        System.out.println(qaly);
    }
}
