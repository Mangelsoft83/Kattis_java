import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/***********************************************************************
 * All numbers can be divide in multiples of prime numbers.
 * The given numbers are divided with the lowest prime value until all values are 1.
 * If at least 1 of the numbers can be divided by a prime number the commonMultiple number
 * is multiplied by that prime number.  The algorithm ends when all numbers are 1
 */

public class smallestmultiple {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String line;

        while ((line = bufferedReader.readLine()) != null){
            List<Long> numbers = getNumbers(line);

            //init variables
            BigInteger commonMultiple= new BigInteger(String.valueOf(1));
            Primes primeGen = new Primes();
            long prime = 1;
            boolean isAllOnes = false;



            while (!isAllOnes){
                prime = primeGen.nextPrime(prime);
                boolean isDivided = false;
                isAllOnes = true;
                for (int i = 0; i < numbers.size(); i++) {
                    if(numbers.get(i) == 1) continue;
                    else isAllOnes = false;

                    if(numbers.get(i) % prime == 0){
                        isDivided = true;
                        numbers.set(i,numbers.get(i) / prime);
                    }
                }

                if(isDivided) {
                    BigInteger bigPrime = new BigInteger(String.valueOf(prime));
                    commonMultiple = commonMultiple.multiply(bigPrime);
                    prime = 1;
                }

            }

            System.out.println(commonMultiple);

        }
        bufferedReader.close();
    }

    private static List<Long> getNumbers(String line) {
        String[] split = line.split(" ");
        List<Long> numbers = new ArrayList<>();

        for (String s : split) {
            numbers.add((long) Integer.parseInt(s));
        }
        return numbers;
    }

}


class Primes{
    private final HashMap<Long,Long> nextPrimes;
    private long lastPrime = 3;

    public Primes() {
        nextPrimes = new HashMap<>();
        nextPrimes.put(1L,2L);
        nextPrimes.put(2L,3L);
    }

    public long nextPrime(long p){
        if(nextPrimes.containsKey(p)) return nextPrimes.get(p);

        p++;
        if(!isPrime(p)) {
            p = nextPrime(p);
        }else{
            nextPrimes.put(lastPrime,p);
            lastPrime = p;
        }

        return p;
    }

    private static boolean isPrime(long n) {

        if (n == 2L)
            return true;

        if (n % 2L == 0)
            return false;

        int sqrt = (int)Math.sqrt(n);

        for (int i = 3; i <= sqrt; i += 2)
            if (n % i == 0)
                return false;

        return true;
    }
}
