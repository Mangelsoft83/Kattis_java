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
    while ((line = in.readLine()) != null) {
      // Each move is one of left, right, up or down.
      if (line.equals("left"))
        x--;
      else if (line.equals("right"))
        x++;
      else if (line.equals("up"))
        y++;
      else if (line.equals("down"))
        y--;

      path.add(new int[] { x, y });
    }

    int xMin = Integer.MAX_VALUE;
    int xMax = Integer.MIN_VALUE;
    int yMin = Integer.MAX_VALUE;
    int yMax = Integer.MIN_VALUE;

    for (int[] a : path) {
      xMin = Math.min(xMin, a[0]);
      xMax = Math.max(xMax, a[0]);
      yMin = Math.min(yMin, a[1]);
      yMax = Math.max(yMax, a[1]);
    }

    for (int[] p : path) {
      p[0] = p[0] - xMin + 1;
      p[1] = p[1] - yMin + 1;
    }

    int R = yMax - yMin + 3;
    int C = xMax - xMin + 3;

    char[][] grid = new char[R][C];

    for (char[] l : grid)
      Arrays.fill(l, '#');

    for (char[] l : grid)
      System.out.println(Arrays.toString(l));
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
