import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map.Entry;

public class addingwords {
  private static HashMap<String, Integer> map = new HashMap<>();

  public static void main(String[] args) throws IOException {
    FastReaderAddingWords in = new FastReaderAddingWords();

    String line;
    while ((line = in.readLine()) != null) {

      String[] split = line.split(" ");
      String cmd = split[0];

      if (cmd.equals("def")) {
        String key = split[1];
        int value = Integer.parseInt(split[2]);
        map.put(key, value);
      } else if (cmd.equals("clear")) {
        map.clear();
      } else if (cmd.equals("calc")) {
        int sum = 0;
        StringBuilder sb = new StringBuilder();
        boolean isOk = true;
        Integer val = 0;
        String opp = "+";

        for (int i = 1; i < split.length; i++) {
          sb.append(split[i]).append(" ");

          if (!isOk)
            continue;

          boolean odd = (i % 2) == 1;

          if (odd) { // get value

            String key = split[i];
            val = map.get(key);
            if (val == null) {
              isOk = false;
              continue;
            }

            if (opp.equals("+"))
              sum += val;
            if (opp.equals("-"))
              sum -= val;

          } else { // do calculation
            opp = split[i];
          }

        }

        if (isOk) { // look for the var name
          String answer = "unknown";

          for (Entry<String, Integer> e : map.entrySet()) {
            if (sum == e.getValue()) {
              answer = e.getKey();
              break;
            }
          }

          sb.append(answer);

        } else {
          sb.append("unknown");
        }

        System.out.println(sb.toString());

      }
    }
  }

}

class FastReaderAddingWords {
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
