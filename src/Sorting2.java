import java.util.*;

public class Sorting2 {
    public static void main(String[] args) {
        int [] input = {4,3,2,10,12,1,5,6};
        System.out.println("Before Sorting:\n"+Arrays.toString(input));
        //MergeSort(input,0,input.length-1);
        quickSort(input,0,input.length-1);
        System.out.println("After Sorting:\n"+Arrays.toString(input));
    }
    public static void MergeSort(int[] input , int beg , int end){
        if(beg < end){
            int mid = (beg+end) / 2;
            MergeSort(input,beg,mid);
            MergeSort(input,mid+1,end);
            Merge(input,beg,mid,end);
        }
    }
    public static void Merge(int[] input , int beg, int mid, int end){
        int i,j,k;
        int n1 = mid - beg + 1;
        int n2 = end - mid;

        int[] leftArray= new int[n1];
        int[] rightArray= new int[n2];

        for(i=0;i<n1;i++){
            leftArray[i] = input[beg+i];
        }

        for(j=0;j<n2;j++){
            rightArray[j] = input[mid+1+j];
        }

        i=0;j=0;k=beg;
        while(i<n1 && j<n2){
            if(leftArray[i] < rightArray[j]){
                input[k] = leftArray[i];
                i++;
            }
            else{
                input[k] = rightArray[j];
                j++;
            }
            k++;
        }
        while(i<n1){
            input[k] = leftArray[i];
            i++;
            k++;
        }
        while(j<n2){
            input[k] = rightArray[j];
            j++;
            k++;
        }
    }
    public static void quickSort(int[] input , int beg , int end){
        if(beg < end){
            int p = partition(input, beg, end);
            quickSort(input,beg,p-1);
            quickSort(input,p+1,end);
        }
    }
    public static int partition(int[] input , int beg , int end){
        int pivot = input[end] , temp = 0;
        int i = beg - 1;
        for(int j = beg ;j <=end-1  ;j++){
            if(input[j] < pivot) {
                i++;
                temp = input[i];
                input[i] = input[j];
                input[j] = temp;
            }
        }
        temp = input[end];
        input[end] = input[i+1];
        input[i+1] = temp;
        return i+1;
    }
}
