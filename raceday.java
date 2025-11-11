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
      List<runner> runnerList = new ArrayList<>();
      int start = 0;
      if (!first) {
        System.out.println();
        String[] split = l.split(" ");
        runnerList.add(new runner(split[0], split[1], split[2]));
        start = 1;
      } else
        N = Integer.parseInt(l);

      // System.out.println(N);
      for (int i = start; i < N; i++) {
        String[] split = in.readLine().split(" ");
        runnerList.add(new runner(split[0], split[1], split[2]));
      }

      String[] split;
      while ((split = in.readLine().split(" ")).length == 3) {
        String bib = split[0];
        String location = split[1];
        String time = split[2];

        // System.out.println(Arrays.toString(split));
        runner r = null;
        for (runner rr : runnerList) {
          if (rr.bib.equals(bib)) {
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

      // Sorting
      runnerList.sort(Comparator.comparing(runner::getName));

      List<runner> s1List = new ArrayList<>(runnerList);
      s1List.sort((a, b) -> Integer.compare(a.s1, b.s1));

      for (int i = 0; i < s1List.size(); i++) {
        runner r = s1List.get(i);
        r.rankS1 = 1 + i;
      }

      int tempTime = -1;
      int tempRank = -1;
      for (runner r : s1List) {
        if (r.s1 == tempTime)
          r.rankS1 = tempRank;
        tempTime = r.s1;
        tempRank = r.rankS1;
      }

      List<runner> s2List = new ArrayList<>(runnerList);
      s2List.sort((a, b) -> Integer.compare(a.s2, b.s2));

      for (int i = 0; i < s2List.size(); i++) {
        runner r = s2List.get(i);
        r.rankS2 = 1 + i;
      }

      tempTime = -1;
      tempRank = -1;
      for (runner r : s2List) {
        if (r.s2 == tempTime)
          r.rankS2 = tempRank;
        tempTime = r.s2;
        tempRank = r.rankS2;
      }

      List<runner> finList = new ArrayList<>(runnerList);
      finList.sort((a, b) -> Integer.compare(a.fin, b.fin));

      for (int i = 0; i < finList.size(); i++) {
        runner r = finList.get(i);
        r.rankFin = 1 + i;
      }

      tempTime = -1;
      tempRank = -1;
      for (runner r : finList) {
        if (r.fin == tempTime)
          r.rankFin = tempRank;
        tempTime = r.fin;
        tempRank = r.rankFin;
      }

      // printing
      String fmt = "%-25s %4s %9s %9s %9s %9s %9s %9s%n";
      String fmt2 = "%-24s %4s %9s %9s %9s %9s %9s %9s%n";
      System.out.printf(fmt, "NAME", "BIB", "SPLIT1", "RANK", "SPLIT2", "RANK", "FINISH", "RANK");

      for (runner r : runnerList) {
        System.out.printf(
            fmt2,
            r.getName(), r.bib, r.getSplit1(), r.rankS1, r.getSplit2(), r.rankS2, r.getFinal(), r.rankFin);

        // System.out.printf(
        // "%-25s %-6s %10s %8d %10s %8d %10s %8d%n",
        // r.name, r.bib, r.split1, r.rank1, r.split2, r.rank2, r.finish, r.rank3);
      }

      N = Integer.parseInt(split[0]);

      first = false;
    }
    System.out.println();

  }

}

class runner {
  String firstName, lastName, bib;
  int s1, s2, fin, rankS1, rankS2, rankFin;

  public runner(String firstName, String lastName, String bib) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.bib = bib;
  }

  private String getTimeString(int time) {
    int seconds = time % 100;
    int minutes = time / 100;
    return String.format("%02d:%02d", minutes, seconds);

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
