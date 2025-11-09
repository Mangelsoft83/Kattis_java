
//Convex hull experiment
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class dartscoring {

  static final class Pt {
    final double x, y;

    Pt(double x, double y) {
      this.x = x;
      this.y = y;
    }
  }

  public static void main(String[] args) throws IOException {

    FastReaderPathDs in = new FastReaderPathDs();

    String l;
    while ((l = in.readLine()) != null) {
      String[] split = l.split(" ");

      double[] values = new double[split.length];

      for (int i = 0; i < values.length; i++) {
        values[i] = Double.parseDouble(split[i]);
      }

      Pt[] points = new Pt[values.length / 2];

      // System.out.println(l);
      for (int i = 0; i < values.length; i++) {
        if (i % 2 == 0)
          continue;
        // System.out.println("[" + values[i - 1] + " , " + values[i] + "]");

        points[i / 2] = new Pt(values[i - 1], values[i]);

      }

      Arrays.sort(points, (p1, p2) -> {
        if (p1.x != p2.x)
          return Double.compare(p1.x, p2.x);
        else
          return Double.compare(p1.y, p2.y);
      });

      // for (Pt p : points) {
      // System.out.println("[" + p.x + " , " + p.y + "]");
      // }
      //
      // build circumference
      // System.out.println("Full hull");
      List<dartscoring.Pt> convexHull = convexHull(points);

      // for (Pt p : convexHull) {
      // System.out.println("[" + p.x + " , " + p.y + "]");
      // }
      //
      double s = 0;

      int n = points.length;

      for (int i = 0; i < convexHull.size(); i++) {
        int j = (i + 1) % convexHull.size();
        Pt p1 = convexHull.get(j);
        Pt p2 = convexHull.get(i);

        s += dist(p1, p2);
      }

      double d = 100 * n / (s + 1);
      System.out.printf(Locale.US, "%.10f%n", d);

    }

  }

  static double cross(Pt o, Pt a, Pt b) {
    return (a.x - o.x) * (b.y - o.y) - (a.y - o.y) * (b.x - o.x);
  }

  static double dist(Pt a, Pt b) {
    double dx = a.x - b.x, dy = a.y - b.y;
    return Math.hypot(dx, dy);
  }

  private static List<Pt> convexHull(Pt[] points) {

    if (points.length <= 1)
      return List.of(points);

    // Clockwise scan
    List<Pt> cw = new ArrayList<>();
    for (int i = 0; i < points.length; i++) {
      Pt p = points[i];

      while (cw.size() >= 2
          && cross(cw.get(cw.size() - 2), cw.get(cw.size() - 1), p) <= 0) {
        cw.removeLast();
      }
      cw.add(p);

    }

    // Counter Clockwise scan
    List<Pt> ccw = new ArrayList<>();
    for (int i = points.length - 1; i >= 0; i--) {
      Pt p = points[i];

      while (ccw.size() >= 2
          && cross(ccw.get(ccw.size() - 2), ccw.get(ccw.size() - 1), p) <= 0) {
        ccw.removeLast();
      }
      ccw.add(p);

    }

    cw.removeLast();
    ccw.removeLast();

    cw.addAll(ccw);
    return cw;
  }
}

class FastReaderPathDs {
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
