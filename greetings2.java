import java.util.Scanner;

public class greetings2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String in = scanner.nextLine();
        char[] charArray = in.toCharArray();
        String out = "";
        for (char a:charArray) {
            if (a == 'e') out = out + "ee";
            else out = out + a;
        }
        System.out.println(out);
    }
}
