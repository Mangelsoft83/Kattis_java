import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class variablearithmetic {
    public static void main(String[] args) throws IOException {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

        HashMap<String,Integer> map = new HashMap<>();
        while (true){
            String line = scanner.readLine();
            if (line.equals("0"))break;

            if(line.contains("=")){
                String[] split = line.split(" = ");
                int num = Integer.parseInt(split[1]);
                map.put(split[0],num );
                continue;
            }

            String[] split = line.split(" ");

            int sum=0;
            StringBuilder vars = new StringBuilder();

            for (String s : split) {
                if(s.trim().equals("+")) continue;
                if (isInteger(s)) {
                    sum += Integer.parseInt(s);
                    continue;
                }
                if (map.containsKey(s)) {
                    sum += map.get(s);
                    continue;
                }

                vars.append(" ").append(s);
            }

            String result = "";
            if(sum !=0) result = result.concat(String.valueOf(sum));

            result = result + vars;
            result = result.trim().replace(" ", " + ");
            System.out.println(result);

        }
        scanner.close();
    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


}
