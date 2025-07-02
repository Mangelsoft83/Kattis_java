package Baylor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class hurricanedanger {

    private static List<String> cities;
    private static List<int[]> coordinates;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();
            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();

            int m = scanner.nextInt();
            cities = new ArrayList<>();
            //coordinates = new ArrayList<>();
            int[] dist2path = new int[m];
            int minDist = Integer.MAX_VALUE;

            for (int j = 0; j < m; j++) {
                cities.add(scanner.next());
                int x0 = scanner.nextInt();
                int y0 = scanner.nextInt();
                //coordinates.add(new int[]{x,y});

                dist2path[j] = Math.abs(((x2-x1)*(y1-y0) )- ((x1-x0)*(y2-y1)));
                minDist = Math.min(dist2path[j],minDist);
                System.out.println();
            }

            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < m; j++) {
                if(minDist == dist2path[j]) sb.append(cities.get(j)).append(" ");
            }

            System.out.println(sb.toString().trim());
        }


    }


}
