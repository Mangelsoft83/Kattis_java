import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class securedoors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        HashMap<String,Boolean> stateMap = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            String action = scanner.next();
            String person = scanner.next();

            Boolean value = action.equals("entry");

            if (!stateMap.containsKey(person)) stateMap.put(person,false);
            Boolean state = stateMap.get(person);

            if(state && value) System.out.println(person + " entered (ANOMALY)");
            else if (!state && value) System.out.println(person + " entered");
            else if (state) System.out.println(person + " exited");
            else System.out.println(person + " exited (ANOMALY)");

            stateMap.put(person,value);
        }
        
    }
}
