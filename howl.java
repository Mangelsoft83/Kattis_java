import java.util.Scanner;

public class howl {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String in = scanner.nextLine();

        String out = in;
        if (!out.contains("A"))out = "A" + out;
        if (!out.contains("H"))out = "H" + out;
        out = out.replaceAll("O","");
        out = out + "O";
        if (!out.contains("W"))out = out + "W";

        out = out.replaceAll("WW","W");
        out = out.replaceAll("HH","H");

        out = out.replaceAll("HW","HOW");
        out = out.replaceAll("HA","AH");

        System.out.println(out);
    }
}
