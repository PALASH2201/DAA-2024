import java.util.*;

public class Sorting {
    public static void main(String[] args) {
         int [] input = {4,3,2,10,12,1,5,6};
         System.out.println("Before Sorting:\n"+Arrays.toString(input));
         insertionSort(input);
         selectionSort(input);
         System.out.println("After Sorting:\n"+Arrays.toString(input));
    }
    public static void insertionSort(int[] input){
        int key = 0 ,j=0;
        for(int i=1;i<input.length;i++){
            key = input[i];
            j=i-1;
            while(j>=0 && input[j] >= key){
                input[j+1] = input[j];
                j--;
            }
            input[j+1] = key;
        }
    }
    public static void selectionSort(int[] input){
        int min = 0 ;
        for(int i=0;i<input.length-1;i++){
            min = i;
            for (int j=i+1;j<input.length;j++){
                if(input[j] < input[min]){
                    min = j;
                }
            }
            int temp = input[min];
            input[min] = input[i];
            input[i] = temp;
        }
    }
}