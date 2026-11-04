import java.util.*;
import java.io.IOException;
import java.io.InputStream;

public class loworderzeros {

  // https://open.kattis.com/problems/loworderzeros

  public static void main(String[] args) throws IOException {
    FastReader fr = new FastReader();

    while (true) {

      int N = Integer.parseInt(fr.readLine());
      if (N == 0)
        break;

      System.out.println(lastNonZero(N));
    }

  }

  public static int lastNonZero(int n) {
    if (n < 5) {
      return new int[] { 1, 1, 2, 6, 4 }[n];
    }

    int[] last = { 1, 1, 2, 6, 4 };

    int result = lastNonZero(n / 5);
    result = result * last[n % 5] % 10;

    int[] pow2 = { 6, 2, 4, 8 };
    result = result * pow2[(n / 5) % 4] % 10;

    return result;
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
