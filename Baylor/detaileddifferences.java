import java.util.Scanner;

public class detaileddifferences {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        String[] inputStings = new String[2*n];
        scanner.nextLine();
        for (int i = 0; i < 2*n; i++) {
            inputStings[i] = scanner.nextLine();
        }

        for (int i = 0; i < n; i++) {
            String str1 = inputStings[2 * i];
            String str2 = inputStings[2 * i + 1];
            System.out.println(str1);
            System.out.println(str2);

            for (int j = 0; j < str1.length(); j++) {
                char char1 = str1.charAt(j);
                char char2 = str2.charAt(j);

                char char3 = (char1 == char2) ? '.' : '*';
                System.out.print(char3);
            }


            System.out.println();
        }
    }
}
