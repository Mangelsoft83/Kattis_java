import java.util.*;

public class baconeggsandspam {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = -1;//scanner.nextInt();

        while (N != 0){
            TreeMap<String, ArrayList<String>> lijst = new TreeMap<>();

            N = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < N; i++) {
                String[] parseArray = scanner.nextLine().split(" ");

                for (int j = 1; j < parseArray.length; j++) {
                    String key = parseArray[j];
                    String name = parseArray[0];
                    if(lijst.get(key) == null){
                        ArrayList<String> names = new ArrayList<>();
                        names.add(name);
                        lijst.put(key,names);
                    }else {
                        ArrayList<String> names = lijst.get(key);
                        if(!names.contains(name)) names.add(name);

                    }


                }
            }

            for (Map.Entry<String, ArrayList<String>> stringArrayListEntry : lijst.entrySet()) {
                String key = stringArrayListEntry.getKey();
                ArrayList<String> entry = stringArrayListEntry.getValue();
                Collections.sort(entry);

                System.out.print(key);
                for (int i = 0; i < entry.size(); i++) {

                    System.out.print(" " + entry.get(i));
                    if(i == entry.size()-1) System.out.println();
                }


            }
            if (N!=0)System.out.println();




        }


    }
}
