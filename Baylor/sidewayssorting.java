import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class sidewayssorting {
    public static void main(String[] args) throws IOException {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            String[] x = scanner.readLine().split(" ");
            int n = Integer.parseInt(x[0]);
            int w = Integer.parseInt(x[1]);

            if(n==0 || w==0) break;

            String[][] table = new String[w][n];

            for (int i = 0; i < n; i++) {
                char[] ch = scanner.readLine().toCharArray();

                for (int j = 0; j < w; j++) {
                    table[j][i] = String.valueOf(ch[j]);
                }
            }

            int index = 0;

            for (int i = n-1; i >= 0 ; i--) {
                sortTable(table, i);
            }


            //System.out.println();
            for (int j = 0; j < n; j++) {
                String line = "";
                for (int i = 0; i < w; i++){
                    line=line.concat(table[i][j].toString());
                }
                System.out.println(line);

            }
            System.out.println();


        }
    }

    private static void sortTable(String[][] table, int index) {
        Arrays.sort(table,new Comparator<String[]>() {
                        @Override
                        public int compare(String[] o1, String[] o2) {
                            return o1[index].toUpperCase().compareTo(o2[index].toUpperCase());
                        }

                    }

                );
    }
}
