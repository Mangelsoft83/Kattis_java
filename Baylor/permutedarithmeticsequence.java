import java.util.*;

public class permutedarithmeticsequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            int m = scanner.nextInt();

            List<Integer> numbers = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                numbers.add(scanner.nextInt());
            }
            List<Integer> sortedNumbers = new ArrayList<>(numbers);
            Collections.sort(sortedNumbers);

            if(isArithmetic(numbers)) System.out.println("arithmetic");
            else if (isArithmetic(sortedNumbers)) System.out.println("permuted arithmetic");
            else System.out.println("non-arithmetic");


        }
    }

    private static boolean isArithmetic(List<Integer> numbers) {
        if(numbers.size() < 2) return false;

        int delta = numbers.get(1)-numbers.get(0);

        for (int i = 1; i < numbers.size(); i++) {
            if(numbers.get(i)-numbers.get(i-1) != delta) return false;
        }
        return true;
    }
}
