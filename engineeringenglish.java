import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class engineeringenglish {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> textLines = new ArrayList<>();

        //textLines.add("Engineering will save the world from inefficiency");
        //textLines.add("Inefficiency is a blight on the world and its");
        //textLines.add("humanity");

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line == null || line.equals("0")) break;
            textLines.add(line);
        }

        HashSet<String> used = new HashSet<>();

        StringBuilder sentence = new StringBuilder();

        for(String orSentence:textLines){
            String result = "";
            for(String word:orSentence.split(" ")){
                boolean isUsed = used.add(word.toLowerCase());
                result = result.concat(!isUsed ? ". " : word.concat(" "));

            }
            System.out.println(result.stripTrailing());
        }

    }
}
