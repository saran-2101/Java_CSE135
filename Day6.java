import java.util.Arrays;
import java.util.Scanner;
public class Day6{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        // Problem 1 Count common value
        // int count = 0;
        // int arr[] = {10,20,10,40,20,20,10}; 
        // for(int i:arr){
        //     if(i==10){
        //         count+=1;
        //     }
        // }
        // System.out.println("10 occurs " + count +" times" ); 
        
        
        // Problem 2 Missing Positive Integer
        // int arr[] = {5,3,-1,2,1,8}; 
        // int n = 1;
        // while (true) {
        //     boolean found = false;
        //     for (int i : arr) {
        //         if (i == n) {
        //             found = true;
        //             break;
        //         }
        //     }
        //     if (!found) {
        //         System.out.println(n);
        //         break;
        //     }
        //     n++;
        // }


        // Problem 3 Swap
        // int arr[] = {5,3,-1,2,1,8};
        // int temp = 0;
        // for(int i=0;i<arr.length;i++){  
        //     for(int j=i+1;j<arr.length;j++){
        //         if(arr[j]<arr[i]){
        //             temp = arr[j];
        //             arr[j]=arr[i]; 
        //             arr[i]=temp;
        //         }
        //         // if(arr1[i]>0){
        //         //     arr2[i]=arr1[i];
        //         // }
        //     }
        // }
        // IO.print(Arrays.toString(arr));


        // Problem 4 First and Last index Value
        // int a[] = {10,20,30,20,40,20};
        // int target = 20;
        // int first = 0;
        // int last = 0;
        // for(int i=0;i<a.length;i++){
        //     if (a[i] == target){
        //         if (first == 0)
        //             first = i;
        //         last = i;
        //     }
        // } 
        // System.out.println("First Ocurrence:" + first + "\n"
        //     + "Last Ocurrence:" + last);


        //Problem 5 Leat code 283(Move Zeroes)
        // int num[] = {0,1,0,3,12};
        // int arr[] = new int[num.length];
        // int c=0;
        // for(int i=0;i<num.length;i++){
        //     if(num[i]>0)
        //         arr[c++]=num[i];
        // }
        // IO.print(Arrays.toString(arr));

        //Problem 6 Leat code 44


        //Problem 7 Leat code 136(Single Number)
        // int nums[] = {2,2,1};//4,1,2,1,2
        // for (int i = 0; i < nums.length; i++) {
        //     int count = 0;
        //     for (int j = 0; j < nums.length; j++) {
        //         if (nums[i] == nums[j]) {
        //             count++;
        //         }
        //     }
        //     if (count == 1) {
        //         IO.print(nums[i]);
        //     }
        // }
        

        //Problem 8 Seperate Odd and Even in an Array
        // int arr[] = {6,1,0,2,9,5,6,3,7,4};
        // int ec = 0, oc = 0;
        // for(int i:arr){
        //     if(i%2==0)
        //         ec++;
        //     else
        //         oc++;
        // }
        // int Even[] = new int[ec];
        // int Odd[]= new int[oc];
        // ec=0; oc=0;
        // for(int i=0;i<arr.length;i++){
        //     if(arr[i]%2==0)
        //         Even[ec++]=arr[i];
        //     else
        //         Odd[oc++]=arr[i];   
        // }
        // IO.print(Arrays.toString(Even) + "\n" 
        // + Arrays.toString(Odd));


        //Problem 9 Count Odd and Even in an Array 
        // int arr[] = {6,1,0,2,9,5,6,3,7,4};
        // int even = 0;
        // int odd = 0 ;
        // for(int i:arr){
        //     int c = i%2==0?even++:odd++;
        // }
        // IO.println(even + "\n" + odd);
    
    }
}