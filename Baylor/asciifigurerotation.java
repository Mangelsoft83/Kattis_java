
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class asciifigurerotation {

  static HashMap<Character, Character> dict = new HashMap<>();

  public static void main(String[] args) throws IOException {

    dict.put('|', '-');
    dict.put('-', '|');
    dict.put('+', '+');
    dict.put(' ', ' ');

    FastReader fr = new FastReader();

    String line;
    boolean first = true;
    while ((line = fr.readLine()) != null) {
      int n = Integer.parseInt(line);

      if (line == null || n == 0)
        break;

      List<String> fig = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        fig.add(fr.readLine());
      }
      if (!first)
        System.out.println();

      proces(fig);
      first = false;

    }

  }

  private static void proces(List<String> fig) {
    for (String string : fig) {
      // System.out.println(string);
    }
    int y = 0;
    for (String string : fig)
      y = Math.max(string.length(), y);

    for (int i = 0; i < y; i++) {

      StringBuilder sb = new StringBuilder();

      for (int j = fig.size() - 1; j >= 0; j--) {
        String line = fig.get(j);
        char c = i >= line.length() ? ' ' : line.charAt(i);
        char newChar = dict.get(c);
        sb.append(newChar);

      }

      String l = trim(sb.toString());
      System.out.println(l);
    }

  }

  static String trim(String str) {
    int len = str.length();
    int st = 0;

    char[] val = str.toCharArray();

    while ((st < len) && (val[len - 1] <= ' ')) {
      len--;
    }
    return str.substring(st, len);
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
