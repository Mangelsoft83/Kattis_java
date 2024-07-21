import java.util.Arrays;
import java.util.Scanner;

public class electricaloutlets {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int nummer = 1;
            int m = scanner.nextInt();


            for (int j = 0; j < m; j++) {
                nummer -=1;
                nummer += scanner.nextInt();
            }

            //System.out.println(numbers);
            System.out.println(nummer);

        }



    }
}
