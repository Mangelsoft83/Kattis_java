import java.util.Scanner;

public class permutationencryption {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true){
            int n = scanner.nextInt();
            if(n == 0){scanner.close();break;}

            int[] key = new int[n];
            for (int i = 0; i < n; i++) {
                key[i] = scanner.nextInt();
            }
            
            scanner.nextLine();
            String text = scanner.nextLine();

            System.out.println(encrypt(key,text));
        }
    }

    private static String encrypt(int[] key, String text) {
        int len = key.length;
        StringBuilder encrypted = new StringBuilder();
        
        int encryptedLen = text.length();

        while (encryptedLen % len != 0){
            encryptedLen++;
        }

        encrypted.append('\'');
        for (int i = 0; i < encryptedLen; i++) {
            int segment = i /len;
            int pos = key[i % len]  + len * segment -1;

            if(pos < text.length()){
                encrypted.append(text.charAt(pos));
            }else {
                encrypted.append(" ");
            }

        }
        encrypted.append('\'');
        return encrypted.toString();
    }
}
