import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class connectthedots {
  public static void main(final String[] args) throws IOException {
    final FastReaderConnectTheDots fr = new FastReaderConnectTheDots();

    final List<String> values = new ArrayList<>();
    String line;

    while ((line = fr.readLine()) != null) {
      if (line.isEmpty()) {
        process(values);
        System.out.println();
        values.clear();
      } else {
        values.add(line);
      }
    }

    if (!values.isEmpty()) {
      process(values);
    }
  }

  /**
   * @param values
   */
  public static void process(final List<String> values) {
    final char[][] grid = new char[values.size()][values.getFirst().length()];
    final Map<Character, int[]> cords = new HashMap<>();

    for (int y = 0; y < grid.length; y++) {
      final char[] line = values.get(y).toCharArray();
      for (int x = 0; x < line.length; x++) {
        grid[y][x] = line[x];

        if (grid[y][x] != '.')
          cords.put(line[x], new int[] { x, y });
      }
    }

    final String seq = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    for (int i = 1; i < seq.length(); i++) {
      final int[] c1 = cords.get(seq.charAt(i - 1));
      final int[] c2 = cords.get(seq.charAt(i));

      if (c2 == null)
        break;

      if (c1[0] < c2[0]) {// RIGHT
        int x = c1[0];
        final int y = c1[1];
        while (x < c2[0]) {
          if (grid[y][x] == '.')
            grid[y][x] = '-';
          else if (grid[y][x] == '|')
            grid[y][x] = '+';
          x++;
        }
      } else if (c1[0] > c2[0]) {// LEFT
        int x = c1[0];
        final int y = c1[1];

        while (x > c2[0]) {
          if (grid[y][x] == '.')
            grid[y][x] = '-';
          else if (grid[y][x] == '|')
            grid[y][x] = '+';
          x--;
        }
      } else if (c1[1] < c2[1]) {// DOWN
        final int x = c1[0];
        int y = c1[1];

        while (y < c2[1]) {
          if (grid[y][x] == '.')
            grid[y][x] = '|';
          else if (grid[y][x] == '-')
            grid[y][x] = '+';
          y++;
        }
      } else if (c1[1] > c2[1]) {// UP
        final int x = c1[0];
        int y = c1[1];

        while (y > c2[1]) {
          if (grid[y][x] == '.')
            grid[y][x] = '|';
          else if (grid[y][x] == '-')
            grid[y][x] = '+';
          y--;
        }
      }
    }
    //
    // Print
    for (final char[] row : grid) {
      for (final char c : row) {
        System.out.print(c);
      }
      System.out.println();
    }
  }

}

class FastReaderConnectTheDots {
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
