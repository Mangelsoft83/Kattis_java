import java.util.*;
import java.io.*;
import java.util.StringTokenizer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class holeynqueensbatman {
    static List<HashSet<List<Integer>>> uniqueSets = new ArrayList<>(13);

    public static void main(String[] args) {
        Kattio scanner = new Kattio(System.in);

        createUniqueSets();

        while (true) {
            int N = scanner.getInt();
            int M = scanner.getInt();

            if(N==0 && M==0) break;

            int[] holes = new int[2*M];

            for (int i = 0; i < holes.length; i++) {
                holes[i] = scanner.getInt();
            }

            HashSet<List<Integer>> uniqueSet= uniqueSets.get(N);

            System.out.println(uniqueSet.size() - nAreHoles(uniqueSet,holes));

        }
        scanner.close();
    }

    private static int nAreHoles(HashSet<List<Integer>> uniqueSet, int[] holes) {
        int count =0;

        for (List<Integer> path:uniqueSet){

            for (int i = 0; i < holes.length; i=i+2) {
                if(path.get(holes[i])-1 == holes[i+1]) {
                    count++;
                    //System.out.println("deleted: " + path + " " + holes[i] + " " + holes[i+1]);
                    break;
                }
            }
        }
        return count;
    }

    private static void createUniqueSets() {
        for (int i = 0; i < 13; i++) {
            uniqueSets.add(new HashSet<>());
        }

        for (int N = 0; N < 14; N++) {
            List<Integer> elements = new ArrayList<>();
            for (int i = 1; i < N; i++) {
                elements.add(i);
            }

            getSetAll(new ArrayList<>(),elements);
        }
    }

    private static void getSetAll(List<Integer> path ,List<Integer> elements) {
        if(path.size() == elements.size()){
            uniqueSets.get(elements.size()).add(path);
            return ;
        }

        for (int i = 0; i < elements.size(); i++) {
            int elementToInsert = elements.get(i);

            boolean skip = path.contains(elementToInsert);

            if(!skip) {
                int varBan1 = elementToInsert;
                int varBan2 = elementToInsert;
                for (int j = path.size() - 1; j >= 0; j--) {
                    int z = path.get(j);
                    varBan1--;
                    varBan2++;
                    if (z == varBan1 || z == varBan2){
                        skip=true;
                        break;
                    }

                }
            }

            if(!skip ){
                List<Integer> newPath = new ArrayList<>(path);
                newPath.add(elements.get(i));
                getSetAll(newPath,elements);
            }
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