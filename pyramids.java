import java.util.Scanner;

public class pyramids {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int i = 1;
        int j = 0;
        int temp = 0;
        while (temp < n)
        {

            temp +=(i*i);
            i = i+2;
            j++;
        }
        if (temp > n) j--;
        System.out.println(j);
    }
}
