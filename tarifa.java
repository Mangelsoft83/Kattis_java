import java.util.Scanner;

public class tarifa {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int X = scanner.nextInt();
        int N = scanner.nextInt();

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
        }

        int data = X;

        for (int i = 0; i < N; i++) {
            data+=X;
            data-=arr[i];
        }

        System.out.println(data);
    }
}
