import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class maximizingwinnings {

  public static void main(String[] args) throws IOException {
    FastReader fr = new FastReader();
    int N = Integer.parseInt(fr.readLine());

    while (true) {
      // init

      if (N == 0)
        break;
      // Read matrix
      int[][] matrix = new int[N][N];
      int maxMove = Integer.MAX_VALUE;

      for (int i = 0; i < N; i++) {
        String[] s = fr.readLine().split(" ");

        for (int j = 0; j < N; j++) {
          matrix[i][j] = Integer.parseInt(s[j]);
          maxMove = matrix[i][j] > maxMove ? matrix[i][j] : maxMove;
        }
      }

      int M = Integer.parseInt(fr.readLine());

      int[] max = new int[N];
      int[] min = new int[N];

      // Move 1: start in room 0
      for (int i = 0; i < N; i++) {
        max[i] = matrix[0][i];
        min[i] = matrix[0][i];
      }

      // Moves 2..M
      for (int move = 1; move < M; move++) {

        int[] nextMax = new int[N];
        int[] nextMin = new int[N];

        for (int destination = 0; destination < N; destination++) {

          int maxValue = Integer.MIN_VALUE;
          int minValue = Integer.MAX_VALUE;

          for (int source = 0; source < N; source++) {

            int candidateMax = max[source] + matrix[source][destination];

            int candidateMin = min[source] + matrix[source][destination];

            maxValue = Math.max(maxValue, candidateMax);
            minValue = Math.min(minValue, candidateMin);
          }

          nextMax[destination] = maxValue;
          nextMin[destination] = minValue;
        }

        max = nextMax;
        min = nextMin;
      }

      int ma = Arrays.stream(max).max().getAsInt();
      int mi = Arrays.stream(min).min().getAsInt();

      System.out.println(ma + " " + mi);

      N = Integer.parseInt(fr.readLine());
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
