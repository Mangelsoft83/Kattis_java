import java.io.IOException;
import java.io.InputStream;

public class fouledfiles {

  public static void main(String[] args) throws IOException {
    FastReaderff in = new FastReaderff();

    int N = Integer.parseInt(in.readLine());

    for (int i = 0; i < N; i++) {

      char[] line = in.readLine().toCharArray();

      StringBuilder sb = new StringBuilder();

      int L = Math.min(line.length, 80);

      for (int l = 0; l < L; l = l + 2) {
        sb.append(line[l]);
      }

      System.out.println(sb.toString());

    }

  }

}

class FastReaderff {
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
