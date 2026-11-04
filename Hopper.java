import java.util.*;
import java.io.*;

public class Hopper {
    static int n, D, M;
    static int[] a;
    static int ans = 1;
    static HashMap<Integer, Integer> next;

    // Cell values: 0 = inactive (unused or degree 2), 1 = singleton (degree 0, used),
    // 2 = degree-1 end whose fragment's other end is already final,
    // >=3 = degree-1 end, the same label marks the two ends of one fragment.

    static void put(int[] t, int f, int cnt) {
        // t has D cells; normalize labels and encode
        int[] map = new int[200];
        int nl = 3;
        int key = 0;
        for (int p = 0; p < D; p++) {
            int v = t[p];
            if (v >= 3) {
                if (map[v] == 0) map[v] = nl++;
                v = map[v];
            }
            key |= v << (4 * p);
        }
        key |= f << 28;
        Integer old = next.get(key);
        if (old == null || old < cnt) next.put(key, cnt);
    }

    // s has D+1 cells (the new vertex is at index D); s[0] is the vertex leaving the window
    static void slide(int[] s, int f, int cnt) {
        int x = s[0];
        if (x == 1) {
            return; // isolated single vertex can never grow
        }
        if (x == 2) {
            // the fragment now has two final ends -> complete path
            for (int p = 1; p <= D; p++) if (s[p] != 0) return;
            ans = Math.max(ans, cnt);
            return;
        }
        if (x >= 3) {
            f++;
            if (f > 2) return;
            for (int q = 1; q <= D; q++) if (s[q] == x) { s[q] = 2; break; }
        }
        int[] t = Arrays.copyOfRange(s, 1, D + 1);
        put(t, f, cnt);
    }

    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedInputStream(System.in));
        in.nextToken(); n = (int) in.nval;
        in.nextToken(); D = (int) in.nval;
        in.nextToken(); M = (int) in.nval;
        a = new int[n];
        for (int i = 0; i < n; i++) { in.nextToken(); a[i] = (int) in.nval; }

        HashMap<Integer, Integer> cur = new HashMap<>();
        cur.put(0, 0);

        for (int i = 0; i < n; i++) {
            next = new HashMap<>();
            for (Map.Entry<Integer, Integer> e : cur.entrySet()) {
                int key = e.getKey(), cnt = e.getValue();
                int f = key >>> 28;
                int[] st = new int[D];
                for (int p = 0; p < D; p++) st[p] = (key >> (4 * p)) & 15;

                // 1) skip vertex i
                int[] s = Arrays.copyOf(st, D + 1);
                slide(s, f, cnt);

                // candidate neighbours
                int[] c = new int[D];
                int k = 0;
                for (int p = 0; p < D; p++) {
                    int idx = i - D + p;
                    if (st[p] != 0 && idx >= 0 && Math.abs(a[idx] - a[i]) <= M) c[k++] = p;
                }

                // 2a) use vertex, no connection
                s = Arrays.copyOf(st, D + 1);
                s[D] = 1;
                slide(s, f, cnt + 1);

                // 2b) one connection
                for (int x = 0; x < k; x++) {
                    int u = c[x];
                    s = Arrays.copyOf(st, D + 1);
                    if (st[u] == 1) {
                        s[u] = 100;
                        s[D] = 100;
                    } else {
                        s[D] = s[u];
                        s[u] = 0;
                    }
                    slide(s, f, cnt + 1);
                }

                // 2c) two connections
                for (int x = 0; x < k; x++) {
                    for (int y = x + 1; y < k; y++) {
                        int u1 = c[x], u2 = c[y];
                        if (st[u1] >= 3 && st[u1] == st[u2]) continue; // would close a cycle
                        int r1 = remaining(st, u1);
                        int r2 = remaining(st, u2);
                        s = Arrays.copyOf(st, D + 1);
                        if (st[u1] != 1) s[u1] = 0;
                        if (st[u2] != 1) s[u2] = 0;
                        s[D] = 0;
                        if (r1 == -1 && r2 == -1) {
                            boolean ok = true;
                            for (int p = 0; p <= D; p++) if (s[p] != 0) { ok = false; break; }
                            if (ok) ans = Math.max(ans, cnt + 1);
                            continue;
                        }
                        if (r1 == -1) s[r2] = 2;
                        else if (r2 == -1) s[r1] = 2;
                        else { s[r1] = 100; s[r2] = 100; }
                        slide(s, f, cnt + 1);
                    }
                }
            }
            cur = next;
        }
/*        8 3 1
        1 7 8 2 6 4 3 5*/

        // final check: the frontier plus finalized ends must form exactly one path
        for (Map.Entry<Integer, Integer> e : cur.entrySet()) {
            int key = e.getKey(), cnt = e.getValue();
            int f = key >>> 28;
            int ones = 0, twos = 0, pairs = 0, active = 0;
            for (int p = 0; p < D; p++) {
                int v = (key >> (4 * p)) & 15;
                if (v == 0) continue;
                active++;
                if (v == 1) ones++;
                else if (v == 2) twos++;
                else pairs++;
            }
            boolean ok = (f == 0 && active == 1 && ones == 1)
                    || (f == 0 && active == 2 && pairs == 2)
                    || (f == 1 && active == 1 && twos == 1);
            if (ok) ans = Math.max(ans, cnt);
        }
        System.out.println(ans);
    }

    // After attaching the new vertex to u: the position of the fragment's other free end,
    // or -1 if that end is already final. A singleton stays active and is its own other end.
    static int remaining(int[] st, int u) {
        if (st[u] == 1) return u;
        if (st[u] == 2) return -1;
        for (int q = 0; q < D; q++) if (q != u && st[q] == st[u]) return q;
        return -1;
    }
}