import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class tenKindsOfPeople {

  static boolean[][] grid;
  static int r, c, n;
  static List<int[]> starts, stops;

  public static void main(String[] args) throws IOException {

    FastReaderTena n = new FastReaderTena();

    List<String> lines = new ArrayList<>();

    String l = "";
    while ((l = n.readLine()) != null) {
      lines.add(l);
    }

    proces(lines);

  }

  private static void proces(List<String> lines) {
    // init
    // read grid
    readGrid(lines);
    // read coordinates
    readCoordinates(lines);
    // printGrid();
    // solve
    solve();
  }

  private static void solve() {
    for (int i = 0; i < n; i++) {
      int[] start = starts.get(i);
      int[] stop = stops.get(i);

      if (grid[start[0]][start[1]] != grid[stop[0]][stop[1]]) {
        System.out.println("neither");
      } else if (grid[start[0]][start[1]] && grid[stop[0]][stop[1]]) {
        System.out.println(route(start, stop, true) ? "decimal" : "neither");
      } else if (!grid[start[0]][start[1]] && !grid[stop[0]][stop[1]]) {
        System.out.println(route(start, stop, false) ? "binary" : "neither");
      }
    }
  }

  private static boolean route(int[] start, int[] stop, boolean type) {

    List<int[]> q = new ArrayList<>();
    HashSet<String> closed = new HashSet<>();

    List<int[]> dirs = List.of(
        new int[] { 0, 1 }, new int[] { 0, -1 }, new int[] { 1, 0 }, new int[] { -1, 0 });

    q.add(start);

    String endKey = getKey(stop);

    while (!q.isEmpty()) {
      // System.out.println("q size is: " + q.size());

      int[] act = q.removeFirst();

      closed.add(getKey(act));
      if (endKey.equals(getKey(act)))
        return true;

      // System.out.println(Arrays.toString(act));
      for (int[] dir : dirs) {
        int rr = act[0] + dir[0];
        int cc = act[1] + dir[1];

        int[] newCord = new int[] { rr, cc };

        String newKey = getKey(newCord);

        if (newKey.equals(endKey))
          return true;

        if (isValid(rr, cc) && grid[rr][cc] == type && !closed.contains(newKey)) {
          q.addFirst(newCord);

        }

      }

    }

    return false;
  }

  private static boolean isValid(int rr, int cc) {
    return rr >= 0 && rr < r && cc >= 0 && cc < c;
  }

  private static String getKey(int[] arr) {
    return Arrays.toString(arr);
  }

  private static void readCoordinates(List<String> lines) {
    starts = new ArrayList<>();
    stops = new ArrayList<>();

    n = Integer.parseInt(lines.get(r + 1));

    for (int i = 0; i < n; i++) {
      String[] split = lines.get(i + r + 2).split(" ");
      int[] start = new int[] { Integer.parseInt(split[0]) - 1, Integer.parseInt(split[1]) - 1 };
      int[] stop = new int[] { Integer.parseInt(split[2]) - 1, Integer.parseInt(split[3]) - 1 };
      starts.add(start);
      stops.add(stop);
    }

  }

  private static void printGrid() {
    for (boolean[] row : grid) {
      for (boolean val : row) {
        System.out.print(val ? "1 " : "0 ");
      }
      System.out.println();
    }

    System.out.println("Coordinates");

    for (int i = 0; i < n; i++) {
      int[] start = starts.get(i);
      int[] stop = stops.get(i);

      System.out.println("[" + start[0] + " " + start[1] + "] [" + stop[0] + " " + stop[1] + "]");
    }

  }

  private static void readGrid(List<String> lines) {
    r = Integer.parseInt(lines.getFirst().split(" ")[0]);
    c = Integer.parseInt(lines.getFirst().split(" ")[1]);

    grid = new boolean[r][c];

    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        grid[i][j] = lines.get(i + 1).charAt(j) == '1';
      }
    }
  }

}

class FastReaderTena {
  private final InputStream in = System.in;
  private final byte[] buffer = new byte[1 << 16];
  private int ptr = 0, len = 0;

  public String readLine() throws IOException {
    int c;
    final StringBuilder sb = new StringBuilder();
    boolean seenChar = false;

    while ((c = read()) != -1) {
      if (c == '\n')
        break;
      if (c == '\r')
        continue; // skip carriage return
      sb.append((char) c);
      seenChar = true;
    }

    if (!seenChar && c == -1)
      return null; // true EOF
    return sb.toString();
  }

  private int read() throws IOException {
    if (ptr >= len) {
      ptr = 0;
      len = in.read(buffer);
      if (len <= 0)
        return -1;
    }
    return buffer[ptr++];
  }
}
