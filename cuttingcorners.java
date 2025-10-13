import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class cuttingcorners {
    static double maxAlpha = 0;
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        while (true){
            String line = scanner.nextLine();
            if(line.equals("0")){scanner.close();break;}
            List<Integer> x = new ArrayList<>();
            List<Integer> y = new ArrayList<>();

            int c = 0;
            for(String i:line.split(" ")){
                if(c==0){c++;continue;}
                if(c % 2 == 1) x.add(Integer.parseInt(i));
                else y.add(Integer.parseInt(i));
                c++;
            }


            int indMax = getInd(x, y);
            double max;
            while (true) {
                if (x.size() <= 3) {
                    printPoly(x, y);
                    break;
                }

                max = maxAlpha;
                List<Integer> x2 = new ArrayList<>(x);
                x2.remove(indMax);
                List<Integer> y2 = new ArrayList<>(y);
                y2.remove(indMax);
                indMax = getInd(x2, y2);

                if(maxAlpha>max){
                    printPoly(x,y);
                    break;
                }else{
                    x = x2;
                    y = y2;
                }
            }

        }
    }

    private static int getInd(List<Integer> x, List<Integer> y) {
        int i = Integer.MAX_VALUE;
        double max = Double.MIN_VALUE;

        for (int j = 0; j < x.size(); j++) {
            double a = getAngle(x,y,j);
            if(a > max) {
                max = a;
                i = j;
            }
        }
        maxAlpha = max;
        return i;
    }

    private static void printPoly(List<Integer> x, List<Integer> y) {
        StringBuilder result = new StringBuilder();
        result.append(x.size());
        for (int i = 0; i < x.size(); i++) {
            result.append(" ").append(x.get(i)).append(" ").append(y.get(i));
        }
        System.out.println(result);
    }

    private static double getAngle(List<Integer> x, List<Integer> y, int i) {
        int i1 = (x.size()+i-1) % x.size();
        int i2 = (x.size()+i) % x.size();
        int i3 = (x.size()+i+1) % x.size();
        double[] V1 = new double[]{x.get(i3) - x.get(i2),y.get(i3) - y.get(i2)};
        double[] V2 = new double[]{x.get(i2) - x.get(i1),y.get(i2) - y.get(i1)};
  
        double num = (V1[0]*V2[0] + V1[1]*V2[1]);
        double den = (Math.sqrt(Math.pow(V1[0], 2) + Math.pow(V1[1], 2)) * (Math.sqrt(Math.pow(V2[0], 2) + Math.pow(V2[1], 2))) );
        return Math.acos(num / den) * 180 / Math.PI;
    }
}
