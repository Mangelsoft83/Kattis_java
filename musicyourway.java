import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class musicyourway {

    static String attributes;
    public static void main(String[] args) throws IOException {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

        attributes = scanner.readLine();
        dbEntry.attributes = attributes.split(" ");

        Map<String,Integer> attributeToIndex = new HashMap<>();
        for (int i = 0; i < dbEntry.attributes.length; i++) {
            attributeToIndex.put(dbEntry.attributes[i],i );
        }

        int n = Integer.parseInt(scanner.readLine());

        dbEntry[] songs = new dbEntry[n];

        for (int i = 0; i < n; i++) {
            songs[i] = new dbEntry(scanner.readLine());
        }

        int nSorts = Integer.parseInt(scanner.readLine());

        for (int i = 0; i < nSorts; i++) {
            int index = attributeToIndex.get(scanner.readLine());

            sortEntries(songs,index);
            System.out.println(attributes);
            for (dbEntry song : songs) {
                System.out.println(song.toString());
            }
            System.out.println();
        }
        
    }

    private static void sortEntries(dbEntry[] songs, int index) {

        Arrays.sort(songs, new Comparator<dbEntry>() {
            public int compare(dbEntry o1, dbEntry o2) {
                return o1.names[index].compareTo(o2.names[index]);
            }
        });
    }

}
    class dbEntry{
    static String[] attributes;

    String[] names;

        dbEntry(String name){
            names = name.split(" ");
        }

        @Override
        public String toString() {
            String line = "";
            for (String value: names) line = line.concat(value).concat(" ");

            return line;
        }

    }
