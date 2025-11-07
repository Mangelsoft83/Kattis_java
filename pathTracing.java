import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class pathTracing {

  public static void main(String[] args) throws IOException {
    List<int[]> path = new ArrayList<>();

    FastReaderPath in = new FastReaderPath();

    String line;

    int x = 0, y = 0;
    path.add(new int[] { 0, 0 });
    while ((line = in.readLine()) != null) {
      // Each move is one of left, right, up or down.
      if (line.equals("left"))
        x--;
      else if (line.equals("right"))
        x++;
      else if (line.equals("up"))
        y--;
      else if (line.equals("down"))
        y++;

      path.add(new int[] { y, x });
    }

    int gMin = Integer.MAX_VALUE;
    int gMax = Integer.MIN_VALUE;
    int cMin = Integer.MAX_VALUE;
    int cMax = Integer.MIN_VALUE;

    for (int[] a : path) {
      gMin = Math.min(gMin, a[0]);
      gMax = Math.max(gMax, a[0]);
      cMin = Math.min(cMin, a[1]);
      cMax = Math.max(cMax, a[1]);
    }

    for (int[] p : path) {
      p[0] = p[0] - gMin + 1;
      p[1] = p[1] - cMin + 1;
    }

    int C = cMax - cMin + 3;
    int R = gMax - gMin + 3;

    char[][] grid = new char[R][C];

    // Border
    for (int i = 0; i < R; i++) {
      char[] l = grid[i];
      if (i == 0 || i == (R - 1))
        Arrays.fill(l, '#');
      else {
        Arrays.fill(l, ' ');
        l[0] = '#';
        l[C - 1] = '#';
      }
    }

    for (int[] p : path) {
      int r = p[0];
      int c = p[1];
      grid[r][c] = '*';
    }

    int[] s = path.getFirst();
    int[] e = path.getLast();
    grid[s[0]][s[1]] = 'S';
    grid[e[0]][e[1]] = 'E';

    for (char[] l : grid)
      System.out.println(new String(l));
  }
}

class FastReaderPath {
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
