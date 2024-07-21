import java.util.*;

public class hopper_final {
    public static void main(String[] args) {
        boolean isKattis = true;
        if (isKattis) {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            int d = scanner.nextInt();
            int m = scanner.nextInt();

            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }

            int max = getLongestPath(n, d, m, arr,true);

            System.out.println(max);

        } else {
            List<int[][]> testSets = new ArrayList<>();

            getTestSets(testSets);
            int setCount = 0;

            for (int[][] testSet : testSets) {
                long startTime = System.nanoTime();
                int n = testSet[0][0];
                int d = testSet[0][1];
                int m = testSet[0][2];
                int[] arr = testSet[1];
                int correctResult = testSet[2][0];
                int max = getLongestPath(n, d, m, arr,false);

                setCount++;

                long TimeNeeded = (System.nanoTime() - startTime) / 1000;
                String test = correctResult == max ? "Correct: " + TimeNeeded + " ms" : "Wrong \t correct answer: " + correctResult + "\t result: " + max + "\t" + Arrays.toString(arr);

                System.out.println(setCount + "\tResult: " + max + "\t" + test);
            }
        }
    }

    private static int getLongestPath(int n, int d, int m, int[] arr, boolean print) {
        List <List <Rope>> adjArr = new ArrayList<>(n);
        //seed adjArr
        Rope longestPath = seedAdjArray(n, d, m, arr, adjArr);

        if(print){
            for (int j = 0; j < adjArr.size(); j++) {
                List<Rope> pos = adjArr.get(j);
                for (Rope x : pos) {
                    System.out.println("Index: " + j + " " + x.toString());

                }
            }
        }


        longestPath = combineByIndex(n, adjArr, longestPath);

        if(print){
            for (int j = 0; j < adjArr.size(); j++) {
                List<Rope> pos = adjArr.get(j);
                for (Rope x : pos) {
                    System.out.println("Index: " + j + " " + x.toString());

                }
            }
        }



    return longestPath.seq.size();

    }

    private static Rope combineByIndex(int n, List <List <Rope>>  adjArr, Rope longestPath) {


        for (int i = 0; i < n; i++) {
            List<Rope> conListIndex = adjArr.get(i);
            List<Rope> newRopes = new ArrayList<>(); //List to store new connected ropes
            List<Rope> delRope = new ArrayList<>();  //list to store ropes to be removed from the buckets
            List<Integer> indexes = new ArrayList<>(); //list to store the indexes of buckets to be removed
            indexes.add(i);

            if (conListIndex.size() > 1){
                for (int indA = 0; indA < conListIndex.size(); indA++) {
                    for (int indB = indA+1; indB < conListIndex.size(); indB++) {
                        Rope A = conListIndex.get(indA);
                        Rope B = conListIndex.get(indB);
                        HashMap<Integer,Integer> newMap = (HashMap<Integer, Integer>) A.seq.clone();

                        int duplicates = 0;
                        for (Map.Entry<Integer,Integer> e:B.seq.entrySet()){
                            if(newMap.put(e.getKey(),e.getValue()) != null) {
                                duplicates++;
                                if(duplicates > 1)break;
                            }
                        }

                        boolean canCombine = duplicates <= 1;
                        if(canCombine){
                            Rope newRope = new Rope(newMap,A.connectIndex, B.connectIndex);
                            newRopes.add(newRope);
                            indexes.add(newRope.connectIndex[0]);
                            indexes.add(newRope.connectIndex[1]);
                            delRope.add(A);
                            delRope.add(B);

                        }

                        System.out.println("Combine @ index: " + i + " cA0: " + A.connectIndex[0] + " cA1: " + A.connectIndex[1]+ " cB0: " + B.connectIndex[0] + " cB1: " + B.connectIndex[1]);
                    }

                }
            }


            for (int index:indexes) {
                List<Rope> indList = adjArr.get(index);
                indList.removeAll(delRope);
            }


            for(Rope r:newRopes){
                int indA = r.connectIndex[0];
                int indB = r.connectIndex[1];

                List<Rope> A = adjArr.get(indA);
                List<Rope> B = adjArr.get(indB);
                A.add(r);B.add(r);
                if(longestPath.seq.size() < r.seq.size()) {
                    longestPath = r;
                }
            }

        }


        return longestPath;

        //size == 1 len = 2
        //winner fori size > len
    }

    private static Rope seedAdjArray(int n, int d, int m, int[] arr, List <List <Rope>> adjArr) {
        for (int i = 0; i < n; i++) {
            List<Rope> x = new ArrayList<>();
            adjArr.add(x);
        }
        Rope longestPath = new Rope(0,1);
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= d; j++) {
                int o = i-j;
                if (o < 0) continue;
                if (Math.abs(arr[i] - arr[o]) <= m){
                    Rope newRope = new Rope(i,o);
                    List <Rope> ropeR= adjArr.get(i);
                    List <Rope> ropeL= adjArr.get(o);
                    ropeR.add(newRope);
                    ropeL.add(newRope);
                    if(longestPath.seq.size() < newRope.seq.size()) longestPath = newRope;
                }
            }
        }

        return longestPath;
    }

    private static void getTestSets(List<int[][]> testSets) {
        //1
        int[][] set = new int[][]{
                {8, 3, 1},
                {1, 7, 8, 2, 6, 4, 3, 5},
                {8}};
        testSets.add(set);
        //2
        set = new int[][]{
                {8, 2, 1},
                {1, 7, 8, 2, 6, 4, 3, 5},
                {3}};
        testSets.add(set);
        //3
        set = new int[][]{
                {8, 1, 1},
                {1, 7, 8, 2, 6, 4, 3, 5},
                {2}};
        testSets.add(set);
        //4
        set = new int[][]{
                {20, 2, 1},
                {20, 1, 19, 2, 18, 3, 17, 4, 16, 5, 15, 6, 14, 7, 13, 8, 12, 9, 11, 10},
                {20}};
        testSets.add(set);
        //5
        set = new int[][]{
                {11, 2, 2},
                {1, 9, 2, 8, 3, 7, 4, 6, 0, 8, 9},
                {8}};
        testSets.add(set);
        //6
        set = new int[][]{
                {20, 4, 2},
                {20, 1, 19, 2, 18, 3, 17, 4, 16, 5, 15, 6, 14, 7, 13, 8, 12, 9, 11, 10},
                {20}};
        testSets.add(set);
        //7
        set = new int[][]{
                {10, 7, 1},
                {-1, -1, -1, -1, 0, 1, 1, 1, 1, 1},
                {10}};
        testSets.add(set);
    }


}
class Rope implements Comparable<Rope>{
    HashMap<Integer,Integer> seq;
    static int nextID = 0;
    int ID;
    int[] connectIndex;
    public Rope(Integer indexA, int indexB) {
        this.ID = nextID;
        nextID++;
        this.connectIndex = new int[] {indexA,indexB};

        this.seq = new HashMap<>();
        this.seq.put(indexA,indexA);
        this.seq.put(indexB,indexB);
    }
    public Rope(HashMap<Integer,Integer> map, int[] connectIndexA, int[] connectIndexB){
        this.ID = nextID;
        nextID++;

        this.seq = map;
        this.connectIndex = new int[2];
        if(connectIndexA[1] == connectIndexB[1]) {this.connectIndex[0] = connectIndexA[0]; this.connectIndex[1] = connectIndexB[0];}
        else if(connectIndexA[0] == connectIndexB[1]) {this.connectIndex[0] = connectIndexA[1]; this.connectIndex[1] = connectIndexB[0];}
        else if(connectIndexA[1] == connectIndexB[0]) {this.connectIndex[0] = connectIndexA[0]; this.connectIndex[1] = connectIndexB[1];}
        else if(connectIndexA[0] == connectIndexB[0]) {this.connectIndex[0] = connectIndexA[1]; this.connectIndex[1] = connectIndexB[1];}
    }

    @Override
    public String toString() {
        return "ID: " + ID + " Seq{" +
                "seq=" + seq +
                '}';
    }

    @Override
    public int compareTo(Rope o) {
        Integer idA = this.ID;
        Integer idB = o.ID;
        return idA.compareTo(idB);
    }
}
