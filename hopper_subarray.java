import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;


public class hopper_subarray {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        //int n = scanner.nextInt();
        //int d = scanner.nextInt();
        //int m = scanner.nextInt();

        int n = 8;
        int m = 1;
        int d = 3;
        int[] arr = new int[] {1, 7, 8, 2, 6, 3, 3, 3};
        //for (int i = 0; i < n; i++) {
            //arr[i] = scanner.nextInt();
        //}


        int[] arrSort = new int[n];

        int[] arrSortTemp = new int[n];
        System.arraycopy(arr,0,arrSort,0,arr.length);
        Arrays.sort(arrSort);
        System.arraycopy(arrSort,0,arrSortTemp,0,arrSort.length);

        //int[] arrIndex = new int[n];
        TreeMap<Integer,Integer> xx = new TreeMap<>();
        for (int i = 0; i < arr.length; i++) {
              xx.put(arr[i],i);
        }

        Integer [] arrIndex = xx.keySet().toArray(new Integer[0]);
        System.out.println("aa = " + Arrays.toString(arrIndex));        /*
        int lastIndex = 0;
        for (int i = 0; i < n; i++) {
            int sort = arrSortTemp[i];
            for (int j = 0; j < n; j++) {
                int test = arr[j];
                if((test==sort) && (lastIndex != j))
                {
                    arrIndex[i] = j;
                    lastIndex = j;
                    arrSortTemp[i]=Integer.MIN_VALUE;
                    break;
                }
            }
        }*/


        //System.out.println(Arrays.toString(arrIndex));


    }


    public class Item implements Comparable {
        int content;
        int index;

        @Override
        public int compareTo(Object o) {
            Item item = (Item) o;
            if (content == item.content)
                return 0;
            else if (content > item.content)
                return 1;
            else
                return -1;
        }
    }


}


