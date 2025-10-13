import java.util.HashMap;
import java.util.Scanner;

public class printingcosts {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        HashMap<Character, Integer> table = new HashMap<>();
        String x = "    0        !   9        \"   6        #  24        $  29        %  22\n" +
                "&  24        '   3        (  12        )  12        *  17        +  13\n" +
                ",   7        -   7        .   4        /  10        0  22        1  19\n" +
                "2  22        3  23        4  21        5  27        6  26        7  16\n" +
                "8  23        9  26        :   8        ;  11        <  10        =  14\n" +
                ">  10        ?  15        @  32        A  24        B  29        C  20\n" +
                "D  26        E  26        F  20        G  25        H  25        I  18\n" +
                "J  18        K  21        L  16        M  28        N  25        O  26\n" +
                "P  23        Q  31        R  28        S  25        T  16        U  23\n" +
                "V  19        W  26        X  18        Y  14        Z  22        [  18\n" +
                "\\  10        ]  18        ^   7        _   8        `   3        a  23\n" +
                "b  25        c  17        d  25        e  23        f  18        g  30\n" +
                "h  21        i  15        j  20        k  21        l  16        m  22\n" +
                "n  18        o  20        p  25        q  25        r  13        s  21\n" +
                "t  17        u  17        v  13        w  19        x  13        y  24\n" +
                "z  19        {  18        |  12        }  18        ~   9";

        x = x.replace("       ","").replace("\n"," ");
        boolean a = true;

        Character key = ' ';
        int value;
        for(String ch:x.split(" ")){
            if(!ch.isEmpty() && !a){
                a = true;
                key = ch.toCharArray()[0];
            } else if (!ch.isEmpty()) {
                a = false;
                value = Integer.parseInt(ch);
                //System.out.println(key + " " + value);
                table.put(key,value);
            }

        }
        /*table.put(' ',0);
        table.put('&',24);
        table.put(',',7);
        table.put('2',22);
        table.put('8',23);
        table.put('>',10);
        table.put('D',26);
        table.put('J',18);
        table.put('P',23);
        table.put('V',19);
        table.put('\\',10);
        table.put('b',25);
        table.put('h',21);
        table.put('n',18);
        table.put('t',17);
        table.put('z',19);
        table.put('!',9);
        table.put('\'',9);
        table.put('-',7);
        table.put('3',23);
        table.put('9',26);
        table.put('?',15);
        table.put('E',26);
        table.put('K',21);
        table.put('Q',31);
        table.put('W',26);
        table.put(']',18);
        table.put('c',17);
        table.put('i',15);
        table.put('o',20);
        table.put('u',17);
        table.put('{',18);
        table.put('"',6);
        table.put('(',12);
        table.put('.',4);
        table.put('4',21);
        table.put(':',8);
        table.put('@',32);
        table.put('F',20);
        table.put('L',16);
        table.put('R',28);
        table.put('X',18);
        table.put('^',7);
        table.put('d',25);
        table.put('j',20);
        table.put('p',25);
        table.put('v',13);
        table.put('|',12);
        table.put('#',24);
        table.put(')',12);
        table.put('/',10);
        table.put('5',27);
        table.put(';',11);
        table.put('A',24);
        table.put('G',25);
        table.put('M',28);
        table.put('S',25);
        table.put('Y',14);
        table.put('_',8);
        table.put('e',23);
        table.put('k',21);
        table.put('q',25);
        table.put('w',19);
        table.put('}',18);
        table.put('$',29);
        table.put('*',17);
        table.put('0',22);
        table.put('6',26);
        table.put('<',10);
        table.put('B',29);
        table.put('H',25);
        table.put('N',25);
        table.put('T',16);
        table.put('Z',22);
        table.put('`',3);
        table.put('f',18);
        table.put('l',16);
        table.put('r',13);
        table.put('x',13);
        table.put('~',9);
        table.put('%',22);
        table.put('+',13);
        table.put('1',19);
        table.put('7',16);
        table.put('=',14);
        table.put('C',20);
        table.put('I',18);
        table.put('O',26);
        table.put('U',23);
        table.put('[',18);
        table.put('a',23);
        table.put('g',30);
        table.put('m',22);
        table.put('s',21);
        table.put('y',24);*/

        for (int i = 0; i < 100; i++) {
            if (!scanner.hasNextLine()) return;

            String line = scanner.nextLine();
            int score = 0;
            for (Character ch:line.toCharArray()){
                score += table.get(ch);
            }
                System.out.println(score);
        }
    }
}
