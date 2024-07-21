import java.io.*;
import java.util.StringTokenizer;

public class enlarginghashtables {
    public static void main(String[] args) {
        Kattio scanner = new Kattio(System.in);

        while (true){
            long n = scanner.getLong();
            if(n==0)break;

            boolean isPrimeNumber = isPrime(n);

            long len = 2*n;

            while (!isPrime(len)){
                len++;
            }

            System.out.println(isPrimeNumber ? len : len + " (" + n + " is not prime)");
            

        }


    }

    private static boolean isPrime(long n) {

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
