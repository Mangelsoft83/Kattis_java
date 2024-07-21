import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Arrays;
import java.util.Scanner;

public class savingdaylight {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 1000; i++) {
            if (!scanner.hasNext()) break;

            String[] line = scanner.nextLine().split(" ");

            String[] time1Split = line[3].split(":");
            String[] time2Split = line[4].split(":");

            int hour = Integer.parseInt(time2Split[0]) - Integer.parseInt(time1Split[0]);
            int min1 = Integer.parseInt(time1Split[1]), min2 = Integer.parseInt(time2Split[1]);
            int min;

            if(min1 > min2){
                hour--;
                min = min2 + 60 - min1;
            }else
                min = min2 - min1;

            System.out.println(line[0] + " " + line[1] + " " + line[2] + " " + hour + " hours " + min + " minutes");
        }
    }
}
