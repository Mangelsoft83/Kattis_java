import java.util.Scanner;

public class pot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        scanner.nextLine();
        int sum = 0;
        for (int i = 0; i < N; i++) {
            String number = scanner.nextLine();
            Integer num = Integer.parseInt(number.substring(0,number.length()-1));
            String x = number.substring((number.length()-1));
            Integer exp = Integer.parseInt(x);

            sum += Math.pow(num,exp);
        }

        System.out.println(sum);
    }
}
