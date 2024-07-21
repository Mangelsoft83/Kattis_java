import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class pachydermpeanutpacking {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            if (n == 0) break;

            List<String> boxNames = new ArrayList<>();
            List<Double[]> boxCoordinates = new ArrayList<>();

            readBoxes(n, scanner, boxNames, boxCoordinates);

            int m = scanner.nextInt();
            List<String> peanutNames = new ArrayList<>();
            List<Double[]> peanutCoordinates = new ArrayList<>();

            readPeanuts(m, scanner, peanutNames, peanutCoordinates);

            for (int i = 0; i < m; i++) {
                StringBuilder result = new StringBuilder();

                result.append(peanutNames.get(i)).append(" ");
                result.append(testPeanut(peanutNames.get(i),peanutCoordinates.get(i),boxNames,boxCoordinates));

                System.out.println(result);
            }
            System.out.println();
        }
    }

    private static String testPeanut(String peanutName, Double[] peanutCoordinate, List<String> boxNames, List<Double[]> boxCoordinates) {

        for (int i = 0; i < boxNames.size(); i++) {
            String boxName = boxNames.get(i);
            Double[] boxCoordinate = boxCoordinates.get(i);
            if(isInBox(peanutCoordinate,boxCoordinate)) {
                return  boxName.equals(peanutName) ? "correct":boxName;
            }

        }


        return "floor";
    }

    private static boolean isInBox(Double[] peanutCoordinate, Double[] boxCoordinate) {
        double x = peanutCoordinate[0];
        double y = peanutCoordinate[1];

        return x >= boxCoordinate[0] && x <= boxCoordinate[2] && y >= boxCoordinate[1] && y <= boxCoordinate[3];
    }

    private static void readBoxes(int n, Scanner scanner, List<String> boxNames, List<Double[]> boxCoordinates) {
        for (int i = 0; i < n; i++) {
            Double[] boxCoordinate = new Double[4];
            for (int j = 0; j < 4; j++) {
                boxCoordinate[j] = scanner.nextDouble();
            }
            boxNames.add(scanner.next());
            boxCoordinates.add(boxCoordinate);
        }
    }

    private static void readPeanuts(int n, Scanner scanner, List<String> boxNames, List<Double[]> boxCoordinates) {
        for (int i = 0; i < n; i++) {
            Double[] boxCoordinate = new Double[2];
            for (int j = 0; j < 2; j++) {
                boxCoordinate[j] = scanner.nextDouble();
            }
            boxNames.add(scanner.next());
            boxCoordinates.add(boxCoordinate);
        }
    }
}
