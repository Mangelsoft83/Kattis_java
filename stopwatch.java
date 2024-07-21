import java.util.Scanner;

public class stopwatch {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int[] bp = new int[N];
        for (int i = 0; i < N; i++) {
            bp[i] = scanner.nextInt();
        }


        int time = 0;
        boolean running = false;

        for (int i = 0; i < bp.length; i++) {

            if (running) time += bp[i] - bp[i-1];

            running = running ^ true;
        }

        System.out.println(running ? "still running" : time);
    }
}
