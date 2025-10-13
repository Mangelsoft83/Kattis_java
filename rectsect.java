import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class rectsect {
  static HashMap<Integer, String> values2dict = new HashMap<>();
  static HashMap<String, Integer> dict2values = new HashMap<>();

  public static void main(String[] args) throws IOException {
    FastReaderRectSect fr = new FastReaderRectSect();

    String line;
    while ((line = fr.readLine()) != null) {
      int c = Integer.parseInt(line);

      for (int i = 0; i < c; i++) {
        int N = Integer.parseInt(fr.readLine());

        List<Rectangle> rects = new ArrayList<>();

        for (int n = 0; n < N; n++) {
          String[] split = fr.readLine().split(" ");
          int left = Integer.parseInt(split[0]);
          int top = Integer.parseInt(split[1]);
          int right = Integer.parseInt(split[2]);
          int bottom = Integer.parseInt(split[3]);

          // normalize in case top > bottom
          int x = Math.min(left, right);
          int y = Math.min(top, bottom);
          int width = Math.abs(right - left);
          int height = Math.abs(bottom - top);

          Rectangle r = new Rectangle(x, y, width, height);
          rects.add(r);

        }

        int overlap = 0;

        Rectangle r = rects.getFirst();
        for (int j = 1; j < rects.size(); j++) {
          Rectangle rn = rects.get(j);
          r = r.intersection(rn);
        }

        System.out.println(r.width * r.height);

      }

    }

  }

}

class FastReaderRectSect {
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
