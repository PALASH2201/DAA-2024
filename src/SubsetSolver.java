import java.util.*;

class SubsetSolution{
    Scanner sc = new Scanner(System.in);
    int n;
    int[] set;
    int targetSum,tempSum=0;
    ArrayList<Integer> list = new ArrayList<>();
    SubsetSolution(int n){
        this.n = n;
        set = new int[n];
        System.out.println("Enter the set:");
        for(int i=0;i<n;i++){
            set[i] = sc.nextInt();
        }
        System.out.println("Enter the target sum:");
        targetSum = sc.nextInt();
    }
    void findSet(int index){
        if(index == n){
            return;
        }
        for(int i=index;i<n;i++){
            if(set[i] + tempSum <= targetSum){
                tempSum+= set[i];
                list.add(set[i]);
                if(tempSum == targetSum){
                    System.out.println("Solution:");
                    System.out.println(list);
                }
                findSet(i+1);

                tempSum-= set[i];
                list.removeLast();
            }
        }
    }
}
public class SubsetSolver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of elements in set:");
        int n = sc.nextInt();
        SubsetSolution subsetSolution = new SubsetSolution(n);
        subsetSolution.findSet(0);
    }
}
