import java.util.LinkedList;
import java.util.Scanner;

public class a2048 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = new int[4][4];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        int mode = scanner.nextInt();
        LinkedList<Integer> list0 = new LinkedList<>();
        LinkedList<Integer> list1 = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();
        LinkedList<Integer> list3 = new LinkedList<>();

        if(mode==0){//left
            // add to arraylist
            for (int i = 0; i < 4; i++) {
                list0.addLast(matrix[0][i]);
                list1.addLast(matrix[1][i]);
                list2.addLast(matrix[2][i]);
                list3.addLast(matrix[3][i]);
            }
        } else if (mode==1) {
            for (int i = 0; i < 4; i++) {
                list0.addLast(matrix[i][0]);
                list1.addLast(matrix[i][1]);
                list2.addLast(matrix[i][2]);
                list3.addLast(matrix[i][3]);
            }
        } else if (mode ==2) {
            for (int i = 3; i >= 0; i--) {
                list0.addLast(matrix[0][i]);
                list1.addLast(matrix[1][i]);
                list2.addLast(matrix[2][i]);
                list3.addLast(matrix[3][i]);
            }

        }else if (mode==3) {
            for (int i= 3; i>=0;i--) {
                list0.addLast(matrix[i][0]);
                list1.addLast(matrix[i][1]);
                list2.addLast(matrix[i][2]);
                list3.addLast(matrix[i][3]);
            }
        }

        //remove zeros
        for (int i = 0; i < list0.size(); i++) if (list0.get(i).equals(0)) {list0.remove(i);i--;}
        for (int i = 0; i < list1.size(); i++) if (list1.get(i).equals(0)) {list1.remove(i);i--;}
        for (int i = 0; i < list2.size(); i++) if (list2.get(i).equals(0)) {list2.remove(i);i--;}
        for (int i = 0; i < list3.size(); i++) if (list3.get(i).equals(0)) {list3.remove(i);i--;}

        //combine
        for (int i = 0; i < list0.size()-1; i++) {if(list0.get(i).equals(list0.get(i+1))){list0.set(i,list0.get(i) + list0.get(i+1));list0.remove(i+1);}}
        for (int i = 0; i < list1.size()-1; i++) {if(list1.get(i).equals(list1.get(i+1))){list1.set(i,list1.get(i) + list1.get(i+1));list1.remove(i+1);}}
        for (int i = 0; i < list2.size()-1; i++) {if(list2.get(i).equals(list2.get(i+1))){list2.set(i,list2.get(i) + list2.get(i+1));list2.remove(i+1);}}
        for (int i = 0; i < list3.size()-1; i++) {if(list3.get(i).equals(list3.get(i+1))){list3.set(i,list3.get(i) + list3.get(i+1));list3.remove(i+1);}}

        //add zeros
        while (list0.size() < 4) list0.addLast(0);
        while (list1.size() < 4) list1.addLast(0);
        while (list2.size() < 4) list2.addLast(0);
        while (list3.size() < 4) list3.addLast(0);


        //Print
        if(mode==0){
            for (int number:list0) {System.out.print(number + " ");}System.out.print("\n");
            for (int number:list1) {System.out.print(number + " ");}System.out.print("\n");
            for (int number:list2) {System.out.print(number + " ");}System.out.print("\n");
            for (int number:list3) {System.out.print(number + " ");}
        } else if (mode==1) {
            for (int i = 0; i < 4; i++) {
                System.out.println(list0.get(i) + " " + list1.get(i) + " " + list2.get(i) + " " + list3.get(i));
            }
        }else if (mode==2) {
            for (int i= 3; i>=0;i--) {System.out.print(list0.get(i) + " ");}System.out.print("\n");
            for (int i= 3; i>=0;i--) {System.out.print(list1.get(i) + " ");}System.out.print("\n");
            for (int i= 3; i>=0;i--) {System.out.print(list2.get(i) + " ");}System.out.print("\n");
            for (int i= 3; i>=0;i--) {System.out.print(list3.get(i) + " ");}System.out.print("\n");
        } else if (mode==3) {
            for (int i= 3; i>=0;i--) {
                System.out.println(list0.get(i) + " " + list1.get(i) + " " + list2.get(i) + " " + list3.get(i));
            }


        }

    }
}
