import java.util.Scanner;

public class skruop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int vol = 7;
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String cmd = scanner.nextLine();
            if (cmd.equals("Skru op!") && vol < 10) vol++;
            else if(cmd.equals("Skru ned!") && vol > 0) vol--;


        }
        System.out.println(vol);
}}
