import java.util.Scanner;

public class musicalnotation {
    static String[] notes;
    static StringBuilder[] page;

    static final String[] notesPage = new String[] {"G","F","E","D","C","B","A","g","f","e","d","c","b","a"};

    public static void main(String[] args) {
        readNotes();

        initPage();


        for (int i = 0; i < notes.length; i++) {

            String note = notes[i].substring(0,1);

            int noteIndex = indexOfNote(note);

            int T = getNumberofNotes(i);

            for (int t = 0; t < T; t++) {
                appendNote(noteIndex);
            }

            if(i <= notes.length-2) appendBlankSpace();

        }

        printPage();
    }

    private static int getNumberofNotes(int i) {
        int T = 1; // number of notes to print
        if(notes[i].length() == 2){
            T = Integer.parseInt(notes[i].substring(1,2));
        }
        return T;
    }

    private static void appendBlankSpace() {
        for (int k = 0; k < page.length; k++) {
            StringBuilder line = page[k];

            if (k % 2 == 0 || k == 11) line.append(" ");
            else line.append("-");
        }
    }

    private static void appendNote(int index) {
        for (int j = 0; j < page.length; j++) {
            StringBuilder line = page[j];
            if (index == j) line.append("*");
            else if (j % 2 == 0 || j == 11) line.append(" ");
            else line.append("-");
        }
    }

    private static int indexOfNote(String note) {
        for (int i = 0; i < notesPage.length; i++) {
            if (notesPage[i].equals(note)) return i;
        }
        return -1;
    }

    private static void printPage() {
        for (StringBuilder s:page) {
            System.out.println(s.toString());
        }
    }

    private static void initPage() {
        page = new StringBuilder[14];

        for (int i = 0; i < 14; i++) {
            page[i] = new StringBuilder();
            page[i].append(notesPage[i]).append(": ");
        }
    }

    private static void readNotes() {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        notes = new String[n];
        for (int i = 0; i < n; i++) {
            notes[i] = scanner.next();
        }
    }
}
