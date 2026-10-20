import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

public class checkingforcorrectness {

  public static void main(String[] args) throws IOException {
    FastReader fr = new FastReader();

    String in = fr.readLine();

    while (in != null) {
      String[] split = in.split(" ");

      BigInteger n1 = new BigInteger(split[0]);
      BigInteger n2 = new BigInteger(split[2]);
      String opp = split[1];

      BigInteger value = null;

      switch (opp) {
        case "+" -> {
          value = n1.add(n2);

        }
        case "*" -> {

          value = n1.multiply(n2);

        }
        case "^" -> {
          value =  BigInteger.valueOf(Long.parseLong(split[0]))
              .modPow(BigInteger.valueOf(Long.parseLong(split[2])), BigInteger.valueOf(10000));

        }
      }

      String number = value.toString();
      String last4 = number.substring(Math.max(0, number.length() - 4));
      String result = String.valueOf(Integer.parseInt(last4));
      System.out.println(result);

      in = fr.readLine();
      if (in == null)
        break;
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
