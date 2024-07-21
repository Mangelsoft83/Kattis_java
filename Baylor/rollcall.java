import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class rollcall {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String[]> persons = new ArrayList<>();
        while (scanner.hasNextLine()){
            String[] person = scanner.nextLine().split(" ");
            persons.add(person);
            if(person[1].equals("Gunn"))break;
        }
        scanner.close();

        persons.sort(new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return  o1[0].compareTo(o2[0]);
            }
        });

        persons.sort(new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return  o1[1].compareTo(o2[1]);
            }
        });

        for (String[] person:persons){
            boolean firstNameDouble = isMorefirstName(person,persons);
            System.out.println(firstNameDouble ? person[0] + " " + person[1] : person[0]);
        }
    }

    private static boolean isMorefirstName(String[] person, List<String[]> persons) {
        for (String[] personnn:persons){
            if(personnn[0].equals(person[0]) && !personnn[1].equals(person[1])) return true;
        }

        return false;
    }
}

