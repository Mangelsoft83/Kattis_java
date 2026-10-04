// https://open.kattis.com/problems/runningmom

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class runningmom {

  public static void main(String[] args) throws IOException {
    FastReader fb = new FastReader();

    int N = Integer.parseInt(fb.readLine());

    List<String[]> vertexList = new ArrayList<>();
    List<String> cityList = new ArrayList<>();

    // First N lines: vertices
    for (int i = 0; i < N; i++) {
      String[] cities = fb.readLine().split(" ");
      vertexList.add(cities);
    }

    // Remaining lines: cities
    String line;
    while ((line = fb.readLine()) != null) {
      if (!line.isBlank()) {
        cityList.add(line);
      }
    }

    // adjList creation
    HashMap<String, Set<String>> adjMap = new HashMap<>(2 * N);

    for (String[] vertex : vertexList) {
      String from = vertex[0];
      String to = vertex[1];
      adjMap.computeIfAbsent(from, k -> new HashSet<>()).add(to);

    }

    // testing and output
    for (String city : cityList) {
      Set<String> visited = new HashSet<>();
      Set<String> path = new HashSet<>();
      System.out.println(city + " " + (dfs(city, adjMap, visited, path) ? "safe" : "trapped"));
    }

  }

  private static boolean dfs(String city, HashMap<String, Set<String>> adjMap, Set<String> visited, Set<String> path) {

    if (path.contains(city))return true;

    if(visited.contains(city)) return false;

    visited.add(city);
    path.add(city);

    for (String toCity : adjMap.getOrDefault(city, Set.of())) {

      if (dfs(toCity, adjMap, visited, path))
        return true;

    }

    path.remove(city);

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
