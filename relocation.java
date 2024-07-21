import java.util.Scanner;

public class relocation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int Q = scanner.nextInt();

        int[] street = new int[N];

        int[][] RQ = new int[Q][3];

        for (int i = 0; i < N; i++) {
            street[i] = scanner.nextInt();
        }

        for (int i = 0; i < Q; i++) {
            for (int j = 0; j < 3; j++) {
                RQ[i][j] = scanner.nextInt();
            }
        }

        for (int[] ints : RQ) {
            int opp = ints[0];
            if (opp == 1) {
                int c = ints[1]-1;
                int x = ints[2];

                street[c] = x;

            } else {
                int a = ints[1]-1;
                int b = ints[2]-1;
                System.out.println(Math.abs(street[a] - street[b]));
            }
        }

    }
}
