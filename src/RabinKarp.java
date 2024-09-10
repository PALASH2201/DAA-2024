import java.util.*;
public class RabinKarp {

    public static int d = 256;
    public static int q = 101;

    public static void main(String[] args) {
          Scanner sc = new Scanner(System.in);
         String pattern = "ABXAB";
         String text = "ABXABABBXAB";
         searchPattern(pattern,text);
    }
    public static void searchPattern(String pattern, String text){
        int M = pattern.length();
        int N = text.length();
        int p =0;
        int t=0;
        int h=1;
        int i,j;

        for(i=0;i<M-1;i++){
            h = (h*d) % q;
        }

        for(i=0;i<M;i++){
            p = (d*p + pattern.charAt(i))%q;
            t = (d*t + text.charAt(i))%q;
        }

        for(i=0;i<=N-M;i++){
            if(p==t){
                for(j=0;j<M;j++){
                    if(text.charAt(i+j) != pattern.charAt(j))
                        break;
                }
                if(j==M){
                    System.out.println("Pattern found at index: "+i);
                }
            }
            else{
                t = (d * (t - text.charAt(i) * h) + text.charAt(i+M))%q;
                if(t<0)
                    t = t+q;
            }
        }
    }
}
