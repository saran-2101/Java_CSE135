import java.util.Arrays;
import java.util.Scanner;
public class Day7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // int n = sc.nextInt();

        //String Concept
        // String s1="Hello";
        // String s2="Hello";
        // String s3=new String("Hello"); 
        // System.out.println(s1==s2);
        // System.out.println(s1.equals(s3));


        //Prblem 1 Find Vowels & Consonants in String
        // String s="Hello W o rld";
        // int v=0;
        // int c=0;
        // String lower = s.toLowerCase();
        // for (int i = 0; i < lower.length(); i++){
        //     char ch = lower.charAt(i);
        //     if (ch >= 'a' && ch <= 'z'){
        //         if (ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u') {
        //             v++;
        //         } 
        //         else{
        //             c++;
        //         }
        //     }
        // }
        // // IO.println(s.length());
        // IO.println("Vowels: " + v);
        // IO.println("Consonants: " + c);


        //Problem 2 Palindrome
        // String str = "Amma";
        // String lower = str.toLowerCase();
        // int left = 0;
        // int right = lower.length()-1;
        // boolean isPalindrome = true;
        // while (left < right){
        //     if (lower.charAt(left)!=lower.charAt(right)){
        //         isPalindrome = false;
        //         break;
        //     }
        //     left++;
        //     right--;
        // }
        // System.out.println(isPalindrome?"Palindrome":"Not Palindrome");

        
        //problem 3 Check given Strings are Anagram
        // String s="listen";
        // String t="silent";
        // boolean r=true;
        // int [] a=new int[26];
        // for(int i=0;i<s.length();i++){
        // a[s.charAt(i)-'a']++;
        // a[t.charAt(i)-'a']--;
        // }
        // // System.out.print(Arrays.toString(a));
        // for(int i=0;i<a.length;i++){
        //     if(a[i]!=0){r=false;
        //         // System.out.print(i);
        //         break;
        //     }
        // }
        // System.out.println(r);
    
    
        //Problem 4 
        // char n = sc.next().charAt(0);
        // int i = n;
        // IO.print(i);

        //Problem 5
        String s = "456";
        String t = "123";
        
        int c = s.charAt(0)-'0';
        IO.print(c);

    }
}