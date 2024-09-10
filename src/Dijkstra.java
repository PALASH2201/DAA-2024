import java.util.*;

class Pair implements Comparable<Pair>{
     int v;
     double wt;

     Pair(int v,double wt){
         this.v = v;
         this.wt = wt;
     }

     public int compareTo(Pair that){
         return (int)this.wt - (int)that.wt;
     }
}

public class Dijkstra {
    public static void main(String[] args) {
       Scanner  sc = new Scanner(System.in);
       System.out.println("Enter number of vertices");
       int V = sc.nextInt();
       ArrayList<ArrayList<ArrayList<Double>>> adj = new ArrayList<>();
//        adj.add(new ArrayList<>(Arrays.asList(new ArrayList<>(Arrays.asList(1,2)),new ArrayList<>(Arrays.asList(4,8)))));
//        adj.add(new ArrayList<>(Arrays.asList(new ArrayList<>(Arrays.asList(0,2)),new ArrayList<>(Arrays.asList(4,2)),new ArrayList<>(Arrays.asList(2,3)))));
//        adj.add(new ArrayList<>(Arrays.asList(new ArrayList<>(Arrays.asList(1,3)),new ArrayList<>(Arrays.asList(3,1)))));
//        adj.add(new ArrayList<>(Arrays.asList(new ArrayList<>(Arrays.asList(2,1)),new ArrayList<>(Arrays.asList(4,1)))));
//        adj.add(new ArrayList<>(Arrays.asList(new ArrayList<>(Arrays.asList(0,8)),new ArrayList<>(Arrays.asList(1,2)),new ArrayList<>(Arrays.asList(3,1)))));
        for (int i = 0; i < 26; i++) {
            adj.add(new ArrayList<>());
        }
//        adj.get(0).add(new ArrayList<>(Arrays.asList(1, 2)));
//        adj.get(1).add(new ArrayList<>(Arrays.asList(2, 5)));
//        adj.get(2).add(new ArrayList<>(Arrays.asList(1, 5)));
//        adj.get(2).add(new ArrayList<>(Arrays.asList(4, 1)));
//        adj.get(3).add(new ArrayList<>(Arrays.asList(4, 20)));
//        adj.get(4).add(new ArrayList<>(Arrays.asList(1, 2)));
        adj.get(0).add(new ArrayList<>(Arrays.asList((double)1,0.693)));
        adj.get(0).add(new ArrayList<>(Arrays.asList((double)2,1.609)));
//        adj.get(0).add(new ArrayList<>(Arrays.asList((double)2,0.39)));
        adj.get(1).add(new ArrayList<>(Arrays.asList((double)2,0.693)));
//        adj.get(2).add(new ArrayList<>(Arrays.asList((double)4,0.17)));
//        adj.get(2).add(new ArrayList<>(Arrays.asList((double)3,0.04)));
        System.out.println("Enter the source vertex:");
        int S = sc.nextInt();
        HashMap<Integer,Integer> parent = new HashMap<>();
        double[] distance = findShortestDistance(adj,V,S,parent);
        for(int i=0;i<distance.length;i++){
            System.out.println("Distance from "+S+" to "+i+" is "+distance[i]+"\nPath:"+getPath(i,parent));
        }
    }
    public static double[] findShortestDistance(ArrayList<ArrayList<ArrayList<Double>>> adj , int V , int S,HashMap<Integer,Integer> parent){
        boolean[] visited = new boolean[V];
        PriorityQueue<Pair> q = new PriorityQueue<>();
        q.add(new Pair(S,0));
        double[] ans = new double[V];
        Arrays.fill(ans,Integer.MAX_VALUE);
        ans[S]  = 0;
        while(!q.isEmpty()){
            Pair cur = q.remove();
            int u = cur.v;

            if(visited[u])
                continue;

            visited[u] = true;

            ArrayList<ArrayList<Double>> neighbours = adj.get(u);

            for(ArrayList<Double> list:neighbours){
                double vertex =  list.get(0);
                double wt = list.get(1);
                if(ans[(int)vertex] > ans[u] + wt){
                    ans[(int)vertex] = ans[u] + wt;
                    q.add(new Pair((int)vertex,ans[(int) vertex]));
                    parent.put((int)vertex,u);
                }
            }
        }
        return  ans;
    }
    public static String getPath(int destination,HashMap<Integer,Integer> parent){
        if(parent.get(destination) == null)
            return destination+" ";
        StringBuilder path = new StringBuilder();
        path.insert(0,destination+" ");
        int current = destination;
        while (parent.containsKey(current)){
            current = parent.get(current);
            path.insert(0,current+" ");
        }
        return path.toString();
    }
}

