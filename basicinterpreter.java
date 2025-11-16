import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class basicinterpreter {

  static HashMap<String, Integer> vars = new HashMap<>();
  static HashMap<Integer, Integer> lNum = new HashMap<>();

  public static void main(String[] args) throws IOException {

    List<Line> lines = new ArrayList<>();
    FastReader in = new FastReader();

    String l;
    while ((l = in.readLine()) != null) {
      lines.add(new Line(l));
    }
    lines.sort((a, b) -> Integer.compare(a.n, b.n));

    int ind = 0;
    for (Line line : lines) {
      lNum.put(line.n, ind++);
      // System.out.println(line.line);
    }

    // System.out.println(lNum.toString());

    int count = 0;
    int N = lines.size();
    ind = 0;
    while (ind < N) {
      if (count > 50)
        break;
      count++;
      // System.out.println("ff " + ind);
      Line line = lines.get(ind);

      ind++;
      switch (line.cmd) {
        case "PRINT":
          System.out.print(getStringToPrint(line));
          break;

        case "PRINTLN":
          System.out.println(getStringToPrint(line));
          break;

        case "LET":
          String varName = line.split[2].trim();
          vars.put(varName, formula(line.split));
          // System.out.println(vars.toString());
          break;

        case "IF":
          if (testCondition(line.split)) {
            int gotoLine = vars.getOrDefault(line.split[7], Integer.parseInt(line.split[7]));
            ind = lNum.get(gotoLine);
            // System.out.println("Jump " + gotoLine + "index: " + ind);
          }
          break;

        default:
          // optional: handle unknown commands
          System.err.println("Unknown command: " + line.cmd);
      }

    }
  }

  private static String getStringToPrint(Line line) {
    if (line.line.contains("\"")) {
      String[] split = line.line.split("\"");

      return split[split.length - 1];
    } else {

      String[] split = line.line.split(" ");
      String key = split[split.length - 1];
      return "" + vars.get(key);
    }
  }

  private static boolean testCondition(String[] line) {
    int n = line.length;
    String A = line[n - 6].trim();
    String opp = line[n - 5].trim();
    String B = line[n - 4].trim();

    // System.out.println(vars.toString());
    // System.out.println("aaa: " + A + " " + opp + " " + B + " " + vars.get(A));

    Integer a = vars.get(A);
    Integer b = vars.get(B);
    if (a == null)
      a = Integer.parseInt(A);
    if (b == null)
      b = Integer.parseInt(B);

    // System.out.println(a + " " + opp + " " + b);
    switch (opp) {
      case "=" -> {
        return a == b;
      }
      case "<" -> {
        return a < b;
      }
      case ">" -> {
        return a > b;
      }
      case "<>" -> {
        return a != b;
      }
      case ">=" -> {
        return a >= b;
      }
      case "<=" -> {
        return a <= b;
      }
      default -> System.out.println("Error");
    }

    return false;
  }

  private static Integer formula(String[] line) {

    int result = 0;
    // System.out.println(Arrays.toString(line));
    String opp = line[line.length - 2].trim();
    // System.out.println("opp: " + opp);
    String key = line[line.length - 1];
    Integer value = vars.get(key);
    if (value == null)
      value = Integer.parseInt(key);
    // System.out.println(" " + opp + " ");
    switch (opp) {
      case "=" -> {
        return value;
      }
      case "+" -> {
        String string = line[line.length - 3];
        Integer a = vars.get(string);
        if (a == null)
          a = Integer.parseInt(string);
        return a + value;
      }
      case "-" -> {
        String string = line[line.length - 3];
        Integer a = vars.get(string);
        if (a == null)
          a = Integer.parseInt(string);
        return a - value;
      }
      case "*" -> {
        String string = line[line.length - 3];
        Integer a = vars.get(string);
        if (a == null)
          a = Integer.parseInt(string);
        return a * value;
      }
      case "/" -> {
        String string = line[line.length - 3];
        Integer a = vars.get(string);
        if (a == null)
          a = Integer.parseInt(string);
        return a / value;
      }
    }

    return result;
  }

  private final static class Line {
    int n;
    String cmd;
    String[] split;
    String line;

    public Line(String s) {
      String[] split = s.split(" ");
      for (int i = 0; i < split.length; i++) {
        split[i] = split[i].trim();
      }
      this.n = Integer.parseInt(split[0]);
      this.cmd = split[1];
      this.split = split;
      this.line = s;
    }
  }

  private static class FastReader {
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

}
