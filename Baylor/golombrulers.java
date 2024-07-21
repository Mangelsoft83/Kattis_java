import java.util.*;

public class golombrulers {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);



        while (scanner.hasNextLine()){
            List<Integer> nums = new ArrayList<>();
            String line = scanner.nextLine();
            String[] split = line.split(" ");

            for (String s:split){
                nums.add(Integer.valueOf(s));
            }

            Collections.sort(nums);

            TreeSet<Integer> ds = new TreeSet<>();

            //System.out.println("arr: " + nums);

            boolean isRuler = makeGb(nums, ds);
            if (!isRuler) {
                System.out.println("not a ruler");
                continue;
            }

            List<Integer> missing = new ArrayList<>();

            for (int i = 1; i < nums.get(nums.size()-1); i++) {
                if(!ds.contains(i)) missing.add(i);
            }

            if (missing.isEmpty()){
                System.out.println("perfect");
            }else {
                StringBuilder result = new StringBuilder("missing");
                for(int n:missing) result.append(" ").append(n);
                System.out.println(result);
            }
            //System.out.println("deltas: " + ds);
        }


    }

    private static boolean makeGb(List<Integer> nums, TreeSet<Integer> ds) {
        for (int i = nums.size() - 1; i >= 1; i--) {
            for (int j = 0; j <= i-1; j++) {
                int d = (nums.get(i) - nums.get(j));
                if (!ds.add(d)) return false;
                //System.out.println(i + " " + j + " " + d);
                //if(inSet) System.out.println(" Duplicate");
            }
        }

        return true;
    }
}
