import java.util.Scanner;

public class Day3{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        //Code 1 Odd or Even
        System.out.println("Find Odd or Even");
        System.out.println("Enter Value: ");
        int OE = sc.nextInt();
        if (OE%2==0){
            System.out.println(OE + " is Even Number");
        }
        else{
            System.out.println(OE + " is Odd Number");
        }

        //Code 2 Max value
        System.out.println("\nFind Max value");
        System.out.println("Enter Value1: ");
        int n1 = sc.nextInt();
        System.out.println("Enter Value2: ");
        int n2 = sc.nextInt();
        System.out.println("Enter Value3: ");
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
        System.out.println("\nFind Leap Year");
        System.out.println("Enter Year: ");
        int leep = sc.nextInt();
        if (leep%4==0 && leep%4!=0){
            System.out.println(leep + " is a leap year");
        }
        else{
            System.out.println(leep + " is not a leap year");
        }

        //Code 4 Positive or Negative
        System.out.println("\nPositive or Negative");
        System.out.println("Enter Value: ");
        int value = sc.nextInt();
        if(value<0){
            System.out.println(value + " is Negative Number");
        }
        else{
            System.out.println(value +" is Positive Number");
        }

        //Code 5 
        System.out.println("\nPositive or Negative");
        boolean a = false;
        boolean b = true;
        int c=5;
        if(c<<2>15){
            if(b){
                a=!a;
                if(a){
                    b=!b;
                    if(b){
                        c+=5;
                    }
                }
            }
        }
        else{
            c+=10;
        }
        System.out.println(c);

        //Code 6 Switch 
        int DAY = sc.nextInt();
        switch(DAY){
            case 1:
                
        }
        //Switch without break()
        int day = sc.nextInt();
        switch(day){
            case 1, 2, 3, 4, 5 -> System.out.println("Weekdays");
            case 6, 7 -> System.out.println("Weekend");
            default -> System.out.println("Invalid");
        }


    }      
        
}
