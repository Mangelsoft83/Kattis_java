import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class roundedbuttons {

  public static void main(String[] args) throws IOException {
    FastReaderRb in = new FastReaderRb();

    int N = Integer.parseInt(in.readLine());

    for (int n = 0; n < N; n++) {
      String[] chars = in.readLine().split(" ");
      double[] doubles = new double[chars.length];

      for (int i = 0; i < chars.length; i++) {
        doubles[i] = Double.parseDouble(chars[i]); // convert char digit to number
      }

      double x = doubles[0];
      double y = doubles[1];
      double w = doubles[2];
      double h = doubles[3];
      double r = doubles[4];

      // System.out.println(x + " " + y + " " + w + " " + h + " " + r);

      int M = (int) doubles[5];
      // System.out.println(M);

      for (int m = 0; m < M; m++) {
        double px = doubles[6 + 2 * m];
        double py = doubles[7 + 2 * m];

        System.out.println(isInside(x, y, w, h, r, px, py) ? "inside" : "outside");
        // System.out.println("[" + px + "][" + py + "]");

      }

    }

  }

  private static boolean isInside(double x, double y, double w, double h, double r, double px, double py) {
    if (!insideRectange(x, y, w, h, px, py))
      return false;

    return insideRectange(x + r, y, w - 2 * r, h, px, py) ||
        insideRectange(x, y + r, w, h - 2 * r, px, py) ||
        insideCircle(x + r, y + r, r, px, py) ||
        insideCircle(x + w - r, y + r, r, px, py) ||
        insideCircle(x + w - r, y + h - r, r, px, py) ||
        insideCircle(x + r, y + h - r, r, px, py);

  }

  private static boolean insideRectange(double x, double y, double w, double h, double px, double py) {
    return px >= x && px <= x + w && py >= y && py <= y + h;
  }

  private static boolean insideCircle(double x, double y, double r, double px, double py) {
    double dx = x - px;
    double dy = y - py;
    return dx * dx + dy * dy <= r * r;
  }

}

class FastReaderRb {
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
