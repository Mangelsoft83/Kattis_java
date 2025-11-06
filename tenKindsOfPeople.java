import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class tenKindsOfPeople {
  public static void main(String[] args) throws IOException {

    FastReaderTena in = new FastReaderTena();

    String[] rc_split = in.readLine().split(" ");
    int R = Integer.parseInt(rc_split[0]);
    int C = Integer.parseInt(rc_split[1]);

    boolean[][] grid = new boolean[R][C];

    for (int r = 0; r < R; r++) {
      char[] line = in.readLine().toCharArray();
      for (int c = 0; c < C; c++) {
        grid[r][c] = line[c] == '1';
      }
    }

    int[] dr = new int[] { 0, 0, 1, -1 };
    int[] dc = new int[] { 1, -1, 0, 0 };

    int id = 0;

    int[][] map = new int[R][C];
    for (int[] row : map)
      Arrays.fill(row, -1);

    List<Boolean> typeList = new ArrayList<>();

    for (int r = 0; r < R; r++) {
      for (int c = 0; c < C; c++) {

        if (map[r][c] != -1)
          continue;

        boolean sign = grid[r][c];

        typeList.add(sign);

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[] { r, c });

        map[r][c] = id;

        while (!q.isEmpty()) {
          int[] act = q.pollFirst();

          for (int i = 0; i < 4; i++) {

            int newR = act[0] + dr[i];
            int newC = act[1] + dc[i];

            if (newR < 0 || newR >= R || newC < 0 || newC >= C || grid[newR][newC] != sign)
              continue;

            if (map[newR][newC] != -1)
              continue;

            q.addLast(new int[] { newR, newC });
            map[newR][newC] = id;
          }
        }

        id++;

      }
    }

    int N = Integer.parseInt(in.readLine());

    for (int n = 0; n < N; n++) {
      String[] coordSplit = in.readLine().split(" ");
      int r1 = Integer.parseInt(coordSplit[0]) - 1;
      int c1 = Integer.parseInt(coordSplit[1]) - 1;
      int r2 = Integer.parseInt(coordSplit[2]) - 1;
      int c2 = Integer.parseInt(coordSplit[3]) - 1;

      if (map[r1][c1] == map[r2][c2]) {
        boolean type = typeList.get(map[r1][c1]);
        System.out.println(type ? "decimal" : "");
      } else
        System.out.println("neither");

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
