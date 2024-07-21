import java.util.Scanner;

public class nastyhacks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        for (int i = 0; i < N; i++) {
            int r = scanner.nextInt();
            int e = scanner.nextInt();
            int c = scanner.nextInt();

            int xx = e-c;

            System.out.println(r==xx ? "does not matter" : r > xx ? "do not advertise" : "advertise");
        }
    }
}


