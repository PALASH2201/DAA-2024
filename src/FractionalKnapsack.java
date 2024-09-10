import java.util.* ;

class Weight{
    double wt;
    double p;
    double ratio;

    Weight(double wt,double p){
        this.wt = wt;
        this.p = p;
        this.ratio = p/wt;
    }
    void sort(Weight[] items){
        int max;
        for(int i=0;i<items.length-1;i++){
            max = i;
            for(int j=i+1;j<items.length;j++){
                if(items[j].ratio > items[max].ratio){
                    max = j;
                }
            }
            Weight temp = items[i];
            items[i] = items[max];
            items[max] = temp;
        }
    }
}

public class FractionalKnapsack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of items:");
        int n = sc.nextInt();
        Weight[] items = new Weight[n];
        for(int i=0;i<n;i++){
            System.out.println("Enter weight of item:"+(i+1));
            double wt = sc.nextDouble();
            System.out.println("Enter profit of item:"+(i+1));
            double p = sc.nextDouble();
            items[i] =new Weight(wt,p);
        }
        System.out.println("Enter maximum capacity of bag:");
        double W = sc.nextDouble();
        items[0].sort(items);
        double sum=0;
        double profit=0;
        int i=0;
        while(sum<=W){
            double w = items[i].wt;
            if((sum+w) <= W){
                System.out.println("Item with weight "+w+" and profit "+items[i].p+" is added");
                sum+= w;
                profit+= items[i].p;
                i++;
            }
            else{
                double rem = W - sum;
                double frac = rem/w;
                double frac_profit = frac * items[i].p;
                System.out.println("Item with weight "+rem+" and profit "+frac_profit+" is added");
                profit+= frac_profit;
                break;
            }
        }
        System.out.println("Maximum profit: "+profit);
    }
}
