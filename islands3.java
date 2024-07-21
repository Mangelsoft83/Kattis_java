import java.util.Scanner;

public class islands3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        char[][] scan= new char[a][b];

        for (int i = 0; i < a ; i++) {
            String line = scanner.next();
            //System.out.println("line = " + line);
            for (int j = 0; j < b; j++) {
                char x = line.charAt(j);
                scan[i][j] = x;
                //System.out.println("x = " + x);
            }
        }



        int nIlands = 0;

        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                if (scan[i][j] == 'L')
                {
                    nIlands++;
                    scan[i][j] = 'x';
                    step(scan,i+1,j,a,b);
                    step(scan,i-1,j,a,b);
                    step(scan,i,j+1,a,b);
                    step(scan,i,j-1,a,b);
                }


            }
        }

        System.out.println(nIlands);
        

        
    }

    private static void step(char[][] scan,int i, int j,int a, int b)
    {
        if (i<0 || i >= a) return;
        if (j<0 || j >= b) return;
        if (scan[i][j] == 'L' || scan[i][j] == 'C')
        {
            scan[i][j] = 'x';
            step(scan,i+1,j,a,b);
            step(scan,i-1,j,a,b);
            step(scan,i,j+1,a,b);
            step(scan,i,j-1,a,b);

        }


    }
}
