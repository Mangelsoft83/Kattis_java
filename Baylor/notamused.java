import java.text.DecimalFormat;
import java.util.*;

public class notamused {
    static TreeMap<String,employee> db = new TreeMap<>();
    static int day = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()){
            String[] line = scanner.nextLine().split(" ");
            String cmd = line[0];
            switch (cmd) {
                case "OPEN":
                    day++;
                    db = new TreeMap<>();
                    break;
                case "ENTER":
                    String name = line[1];
                    int startTime = Integer.parseInt(line[2]);
                    if(!db.containsKey(name)){
                        db.put(name,new employee(name,startTime));
                    }else {
                        employee emp = db.get(name);
                        emp.enter = Integer.parseInt(line[2]);
                    }
                    break;
                case "EXIT":
                    String aname = line[1];
                    employee em = db.get(aname);
                    int exitTime = Integer.parseInt(line[2]);
                    em.exit(exitTime);
                    break;
                case "CLOSE":
                    System.out.println("Day " + day);
                    for (employee e:db.values()){
                        double dollars = 0.1 * (e.time);
                        DecimalFormat df = new DecimalFormat("0.00");
                        System.out.println(e.name + " $" + df.format(dollars));
                    }
                    System.out.println();
                    break;
            }

        }

    }
}

class employee implements Comparator<employee>{
    String name;
    int enter,time;

    public employee(String name, int enter) {
        this.name = name;
        this.enter = enter;

        this.time=0;
    }

    @Override
    public int compare(employee o1, employee o2) {
        return o1.name.compareTo(o2.name);
    }

    public void exit(int exitTime) {
        time+=exitTime - enter;
    }
}
