import java.util.HashMap;
import java.util.Scanner;

public class magictrick {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String woord = scanner.nextLine();

        char[] arr = woord.toCharArray();

        HashMap<Character,Character> hashMap = new HashMap<>();

        for (char c : arr) {
            hashMap.put(c, c);
        }

        System.out.println(hashMap.size() == arr.length ? "1":"0");
    }
}
