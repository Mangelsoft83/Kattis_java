import java.util.Scanner;

public class provincesandgold {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int G = scanner.nextInt();
        int S = scanner.nextInt();
        int C = scanner.nextInt();

        int bp = G*3+S*2+C;

        String vc = bp >= 8 ? "Province" : bp >= 5 ? "Duchy" : "Estate";
        String tc = bp >= 6 ? "Gold" : bp >= 3 ? "Silver" : "Copper";

        System.out.println(bp>1 ? vc + " or " + tc : tc);
    }
}
