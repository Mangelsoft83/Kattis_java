import java.util.Scanner;

public class autori {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        String[] arrInput = input.split("-");
        for (String a:arrInput) {
            System.out.print(a.charAt(0));

        }

    }
}
