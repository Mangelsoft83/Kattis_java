
//Palindrome 
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class palindromesubstring {

  public static void main(String[] args) throws IOException {

    FastReaderPathPd in = new FastReaderPathPd();
    Boolean first = true;

    String s;
    while ((s = in.readLine()) != null) {
      if (!first)
        System.out.println();

      HashSet<String> result = new HashSet<>(); // use Set to avoid duplicates
      int n = s.length();

      for (int center = 0; center < 2 * n - 1; center++) {
        int left = center / 2;
        int right = left + (center % 2);

        while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
          // substring is a palindrome
          result.add(s.substring(left, right + 1));
          left--;
          right++;
        }
      }

      List<String> list = new ArrayList<>(result);
      Collections.sort(list);

      for (String str : list) {
        if (str.length() >= 2)
          System.out.println(str);
      }

      first = false;

    }

  }
}

class FastReaderPathPd {
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
