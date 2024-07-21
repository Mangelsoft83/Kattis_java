import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class sumoftheothers {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 250; i++) {
            if(!scanner.hasNext()) break;
            String[] line = scanner.nextLine().split(" ");

            int[] values = new int[line.length];
            for (int j = 0; j < line.length; j++) {
                values[j] = Integer.parseInt(line[j]);
            }

            int sumTotal = 0;
            for (int v:values) sumTotal+=v;

            for (int v:values) {
                if(sumTotal - v == v) {
                    System.out.println(v);
                    break;
                }
            }
        }
    }
}
