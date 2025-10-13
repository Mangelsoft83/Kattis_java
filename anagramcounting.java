import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;

public class anagramcounting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);





        while (scanner.hasNextLine()){
            HashMap<Character,Integer> admin = new HashMap<>();


            String s = scanner.nextLine();
            for(Character c:s.toCharArray()){

                if(admin.containsKey(c)){
                    int x = admin.get(c);
                    x++;
                    admin.put(c,x);

                }else {
                    admin.put(c,1);
                }
            }

            //factorial
            BigInteger cnt = factorial(s.length());
            
            //fact off unique characters
            for(Integer n:admin.values()){
                cnt = cnt.divide(factorial(n));
            }

            System.out.println(cnt);
        }


    }

    private static BigInteger factorial(int n) {
        BigInteger cnt = BigInteger.valueOf(1);
        for (int i = 2; i <= n; i++) {
            cnt = cnt.multiply(BigInteger.valueOf(i));
        }
        return cnt;
    }
}
