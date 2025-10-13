import java.util.*;

public class wordsfornumbers {
    static char[] numbers = "0123456789".toCharArray();
    static HashMap<Integer,String> tens = new HashMap<>();
    static HashMap<Integer,String> dec = new HashMap<>();


    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        tens.put(1,"ten");tens.put(2,"twenty");tens.put(3,"thirty");tens.put(4,"forty");tens.put(5,"fifty");tens.put(6,"sixty");tens.put(7,"seventy");tens.put(8,"eighty");tens.put(9,"ninety");tens.put(0,"");
        dec.put(1,"one");dec.put(2,"two");dec.put(3,"three");dec.put(4,"four");dec.put(5,"five");dec.put(6,"six");dec.put(7,"seven");dec.put(8,"eight");dec.put(9,"nine");dec.put(0,"zero");
        while (scan.hasNextLine()){
            String line = scan.nextLine();
            String[] lineSep = line.split(" ");

            StringBuilder sb = new StringBuilder();

            for (String s:lineSep){
                if (isNum(s)) {
                    //System.out.println(s + "-" + getWords(s));
                    sb.append(getWords(s)).append(" ");
                }else{
                    sb.append(s).append(" ");
                }
            }

            sb.replace(0,1,sb.substring(0,1).toUpperCase());
            System.out.println(sb.toString().trim());
        }
    }

    private static String getWords(String s) {
        char[] cc = s.toCharArray();
        int count = 0;
        StringBuilder sb = new StringBuilder();
        if(s.equals("11")) return "eleven";
        if(s.equals("12")) return "twelve";
        if(s.equals("13")) return "thirteen";
        if(s.equals("14")) return "fourteen";
        if(s.equals("15")) return "fifteen";
        if(s.equals("16")) return "sixteen";
        if(s.equals("17")) return "seventeen";
        if(s.equals("18")) return "eighteen";
        if(s.equals("19")) return "nineteen";

        int key = Integer.parseInt(String.valueOf(cc[0]));
        if (cc.length ==1){
            sb.append(dec.get(key));
        }else {
            sb.append(tens.get(key));
            int decimal = Integer.parseInt(String.valueOf(cc[1]));
            if (decimal != 0) sb.append("-").append(dec.get(decimal));
        }


        return sb.toString();
    }

    private static boolean isNum(String s) {
        if(s.trim().length() > 2) return false;

        char[] cc = s.toCharArray();

        for(char c:cc){
            if(Arrays.binarySearch(numbers,c) < 0) return false;
        }

        return true;

    }
}
