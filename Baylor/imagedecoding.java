import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class imagedecoding {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n =-1;
        boolean first = true;

        while ((n = Integer.parseInt(bufferedReader.readLine())) != 0){
            if(!first) System.out.println();

            List<String[]> lines = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                lines.add(bufferedReader.readLine().split(" "));
            }

            List<StringBuilder> results = new ArrayList<>();

            boolean error = false;
            for(String[] line:lines){
                String ch = line[0];
                StringBuilder result = new StringBuilder();
                for (int i = 1; i < line.length; i++) {
                    int k = Integer.    parseInt(String.valueOf(line[i]));

                    for (int j = 0; j < k; j++) {
                        result.append(ch);
                    }

                    ch = ch.equals("#") ? "." : "#";
                }

                if(!results.isEmpty() && results.get(results.size()-1).length() != result.length()){
                    error = true;
                }
                results.add(result);
            }

            for(StringBuilder result:results){
                System.out.println(result);
            }

            if(error) {
                System.out.println("Error decoding image");
            }

            first = false;

        }
    }
}
