import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


class Number {
    String value;
    int distance;

    Number(String value) {
        this.value = value;
        this.distance = 0;
    }
}

public class MakePowerOfTwo {
    /*
     * Algorithm used: BFS Root Node: the entered number Descendants: 
     * 1. n-case of 1-digit-removed number 
     * 2. 4 case of adding one digit to the right */

    public ArrayList<Number> generateDescendants(Number number) {
        ArrayList<Number> children = new ArrayList<Number>();

        String[] digits = number.value.split("");
        // First Case
        for (int i = 0; i < digits.length-1; i++) {

            String[] firstArray = Arrays.copyOfRange(digits, 0, i);
            String[] secondArray = Arrays.copyOfRange(digits, i+1, digits.length);

            String firstString = String.join("", firstArray);
            String secondString = String.join("", secondArray);
            String finalString = firstString + secondString;


            children.add(new Number(finalString));
        }

        // SecondCase
        String[] cases = { "2", "4", "6", "8" };

        for (int i = 0; i < cases.length; i++) {

            String finalString = number.value + cases[i];
        
            children.add(new Number(finalString));

        }
        return children;
    }

    public boolean checkPowerOfTwo(String number) {
        
        //If number begins with 0, then it still need one more step to remove that zero digit
        return number.charAt(0) != '0' && Math.floor(Math.log10(Long.valueOf(number))/Math.log10(2)) ==  Math.log10(Long.valueOf(number))/Math.log10(2);

    }

    public Integer bfs(String number) {

        int smallestStep = Integer.MAX_VALUE;
        Number newNumber = new Number(number);

        /*  BFS Procedure
        1  procedure BFS(G, root) is
        2      let Q be a queue
        3      label root as explored
        4      Q.enqueue(root)
        5      while Q is not empty do
        6          v := Q.dequeue()
        7          if v is the goal then
        8              return v
        9          for all edges from v to w in G.adjacentEdges(v) do
        10              if w is not labeled as explored then
        11                  label w as explored
        12                  Q.enqueue(w) */
        
        Queue<Number> q = new LinkedList<Number>();
        q.add(newNumber);

        while (!q.isEmpty()) {

            Number headNumber = q.poll();

            if (checkPowerOfTwo(headNumber.value)) {
                if (smallestStep > headNumber.distance) {
                    
                    smallestStep = headNumber.distance;

               }

            } else {

               ArrayList<Number> temp = generateDescendants(headNumber);
               for (Number v :temp) {

                   if (v.distance == 0) {

                       
                       v.distance = headNumber.distance + 1; 

                       if (v.distance <= number.length() + 1) {

                            q.add(v);

                       } else {
                           continue;
                       }

                   }
               }
            }
        }

        return smallestStep;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MakePowerOfTwo makePowerOfTwo = new MakePowerOfTwo();

        int t = sc.nextInt();
        sc.nextLine();
        String[] lstInt  = new String[t];

        for (int i = 0; i < t; i++) {

            lstInt[i] = sc.nextLine();

        }

        for (int i = 0; i < t; i++) {

            System.out.println(makePowerOfTwo.bfs(lstInt[i]));   

        }

        sc.close();
    }

}