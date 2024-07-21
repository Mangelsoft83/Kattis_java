import java.util.Scanner;

public class dicecup {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt(); int b = scanner.nextInt();
        a = Math.min(Math.max(a,4),20);
        b = Math.min(Math.max(b,4),20);

        int N = Math.min(a,b);
        int M = Math.max(a,b);
        for (int i = N+1; i <= M+1; i++) {
            System.out.println(i);

        }

    }
}
