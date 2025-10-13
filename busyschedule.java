import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class busyschedule {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){
            int n = scanner.nextInt();
            if (n==0)break;
            LocalTime[] times = new LocalTime[n];
            for (int i = 0; i < n; i++) {
                String time = scanner.nextLine().replace("a.m.","AM").replace("p.m.","PM");
                if(time.isEmpty()){
                    i--;
                    continue;
                }
                DateTimeFormatter inputFormatter =  DateTimeFormatter.ofPattern("h:mm a", Locale.US);
                times[i] = LocalTime.parse(time, inputFormatter);
            }
            Arrays.sort(times);

            DateTimeFormatter outputFormatter =  DateTimeFormatter.ofPattern("h:mm a", Locale.US);
            for (int i = 0; i < n; i++) {
                String time = times[i].format(outputFormatter).replace("AM","a.m.").replace("PM","p.m.");
                System.out.println(time);
            }
            System.out.println();

        }


    }
}
