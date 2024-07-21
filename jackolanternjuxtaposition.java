import java.util.Scanner;

public class jackolanternjuxtaposition {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = Math.min(Math.max(scanner.nextInt(),0),500);
        int M = Math.min(Math.max(scanner.nextInt(),0),500);
        int T = Math.min(Math.max(scanner.nextInt(),0),500);

        System.out.println(N*M*T);
    }


}
