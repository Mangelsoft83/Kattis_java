import java.util.Scanner;

public class chanukah {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int nummer = -0;
            int a = scanner.nextInt();
            int m = scanner.nextInt();


            for (int j = 1; j <= m; j++) {
                nummer +=1;
                nummer += j;
            }

            System.out.println(a + " " + nummer);
        }
    }
}
