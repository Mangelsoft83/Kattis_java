import java.util.Scanner;

public class irepeatmyself {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        scanner.nextLine();

        while (n --> 0) {
            String line = scanner.nextLine();
            
            for (int j = 1; j <= line.length(); j++) {

                String rep = line.substring(0,j);

                String newLine = "";
                while (newLine.length() < line.length()){
                    newLine = newLine.concat(rep);
                }

                if(newLine.startsWith(line)){
                    System.out.println(rep.length());
                    break;
                }
            }
        }
        scanner.close();
    }
}