import java.util.*;
import java.util.stream.Collectors;

public class sortofsorting {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext())
        {
            int n = scanner.nextInt();
            if (n==0) return;

            List<String> words = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String name = scanner.next();
                words.add(name);
            }



                String result = words.stream().sorted(Comparator.comparing(e -> e.substring(0,2)))
                        .collect(Collectors.joining("\n"));
                System.out.println(result);

            System.out.println();
        }

    }
}
