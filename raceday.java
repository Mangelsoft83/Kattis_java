import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class raceday {

  public static void main(String[] args) throws IOException {
    FastReader in = new FastReader();

    List<String> input = new ArrayList<>();

    String l;
    while ((l = in.readLine()) != null) {
      input.add(l);
    }

    boolean first = true;

    int idx = 0;

    while (idx < input.size() - 1) {
      List<Runner> runnerList = new ArrayList<>();
      System.out.println(idx + " " + input.size());
      HashMap<String, Runner> byBib = new HashMap<>();
      if (!first) {
        System.out.println();
        // idx--;
      }
      int N = Integer.parseInt(input.get(idx));

      for (int i = 0; i < N; i++) {
        String string = input.get(++idx);
        int p1 = nextSpace(string, 0);
        int p2 = nextSpace(string, p1 + 1);

        String firstName = string.substring(0, p1);
        String last = string.substring(p1 + 1, p2);
        String bib = string.substring(p2 + 1);
        // String[] split = string.split(" ");
        // System.out.println(Arrays.toString(split));
        Runner runner = new Runner(firstName, last, bib);
        runnerList.add(runner);
        byBib.put(bib, runner);
      }

      while (idx < input.size()) {
        String string = input.get(++idx);

        if (string.length() == 1)
          break;

        int p1 = nextSpace(string, 0);
        int p2 = nextSpace(string, p1 + 1);

        String bib = string.substring(0, p1);
        String location = string.substring(p1 + 1, p2);
        String time = string.substring(p2 + 1);

        Runner r = byBib.get(bib);

        if (location.equals("S1")) {
          r.setSplit1(time);
        } else if (location.equals("S2")) {
          r.setSplit2(time);
        } else if (location.equals("F")) {
          r.setFinal(time);
        }

      }

      // Sorting
      runnerList.sort(Comparator.comparing(Runner::getName));

      List<Runner> s1List = new ArrayList<>(runnerList);
      s1List.sort((a, b) -> Integer.compare(a.s1, b.s1));

      for (int i = 0; i < s1List.size(); i++) {
        Runner r = s1List.get(i);
        r.rankS1 = 1 + i;
      }

      int tempTime = -1;
      int tempRank = -1;
      for (Runner r : s1List) {
        if (r.s1 == tempTime)
          r.rankS1 = tempRank;
        tempTime = r.s1;
        tempRank = r.rankS1;
      }

      List<Runner> s2List = new ArrayList<>(runnerList);
      s2List.sort((a, b) -> Integer.compare(a.s2, b.s2));

      for (int i = 0; i < s2List.size(); i++) {
        Runner r = s2List.get(i);
        r.rankS2 = 1 + i;
      }

      tempTime = -1;
      tempRank = -1;
      for (Runner r : s2List) {
        if (r.s2 == tempTime)
          r.rankS2 = tempRank;
        tempTime = r.s2;
        tempRank = r.rankS2;
      }

      List<Runner> finList = new ArrayList<>(runnerList);
      finList.sort((a, b) -> Integer.compare(a.fin, b.fin));

      for (int i = 0; i < finList.size(); i++) {
        Runner r = finList.get(i);
        r.rankFin = 1 + i;
      }

      tempTime = -1;
      tempRank = -1;
      for (Runner r : finList) {
        if (r.fin == tempTime)
          r.rankFin = tempRank;
        tempTime = r.fin;
        tempRank = r.rankFin;
      }

      // printing
      String fmt = "%-25s %4s %9s %9s %9s %9s %9s %9s%n";
      String fmt2 = "%-24s %4s %9s %9s %9s %9s %9s %9s%n";
      System.out.printf(fmt, "NAME", "BIB", "SPLIT1", "RANK", "SPLIT2", "RANK", "FINISH", "RANK");

      for (Runner r : runnerList) {
        System.out.printf(
            fmt2,
            r.getName(), r.bib, r.getSplit1(), r.rankS1, r.getSplit2(), r.rankS2, r.getFinal(), r.rankFin);
      }

      // N = Integer.parseInt(split[0]);

      first = false;
    }
    System.out.println();

  }

  private static int nextSpace(String s, int from) {
    int n = s.length();
    for (int i = from; i < n; i++)
      if (s.charAt(i) == ' ')
        return i;
    return n; // not found (caller expects well-formed input)
  }

  private static int parseMMSS(String s) {
    String[] t = s.split(":");
    int m = Integer.parseInt(t[0]);
    int sec = Integer.parseInt(t[1]);
    return m * 60 + sec; // store as total seconds (correct numeric ordering)
  }

  private static String toMMSS(int totalSeconds) {
    int m = totalSeconds / 60, s = totalSeconds % 60;
    return String.format("%02d:%02d", m, s);
  }

  private static class Runner {
    String firstName, lastName, bib;
    int s1, s2, fin, rankS1, rankS2, rankFin;

    public Runner(String firstName, String lastName, String bib) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.bib = bib;
    }

    public String getSplit1() {
      return toMMSS(s1);
    }

    public String getSplit2() {
      return toMMSS(s2);
    }

    public String getFinal() {
      return toMMSS(fin);
    }

    public void setSplit1(String time) {
      this.s1 = parseMMSS(time);
    }

    public void setFinal(String time) {
      this.fin = parseMMSS(time);
    }

    public void setSplit2(String time) {
      this.s2 = parseMMSS(time);
    }

    public String getName() {
      return lastName + ", " + firstName;
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
