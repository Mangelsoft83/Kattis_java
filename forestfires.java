import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class forestfires {
  // https://open.kattis.com/problems/forestfire

  private static int r;
  private static final List<int[]> dirs = List.of(new int[] { 0, 1 }, new int[] { 0, -1 }, new int[] { 1, 0 },
      new int[] { -1, 0 });

  private static int[] parent = new int[10000];
  private static boolean[] present = new boolean[10000];

  public static void main(String[] args) throws IOException {
    FastReader fr = new FastReader();

    String in = fr.readLine();

    while (true) {
      String[] query = in.split(" ");

      r = Integer.parseInt(query[0]);

      int N = Integer.parseInt(query[1]);

      List<String> results = new ArrayList<>();

      List<Integer> trees = new ArrayList<>();

      // init union tree
      present = new boolean[10000];
      parent = new int[10000];

      for (int i = 0; i < parent.length; i++)
        parent[i] = i;

      // start sequence
      int count = 0;
      for (int i = 0; i < N; i++) {
        // (3) Place a tree
        while (true) { // Find until unique tree is found
          
          int m = random() % 10000;
          if (!present[m]) {
            present[m] = true;
            trees.add(m);
            int x = m / 100, y = m % 100;
            for (int[] d : dirs) {
              int nx = x + d[0], ny = y + d[1];
              int pos = nx * 100 + ny;
              if (nx >= 0 && nx < 100 && ny >= 0 && ny < 100 && present[pos])
                union(m, pos);
            }
            break;
          }
        }

        // (4) Choose A
        int A = trees.get(random() % (i + 1));

        // (5) Choose B
        int B = trees.get(random() % (i + 1));

        // (6) Fire query
        if (find(A) == find(B))
          count++;

        // store
        if ((i) % 100 == 99) {
          results.add(Integer.toString(count));
          count = 0;
        }

      }

      // print
      System.out.println(String.join(" ", results));

      in = fr.readLine();
      if (in == null)
        break;
    }
  }

  private static int find(int x) {
    while (parent[x] != x) {
      parent[x] = parent[parent[x]];
      x = parent[x];
    }
    return x;
  }

  private static void union(int a, int b) {
    parent[find(a)] = find(b);
  }

  public forestfires() {
    super();
  }

  private static int random() {
    return r = (r * 5171 + 13297) % 50021;
  }

  private static class FastReader {
    private final InputStream in = System.in;
    private final byte[] buffer = new byte[1 << 16];
    private int ptr = 0;
    private int len = 0;

    public String readLine() throws IOException {
      int c;
      StringBuilder sb = new StringBuilder();
      boolean seenChar = false;

      while ((c = read()) != -1) {
        if (c == '\n') {
          break;
        }

        if (c == '\r') {
          continue;
        }

        sb.append((char) c);
        seenChar = true;
      }

      if (!seenChar && c == -1) {
        return null;
      }

      return sb.toString();
    }

    private int read() throws IOException {
      if (ptr >= len) {
        ptr = 0;
        len = in.read(buffer);

        if (len <= 0) {
          return -1;
        }
      }

      return buffer[ptr++];
    }
  }

}
