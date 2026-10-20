import java.util.*;
import java.io.IOException;
import java.io.InputStream;

public class classpicture {

  //https://open.kattis.com/problems/classpicture

  public static void main(String[] args) throws IOException {
    FastReader fr = new FastReader();

    String in = fr.readLine();

    while (true) {

      int N = Integer.parseInt(in);

      List<String> names = new ArrayList<>();

      for (int n = 0; n < N; n++) {
        names.add(fr.readLine());
      }

      Map<String, List<String>> adjList = new HashMap<>();

      for (String name : names) {
        List<String> l = new ArrayList<>(names);

        l.remove(name);
        Collections.sort(l);

        adjList.put(name, l);
      }

      List<String[]> donts = new ArrayList<>();

      int M = Integer.parseInt(fr.readLine());

      for (int m = 0; m < M; m++) {
        donts.add(fr.readLine().split(" "));
      }

      for (String[] dont : donts) {
        adjList.get(dont[0]).remove(dont[1]);
        adjList.get(dont[1]).remove(dont[0]);
      }

      // adjList.entrySet().forEach(e -> System.out.println(e.toString()));

      HashSet<String> visited = new HashSet<>();

      // Find if graph is byparted


      if (getNumberOfParts(adjList) > 1) {
        //If the graph is multi parted than no picture can be taken
        System.out.println("You all need therapy.");
      } else {
        // DFS find best setup lexicographically
        List<String> photo = new ArrayList<>(N);
        Collections.sort(names);
        visited.clear();

        boolean isMatch = dfs(visited, photo, names, adjList);

        if (isMatch) {
          String result = "";

          for (String name : photo) {
            result = String.join(" ", result, name);
          }

          result = result.trim();
          System.out.println(result);

        } else {
          System.out.println("You all need therapy.");
        }
      }

      in = fr.readLine();

      if (in == null || in.isEmpty())
        break;

    }

    // private static void dfs(HashSet<String> visited ,List<String> photo,
    // List<String> names, HashMap<String,List<String>> adjList){
    //
    // }
  }

  private static int getNumberOfParts(Map<String, List<String>> adjList) {
    int count = 0;
    HashSet<String> visited = new HashSet<>();
    List<String> extend = new ArrayList<>();

    List<String> names = new ArrayList<>(adjList.keySet());
    for (int n = 0; n < adjList.size(); n++) {
      if (visited.contains(names.get(n)))
        continue;

      count++;
      extend.add(names.get(n));

      while (!extend.isEmpty()) {
        String current = extend.removeLast();

        visited.add(current);
        for (String next : adjList.get(current)) {
          if (visited.contains(next))
            continue;

          // visited.add(next);
          extend.add(next);
        }

      }

    }
    return count;
  }

  private static boolean _dfs(HashSet<String> visited, List<String> photo, List<String> names,
      Map<String, List<String>> adjList) {
    if (photo.size() == names.size())
      return true;

    String current = photo.getLast();
    for (String name : adjList.get(current)) {
      if (visited.contains(name))
        continue;

      visited.add(name);

      photo.add(name);

      boolean stop = _dfs(visited, photo, names, adjList);

      if (stop)
        return true;
      photo.remove(name);
      visited.remove(name);
    }

    return false;
  }

  private static boolean dfs(HashSet<String> visited, List<String> photo, List<String> names,
      Map<String, List<String>> adjList) {
    if (photo.size() == names.size())
      return true;

    for (String name : names) {
      if (visited.contains(name))
        continue;

      visited.add(name);

      photo.add(name);

      boolean stop = _dfs(visited, photo, names, adjList);

      if (stop)
        return true;
      photo.remove(name);
      visited.remove(name);
    }

    return false;
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
