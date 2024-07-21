import java.util.Scanner;

public class twostones {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int rest = n%2;

        System.out.println(rest%2 == 0 ? "Bob" : "Alice");
    }
}
