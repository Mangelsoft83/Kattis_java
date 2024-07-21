import java.awt.geom.Line2D;
import java.util.*;

public class countingtriangles {

    static List<Double[]> lines;
    static List<List<Integer>> adjList;


    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        for (int i = 0; i < 250; i++) {
            int n = scan.nextInt();
            if (n == 0) break;

            //lines = new ArrayList<>(n);
            //String[] l = scan.nextLine().split(" ");

            lines = new ArrayList<>(n);
            //String[] l = scan.nextLine().split(" ");
            for (int j = 0; j < n; j++) {
               // scan.next();
                Double[] line = new Double[4];
                line[0] = scan.nextDouble();
                line[1] = scan.nextDouble();
                line[2] = scan.nextDouble();
                line[3] = scan.nextDouble();
                lines.add(line);
            }

            //readLines(n, scan);

            makeAdjList(n);

            System.out.println(nTriangles());
        }

    }

    private static int nTriangles() {
        HashSet<String> triangles = new HashSet<>();

        //got a to b to c. In c test for a
        for (int a = 0; a < adjList.size(); a++) {

            for (Integer b: adjList.get(a)) { //
                for (Integer c: adjList.get(b)){
                    for (Integer d: adjList.get(c)){
                        if (a==d){
                            Integer[] path = new Integer[]{a,b,c};
                            Arrays.sort(path);
                            triangles.add(Arrays.toString(path));
                        }
                    }

                }

            }

        }

        return triangles.size();
    }

    private static void makeAdjList(int n) {
        adjList = new ArrayList<>(n);
        for (int j = 0; j < n; j++) {
            adjList.add(new ArrayList<>());
        }

        for (int j = 0; j < n; j++) {
            Double[] line1 = lines.get(j);
            for (int k = 0; k < n; k++) {
                Double[] line2 = lines.get(k);
                if(j==k)continue;

                if(doIntersect(line1,line2)){
                    adjList.get(j).add(k);
                }
            }

        }
    }

    private static boolean doIntersect(Double[] line1, Double[] line2) {
        Line2D l1 = new Line2D.Float(line1[0].floatValue(), line1[1].floatValue(), line1[2].floatValue(), line1[3].floatValue());
        Line2D l2 = new Line2D.Float(line2[0].floatValue(), line2[1].floatValue(), line2[2].floatValue(), line2[3].floatValue());
        return  l2.intersectsLine(l1);
        /*
        double[] r1_c1 = getFormula(line1); // rc c x0 x1
        double[] r2_c2 = getFormula(line2);

        if(r1_c1 == null || r2_c2 == null) return false;

        double r1 = r1_c1[0];
        double c1 = r1_c1[1];
        double r2 = r2_c2[0];
        double c2 = r2_c2[1];



        double teller = c2 - c1;
        double noemer = r1 - r2;
        if (noemer == 0.0) return false;

        double x0 = teller/noemer;

        //if on the line
        return (x0 >= r1_c1[2] && x0 < r1_c1[3] && x0 >= r2_c2[2] && x0 < r2_c2[3]);
        */
    }

    private static double[] getFormula(Double[] line) {
        double x1,x2,y1,y2,r1,c1;

        if(line[0]<line[2]){
            x1 = line[0];
            y1 = line[1];
            x2 = line[2];
            y2 = line[3];
        }else {
            x2 = line[0];
            y2 = line[1];
            x1 = line[2];
            y1 = line[3];
        }


        double dx = (x2-x1);
        double dy = (y2-y1);
        if (dy == 0.0 || dx == 0.0) return null;
        r1 = (y2-y1) / (x2-x1);
        c1 = y1;

        return new double[]{r1,c1,x1,x2};
    }

    private static void readLines(int n, Scanner scanner) {
        lines = new ArrayList<>(n);
        //String[] l = scanner.nextLine().split(" ");
        for (int j = 0; j < 4; j++) {
            scanner.next();
            Double[] line = new Double[n];
            line[0] = scanner.nextDouble();
            line[1] = scanner.nextDouble();
            line[2] = scanner.nextDouble();
            line[3] = scanner.nextDouble();
            lines.add(line);
        }
    }
}
