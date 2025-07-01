package Baylor;

import java.io.IOException;
import java.util.*;

public class primalrepresentation {
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

            printResult(resultPrimeExp);
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

    private static void printResult(Map<Integer, Integer> resultPrimeExp) {
        StringBuilder sb = new StringBuilder();

        for (int prime:resultPrimeExp.keySet()){
            sb.append(prime);
            int exp = resultPrimeExp.get(prime);
            if(exp != 1) sb.append("^").append(exp);

            sb.append(" ");
        }

        System.out.println(sb.toString().trim());
    }
}

