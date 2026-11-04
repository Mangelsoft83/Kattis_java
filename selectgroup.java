import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class selectgroup {
  private static final HashMap<String, HashSet<String>> groupMap = new HashMap<>();

  // https://open.kattis.com/problems/classpicture
  private static HashSet<String> union(HashSet<String> S1, HashSet<String> S2) {
    HashSet<String> s = new HashSet<>(S1);

    //System.out.println("union " + S1.toString() + S2.toString());
    s.addAll(S2);
    return s;
  }

  private static HashSet<String> difference(HashSet<String> S1, HashSet<String> S2) {
    HashSet<String> s = new HashSet<>(S1);
    //System.out.println("difference " + S1.toString() + S2.toString());
    s.removeAll(S2);
    return s;
  }

  private static HashSet<String> intersection(HashSet<String> S1, HashSet<String> S2) {
    HashSet<String> s1 = new HashSet<>(S1);
    //System.out.println("intersection " + S1.toString() + S2.toString());
    s1.retainAll(S2);
    return s1;
  }

  private static HashSet<String> find(String[] query, int index, String opp) {

    if(index +1 >= query.length) return groupMap.getOrDefault(opp,new HashSet<>(1));
    String key1 = query[index + 1];

    //use last opperation
    while (key1.equals("union") || key1.endsWith("intersection") || key1.equals("difference")) {
      index++;
      if(index >= query.length) return new HashSet<>();

      opp = query[index];
      key1 = query[index + 1];
    }    

    HashSet<String> s1 = groupMap.getOrDefault(key1, new HashSet<String>());

    if(index+2 >= query.length) return s1;
    String key2 = query[index + 2];
    //HashSet<String> s2 = groupMap.getOrDefault(key2, find(query,index+2, query[index + 2]));
    HashSet<String> s2 = groupMap.get(key2);
    if(s2==null) s2 = find(query,index+2, query[index + 2]);

    switch (opp) {
      case "union" -> {
        return union(s1, s2);
      }
      case "difference" -> {
        return difference(s1, s2);
      }
      case "intersection" -> {
        return intersection(s1, s2);
      }
    }
    return new HashSet<>();
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
        case "union", "intersection", "difference" -> {

          TreeSet<String> hashSet = new TreeSet<>(find(query,0, opp));

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
