import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class rectsect {
  static HashMap<Integer, String> values2dict = new HashMap<>();
  static HashMap<String, Integer> dict2values = new HashMap<>();

  public static void main(String[] args) throws IOException {
    FastReader fr = new FastReader();

    int c = Integer.parseInt(fr.readLine());
    for (int i = 0; i < c; i++) {
      int N = Integer.parseInt(fr.readLine());

      List<int[]> rects = new ArrayList<>();

      for (int n = 0; n < N; n++) {
        String[] split = fr.readLine().split(" ");
        int[] rect = new int[4];
        for (int j = 0; j < rect.length; j++) {
          rect[j] = Integer.parseInt(split[j]);
        }
        rects.add(rect);
      }

      System.out.println(intersectionArea(rects));
    }

  }

  static int intersectionArea(List<int[]> rectangles) {
    int left = Integer.MIN_VALUE;
    int top = Integer.MAX_VALUE;
    int right = Integer.MAX_VALUE;
    int bottom = Integer.MIN_VALUE;

    for (int[] r : rectangles) {
      left = Math.max(left, r[0]);
      top = Math.min(top, r[1]);
      right = Math.min(right, r[2]);
      bottom = Math.max(bottom, r[3]);
    }

    int width = Math.max(0, right - left);
    int height = Math.max(0, top - bottom);

    return width * height;
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
