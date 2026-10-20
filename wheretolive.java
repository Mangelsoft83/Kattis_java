import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class wheretolive {

  public static void main(String[] args) throws IOException {
    FastReader fr = new FastReader();
    int N = Integer.parseInt(fr.readLine());

    while (true) {
      List<int[]> coordinates = new ArrayList<>();

      for (int n = 0; n < N; n++) {
        String[] split = fr.readLine().split(" ");
        int[] coordinate = new int[2];
        coordinate[0] = Integer.parseInt(split[0]);
        coordinate[1] = Integer.parseInt(split[1]);
        coordinates.add(coordinate);
      }

      int avgX = 0;
      int avgY = 0;
      for (int[] coordinate : coordinates) {
        avgX += coordinate[0];
        avgY += coordinate[1];
      }

      int x = (int) Math.ceil(((double) avgX / N) - 0.5);
      int y = (int) Math.ceil(((double) avgY / N) - 0.5);


      if (N == 0)
        break;
      System.out.println(x + " " + y);
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
