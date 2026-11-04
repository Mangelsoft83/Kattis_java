import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class selectgroup {
  private static final HashMap<String, HashSet<String>> groupMap = new HashMap<>();

  private static String[] tokens;
  private static int ind;

  // https://open.kattis.com/problems/classpicture
  // Recursive desent parser
  private static HashSet<String> union(HashSet<String> S1, HashSet<String> S2) {
    HashSet<String> s = new HashSet<>(S1);
    s.addAll(S2);
    return s;
  }

  private static HashSet<String> difference(HashSet<String> S1, HashSet<String> S2) {
    HashSet<String> s = new HashSet<>(S1);
    s.removeAll(S2);
    return s;
  }

  private static HashSet<String> intersection(HashSet<String> S1, HashSet<String> S2) {
    HashSet<String> s1 = new HashSet<>(S1);
    s1.retainAll(S2);
    return s1;
  }

  private static HashSet<String> find() {
    String t = tokens[ind];
    ind++;

    switch (t) {

      case "union" -> {
        HashSet<String> s1 = find();
        HashSet<String> s2 = find();
        return union(s1, s2);
      }
      case "difference" -> {
        HashSet<String> s1 = find();
        HashSet<String> s2 = find();
        return difference(s1, s2);
      }
      case "intersection" -> {
        HashSet<String> s1 = find();
        HashSet<String> s2 = find();
        return intersection(s1, s2);
      }
      default -> {
        return groupMap.getOrDefault(t, new HashSet<>());
      }
    }
  }

  public static void main(String[] args) throws IOException {
    FastReader fr = new FastReader();

    String in = fr.readLine();

    while (true) {
      String[] query = in.split(" ");

      String opp = query[0];

      switch (opp) {
        case "group" -> {
          String name = query[1];
          HashSet<String> set = new HashSet<>();

          for (int i = 3; i < query.length; i++) {
            set.add(query[i]);
          }
          groupMap.put(name, set);
        }
        default -> {
          tokens = query;
          ind = 0;

          TreeSet<String> hashSet = new TreeSet<>(find());

          System.out.println(String.join(" ", hashSet));
        }
      }
      in = fr.readLine();
      if (in == null)
        break;
    }

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
