import java.util.*;

public class hopper_simple {

    static int maxLength = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); //Size array
        int d = scanner.nextInt(); //Max step size
        int m = scanner.nextInt(); //Max difference per step
        maxLength = n;

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.println(dfs(arr,m,d,true));
       //System.out.println(solve(arr,m,d));


    }

    private static int dfs(int[] arr, int m, int d,boolean print) {

        int n = arr.length; int longestPath;

        int[] SHAPE = new int[2*d];
        for (int i = 0; i < d; i++) {
            SHAPE[i] = -d + i;
            SHAPE[i+d] = i +1;
        }

        //Mapping by d
        ArrayList<ArrayList> mapping = new ArrayList<>();

        for (int i = 0; i < n; i++) { // by d
            ArrayList<Integer> indMap = new ArrayList<>(n);
            for (int k : SHAPE) {
                int o = i + k;
                if (o < n && o >= 0) indMap.add(o);
            }
        }

        if(print) {
            for (int i = 0; i < mapping.size(); i++) {
                System.out.print("index: " + i + " : " );
                ArrayList<Integer> indMap = mapping.get(i);
                for (Integer integer : indMap) {
                    System.out.print(integer + " ");
                }
                System.out.println();
            }
        }

        //Mapping by value

            //Adding to list
        ArrayList<DeltaCompare> arrayByDelta = new ArrayList<>();
        for (int index = 0; index < arr.length; index++) {
            DeltaCompare deltaCompare = new DeltaCompare(arr[index],index);
            arrayByDelta.add(deltaCompare);
        }
            //sorting the list
        Collections.sort(arrayByDelta);

            //Filling the allowed indexs
        for (int i = 0; i < arrayByDelta.size(); i++) {
            DeltaCompare delta = arrayByDelta.get(i);
            int act = delta.getNumber();
            for (int j = 1; j < n; j++) {
                int iDown = -j + i;
                if(iDown >= 0){
                    DeltaCompare delta2 = arrayByDelta.get(iDown);
                    if ( Math.abs(act-delta2.getNumber()) <= m) delta.addIndex(delta2.getIndex());
                    else break;
                }else break;
            }

            for (int j = 1; j < n; j++) {
                int iDown = j + i;
                if(iDown < n){
                    DeltaCompare delta2 = arrayByDelta.get(iDown);
                    if ( Math.abs(act-delta2.getNumber()) <= m) delta.addIndex(delta2.getIndex());
                    else break;
                }else break;

            }
        }
            //printing
        if(print) {
            System.out.println("Sorted");
            for (DeltaCompare deltaCompare : arrayByDelta) {
                ArrayList<Integer> x = deltaCompare.getExtendsTo();
                System.out.print("index: " + deltaCompare.getIndex() + " : ");
                for (Integer i : x) {
                    System.out.print(i + " ");
                }
                System.out.println();
            }
        }

        //Combining d and m
        for(DeltaCompare delta:arrayByDelta){
            int ind = delta.getIndex();
            ArrayList<Integer> indMap = mapping.get(ind);
            ArrayList<Integer> ex = delta.getExtendsTo();
            for (int j = 0; j < indMap.size(); j++) {

                boolean exists = false;
                for(int con:ex){
                    if(con == indMap.get(j)) {exists = true;break;}
                }
                if(!exists) indMap.set(j,-1);
            }
            indMap.removeAll(Collections.singleton(-1));
            Collections.sort(indMap);
        }

        if(print) {
            System.out.println("Combined");
            for (int i = 0; i < n; i++) {
                System.out.print("index: " + i + " : ");
                ArrayList<Integer> indMap = mapping.get(i);
                for (Integer integer : indMap) {
                    System.out.print(integer + " ");
                }
                System.out.println();
            }
        }


        PriorityQueue<Set> priorityQueueSets = new PriorityQueue<>(Collections.reverseOrder());
        int x=0;boolean inSet = false;
        for (int i = 0; i < n ; i++) {
            ArrayList<Integer> indMap = mapping.get(i);

            if (indMap.size() > 0 && !inSet) {
                inSet = true;
                x=i;
            }
            else if(inSet && indMap.size() == 0){
                priorityQueueSets.add(new Set(x,i-1));
                inSet = false;
            }else if(inSet && i==n-1){
                priorityQueueSets.add(new Set(x,i));
                inSet = false;
            }

        }

        if(print){
            for(Set set:priorityQueueSets){
                System.out.println("Set: " + set.size + " Start: " + set.start + " Stop: " + set.stop);
            }
        }
        longestPath = 1;
        for(Set set:priorityQueueSets)
        {

            if(longestPath >= set.size){
                break;
            }
            HashMap<Integer, Integer> memory = new HashMap<>();
            for (int i = set.start; i <= set.stop; i++) { //start from every position

                //int longest = dfs(mapping,memory,i);
                boolean[] usedArray= new boolean[n];
                int longest = solve(arr,m,d,n,memory,i, usedArray, 0);

                longestPath = Math.max(longest,longestPath);

                if(print) System.out.println("Solve for index: " + i);
                if(longestPath >= set.size){
                    break;
                }
            }

        }

        return longestPath;
    }

    private static int dfs(ArrayList<ArrayList> mapping, HashMap<Integer, Integer> memory,int start) {
        ArrayList<Integer> indMap = mapping.get(start);
        int max = 0;

        if(memory.get(start)!=null){
            return 0;
        }
        for(Integer ind:indMap){
            memory.put(start,start);
            max = Math.max(max,dfs(mapping,memory,ind));

        }
        return max+1;
    }

    private static int solve(int[] arr, int m, int d) {
        //if (arr.length == 0) return 0;
        int n = arr.length; int longestPath = 0;

        boolean[] usedArray = new boolean[n];

        HashMap<Integer, Integer> memory = new HashMap<>();

        for (int i = 0; i < n; i++) { //start from every position

            int longest = solve(arr,m,d,n,memory,i, usedArray, 0);
            longestPath = Math.max(longest,longestPath);

        }

        return longestPath;
    }

    private static int solve(int[] arr, int m, int d, int n, HashMap<Integer, Integer> memory, int i, boolean[] used, int penalty){
        used[i] = true;

        Integer hash = Arrays.hashCode(used);
        if (memory.get(hash) != null) {
            used[i] = false;
            return memory.get(hash);
        }

        int act = arr[i];
        int max = 0;
        for (int j = -d; j <= d; j++) {

            int x = i + j;
            if (j==0 || x < 0 || x >= n) continue;
            boolean xx = !used[x];
            boolean condM = Math.abs(act-arr[x]) <= m;
            if (condM && xx) {
                int longest = solve(arr, m, d, n, memory, x,used, penalty +j);

                max = Math.max(longest, max);
                memory.put(Arrays.hashCode(used), max);
            }
        }

        used[i] = false;
        return max+1;

    }



}


class DeltaCompare implements Comparable<DeltaCompare>{
    private Integer number;
    private Integer index;

    private ArrayList<Integer> extendsTo;

    public DeltaCompare(Integer number, Integer index) {
        this.number = number;
        this.index = index;
        this.extendsTo = new ArrayList();
    }

    public ArrayList<Integer> getExtendsTo() {
        return extendsTo;
    }

    public void addIndex(Integer i){
        this.extendsTo.add(i);
    }
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }


    @Override
    public String toString() {
        return number + "," + index;
    }

    @Override
    public int compareTo(DeltaCompare o) {
        return this.number.compareTo(o.getNumber());
    }
}

class Set implements Comparable<Set>{
    Integer size;
    int start;
    int stop;

    public Set(int start, int stop) {
        this.size = stop-start + 1;
        this.start = start;
        this.stop = stop;
    }

    @Override
    public int compareTo(Set o) {
        return this.size.compareTo(o.size);
    }
}

