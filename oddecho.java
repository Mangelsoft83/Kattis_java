import java.util.Scanner;

public class oddecho {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        //System.out.println("n = " + n);
        for (int i = 0; i <= n; i++) {
            String txt = scanner.nextLine();
            //System.out.println(txt);
            if (i%2 == 1) System.out.println(txt);

        }
    }
}
