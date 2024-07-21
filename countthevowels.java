import java.util.Scanner;

public class countthevowels {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String n = scanner.nextLine();

        char[] c = n.toUpperCase().toCharArray();

        int count = 0;

        for(char a:c)
        {
            if (a == 'A')count++;
            if (a == 'E')count++;
            if (a == 'I')count++;
            if (a == 'O')count++;
            if (a == 'U')count++;
        }

        System.out.println(count);
    }
}
