import java.util.Scanner;

public class trik {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[] zetten = scanner.nextLine().toCharArray();

        int state = 1;
        for (int i = 0; i < zetten.length; i++) {
            char zet = zetten[i];
            if(zet == 'A' && state == 1) state = 2;
            else if(zet == 'A' && state == 2) state = 1;
            else if(zet == 'B' && state == 2) state = 3;
            else if(zet == 'B' && state == 3) state = 2;
            else if(zet == 'C' && state == 1) state = 3;
            else if(zet == 'C' && state == 3) state = 1;
        }

        System.out.println(state);
    }
}
