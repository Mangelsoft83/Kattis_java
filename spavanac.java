import java.util.Scanner;

public class spavanac {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int H = scanner.nextInt();
        int M = scanner.nextInt();

        int henk = (H * 60 + M) - 45;

        int hours = (henk / 60) % 24;
        int min = (henk % 60 );

        if (min < 0 ){
            hours = 23;
            min += 60;
        }
        System.out.print(hours + " " + min);
        //int min = (henk % 60 ) < 0 ? (henk % 60 ) + 60 : (henk % 60 );
        //System.out.print(" " + min);
    }
}
