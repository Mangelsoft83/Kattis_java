import Baylor.Kattio;

import java.io.*;
import java.util.StringTokenizer;

public class splat {
    public static void main(String[] args) {
        Kattio scanner = new Kattio(System.in);

        int c = scanner.getInt();

        for (int i = 0; i < c; i++) {
            int n = scanner.getInt();
            Splash[] splashes = new Splash[n];

            for (int j = 0; j < n; j++) {
                double x = scanner.getDouble();
                double y = scanner.getDouble();
                double v = scanner.getDouble();
                String name = scanner.getWord();
                splashes[j] = new Splash(x,y,v,name);
            }

            int m = scanner.getInt();
            for (int j = 0; j < m; j++) {
                double x = scanner.getDouble();
                double y = scanner.getDouble();
                System.out.println(getColorSplash(splashes, x, y));
            }

        }

        scanner.close();

    }

    private static String getColorSplash(Splash[] splashes, double x, double y) {
        for (int i = splashes.length-1; i >= 0; i--) {

            if(splashes[i].isIN(x,y)){
                return splashes[i].getName();
            }
        }
        return "white";
    }
}

class Splash{
    private final double x;
    private final double y;
    private final double r;
    private final String name;

    public String getName() {
        return name;
    }

    public Splash(double x, double y, double v, String name) {
        this.x = x;
        this.y = y;
        this.r = Math.sqrt(v / Math.PI);
        this.name = name;
    }
    public boolean isIN(double x1,double y1){
        return Math.hypot(x1-this.x,y1-this.y) <= this.r;
    }

    @Override
    public String toString() {
        return name;
    }
}

/*
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
}*/
