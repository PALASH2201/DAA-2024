import java.util.*;
public class Knapsack {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter number of items:");
        int n = sc.nextInt();
        System.out.println("Enter maximum capacity of bag:");
        int W = sc.nextInt();
        int[] w = new int[n];
        int[] p = new int[n];
        System.out.println("Enter the weights of items in increasing order:");
        for (int i=0;i<n;i++)
            w[i] = sc.nextInt();
        System.out.println("Enter the corresponding profits of the items:");
        for (int j=0;j<n;j++)
            p[j] = sc.nextInt();
        int[][] dp = new int[n+1][W+1];
        long startTime = System.nanoTime();
        System.out.println("Maximum profit: "+findCost(dp,w,p,W,n));
        System.out.println("Time taken:"+(System.nanoTime() - startTime)+" nanoseconds.");
    }
    public static int findCost(int[][] dp,int[] w,int[] p , int W , int n){
        for(int i=0;i<=n;i++)
            dp[i][0] =0;
        for(int j=0;j<=W;j++)
            dp[0][j] = 0;
        for(int i=1;i<=n;i++){
            for (int j=1;j<=W;j++){
                if(j<w[i-1])
                    dp[i][j] = dp[i-1][j];
                else{
                    int temp = p[i-1] + dp[i-1][j-w[i-1]];
                    dp[i][j] = Math.max(dp[i-1][j],temp);
                }
            }
        }
        return dp[n][W];
    }
}
