import java.util.*;

public class hopper_loopie {
    public static void main(String[] args) {
        boolean isKattis = true;
        if(isKattis){
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            int d = scanner.nextInt();
            int m = scanner.nextInt();

            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }

            int max = getLongestPath2(n, d, m, arr);

            System.out.println(max);

        }else{
            List<int[][]> testSets = new ArrayList<>();

            getTestSets(testSets);
            int setCount = 0;

            for (int[][]testSet:testSets) {
                long startTime = System.nanoTime();
                int n = testSet[0][0];int d = testSet[0][1];int m = testSet[0][2];int[] arr = testSet[1];int correctResult = testSet[2][0];
                int max = getLongestPath(n, d, m, arr);

                setCount++;

                long TimeNeeded =  (System.nanoTime() - startTime) / 1000;
                String test = correctResult == max ? "Correct: " + TimeNeeded + " ms": "Wrong \t correct answer: " + correctResult + "\t result: "  +  max + "\t" + Arrays.toString(arr);

                System.out.println(setCount + "\tResult: " + max + "\t" + test);
            }
        }
    }

    private static int getLongestPath2(int n, int d, int m, int[] arr) {
        List<Rope> queue = new ArrayList<>();
        int max = 0;

        for (int i = 0; i < n; i++) {
            addNewSeqToQueue(arr, queue, i);
            removeSeqNotInRange(d, queue, i);

            addNumberToSeqInQueue(d, m, arr, queue, i);

            combineSeqsInQueue(queue, (Integer) i);
        }


        System.out.println("Map in reverse order: "
                + queue);
        return 0;
    }

    private static void combineSeqsInQueue(List<Rope> queue, Integer i) {
        Collections.sort(queue);
        List <Rope> newSeqs= new ArrayList<>();
        for (int j = 0; j < queue.size(); j++) {
            Rope seqA = queue.get(j);
            for (int k = 0; k < j; k++) {
                Rope seqB= queue.get(j);
                if(seqA.connectIndex[1] == seqB.connectIndex[1] ||
                        seqA.connectIndex[0] == seqB.connectIndex[0] ||
                        seqA.connectIndex[1] == seqB.connectIndex[0] ||
                        seqA.connectIndex[0] == seqB.connectIndex[1])
                {
                    HashMap combMap = (HashMap) seqA.seq.clone();
                    for (Map.Entry x:seqB.seq.entrySet())
                    {
                        if(x.getKey() != i){
                            Object xx = combMap.put(x.getKey(),x.getValue());
                            if(xx != null) break; // duplicate is found. Can not merge!!
                        }
                    };

                    Rope seq = new Rope(combMap,seqA.connectIndex,seqB.connectIndex);
                    newSeqs.add(seq);

                }
            }
        }
        queue.addAll(newSeqs);
    }

    private static void addNumberToSeqInQueue(int d, int m, int[] arr, List<Rope> queue, int i) {
        for (Rope seq : queue) {
            if (seq.connectIndex[1] >= i - d && Math.abs(arr[seq.connectIndex[1]] - arr[i]) <= m) {
                seq.seq.put(i, arr[i]);
                seq.connectIndex[1] = i;
            } else if (seq.connectIndex[0] >= i - d && Math.abs(arr[seq.connectIndex[0]] - arr[i]) <= m) {
                seq.seq.put(i, arr[i]);
                seq.connectIndex[0] = i;
            }
        }
    }

    private static void removeSeqNotInRange(int d, List<Rope> queue, int i) {
        for (int j = 0; j < queue.size(); j++) {
            Rope seq = queue.get(j);
            if (seq.connectIndex[1] < i - d && seq.connectIndex[0] < i - d){
                queue.remove(j);j--;
            }
        }
    }

    private static void addNewSeqToQueue(int[] arr, List<Rope> queue, int i) {
        Rope newSeq = new Rope(i, arr[i]);
        queue.add(newSeq);
    }

    private static void getTestSets(List<int[][]> testSets) {
        //1
        int[][] set = new int[][] {
                {8,3,1},
                {1,7,8,2,6,4,3,5},
                {8}};
        testSets.add(set);
        //2
        set = new int[][] {
                {8,2,1},
                {1,7,8,2,6,4,3,5},
                {3}};
        testSets.add(set);
        //3
        set = new int[][] {
                {8,1,1},
                {1,7,8,2,6,4,3,5},
                {2}};
        testSets.add(set);
        //4
        set = new int[][] {
                {20,2,1},
                {20,1,19,2,18,3,17,4,16,5,15,6,14,7,13,8,12,9,11,10},
                {20}};
        testSets.add(set);
        //5
        set = new int[][] {
                {11,2,2},
                {1,9,2,8,3,7,4,6,0,8,9},
                {8}};
        testSets.add(set);
        //6
        set = new int[][] {
                {20,4,2},
                {20,1,19,2,18,3,17,4,16,5,15,6,14,7,13,8,12,9,11,10},
                {20}};
        testSets.add(set);
        //7
        set = new int[][] {
                {10,7,1},
                {-1,-1,-1,-1,0,1,1,1,1,1},
                {10}};
        testSets.add(set);

        //8 all zeros
        set = new int[][] {
                {1000,7,1},
                new int[1000],
                {1000}};
        testSets.add(set);
    }

    static int getLongestPath(int n, int d, int m, int[] arr) {
        int[] sol = new int[n];
        Arrays.fill(sol, 1);
        int max = 0;
        for (int i = 1; i < n; i++) {
            List<Integer> conn = new ArrayList<>();
            for (int j = Math.max(i- d,0); j < i; j++) {
                if(Math.abs(arr[i]- arr[j]) <= m){
                    sol[i] += sol[j];
                    sol[i] = Math.min(sol[i],i+1);
                    //solutionArray[j] ++;
                    max = Math.max(max,sol[i]);
                    conn.add(j);
                }

            }

            for(int ind:conn){
               sol[ind] = sol[i];
            }
        }
        return max;
    }

}

class Seq implements Comparable<Rope>{
    HashMap<Integer,Integer> seq;
    Integer index;
    int[] connectIndex;
    public Seq(Integer i,int num) {
        this.index = i;
        this.connectIndex = new int[] {i,i};

        this.seq = new HashMap<>();
        this.seq.put(i,num);
    }
    public Seq(HashMap<Integer,Integer> map,int[] connectIndexA, int[] connectIndexB){
        this.seq = map;
        this.connectIndex = new int[2];
        if(connectIndexA[1] == connectIndexB[1]) this.connectIndex[0] = connectIndexA[0]; this.connectIndex[1] = connectIndexB[0];
        if(connectIndexA[0] == connectIndexB[1]) this.connectIndex[0] = connectIndexA[1]; this.connectIndex[1] = connectIndexB[0];
        if(connectIndexA[1] == connectIndexB[0]) this.connectIndex[0] = connectIndexA[0]; this.connectIndex[1] = connectIndexB[1];
        if(connectIndexA[0] == connectIndexB[0]) this.connectIndex[0] = connectIndexA[1]; this.connectIndex[1] = connectIndexB[1];
    }

    @Override
    public String toString() {
        return "Seq{" +
                "seq=" + seq +
                '}';
    }

    @Override
    public int compareTo(Rope o) {
        Integer sizeA = this.seq.size();
        Integer sizeB = o.seq.size();
        return sizeA.compareTo(sizeB);
    }
}
