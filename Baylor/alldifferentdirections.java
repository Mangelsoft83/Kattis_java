import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;

public class alldifferentdirections {
    public static void main(String[] args) throws IOException {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

        String l = scanner.readLine();
        while (!l.equals("0")){
            int N = Integer.parseInt(l);

            Destination[] destinations = new Destination[N];
            for (int n = 0; n < N; n++) {
                l = scanner.readLine();
                destinations[n] = getDestination(l.split(" "));
            }

            DecimalFormat df = new DecimalFormat("0.####");
            DecimalFormat df2 = new DecimalFormat("0.#####");

            Destination avgDest = getAvgDestination(destinations);

            Arrays.sort(destinations, Comparator.comparing(o -> o.comp(avgDest)));
            
            System.out.println(df.format(avgDest.x) + " " + df.format(avgDest.y) + " " + df2.format(destinations[N-1].getDistance(avgDest)));
            l = scanner.readLine();
        }
    }

    private static Destination getAvgDestination(Destination[] destinations) {
        double x=0;
        double y=0;
        for (Destination d:destinations){
            x+=d.x;
            y+=d.y;
        }

        return new Destination(x/ destinations.length,y/ destinations.length);
    }

    private static Destination getDestination(String[] s) {
        double x = Double.parseDouble(s[0]);
        double y = Double.parseDouble(s[1]);

        double angle = Double.parseDouble(s[3]);
        Destination dest = new Destination(x,y);

        for (int i = 4; i < s.length; i=i+2) {
            String cmd = s[i];
            double value = Double.parseDouble(s[i+1]);
            if(cmd.equals("walk")){
                dest.x += Math.cos(angle * Math.PI / 180) * value;
                dest.y += Math.sin(angle * Math.PI / 180) * value;
            } else if (cmd.equals("turn")) {
                angle+=value;
            }
        }

        return dest;
    }
}

class Destination {
    double x,y;

    public Destination(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getDistance(Destination d){
        return Math.hypot(this.x-d.x, this.y -d.y);
    }

    public int comp(Destination d){
        return (int) ((this.x-d.x)*(this.x-d.x) + (this.y -d.y) * (this.y -d.y));
    }
}

