import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

public class prettygoodcuberoot {

  public static void main(String[] args) throws IOException {
    FastReader fr = new FastReader();

    String in = fr.readLine();

    while (in != null) {

      System.out.println(cubeRoot(in));

      in = fr.readLine();
    }

  }

  public static BigInteger cubeRoot(String s) {

    BigInteger n = new BigInteger(s);
    if (n.signum() < 0) {
      return cubeRoot(n.negate().negate().toString());
    }

    BigInteger low = BigInteger.ZERO;
    BigInteger high = BigInteger.ONE.shiftLeft(
        (n.bitLength() + 2) / 3 + 1);

    while (low.compareTo(high) < 0) {
      BigInteger mid = low.add(high)
          .add(BigInteger.ONE).shiftRight(1);

      if (mid.pow(3).compareTo(n) <= 0) {
        low = mid;
      } else {
        high = mid.subtract(BigInteger.ONE);
      }
    }
    BigInteger boundary = low.shiftLeft(1)
        .add(BigInteger.ONE).pow(3);

    if (n.shiftLeft(3).compareTo(boundary) >= 0) {
      return low.add(BigInteger.ONE);
    }

    return low;
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
