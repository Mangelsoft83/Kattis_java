package Baylor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class fundamentalneighbours {
    static final int MAX_PRIME = (int) Math.sqrt(Integer.MAX_VALUE);
    static List<Integer> primes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        sieve(MAX_PRIME);

        Kattio scanner = new Kattio(System.in);

        Map<Integer,Integer> resultPrimeExp = new TreeMap<>();

        while (scanner.hasMoreTokens()) {
            int number = scanner.getInt();
            resultPrimeExp.clear();

            if(number < 0){
                resultPrimeExp.put(-1,1);
                number *= -1;
            }

            int remaining = number;
            for (int prime : primes) {
                if (prime * prime > remaining) break;

                int count = 0;
                while (remaining % prime == 0) {
                    remaining /= prime;
                    count++;
                }

                if (count > 0) {
                    resultPrimeExp.put(prime, count);
                }
            }

            // If something is left, it is a prime > sqrt(number)
            if (remaining > 1) {
                resultPrimeExp.put(remaining, 1);
            }

            printResult(resultPrimeExp,number);
        }

        scanner.close();

    }

    private static void sieve(int limit) {
        boolean[] isComposite = new boolean[limit + 1];
        isComposite[0] = isComposite[1] = true;

        for (int i = 2; i * i <= limit; i++) {
            if (!isComposite[i]) {
                for (int j = i * i; j <= limit; j += i) {
                    isComposite[j] = true;
                }
            }
        }

        for (int i = 2; i <= limit; i++) {
            if (!isComposite[i]) primes.add(i);
        }
    }

    private static void printResult(Map<Integer, Integer> resultPrimeExp, int number) {
        int total = 1;

        for (int prime:resultPrimeExp.keySet()){
            int exp = resultPrimeExp.get(prime);

            total *= (int) Math.pow(exp,prime);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(number).append(" ").append(total);

        System.out.println(sb);
    }
}

