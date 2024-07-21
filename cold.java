import java.util.Scanner;

public class cold {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] tArray = new int[n];

        for (int i = 0; i < n; i++) {
            tArray[i] = scanner.nextInt();
        }

        int teller = 0;
        for (int i = 0; i < n; i++) {
            if (tArray[i] < 0) teller++;

        }
        System.out.println(teller);
    }
}
