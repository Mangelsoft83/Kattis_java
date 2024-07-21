import java.io.*;
import java.util.StringTokenizer;

public class primereduction {
    public static void main(String[] args) {
        Kattio scanner = new Kattio(System.in);

        for (int i = 0; i < 20000; i++) {
            if(!scanner.hasMoreTokens()){
                scanner.close();
                break;
            }
            long n = scanner.getLong();
            if(n==4)break;
            int j=0;
            while (true){
                j++;
                if (isPrime(n)){
                    System.out.println(n + " " + j);break;
                }

                n = primeFactors(n);

            }
        }
        scanner.close();
    }



    private static long primeFactors(long n)
    {
        long sum = 0;
        // Print the number of 2s that divide n
        while (n % 2 == 0) {
            sum+=2;
            //System.out.print(2 + " ");
            n /= 2;
        }

        // n must be odd at this point.  So we can
        // skip one element (Note i = i +2)
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            // While i divides n, print i and divide n
            while (n % i == 0) {
                sum+=i;
                //System.out.print(i + " ");
                n /= i;
            }
        }

        // This condition is to handle the case when
        // n is a prime number greater than 2
        if (n > 2)
            sum+=n;
            //System.out.print(n);
        return sum;
    }

    public static boolean isPrime(long n) {

        if (n == 2)
            return true;

        if (n % 2 == 0)
            return false;

        int sqrt = (int)Math.sqrt(n);

        for (int i = 3; i <= sqrt; i += 2)
            if (n % i == 0)
                return false;

        return true;
    }

}

class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }
    public Kattio(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    public int getInt() {
        return Integer.parseInt(nextToken());
    }

    public double getDouble() {
        return Double.parseDouble(nextToken());
    }

    public long getLong() {
        return Long.parseLong(nextToken());
    }

    public String getWord() {
        return nextToken();
    }



    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException ignored) { }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}