
import java.io.*;
import java.util.StringTokenizer;

public class enlarginghashtables {
  public static void main(String[] args) {
    Kattio scanner = new Kattio(System.in);

    while (true) {
      long n = scanner.getLong();
      if (n == 0)
        break;

      boolean isPrimeNumber = isPrime(n);

      long len = 2 * n;

      while (!isPrime(len)) {
        len++;
      }

      System.out.println(isPrimeNumber ? len : len + " (" + n + " is not prime)");

    }

  }

  private static boolean isPrime(long n) {

    if (n == 2)
      return true;

    if (n % 2 == 0)
      return false;

    int sqrt = (int) Math.sqrt(n);

    for (int i = 3; i <= sqrt; i += 2)
      if (n % i == 0)
        return false;

    return true;
  }
}

