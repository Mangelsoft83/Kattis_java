import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class officespace {
  public static void main(String[] args) throws IOException {

    FastReaderOf in = new FastReaderOf();

    String line;

    while ((line = in.readLine()) != null) {
      String[] sizeArray = line.split(" ");
      int W = Integer.parseInt(sizeArray[0]);
      int H = Integer.parseInt(sizeArray[1]);

      int N = Integer.parseInt(in.readLine());

      List<int[]> reqPos = new ArrayList<>();
      List<String> reqNames = new ArrayList<>();

      reqNames.add("UnAllocated");
      reqNames.add("Contested");

      reqPos.add(new int[] { -1, -1, -1, -1 });
      reqPos.add(new int[] { -1, -1, -1, -1 });

      for (int i = 0; i < N; i++) {
        String[] a = in.readLine().split(" ");
        reqNames.add(a[0]);
        int[] req = new int[4];
        for (int j = 1; j < 5; j++) {
          req[j - 1] = Integer.parseInt(a[j]);
        }
        reqPos.add(req);
      }

      // for (int i = 0; i < N; i++) {
      // System.out.println(reqNames.get(i) + " " + Arrays.toString(reqPos.get(i)));
      // }
      //
      int[][] grid = new int[W][H];
      for (int i = 2; i < N + 2; i++) {
        int[] req = reqPos.get(i);
        for (int r = req[1]; r < req[3]; r++) {
          for (int c = req[0]; c < req[2]; c++) {
            grid[c][r] = grid[c][r] == 0 ? i : 1;
          }
        }
      }

      // for (int[] l : grid) {
      // System.out.println(Arrays.toString(l));
      // }

      System.out.println("Total " + W * H);

      int[] count = new int[reqNames.size()];
      for (int c = 0; c < W; c++) {
        for (int r = 0; r < H; r++) {
          int index = grid[c][r];
          count[index]++;
        }
      }

      for (int i = 0; i < reqNames.size(); i++) {
        System.out.println(reqNames.get(i) + " " + count[i]);
      }
      System.out.println();
    }
  }
}

class FastReaderOf {
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
