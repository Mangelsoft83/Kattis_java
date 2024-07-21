import java.util.Scanner;

public class gcvwr {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int G= Math.max(Math.min(25000, scanner.nextInt()),5000);
        int T = Math.max(Math.min(12000, scanner.nextInt()),3000);
        int n = Math.max(Math.min(100, scanner.nextInt()),1);


        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Math.max(Math.min(500, scanner.nextInt()),1);
        }

        int max = (int)(0.9 * (G - T));

        for (int getal:arr) {
            max -= getal;
        }
        System.out.println(max);

    }
}
