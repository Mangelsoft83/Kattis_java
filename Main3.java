public class Main3 {
    public static void main(String[] args) {
        int a = Fibonacci.Fibonnacci(0);
        System.out.println("a = " + a);
        int b = Fibonacci.Fibonnacci(3);
        System.out.println("b = " + b);
        int c = Fibonacci.Fibonnacci(10);
        System.out.println("b = " + c);


        int d = Fibonacci.FibLp(0);
        System.out.println("a = " + d);
        int e = Fibonacci.FibLp(3);
        System.out.println("b = " + e);
        int f = Fibonacci.FibLp(10);
        System.out.println("b = " + f);

        for (int i = 0; i < 100; i++) {
            //System.out.println(i + " " + Fibonacci.FibLp(i));

        }

        for (int i = 0; i < 100; i++) {
            System.out.println(i + " " + Fibonacci.FibLp2(i));

        }
    }
}
//0 1 1 2 3 5