import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class deduplicatingfiles {

  public static void main(String[] args) throws IOException {
    FastReader fr = new FastReader();

    int N = Integer.parseInt(fr.readLine());

    while (N != 0) {
      List<String> files = new ArrayList<>();

      for (int i = 0; i < N; i++) {
        files.add(fr.readLine());
      }

      byte[] hashes = new byte[N];
      for (int i = 0; i < N; i++) {
        hashes[i] = hash(files.get(i));
      }
      int collisions = 0;
      int unique = new HashSet<>(files).size();

      for (int sourceFile = 0; sourceFile < N - 1; sourceFile++) {
        for (int destinationFile = sourceFile + 1; destinationFile < N; destinationFile++) {
          byte sourceHash = hashes[sourceFile]; 
          byte destinationHash = hashes[destinationFile];
          if (sourceHash == destinationHash &&
              !isSame(files.get(sourceFile), files.get(destinationFile))) {

            collisions++;
          }

        }
      }

      System.out.println(unique + " " + collisions);
      N = Integer.parseInt(fr.readLine());
    }
  }

  private static boolean isSame(String s1, String s2) {
    byte[] ba1 = s1.getBytes();
    byte[] ba2 = s2.getBytes();

    if (ba1.length != ba2.length)
      return false;

    for (int i = 0; i < ba2.length; i++) {
      if (ba1[i] != ba2[i])
        return false;
    }

    return true;
  }

  public static byte hash(String text) {
    byte hash = 0;

    for (byte b : text.getBytes(StandardCharsets.UTF_8)) {
      hash ^= b;
    }

    return hash;
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
