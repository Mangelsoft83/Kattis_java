import java.util.Scanner;

public class laptopsticker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        int d = scanner.nextInt();

        if (a-1 > c && b-1 > d) System.out.println(1);
        else System.out.println(0);
    }
}
