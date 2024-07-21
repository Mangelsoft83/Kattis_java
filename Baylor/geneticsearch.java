import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class geneticsearch {
    final static List<String> S= new ArrayList<>();
    final static List<String> L = new ArrayList<>();
    
    public static void main(String[] args) {

        readStrings();

        for (int i = 0; i < L.size(); i++) {

            int type1 = getType1(S.get(i),L.get(i));
            int type2 = getType2(S.get(i),L.get(i));
            int type3 = getType3(S.get(i),L.get(i));


            System.out.println(type1 + " " + type2 + " " + type3);
        }




    }

    private static int getType3(String s, String l) {
        HashSet<String> testSet = new HashSet<>();

        StringBuilder ss = new StringBuilder(s);
        char[] letters =new char[]{'A','G','C','T'};

        for (int i = 0; i < ss.length()+1; i++) {
            for(char ch:letters){
                ss.insert(i,ch);
                testSet.add(ss.toString());
                ss.deleteCharAt(i);
            }
        }

        int count = 0;
        for (String test:testSet) {
            count += getType1(test,l);
        }

        return count;
    }
    private static int getType2(String s, String l) {
        HashSet<String> testSet = new HashSet<>();

        StringBuilder ss = new StringBuilder(s);

        for (int i = 0; i < ss.length(); i++) {
            char a = ss.charAt(i);
            ss.deleteCharAt(i);

            testSet.add(ss.toString());
            ss.insert(i,a);
        }

        int count = 0;
        for (String test:testSet) {
            count += getType1(test,l);
        }

        return count;
    }
    private static int getType1(String s, String l) {

        int count = 0;
        for (int j = 0; j < 1000; j++) {
            int indOf = l.indexOf(s);

            if (indOf==-1) return count;

            l = l .substring(indOf+1);
            count++;

        }


        return count;
    }
    
    private static void readStrings() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 250; i++) {
            String line = scanner.nextLine();
            if(line.equals("0"))break;
            String[] split = line.split(" ");
            S.add(split[0]);
            L.add(split[1]);
        }
    }
}
