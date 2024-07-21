public class Fibonacci {

    public static int Fibonnacci(int N) throws StackOverflowError{
        if(N<0) throw new IllegalArgumentException("Negative numbers are not allowed");
        if(N<=1) return N;

        return (Fibonnacci(N-1) + Fibonnacci(N-2));
    }

    public static int FibLp(int N) {
        if (N < 0) throw new IllegalArgumentException("Negative numbers are not allowed");

        int[] arr = new int[1000];
        arr[0] = 0;
        arr[1] = 1;
        for (int i = 2; i <= N; i++) {
            arr[i] = arr[i - 2] + arr[i - 1];

            if (arr[i] < 0) throw new IllegalArgumentException("N is to large for an int type");
        }

        return arr[N];
    }

        public static long FibLp2(int M){
            if(M<0) throw new IllegalArgumentException("Negative numbers are not allowed");

            long[] arr = new long[1000];
            arr[0] = 0;
            arr[1] = 1;
            for (int i = 2; i <= M; i++) {
                arr[i] = arr[i-2] + arr[i-1];

                if (arr[i] > Long.MAX_VALUE / 2) throw new IllegalArgumentException("N is to large for long type");
            }

            return arr[M];
        }

}
//0 1 1 2 3 5 8