import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class raceday {

  public static void main(String[] args) throws IOException {
    FastReaderRd in = new FastReaderRd();
    boolean first = true;
    String l;
    int N = 0;
    while ((l = in.readLine()) != null) {
      if (!first)
        System.out.println();
      else
        N = Integer.parseInt(l);

      List<runner> runnerList = new ArrayList<>();
      for (int i = 0; i < N; i++) {
        String[] split = in.readLine().split(" ");
        runnerList.add(new runner(split[0], split[1], split[2]));
      }

      String[] split;
      while ((split = in.readLine().split(" ")).length == 3) {
        String bib = split[0];
        String location = split[1];
        String time = split[2];

        runner r = null;
        for (runner rr : runnerList) {
          if (rr.getBib().equals(bib)) {
            r = rr;
            break;
          }
        }

        if (location.equals("S1")) {
          r.setSplit1(time);
        } else if (location.equals("S2")) {
          r.setSplit2(time);
        } else if (location.equals("F")) {
          r.setFinal(time);
        }

      }

      runnerList.sort(Comparator.comparing(runner::getName));

      for (runner r : runnerList) {
        System.out.printf(
            "%-25s %-6s %10s%n",
            r.getName(), r.getBib(), r.getSplit1());

        // System.out.printf(
        // "%-25s %-6s %10s %8d %10s %8d %10s %8d%n",
        // r.name, r.bib, r.split1, r.rank1, r.split2, r.rank2, r.finish, r.rank3);
      }

      N = Integer.parseInt(split[0]);

      first = false;
    }

  }

}

class runner {
  String firstName, lastName, bib;
  int s1, s2, fin;

  public runner(String firstName, String lastName, String bib) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.bib = bib;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  private String getTimeString(int time) {
    return "" + time / 100 + ":" + time % 100;

  }

  public String getSplit1() {
    return getTimeString(s1);
  }

  public String getSplit2() {
    return getTimeString(s2);
  }

  public String getFinal() {
    return getTimeString(fin);
  }

  public void setSplit1(String time) {
    String[] split = time.split(":");
    this.s1 = 100 * Integer.parseInt(split[0]) + Integer.parseInt(split[1]);
  }

  public void setFinal(String time) {
    String[] split = time.split(":");
    this.fin = 100 * Integer.parseInt(split[0]) + Integer.parseInt(split[1]);
  }

  public void setSplit2(String time) {
    String[] split = time.split(":");
    this.s2 = 100 * Integer.parseInt(split[0]) + Integer.parseInt(split[1]);
  }

  public String getBib() {
    return bib;
  }

  public void setBib(String bib) {
    this.bib = bib;
  }

  public String getName() {
    return lastName + ", " + firstName;
  }

}

class FastReaderRd {
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
