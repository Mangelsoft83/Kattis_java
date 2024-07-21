import java.util.*;

public class hopper {

    private final int n;
    private final int d;
    private final int m;
    private final int[] arr;
    public Queue<Path> queue;

    public Path longestPath;

    public static void main(String[] args) {

        //hopper hopper = new hopper();
        hopper hopper = new hopper(1,7,8,2,6,4,3,5);

        System.out.println(hopper.queue.toString());

        //System.out.println(hopper.calcLongestPath());

    }
    public hopper(){
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt(); //Size array
        d = scanner.nextInt(); //Max step size
        m = scanner.nextInt(); //Max difference per step

        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        longestPath = new Path(0,arr[0],n);
        Queue<Path> queue = new PriorityQueue<>(Collections.reverseOrder());
        queue.add(new Path(0,arr[0],n));

    }
    public hopper(int... arr){
        n = arr.length;
        d = 1;
        m = 2;
        this.arr = arr;
        longestPath = null;
        queue = new PriorityQueue<>(Collections.reverseOrder());

    }


    private int calcLongestPath() {
       

        return 0;
    }


}

class Path implements Comparable<Path>{
    TreeMap<Integer, Integer> path;
    boolean[] canConnect;
    static Path longestPath= new Path(0,0,0);

    public Path(Integer index, Integer value, int size) {

        this.path = new TreeMap<>();
        this.path.put(index,value);
        this.canConnect = new boolean[size];
    }

    @Override
    public int compareTo(Path o) {
        Integer size = path.size();
        return size.compareTo(o.path.size());
    }
}


