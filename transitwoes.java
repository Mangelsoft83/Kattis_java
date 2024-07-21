import java.util.Scanner;

public class transitwoes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T0 = scanner.nextInt();
        int T1 = scanner.nextInt();
        int n = scanner.nextInt();
        int[] d = new int[n+1];
        for (int i = 0; i <= n; i++) {
            d[i] = scanner.nextInt();
        }

        int[] b = new int[n+1];
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }

        int[] c = new int[n+1];
        for (int i = 0; i < n; i++) {
            c[i] = scanner.nextInt();
        }

        int time = 0;

        for (int i = 0; i < n; i++) {
            time += d[i];

            while (time%c[i] != 0) time++;

            time += b[i];

        }

        time += d[n-1];

        boolean onTime = (T1-T0) > time;
        System.out.println(onTime ? "yes" : "no");

    }
}
