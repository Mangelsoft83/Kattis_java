import java.util.Scanner;

public class almostperfect {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){

            int num = scanner.nextInt();

            int divs = properSum(num);

            StringBuilder s = new StringBuilder();

            s.append(num).append(" ");

            int delta = Math.abs(divs - num);

            if(delta > 2) s.append("not perfect");
            else if (delta == 0 ) s.append("perfect");
            else {
                s.append("almost perfect");
            }

            System.out.println(s);
        }

    }

    public static int properSum(int n) {
        int sum = 0;
        double x = Math.sqrt(n); // mirror values at sqrt(n) -->  24 divs are 12*2, 8*3, 6*4, 4*6, 3*8, 2*12

        if ((int)x == x)
            sum += (int)x;

        for (int i = 2; i < x; i++)
            if (n % i == 0)
                sum += i + (n / i);

        return sum + 1;
    }

}
