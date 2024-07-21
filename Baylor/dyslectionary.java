import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class dyslectionary {
    static int ind;
    public static void main(String[] args) throws IOException {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        String word;
        boolean end = false;

        List<String> words = new ArrayList<>();
        while ((word = scanner.readLine()) != null){
            int length = Integer.MIN_VALUE;
            while (true){
                if(word == null){
                    end = true;
                    break;
                }
                if(word.isEmpty())break;


                length = Math.max(length,word.length());

                words.add(word);
                word = scanner.readLine();
            }


            //add space
            List<String> wordsLen = new ArrayList<>();
            for (String s:words){
                StringBuilder sBuilder = new StringBuilder(s);
                for (int i = sBuilder.length(); i < length; i++) {
                    sBuilder.insert(0, " ");
                }
                wordsLen.add(sBuilder.toString());
            }


            //sort

            for (int i = 0; i < length; i++) {
                ind = i;
                wordsLen.sort(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        Character c1 = o1.charAt(ind);
                        Character c2 = o2.charAt(ind);

                        return c1.compareTo(c2);
                    }
                });

            }

            //print
            for(String ss:wordsLen){
                System.out.println(ss);
            }

            if(!end) System.out.println();

            words = new ArrayList<>();
            length = Integer.MIN_VALUE;
        }

        scanner.close();
    }
}
