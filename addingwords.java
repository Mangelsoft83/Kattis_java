import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class addingwords {
    static HashMap<Integer, String> values2dict = new HashMap<>();
    static HashMap<String, Integer> dict2values = new HashMap<>();

    static void main(String[] args) throws IOException {
        FastReaderAddingWords fr = new FastReaderAddingWords();
        List<String> textList = new ArrayList<>();

        String line;
        while((line = fr.readLine()) != null) {
            textList.add(line);
        }

        proces(textList);
    }

    private static void proces(List<String> textList) {
        for(String line : textList) {
            if (line.startsWith("def")) {
                procesDef(line);
            } else if (line.startsWith("clear")) {
                values2dict.clear();
            } else if (line.startsWith("calc")) {
                procesCalc(line);
            }
        }

    }

    private static void procesDef(String line) {
        String[] split = line.split(" ");
        String name = split[1];
        int value = Integer.parseInt(split[2]);
        values2dict.put(value, name);
        dict2values.put(name, value);
    }

    private static void procesCalc(String line) {
        String l = line.replaceAll("calc", "").trim();
        String[] split = l.split(" ");
        int act = 0;
        int temp;
        char opperator = ' ';

        for(String word : split) {
            switch (word) {
                case "+" -> opperator = '+';
                case "-" -> opperator = '-';
                case "=" -> {
                    System.out.println(l + " " + values2dict.getOrDefault(act, "unknown"));
                    opperator = ' ';
                }
                default -> {
                    if (!dict2values.containsKey(word)) {
                        System.out.println(l + " unknown");
                        return;
                    }

                    temp = dict2values.get(word);
                    if (opperator == '+') {
                        act += temp;
                        opperator = ' ';
                    } else if (opperator == '-') {
                        act -= temp;
                    } else {
                        act = temp;
                    }
                }
            }
        }

    }
}

class FastReaderAddingWords {
    private final InputStream in = System.in;
    private final byte[] buffer = new byte[1 << 16];
    private int ptr = 0, len = 0;

    public String readLine() throws IOException {
        int c;
        final StringBuilder sb = new StringBuilder();
        boolean seenChar = false;

        while ((c = read()) != -1) {
            if (c == '\n')
                break;
            if (c == '\r')
                continue; // skip carriage return
            sb.append((char) c);
            seenChar = true;
        }

        if (!seenChar && c == -1)
            return null; // true EOF
        return sb.toString();
    }

    private int read() throws IOException {
        if (ptr >= len) {
            ptr = 0;
            len = in.read(buffer);
            if (len <= 0)
                return -1;
        }
        return buffer[ptr++];
    }
}