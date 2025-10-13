import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.io.*;
import java.util.StringTokenizer;

public class cursethedarkness {
    public static void main(String[] args) {
        Kattio scanner = new Kattio(System.in);
        int N = scanner.getInt();

        for (int n = 0; n < N; n++) {
            double xBook = scanner.getDouble();
            double yBook = scanner.getDouble();

            int M = scanner.getInt();
            List<Double[]> candles = new ArrayList<>();

            for (int m = 0; m < M; m++) {
                Double[] candle = new Double[2];
                candle[0] = scanner.getDouble() - xBook;
                candle[1] = scanner.getDouble() - yBook;
                candles.add(candle);
            }

            candles.sort(Comparator.comparing(o -> o[0] * o[0] + o[1] * o[1]));

            if(candles.isEmpty()) {
                System.out.println("curse the darkness");
                continue;
            }
            Double[] cordNearestCandle = candles.get(0);
            double dist = Math.sqrt(cordNearestCandle[0]*cordNearestCandle[0] + cordNearestCandle[1]*cordNearestCandle[1]);
            System.out.println(dist <= 8 ? "light a candle":"curse the darkness");
        }


    }
}

