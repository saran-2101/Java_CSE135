import java.util.Scanner;
public class Day4 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();

        System.out.println("Sum of Number");
        int sum = 0;
        if(a>0){
            for(int i=1;i<=a;i++){
            sum = sum + i;
            }
        }
        System.out.println("Sum Value: " + sum);

        //Odd or Even
        int even = 0;
        int odd = 0;
        if(a>0){
            for(int i=1;i<=a;i++){
                if(i%2==0){
                    even = even + i;
                }
                else{
                    odd = odd + i;
                }
            }
        }
        System.out.println("\nEven sum value:" + even);
        System.out.println("Odd sum value:" + odd);

        //Reverse a Number
        System.out.println("\nReverse a Number");
        int rev = sc.nextInt();
        int v = 0;

        for (int i = rev; i > 0; i /= 10) {
            int digit = i % 10;
            v = v * 10 + digit;
            System.out.println(v);
        
            }

        System.out.println(v);

        // //Star Patern 1
        System.out.println("\nStar Patern 1");
        int n = sc.nextInt();
        for(int i=1;i<=n;i++){
            for(int j=1;j<=i;j++){
                System.out.print(j + "  ");
            }
            System.out.println();
        }
        
        int table = sc.nextInt();
        for(int i=1;i<=10;i++){
            System.out.println(table + "*" + i + "=" + table*i);
        }

        // Star Patern 2
        System.out.println("\nStar Patern 2");
        // int n = sc.nextInt();
        int k = 1;
        int space = n/2;
        for (int i=0;i<n;i++){
            for (int j=0;j<space;j++){
                System.out.print("  ");
            }
            for (int j=0;j<k;j++){
                System.out.print("* ");
            }
            k+=i<n/2?2:-2;
            space+=i<n/2?-1:1;
            System.out.println();
        }

        //Palindrom
        // System.out.println("\nPalindrom");
    }
}
        


