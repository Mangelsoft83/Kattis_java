import java.util.Scanner;

public class ratingproblems {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();
        int b = scanner.nextInt();

        int[] score = new int[b];

        for (int s = 0; s < score.length; s++) {
            score[s] = scanner.nextInt();
        }


        double min = 0;
        double max = 0;

        for (int i = 0; i < score.length; i++) {
            min += score[i];
            max += score[i];
        }

        min += (double) (a-b) * -3.0;
        max += (double) (a-b) * 3.0;

        System.out.println(min / ((double) a) + " " + max  / ((double) a));
    }
}
