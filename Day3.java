import java.util.Scanner;

public class Day3{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        //Code 1 Odd or Even
        System.out.println("Find Odd or Even");
        int a = sc.nextInt();
        if (a%2==0){
            System.out.println(a + "is Even Number");
        }
        else{
            System.out.println(a + "is Odd Number");
        }

        //Code 2 Max value
        System.out.println("Find Max value");
        int n1 = sc.nextInt();
        int n2 = sc.nextInt();
        int n3 = sc.nextInt();
        if (n1 > n2 && n1 > n3){
            System.out.println(n1 + " is greater");
        }
        else if (n2 > n1 && n2 > n3){
            System.out.println(n2 + " is greater");
        }
        else{
            System.out.println(n3 + " is greater");    

        }
        
        //Code 3 Leap Year
        System.out.println("Find Leap Year");
        int leep = sc.nextInt();
        if (leep%4==0 && leep%4!=0){
            System.out.println(leep + " is a leap year");
        }
        else{
            System.out.println(leep + " is not a leap year");
        }

        //Code 4 Positive or Negative
        System.out.println("Positive or Negative");
        int value = sc.nextInt();
        if(value<0){
            System.out.println(value + " is Negative Number");
        }
        else{
            System.out.println(value +" is Positive Number");
        }

        //Code 5 
        


    }
}
