import java.util.Scanner;

public class digitswap {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int input = scanner.nextInt();

        int a = input % 10;
        int b = input / 10;

        System.out.println(a*10+b);
    }
}
