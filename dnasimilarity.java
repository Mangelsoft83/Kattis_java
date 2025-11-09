//LCS Longest common subsequence

import java.io.IOException;
import java.io.InputStream;

public class dnasimilarity {

  public static void main(String[] args) throws IOException {
    FastReaderdna in = new FastReaderdna();

    int N = Integer.parseInt(in.readLine());

    for (int a = 0; a < N; a++) {
      String seq1 = in.readLine();
      String seq2 = in.readLine();

      int n = seq1.length();
      int m = seq2.length();
      int[][] dp = new int[n + 1][m + 1];

      // Build the DP table
      for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
          if (seq1.charAt(i - 1) == seq2.charAt(j - 1))
            dp[i][j] = dp[i - 1][j - 1] + 1;
          else
            dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }

      System.out.println(dp[n][m]);
    }

  }
}

class FastReaderdna {
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
