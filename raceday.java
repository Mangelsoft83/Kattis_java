import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class raceday {

  public static void main(String[] args) throws IOException {
    FastReader fr = new FastReader();
    class Runner {
      String firstName;
      String lastName;
      String id;
      String split1;
      int s1;
      int s2;
      int fin;
      String rank1;
      String split2;
      String rank2;
      String finish;
      String rank3;
    }

    int N = Integer.parseInt(fr.readLine());

    while (true) {

      Map<String, Runner> map = new HashMap<>(2 * N);

      // read runners
      for (int n = 0; n < N; n++) {
        String[] split = fr.readLine().split(" ");
        Runner r = new Runner();
        r.firstName = split[0];
        r.lastName = split[1];
        r.id = split[2];

        map.put(split[2], r);
      }

      // read rounds
      for (int n = 0; n < N * 3; n++) {

        String[] split = fr.readLine().split(" ");
        String id = split[0];
        String pos = split[1];
        String time = split[2];

        Runner r = map.get(id);

        switch (pos) {
          case "S1" -> {
            r.split1 = time;
          }
          case "S2" -> {
            r.split2 = time;
          }
          case "F" -> {
            r.finish = time;
          }
        }
      }

      // Ranking

      List<Runner> runnerList = new ArrayList<>(map.values());

      for (Runner r : runnerList) {
        String[] splitS1 = r.split1.split(":");
        String[] splitS2 = r.split2.split(":");
        String[] splitFin = r.finish.split(":");

        r.s1 = Integer.parseInt(splitS1[0]) * 100 + Integer.parseInt(splitS1[1]);
        r.s2 = Integer.parseInt(splitS2[0]) * 100 + Integer.parseInt(splitS2[1]);
        r.fin = Integer.parseInt(splitFin[0]) * 100 + Integer.parseInt(splitFin[1]);
      }

      List<Runner> sorted = new ArrayList<>(runnerList);
      sorted.sort(Comparator.comparing((Runner r) -> r.s1));

      int last = 0;
      for (int n = 0; n < N; n++) {
        int time = sorted.get(n).s1;

        if (time == last)
          sorted.get(n).rank1 = "" + n;
        else
          sorted.get(n).rank1 = "" + (n + 1);

        last = time;
      }

      sorted.sort(Comparator.comparing((Runner r) -> r.s2));

      last = 0;
      for (int n = 0; n < N; n++) {
        int time = sorted.get(n).s2;

        if (time == last)
          sorted.get(n).rank2 = "" + n;
        else
          sorted.get(n).rank2 = "" + (n + 1);

        last = time;
      }

      sorted.sort(Comparator.comparing((Runner r) -> r.fin));

      last = 0;
      for (int n = 0; n < N; n++) {
        int time = sorted.get(n).fin;

        if (time == last)
          sorted.get(n).rank3 = "" + n;
        else
          sorted.get(n).rank3 = "" + (n + 1);

        last = time;
      }

      runnerList.sort(
          Comparator.comparing((Runner r) -> r.lastName)
              .thenComparing(r -> r.firstName));
      // print
      String format = "%-20s%10s%10s%10s%10s%10s%10s%10s%n";

      System.out.printf(
          format,
          "NAME", "BIB", "SPLIT1", "RANK",
          "SPLIT2", "RANK", "FINISH", "RANK");

      for (Runner r : runnerList) {
        System.out.printf(
            format,
            r.lastName + ", " + r.firstName,
            r.id,
            r.split1,
            r.rank1,
            r.split2,
            r.rank2,
            r.finish,
            r.rank3);
      }

      System.out.println();

      N = Integer.parseInt(fr.readLine());

      if (N == 0) {
        System.out.println();
        break;

      }

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
