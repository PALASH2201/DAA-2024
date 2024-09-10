
import java.util.*;
public class BellmanFord {
    public static void main(String[] args) {
      int[][] edges = {{4,1,-1},{2,0,-1},{0,3,-1},{4,3,-1},{1,4,-1},{0,2,-1},{3,0,-1},{3,4,-1}};
      int V=5;
      int S=0;
      int[] distance = findShortestPath(edges,V,S);
      if(distance==null)
          System.out.println("Negative weight cycle exists");
      else{
          for(int i=0;i<distance.length;i++){
              System.out.println("Distance from "+S+" to "+i+" is "+distance[i]);
          }
      }
    }
    public static int[] findShortestPath(int[][] edges,int V,int S){
        int[] ans = new int[V];
        Arrays.fill(ans,Integer.MAX_VALUE);
        ans[S] = 0;
        for(int count=1;count<= V-1; count++){
            for(int[] edge : edges){
                int src = edge[0];
                int dst = edge[1];
                int distance = edge[2];

                if(ans[src] != Integer.MAX_VALUE && ans[dst] > ans[src] + distance){
                    ans[dst] = ans[src] + distance;
                }
            }
        }
        for(int[] edge : edges){
            int src = edge[0];
            int dst = edge[1];
            int distance = edge[2];

            if(ans[src] != Integer.MAX_VALUE && ans[dst] > ans[src] + distance){
                return null;
            }
        }
        return ans;
    }
}
