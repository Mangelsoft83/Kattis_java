import java.text.DecimalFormat;
import java.util.Scanner;

public class pervasiveheartmonitor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){
        //for (int i = 0; i < 2; i++) {


            String line = scanner.nextLine();
            String[] lineSep = line.split(" ");

            double sum = 0;
            int count = 0;
            String name = "";
            for (String s:lineSep) {
                if (isNum(s)){
                    count++;
                    sum += Double.parseDouble(s);
                }else{
                    name = name.concat(s + " ");
                }
            }
            double avg = sum / count;
            name = name.trim();

            DecimalFormat df = new DecimalFormat("0.00000");

            String result = df.format(avg) + " " + name;
            System.out.println(result);
        }
    }

    private static boolean isNum(String s) {
        String x = "0123456789";

        for (Character c:x.toCharArray()) if (s.contains(c.toString())) return true;

        return false;
    }
}
