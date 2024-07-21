import java.util.Scanner;

public class oktalni {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        //complete to n%3 = 0

        if (input.length()%3 != 0) input = "0" + input;
        if (input.length()%3 != 0) input = "0" + input;

        char[] arr = input.toCharArray();
        StringBuilder oct = new StringBuilder();

        int num = arr.length/3;
        for (int i = 0; i < num; i++) {
            int result = 0;
            int exp = 2;
            for (int j = 0; j < 3; j++) {
                int index = (3*i) + j;
                char b = arr[index];
                if (b == '1') {

                    result += Math.pow(2, exp);
                }
                exp--;
            }
            oct.append("" + result);
        }


        System.out.println(oct);
    }
}
