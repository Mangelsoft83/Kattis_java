import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class chartingprogress {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        int num = 0;
        boolean first = true;
        int stopInt = 0;
        while (scanner.hasNextLine()) {
            //System.out.println(num);
            String line = scanner.nextLine();
            if (first) {
                stopInt = line.length();
                //System.out.println();
            }
            first = line.isEmpty();

            String[] lineArray = line.concat(".").split("\\*");


            int startInt = stopInt- lineArray.length +1;

            for (int i = 0; i < line.length(); i++) {
                System.out.print((i >= startInt && i < stopInt) ? "*" : ".");
            }

            System.out.println();
            stopInt = startInt;


        }
    }

}
