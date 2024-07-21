import java.util.ArrayList;
import java.util.Scanner;

public class zamka {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int L = scanner.nextInt();
        int D = scanner.nextInt();
        int X = scanner.nextInt();

        ArrayList<Integer> results = new ArrayList<>();

        for (int i = L; i <= D; i++) {
            String iString = String.valueOf(i);
            char[] cArray = iString.toCharArray();
            int sum = 0;
            for(Character cNum:cArray){

                sum += Integer.parseInt(cNum.toString());
            }
            if (sum == X) results.add(i);


        }

        System.out.println(results.get(0));
        System.out.println(results.get(results.size()-1));
    }
}
