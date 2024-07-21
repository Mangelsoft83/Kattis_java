import java.util.*;

public class compoundwords {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        List<String> seg = new ArrayList<>();
        TreeSet<String> comp = new TreeSet<>();

        while (scan.hasNext())
            seg.add(scan.next());



        for (int i = 0; i < seg.size(); i++) {
            for (int j = 0; j < seg.size(); j++) {
                if (i==j) continue;
                String word = seg.get(i).concat(seg.get(j));
                comp.add(word);
            }
        }

        for (String x:comp) {
            System.out.println(x);
        }


    }
}
