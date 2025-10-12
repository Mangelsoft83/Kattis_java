import java.io.IOException;
import java.io.InputStream;

public class moreorless {

  public static void main(String[] args) throws IOException {
    final FastReader fr = new FastReader();

    String line;

    while ((line = fr.readLine()) != null) {
      if (line.isEmpty() || proces(line)) {
        break;
      }

    }
  }

  static private boolean proces(String line) {
    String[] split = line.split(" ");

    int i1 = Integer.parseInt(split[0]);
    int i2 = Integer.parseInt(split[1]);

    if (i1 > i2) {
      System.out.println("More");
      return false;
    }
    if (i1 < i2) {
      System.out.println("Less");
      return false;
    }
    return true;

  }

}

class FastReader {
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
