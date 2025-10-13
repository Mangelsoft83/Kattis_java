import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class parsinghex {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        List<Character> hexN = new ArrayList<>();
        for(char c:"0123456789abcdefABCDEF".toCharArray())hexN.add(c);

        while (scan.hasNextLine())
        {
            String str = scan.nextLine();

            while (true)
            {
                int index1 = str.indexOf("0x");
                int index2 = str.indexOf("0X");

                if (index1 == -1 && index2 == -1)
                    break;

                int index = Math.min(index1, index2);

                if (index1 == -1)
                    index = index2;

                if (index2 == -1)
                    index = index1;

                String hex = str.substring(index);
                int i = 2;

                while (i < hex.length() && hexN.contains(hex.charAt(i)))
                    i++;

                hex = hex.substring(0, i);

                System.out.print(hex + " ");
                System.out.println(Long.parseLong(hex.substring(2) , 16));

                str = str.substring(0, index) + str.substring(index + hex.length());
            }
        }

        scan.close();
    }
}

