import java.util.*;
public class MatrixChain {
    static int[][] m;
    static int[][] k_value;
    static int n;
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter size of array:");
        n = sc.nextInt();
        int[] p = new int[n];
        m = new int[n+1][n+1];
        k_value = new int[n+1][n+1];
        System.out.println("Enter P array");
        for(int i=0;i<p.length;i++){
            p[i] = sc.nextInt();
        }
        for(int i=1;i<n;i++){
            for(int j=1;j<n;j++){
                m[i][j] = findCost(p,i,j);
            }
        }
        System.out.println("Matrix M:");
        display(m);
        System.out.println("K value matrix:");
        display(k_value);
        print_parenthesis(k_value,1,n-1);
    }
    public static int findCost(int[] p,int i , int j){
       if(i==j)
           return 0;
       if(i>j)
           return -1;
       m[i][j] = Integer.MAX_VALUE;
           for(int k=i;k<j;k++){
               int cost = Math.min(m[i][j],findCost(p,i,k)+findCost(p,k+1,j)+p[i-1]*p[k]*p[j]);
               if(cost < m[i][j]){
                   m[i][j] = cost;
                   k_value[i][j] = k;
               }
           }
       return m[i][j];
    }
    public static void display(int[][] a){
        System.out.print("\t");
        for(int i=1;i<n;i++){
            System.out.print(i+"\t");
        }
        System.out.println();
        for(int i=1;i<n;i++){
            System.out.print(i+"\t");
            for(int j=1;j<n;j++)
            {
                if(i<=j)
                    System.out.print(a[i][j]+"\t");
                else
                    System.out.print("\t");
            }
            System.out.println();
        }
    }
    public static void print_parenthesis(int[][] k_value, int i, int j ){
        if(i==j){
            System.out.print(" A{" +i+"} ");
        }else {
            System.out.print(" (");
            print_parenthesis(k_value,i,k_value[i][j]);
            System.out.print("x");
            print_parenthesis(k_value,k_value[i][j] + 1,j);
            System.out.print(") ");
        }
    }
}
