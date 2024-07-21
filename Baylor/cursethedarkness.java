import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.io.*;
import java.util.StringTokenizer;

public class cursethedarkness {
    public static void main(String[] args) {
        Kattio scanner = new Kattio(System.in);
        int N = scanner.getInt();

        for (int n = 0; n < N; n++) {
            double xBook = scanner.getDouble();
            double yBook = scanner.getDouble();

            int M = scanner.getInt();
            List<Double[]> candles = new ArrayList<>();

            for (int m = 0; m < M; m++) {
                Double[] candle = new Double[2];
                candle[0] = scanner.getDouble() - xBook;
                candle[1] = scanner.getDouble() - yBook;
                candles.add(candle);
            }

            candles.sort(Comparator.comparing(o -> o[0] * o[0] + o[1] * o[1]));

            if(candles.isEmpty()) {
                System.out.println("curse the darkness");
                continue;
            }
            Double[] cordNearestCandle = candles.get(0);
            double dist = Math.sqrt(cordNearestCandle[0]*cordNearestCandle[0] + cordNearestCandle[1]*cordNearestCandle[1]);
            System.out.println(dist <= 8 ? "light a candle":"curse the darkness");
        }


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