import java.util.Scanner;

public class selfsimilarstrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()){
            
            String line = scanner.nextLine();
            int i;
            for (i = line.length() - 1; i > 0; i--) {

                if (isSame(line, i)) {
                    break;
                }

            }
            System.out.println(i);
        }
    }

    private static boolean isSame(String line, int d) {

        for (int i = 0; i <= line.length() - d; i++) {
            String s = line.substring(i, i + d);
            int a = line.indexOf(s);
            int b = line.lastIndexOf(s);
            if (a == b)
                return false;
        }

        return true;
    }
}
