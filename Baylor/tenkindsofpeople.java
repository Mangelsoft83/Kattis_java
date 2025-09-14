import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;

public class tenkindsofpeople {

  public static void main(String[] args) throws IOException {

    FastReaderTen fr = new FastReaderTen();

    List<String> values = new ArrayList<>();
    String line;

    while ((line = fr.readLine()) != null) {
      values.add(line);
    }

    proces(values);
  }

  private static void proces(List<String> values) {
    // readGrid
    String[] s = values.get(0).split(" ");
    values.removeFirst();

    int r = Integer.parseInt(s[0]);
    int c = Integer.parseInt(s[1]);

    char[][] grid = new char[r][c];

    for (int i = 0; i < r; i++) {
      grid[i] = values.get(0).toCharArray();
      values.removeFirst();
    }

    // parse queries
    int n = Integer.parseInt(values.get(0));
    values.removeFirst();

    for (int i = 0; i < n; i++) {
      int[] query = java.util.Arrays.stream(values.get(i).split(" ")).mapToInt(Integer::parseInt).toArray();

      for (int j = 0; j < query.length; j++) {
        query[j] -= 1;
      }

      char startType = grid[query[1]][query[0]];
      char goalType = grid[query[3]][query[2]];

      if (startType != goalType) {
        System.out.println("neither1");
        printCord(grid, query[0], query[1]);
        System.out.println();
        printCord(grid, query[2], 19);
      } else if (startType == '0' && aStar(r, c, query, grid))
        System.out.println("binary");
      else if (startType == '1' && aStar(r, c, query, grid))
        System.out.println("decimal");
      else {
        System.out.println("neither2");
      }
    }
  }

  private static boolean aStar(int r, int c, int[] query, char[][] grid) {
    int[] start = new int[] { query[0], query[1] };
    int[] goal = new int[] { query[2], query[3] };

    char startType = grid[start[1]][start[0]];

    PriorityQueue<Node> open = new PriorityQueue<>(Comparator.comparingInt(Node::getF));
    Set<int[]> openSet = new HashSet<>();
    Set<int[]> closedSet = new HashSet<>();

    Node startNode = new Node(start[0], start[1], 0, heuristic(start[0], start[1], goal[0], goal[1]));
    open.add(startNode);
    openSet.add(startNode.getCoordinate());

    while (!open.isEmpty()) {
      Node act = open.poll();
      openSet.remove(act.getCoordinate());
      closedSet.add(act.getCoordinate());

      int newG = act.getG() + 1;

      printCord(grid, act.getX(), act.getY());

      if (goal[0] == act.getCoordinate()[0] && goal[1] == act.getCoordinate()[1]) {
        System.out.println("FOUND!!!!");
        return true;
      }

      // Check all neighbours

      System.out.println("act: " + Arrays.toString(act.getCoordinate()));
      List<int[]> newCoordinates = getNewCoordinates(act.getX(), act.getY(), r, c);

      for (int[] coord : newCoordinates) {

        System.out.println("neigb: " + Arrays.toString(coord) + " " + grid[coord[0]][coord[1]]);
        // System.out.println(open);
        if (startType != grid[coord[0]][coord[1]]) {
          System.out.println("wrong symbol " + grid[coord[0]][coord[1]]);
          continue;
        }
        int newH = heuristic(coord[0], coord[1], goal[0], goal[1]);

        Node newNode = new Node(coord[0], coord[1], newG, newH);

        if (newNode.getF() >= act.getF()) {
          // System.out.println("path longer");
          // continue;
        }

        if (closedSet.contains(newNode.getCoordinate())) {
          System.out.println("in closedSet");
          continue;
        }
        System.out.println("neighb OK: " + Arrays.toString(coord));

        if (openSet.contains(newNode.getCoordinate())) {
          open.add(newNode);
        } else {
          openSet.add(newNode.getCoordinate());
          open.add(newNode);
        }

      }

    }

    return false;
  }

  private static void printCord(char[][] grid, int xx, int yy) {
    System.out.println(grid.length + "  " + grid[0].length + " " + xx + " " + yy);
    for (int y = 0; y < grid.length; y++) {
      for (int x = 0; x < grid[0].length; x++) {
        if (y == xx && x == yy) {
          System.out.print("X");
        } else {
          System.out.print(grid[y][x]);
        }
      }

      System.out.println();
    }
  }

  private static List<int[]> getNewCoordinates(int x, int y, int r, int c) {
    List<int[]> list = new ArrayList<>();

    if ((y - 1) > 0 && (y - 1) < c)
      list.add(new int[] { x, y - 1 });// up
    if ((y + 1) > 0 && (y + 1) < c)
      list.add(new int[] { x, y + 1 });// down
    if ((x - 1) > 0 && (x - 1) < r)
      list.add(new int[] { x - 1, y });// left
    if ((x + 1) > 0 && (x + 1) < r)
      list.add(new int[] { x + 1, y });// right

    return list;

  }

  private static int heuristic(int x0, int y0, int x1, int y1) {
    return Math.abs(x1 - x0) + Math.abs(y1 - y0);
  }
}

class Node {
  private int x, y, g, h;

  public Node(int x, int y, int g, int h) {
    this.x = x;
    this.y = y;
    this.g = g;
    this.h = h;
  }

  public int[] getCoordinate() {
    return new int[] { this.x, this.y };
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getG() {
    return g;
  }

  public void setG(int g) {
    this.g = g;
  }

  public int getH() {
    return h;
  }

  public void setH(int h) {
    this.h = h;
  }

  public int getF() {
    return this.g + this.h;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}

class FastReaderTen {
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
