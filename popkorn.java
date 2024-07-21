import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.Scanner;

public class popkorn {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BigInteger n = scanner.nextBigInteger();

        //int nGroup = 0.5 * n * (n-1);
        //DecimalFormat df = new DecimalFormat("#");
        BigInteger group = n.divide(BigInteger.TWO).divide(BigInteger.TWO);


        BigInteger result = group.subtract(BigInteger.ONE).multiply(group).multiply(BigInteger.TWO).add(BigInteger.TWO).add(BigInteger.TWO);
        //BigInteger result = (BigInteger)(4.0 + 2.0 * ((group-1) * group));

        //if(Double(Long.MAX_VALUE) < result)
        //System.out.println(String.format("%.0f",result+""));
        //String numWihoutDecimal = String.valueOf(result).split("\\.")[0];
        //System.out.println(df.format(result));
        System.out.println(result);
        //System.out.println((long) (4.0 + 2.0 * ((group-1) * group)));
    }
}
