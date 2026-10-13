// Operatores in Java 
// 1.Arithmetic operators -- > +,-,*,/,%
// 2. Assignment operators -- > +=,-=.*=,/=,==
// 3. Relational operators -- >  ==,!=,>,<,>=,<=,  
// 4. Logical operators -- > &&(AND),||(OR),!(NOT)
// 5. Unary operators -- > +,-,++,--,!,~
// 6. Bitwise operators -- > &,\|,^,~
// 7. Shift operators -- > <<(Left shift), >>(right shift), >>>(Unsigned right shift)
// 8. Ternary operator -- > condition ? valueIfTrue : valueIfFalse

public class Day2 {
    public static void main(String[] args) {
        int a = 10, b = 3;

        // 1. ARITHMETIC OPERATORS
        System.out.println("1. Arithmetic Operators");
        System.out.println("Addition: " + (a + b));
        System.out.println("Subtraction: " + (a - b));
        System.out.println("Multiplication: " + (a * b));
        System.out.println("Division: " + (a / b));
        System.out.println("Modulus: " + (a % b));

        // 2. ASSIGNMENT OPERATORS
        System.out.println("\n2. Assignment Operators");
        int x = 10;
        x += 5;
        System.out.println("x += 5: " + x);
        x -= 3;
        System.out.println("x -= 3: " + x);
        x *= 2;
        System.out.println("x *= 2: " + x);
        x /= 4;
        System.out.println("x /= 4: " + x);
        x %= 3;
        System.out.println("x %= 3: " + x);

        // 3. RELATIONAL OPERATORS
        System.out.println("\n3. Relational Operators");
        System.out.println("a == b: " + (a == b));
        System.out.println("a != b: " + (a != b));
        System.out.println("a > b: " + (a > b));
        System.out.println("a < b: " + (a < b));
        System.out.println("a >= b: " + (a >= b));
        System.out.println("a <= b: " + (a <= b));

        // 4. LOGICAL OPERATORS
        System.out.println("\n4. Logical Operators");
        boolean p = true, q = false;
        System.out.println("p && q: " + (p && q));
        System.out.println("p || q: " + (p || q));
        System.out.println("!p: " + (!p));

        // 5. UNARY OPERATORS
        System.out.println("\n5. Unary Operators");
        int n = 5;
        System.out.println("Unary plus: " + (+n));
        System.out.println("Unary minus: " + (-n));
        System.out.println("Pre-increment: " + (++n));
        System.out.println("Post-increment: " + (n++));
        System.out.println("After increment: " + n);
        System.out.println("Pre-decrement: " + (--n));
        System.out.println("Post-decrement: " + (n--));
        System.out.println("After decrement: " + n);
        System.out.println("Logical NOT: " + (!p));
        System.out.println("Bitwise complement: " + (~n));

        // 6. BITWISE OPERATORS
        System.out.println("\n6. Bitwise Operators");
        int c = 5, d = 3;
        System.out.println("c & d: " + (c & d));
        System.out.println("c | d: " + (c | d));
        System.out.println("c ^ d: " + (c ^ d));
        System.out.println("~c: " + (~c));

        // 7. SHIFT OPERATORS
        System.out.println("\n7. Shift Operators");
        int num = 8;
        System.out.println("Left shift (8 << 1): " + (num << 1));
        System.out.println("Right shift (8 >> 1): " + (num >> 1));
        System.out.println("Unsigned right shift (8 >>> 1): " + (num >>> 1));

        // Negative number example for signed vs unsigned shift
        int negative = -8;
        System.out.println("-8 >> 1: " + (negative >> 1));
        System.out.println("-8 >>> 1: " + (negative >>> 1));

        // 8. TERNARY OPERATOR
        System.out.println("\n8. Ternary Operator");
        int max = a > b ? (a > c ? a : c) : (b > c ? b : c);    
        System.out.println(max);

    
        
        int f = 4567;

        int sum = f%10;
        f = f/10;
        sum = sum + f%10;
        f = f/10;
        sum = sum + f%10;
        f = f/10;
        System.out.println(sum + f);
    }
}

