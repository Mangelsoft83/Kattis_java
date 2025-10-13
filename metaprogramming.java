import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class metaprogramming {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        HashMap<String,Integer> map = new HashMap<>();

        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            if (line.isEmpty()) break;



            String[] parse = line.split(" ");
            String cmd = parse[0];
            Integer value1, value2;
            String opp;
            switch (cmd) {
                case "define":
                    Integer value = Integer.parseInt(parse[1]);
                    String name = parse[2].toLowerCase().trim();
                    map.put(name, value);
                    break;
                case "eval":
                    String name1 = parse[1].toLowerCase().trim();
                    String name2 = parse[3].toLowerCase().trim();
                    if (!map.containsKey(name1) || !map.containsKey(name2)) {
                        System.out.println("undefined");
                        continue;
                    }

                    opp = parse[2];

                    value1 = map.get(name1);
                    value2 = map.get(name2);

                    switch (opp){
                        case "<":
                            System.out.println(value1 < value2);
                            break;
                        case ">":
                            System.out.println(value1 > value2);
                            break;
                        case "=":
                            System.out.println(Objects.equals(value1, value2));
                            break;
                        default:
                            System.out.println("undefined");
                    }

                    break;
            }


        }
    }
}
