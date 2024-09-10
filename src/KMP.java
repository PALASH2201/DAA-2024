import java.util.*;
public class KMP {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter text:");
        String text = sc.next();
        System.out.println("Enter Pattern:");
        String pattern = sc.next();
        findIndex(text,pattern);
    }
    public static void findIndex(String text,String pattern){
        int m = pattern.length();
        int n = text.length();

        int[] lps = computeLPS(pattern);

        int i=0,j=0;
        while((n-i) >= (m-j)){
            if(pattern.charAt(j) == text.charAt(i)){
                i++;
                j++;
            }
            if(j == m){
                System.out.println("Pattern found at index:"+(i-j));
                j = lps[j-1];
            }
            else if(i < n && pattern.charAt(j) != text.charAt(i)){
                if(j!=0){
                    j = lps[j-1];
                }else{
                    i++;
                }
            }
        }
    }
    public static int[] computeLPS(String pattern){
        int m = pattern.length();
        int len = 0;
        int[] lps = new int[m];
        int i=1;
        lps[0] = 0;
        while(i<m){
            if(pattern.charAt(i) == pattern.charAt(len)){
                len++;
                lps[i] = len;
                i++;
            }
            else{
                if(len !=0){
                    len = lps[len - 1];
                }
                else{
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return  lps;
    }
}
