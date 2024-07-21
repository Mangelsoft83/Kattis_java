import java.util.Scanner;

public class boundingrobots {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int w = scanner.nextInt();
        int l = scanner.nextInt();

        while (w!=0) {
            int x=0,xa=0,y=0,ya =0;

            int n = scanner.nextInt();

            for (int i = 0; i < n; i++) {

                String dir = scanner.next();
                int dist = scanner.nextInt();

                switch (dir) {
                    case "u":
                        y += dist;
                        ya += dist;
                        ya = Math.min(ya, l - 1);
                        break;
                    case "d":
                        y -= dist;
                        ya -= dist;
                        ya = Math.max(ya, 0);
                        break;
                    case "l":
                        x -= dist;
                        xa -= dist;
                        xa = Math.max(xa, 0);
                        break;
                    case "r":
                        x += dist;
                        xa += dist;
                        xa = Math.min(xa, w - 1);
                        break;
                }


            }

            System.out.println("Robot thinks " + x + " " + y);
            System.out.println("Actually at  " + xa + " " + ya);
            System.out.println();

            w = scanner.nextInt();
            l = scanner.nextInt();
        }
    }
}
