import java.util.*;

public class synchronizinglists {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n =scanner.nextInt();
        while (n != 0){
            ArrayList<Integer> arrayListFirst = new ArrayList<>(n);
            ArrayList<Integer> arrayListSecond = new ArrayList<>(n);
            ArrayList<Integer> arrayListFirstSorted = new ArrayList<>(n);
            ArrayList<Integer> arrayListSecondSorted = new ArrayList<>(n);

            for (int i = 0; i < n; i++) {
                arrayListFirst.add(scanner.nextInt());
            }
            for (int i = 0; i < n; i++) {
                arrayListSecond.add(scanner.nextInt());
            }

            arrayListFirstSorted.addAll(arrayListFirst); Collections.sort(arrayListFirstSorted);
            arrayListSecondSorted.addAll(arrayListSecond); Collections.sort(arrayListSecondSorted);

            for (int i = 0; i < n; i++) {
                int key = arrayListFirst.get(i);
                int index = arrayListFirstSorted.indexOf(key);
                System.out.println(arrayListSecondSorted.get(index));
            }


            n =scanner.nextInt();
            System.out.println();
        }
    }
}
