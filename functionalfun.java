import java.util.HashSet;
import java.util.Scanner;

public class functionalfun {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String[] domain = scanner.nextLine().replace("domain ","").split(" ");
            String[] codomain = scanner.nextLine().replace("codomain ","").split(" ");

            HashSet<String> domainMap = new HashSet<>();
            HashSet<String> coDomainMap = new HashSet<>();

            boolean nofunction = false;
            boolean injective = true;
            boolean surjective = true;

            int N = scanner.nextInt();
            scanner.nextLine();

            while (N --> 0){
                String[] s = scanner.nextLine().split(" -> ");
                if (coDomainMap.contains(s[1])) injective = false;

                if (domainMap.contains(s[0])) nofunction = true;


                domainMap.add(s[0]);
                coDomainMap.add(s[1]);
            }


            for (String s : codomain)
                if (!coDomainMap.contains(s)) {
                    surjective = false;
                    break;
                }

            if(nofunction) System.out.println("not a function");
            else if (injective && surjective) System.out.println("bijective");
            else if (injective) System.out.println("injective");
            else if (surjective) System.out.println("surjective");
            else System.out.println("neither injective nor surjective");
        }

    }

    private static boolean isSurjective(boolean[][] adjMatrix) {

        for (int i = 0; i < adjMatrix[0].length; i++) {
            boolean isConnected = false;
            for (int j = 0; j < adjMatrix.length; j++) {
                isConnected = isConnected || adjMatrix[j][i];
            }
            if (!isConnected) return false;

        }

        return true;
    }

    private static boolean isInjective(boolean[][] adjMatrix) {
        boolean one = false;
        for (boolean[] matrix : adjMatrix) {
            int sum = 0;
            for (boolean b : matrix) {
                if (b) sum++;
                one = true;
            }
            if (sum > 1) return false;
        }
        return one;
    }


}
