# Java_CSE135

My Java learning journey, documented day by day as part of CSE coursework.

## Progress

| Day | Topics | Status |
|-----|--------|--------|
| [Day 1](#day-1) | Java basics, data types, variables, type conversion, JVM internals | Done |
| [Day 2](#day-2) | Operators in Java | Done |
| [Day 3](#day-3) | Conditional statements, switch case, loops, practice programs | Done |
| [Day 4](#day-4) | Loop practice: sums, reverse a number, patterns, multiplication table | Done |
| [Day 5](#day-5) | Arrays, 2D arrays, min/max, second largest and smallest | Done |
| [Day 6](#day-6) | Strings and String methods, array problems | Done |

---

## Day 1

### Topics Covered

1. [What is Java?](#1-what-is-java)
2. [JDK, JRE and JVM](#2-jdk-jre-and-jvm)
3. [Java Data Types](#3-java-data-types)
4. [Variables](#4-variables)
5. [Type Conversion and Casting](#5-type-conversion-and-casting)
6. [JVM Memory Layout](#6-jvm-memory-layout)
7. [Class Loading](#7-class-loading)
8. [Bytecode Verification](#8-bytecode-verification)

---

### 1. What is Java?

Java is a high-level, object-oriented, class-based programming language. Its core idea is **"Write Once, Run Anywhere" (WORA)**: Java source code is compiled into platform-independent **bytecode**, which runs on any machine that has a JVM.

Key features:
- Platform independent
- Object-oriented
- Strongly typed
- Automatic memory management (Garbage Collection)
- Secure (bytecode verification, no direct pointer access)
- Multithreaded

**How a Java program runs:**

```
Hello.java  --(javac)-->  Hello.class (bytecode)  --(JVM)-->  Machine code / Output
```

---

### 2. JDK, JRE and JVM

| Component | Full Form | Purpose |
|-----------|-----------|---------|
| **JDK** | Java Development Kit | Tools to develop Java programs (`javac`, debugger, etc.) plus the JRE |
| **JRE** | Java Runtime Environment | Libraries and files needed to run Java programs, plus the JVM |
| **JVM** | Java Virtual Machine | Executes bytecode; makes Java platform independent |

Relationship:

```
JDK = JRE + Development Tools
JRE = JVM + Class Libraries
```

---

### 3. Java Data Types

Java has two categories of data types: **Primitive** and **Non-Primitive**.

#### Primitive Data Types

| Type | Size | Range | Default |
|------|------|-------|---------|
| `byte` | 1 byte | -128 to 127 | 0 |
| `short` | 2 bytes | -32,768 to 32,767 | 0 |
| `int` | 4 bytes | -2^31 to 2^31 - 1 (about ±2.14 billion) | 0 |
| `long` | 8 bytes | -2^63 to 2^63 - 1 | 0L |
| `float` | 4 bytes | about ±3.4 × 10^38 (6-7 decimal digits precision) | 0.0f |
| `double` | 8 bytes | about ±1.7 × 10^308 (15-16 decimal digits precision) | 0.0d |
| `char` | 2 bytes | 0 to 65,535 (Unicode) | '\u0000' |
| `boolean` | JVM dependent (1 bit of information) | `true` / `false` | false |

#### Non-Primitive (Reference) Data Types

Created by the programmer (except `String`) and store a **reference** (address) to an object in the heap.

- `String`
- Arrays
- Classes
- Interfaces
- Enums

| Primitive | Non-Primitive |
|-----------|---------------|
| Predefined by Java | Created by the programmer (except String) |
| Stores the actual value | Stores the reference to an object |
| Cannot be `null` | Can be `null` |
| Fixed size | Size varies |
| Starts with a lowercase letter | Starts with an uppercase letter |

---

### 4. Variables

A variable is a named memory location that holds a value.

```java
dataType variableName = value;
int age = 20;
```

Types of variables:

| Type | Declared | Memory | Scope |
|------|----------|--------|-------|
| **Local** | Inside a method/block | Stack | Within the method/block |
| **Instance** | Inside a class, outside methods | Heap (part of object) | One copy per object |
| **Static** | Inside a class with `static` | Method Area | One copy shared by all objects |

```java
public class Demo {
    int instanceVar = 10;          // instance variable
    static int staticVar = 20;     // static variable

    void show() {
        int localVar = 30;         // local variable
    }
}
```

---

### 5. Type Conversion and Casting

#### Widening (Implicit / Automatic)

Smaller type to larger type. Done automatically, with no data loss.

```
byte -> short -> int -> long -> float -> double
                  ^
                 char
```

```java
int a = 100;
double b = a;   // 100.0
```

#### Narrowing (Explicit / Casting)

Larger type to smaller type. Must be done manually and may lose data.

```java
double d = 9.78;
int i = (int) d;   // 9 (decimal part lost)
```

---

### 6. JVM Memory Layout

```
+--------------------------------------------------+
|                      JVM                         |
|  +--------------------+  +--------------------+  |
|  |  Method Area       |  |       Heap         |  |
|  |  (Metaspace)       |  |  (Objects, arrays) |  |
|  +--------------------+  +--------------------+  |
|  +----------+ +--------------+ +---------------+ |
|  |  Stack   | | PC Register  | | Native Method | |
|  | (per     | | (per thread) | | Stack         | |
|  | thread)  | |              | |               | |
|  +----------+ +--------------+ +---------------+ |
+--------------------------------------------------+
```

| Area | Shared? | Stores |
|------|---------|--------|
| **Heap** | Shared by all threads | Objects, instance variables, arrays |
| **Method Area / Metaspace** | Shared | Class metadata, static variables, method code, runtime constant pool |
| **Stack** | Per thread | Stack frames: local variables, partial results, method calls |
| **PC Register** | Per thread | Address of the current JVM instruction |
| **Native Method Stack** | Per thread | Native (non-Java) method calls |

#### Heap

- Where all objects and arrays are created at runtime.
- Managed by the **Garbage Collector**, which removes objects that are no longer referenced.
- Divided into **Young Generation** (Eden + Survivor spaces) and **Old Generation**.

---

### 7. Class Loading

The **Class Loader Subsystem** loads `.class` files into memory when they are first needed. It works in three phases.

#### Phase 1: Loading

Reads the `.class` file and creates a representation of the class in the Method Area. Done by three class loaders:

| Class Loader | Loads |
|--------------|-------|
| **Bootstrap** | Core Java classes (`java.lang.*`, etc.) |
| **Platform (Extension)** | Platform/extension classes |
| **Application (System)** | Classes from the application classpath |

Class loaders follow the **delegation model**: a loader first asks its parent to load the class before trying itself.

#### Phase 2: Linking

1. **Verification**: checks that the bytecode is valid and safe
2. **Preparation**: allocates memory for static variables and assigns default values
3. **Resolution**: replaces symbolic references with direct references

#### Phase 3: Initialization

Assigns the actual values to static variables and runs static blocks.

```
Loading -> Linking (Verify -> Prepare -> Resolve) -> Initialization
```

---

### 8. Bytecode Verification

Before running, the **Bytecode Verifier** checks the loaded class to make sure the code is safe and well-formed. This is a big part of why Java is secure.

It checks that the code:
- Follows the JVM specification and has a valid class file format
- Does not violate access rules (e.g. accessing private members illegally)
- Has no illegal type conversions
- Does not cause stack overflow or underflow
- Uses variables and registers correctly

If verification fails, the JVM throws a `VerifyError` and the class is not executed.

---

## Quick Summary

- Java source -> `javac` -> bytecode -> JVM -> output
- JDK contains JRE, and JRE contains JVM
- 8 primitive types; everything else is a reference type
- Widening is automatic; narrowing needs an explicit cast
- Heap holds objects; stack holds method frames and local variables
- Class loading: Load, Link (verify, prepare, resolve), Initialize
- The bytecode verifier keeps the JVM safe

---

## Day 2

### Topics Covered

1. [Operators in Java](#operators-in-java)
2. [Arithmetic Operators](#1-arithmetic-operators)
3. [Unary Operators](#2-unary-operators)
4. [Assignment Operators](#3-assignment-operators)
5. [Relational Operators](#4-relational-operators)
6. [Logical Operators](#5-logical-operators)
7. [Bitwise Operators](#6-bitwise-operators)
8. [Shift Operators](#7-shift-operators)
9. [Ternary Operator](#8-ternary-operator)

---

### Operators in Java

Operators are special symbols that perform operations on **operands** (variables or values).

```java
int result = a + b;   // '+' is the operator, 'a' and 'b' are operands
```

| Category | Operators |
|----------|-----------|
| Arithmetic | `+` `-` `*` `/` `%` |
| Unary | `+` `-` `++` `--` `!` `~` |
| Assignment | `=` `+=` `-=` `*=` `/=` `%=` and more |
| Relational | `==` `!=` `>` `<` `>=` `<=` |
| Logical | `&&` `\|\|` `!` |
| Bitwise | `&` `\|` `^` `~` |
| Shift | `<<` `>>` `>>>` |
| Ternary | `? :` |

---

### 1. Arithmetic Operators

| Operator | Meaning | Example | Result |
|----------|---------|---------|--------|
| `+` | Addition (also string concatenation) | `10 + 3` | 13 |
| `-` | Subtraction | `10 - 3` | 7 |
| `*` | Multiplication | `10 * 3` | 30 |
| `/` | Division | `10 / 3` | 3 |
| `%` | Modulus (remainder) | `10 % 3` | 1 |

```java
int a = 10, b = 3;
System.out.println(a / b);      // 3   (integer division drops the decimal)
System.out.println(a / 3.0);    // 3.3333333333333335
System.out.println(a % b);      // 1
System.out.println("Hi " + a);  // Hi 10
```

Notes:
- Dividing two integers gives an integer; the decimal part is dropped.
- Integer division by zero throws `ArithmeticException`. Floating-point division by zero gives `Infinity`.

---

### 2. Unary Operators

Work on a single operand.

| Operator | Meaning |
|----------|---------|
| `+` | Unary plus |
| `-` | Unary minus (negates the value) |
| `++` | Increment by 1 |
| `--` | Decrement by 1 |
| `!` | Logical NOT (flips a boolean) |
| `~` | Bitwise complement |

#### Pre vs Post Increment

```java
int x = 5;
int a = ++x;   // pre: increment first, then use  -> x = 6, a = 6

int y = 5;
int b = y++;   // post: use first, then increment -> b = 5, y = 6
```

---

### 3. Assignment Operators

Assign a value to a variable. Compound operators combine an operation with assignment.

| Operator | Example | Same as |
|----------|---------|---------|
| `=` | `a = 5` | assign 5 to a |
| `+=` | `a += 3` | `a = a + 3` |
| `-=` | `a -= 3` | `a = a - 3` |
| `*=` | `a *= 3` | `a = a * 3` |
| `/=` | `a /= 3` | `a = a / 3` |
| `%=` | `a %= 3` | `a = a % 3` |
| `&=` `\|=` `^=` | `a &= 3` | `a = a & 3` etc. |
| `<<=` `>>=` `>>>=` | `a <<= 2` | `a = a << 2` etc. |

Compound assignment includes an **implicit cast**:

```java
byte b = 10;
b += 5;        // OK: works because of the implicit cast
// b = b + 5;  // Error: b + 5 is an int, needs an explicit cast
```

---

### 4. Relational Operators

Compare two values and return a `boolean`.

| Operator | Meaning | Example (a=10, b=5) | Result |
|----------|---------|---------------------|--------|
| `==` | Equal to | `a == b` | false |
| `!=` | Not equal to | `a != b` | true |
| `>` | Greater than | `a > b` | true |
| `<` | Less than | `a < b` | false |
| `>=` | Greater than or equal to | `a >= b` | true |
| `<=` | Less than or equal to | `a <= b` | false |

Note: for objects like `String`, `==` compares **references** (addresses), not content. Use `.equals()` to compare content.

```java
String s1 = new String("java");
String s2 = new String("java");
System.out.println(s1 == s2);       // false (different objects)
System.out.println(s1.equals(s2));  // true  (same content)
```

---

### 5. Logical Operators

Combine boolean expressions.

| Operator | Name | Result |
|----------|------|--------|
| `&&` | Logical AND | true if **both** are true |
| `\|\|` | Logical OR | true if **at least one** is true |
| `!` | Logical NOT | reverses the value |

Truth table:

| A | B | A && B | A \|\| B | !A |
|---|---|--------|----------|----|
| true | true | true | true | false |
| true | false | false | true | false |
| false | true | false | true | true |
| false | false | false | false | true |

**Short-circuit evaluation:**
- `&&`: if the left side is `false`, the right side is **not** evaluated.
- `||`: if the left side is `true`, the right side is **not** evaluated.

```java
int a = 5;
if (a > 10 && ++a > 0) { }   // ++a never runs, a stays 5
```

---

### 6. Bitwise Operators

Work on individual bits of integer types.

| Operator | Name | Rule |
|----------|------|------|
| `&` | AND | 1 only if both bits are 1 |
| `\|` | OR | 1 if at least one bit is 1 |
| `^` | XOR | 1 if the bits are different |
| `~` | NOT | flips every bit |

```java
int a = 5;   // 0101
int b = 3;   // 0011

a & b   // 0001 = 1
a | b   // 0111 = 7
a ^ b   // 0110 = 6
~a      // -6
```

`&` and `|` also work on booleans, but unlike `&&` and `||` they do **not** short-circuit.

---

### 7. Shift Operators

Shift the bits of a number left or right.

| Operator | Name | Effect |
|----------|------|--------|
| `<<` | Left shift | Shifts bits left, fills with 0. Multiplies by 2^n |
| `>>` | Signed right shift | Shifts bits right, keeps the sign bit. Divides by 2^n |
| `>>>` | Unsigned right shift | Shifts bits right, always fills with 0 |

```java
int a = 8;
a << 2     // 32  (8 * 2^2)
a >> 2     // 2   (8 / 2^2)
-8 >> 1    // -4  (sign preserved)
-8 >>> 1   // 2147483644 (zero filled)
```

---

### 8. Ternary Operator

A shorthand for `if-else`. It is the only operator in Java that takes three operands.

```java
variable = (condition) ? valueIfTrue : valueIfFalse;
```

```java
int age = 20;
String result = (age >= 18) ? "Adult" : "Minor";   // Adult
```

---

### Day 2 Quick Summary

- Integer division drops the decimal; `%` gives the remainder
- `++x` increments before use, `x++` after use
- Compound assignment (`+=`) does an implicit cast
- `==` compares references for objects; use `.equals()` for content
- `&&` and `||` short-circuit; `&` and `|` do not
- `<<` multiplies by 2^n, `>>` divides by 2^n, `>>>` fills with zeros
- Ternary `? :` is a one-line if-else

---

---

## Day 3

### Topics Covered

1. [Conditional Statements](#1-conditional-statements)
2. [Switch Case](#2-switch-case)
3. [Loops](#3-loops)
4. [Practice Programs](#4-practice-programs)
5. [Mistakes I Found](#5-mistakes-i-found)

---

### 1. Conditional Statements

Conditional statements let a program choose which code to run based on a `boolean` condition.

| Statement | Use |
|-----------|-----|
| `if` | Run a block only if the condition is true |
| `if-else` | Choose between two blocks |
| `else-if` ladder | Choose between many blocks; the first true condition runs |
| Nested `if` | An `if` inside another `if` |

```java
if (marks >= 90) {
    System.out.println("A");
} else if (marks >= 75) {
    System.out.println("B");
} else {
    System.out.println("C");
}
```

Notes:
- The condition must be a `boolean` (unlike C, `if (1)` is not allowed).
- Conditions are built with relational (`>`, `==`) and logical (`&&`, `||`, `!`) operators from Day 2.
- The shorthand for a simple if-else is the ternary operator: `(cond) ? a : b`.

---

### 2. Switch Case

`switch` compares one value against several fixed cases. It is cleaner than a long else-if ladder when checking one variable against constants.

#### Traditional switch (needs `break`)

```java
switch (day) {
    case 1:
        System.out.println("Monday");
        break;
    case 2:
        System.out.println("Tuesday");
        break;
    default:
        System.out.println("Invalid");
}
```

Without `break`, execution **falls through** into the next case, which is a common source of bugs.

#### Modern switch with arrow `->` (Java 14+)

No `break` is needed and there is no fall-through. Multiple values can share one case.

```java
switch (day) {
    case 1, 2, 3, 4, 5 -> System.out.println("Weekdays");
    case 6, 7          -> System.out.println("Weekend");
    default            -> System.out.println("Invalid");
}
```

Allowed switch types: `byte`, `short`, `char`, `int`, their wrappers, `String`, and `enum`. Not allowed: `long`, `float`, `double`, `boolean`.

---

### 3. Loops

Loops repeat a block of code while a condition holds.

| Loop | Checks condition | Best when |
|------|------------------|-----------|
| `for` | Before each iteration | The number of repetitions is known |
| `while` | Before each iteration | The number of repetitions is not known |
| `do-while` | After each iteration | The body must run at least once |
| Enhanced `for` | n/a | Reading every element of an array or collection |

#### for loop

```java
for (int i = 1; i <= 5; i++) {
    System.out.println(i);
}
```

Parts: initialization (`int i = 1`), condition (`i <= 5`), update (`i++`).

#### while loop

```java
int i = 1;
while (i <= 5) {
    System.out.println(i);
    i++;
}
```

#### do-while loop

```java
int i = 1;
do {
    System.out.println(i);
    i++;
} while (i <= 5);   // note the semicolon
```

#### Enhanced for loop

```java
int[] nums = {10, 20, 30};
for (int n : nums) {
    System.out.println(n);
}
```

#### break and continue

| Keyword | Effect |
|---------|--------|
| `break` | Exit the loop immediately |
| `continue` | Skip the rest of this iteration and go to the next one |

```java
for (int i = 1; i <= 10; i++) {
    if (i == 3) continue;   // skips 3
    if (i == 6) break;      // stops at 6
    System.out.println(i);  // prints 1 2 4 5
}
```

Watch out for **infinite loops**, e.g. forgetting `i++` in a `while` loop.

---

### 4. Practice Programs

All programs are in [`Day3.java`](Day3.java) and read input with `Scanner`.

```java
import java.util.Scanner;
Scanner sc = new Scanner(System.in);
```

#### Program 1: Odd or Even

A number is even if dividing by 2 leaves remainder 0.

```java
int num = sc.nextInt();
if (num % 2 == 0) {
    System.out.println(num + " is Even Number");
} else {
    System.out.println(num + " is Odd Number");
}
```

#### Program 2: Maximum of Three Numbers

```java
if (n1 >= n2 && n1 >= n3) {
    System.out.println(n1 + " is greater");
} else if (n2 >= n1 && n2 >= n3) {
    System.out.println(n2 + " is greater");
} else {
    System.out.println(n3 + " is greater");
}
```

#### Program 3: Leap Year

A year is a leap year if it is divisible by 4 and not by 100, **or** it is divisible by 400.

```java
if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
    System.out.println(year + " is a leap year");
} else {
    System.out.println(year + " is not a leap year");
}
```

| Year | Result | Why |
|------|--------|-----|
| 2024 | Leap | Divisible by 4, not by 100 |
| 1900 | Not leap | Divisible by 100 but not by 400 |
| 2000 | Leap | Divisible by 400 |

#### Program 4: Positive or Negative

```java
if (value > 0) {
    System.out.println(value + " is Positive Number");
} else if (value < 0) {
    System.out.println(value + " is Negative Number");
} else {
    System.out.println("Zero");
}
```

#### Program 5: Nested if Trace (output prediction)

```java
boolean a = false;
boolean b = true;
int c = 5;
if (c << 2 > 15) {
    if (b) {
        a = !a;
        if (a) {
            b = !b;
            if (b) {
                c += 5;
            }
        }
    }
} else {
    c += 10;
}
System.out.println(c);
```

Step-by-step trace:

| Step | Check | Result |
|------|-------|--------|
| 1 | `c << 2` = 5 x 4 = 20, and `20 > 15` | true, enter outer if |
| 2 | `if (b)`: b is true | true, enter |
| 3 | `a = !a` makes a = true; `if (a)` | true, enter |
| 4 | `b = !b` makes b = false; `if (b)` | false, skip `c += 5` |

**Output: `5`**

#### Program 6: Weekday or Weekend (switch)

```java
int day = sc.nextInt();
switch (day) {
    case 1, 2, 3, 4, 5 -> System.out.println("Weekdays");
    case 6, 7 -> System.out.println("Weekend");
    default -> System.out.println("Invalid");
}
```

---

### 5. Mistakes I Found

Being honest about where my first version went wrong:

| Program | Mistake | Fix |
|---------|---------|-----|
| Leap year | Wrote `year % 4 == 0 && year % 4 != 0`, which can never be true, so every year printed "not a leap year" | Use `% 100` for the second check and add the `% 400` rule |
| Max of three | Used `>` only, so ties (e.g. 5, 5, 3) fall into the `else` and print the wrong value | Use `>=` |
| Positive/negative | Zero was reported as "Positive" | Add a separate branch for zero |
| Program 5 | Copied the heading "Positive or Negative" from the previous program | Give it its own label |
| Switch | The first `switch` (`case 1:`) was left unfinished, which does not compile | Complete it with `break`, or use the arrow form |

Lesson: test with edge cases (ties, zero, century years), not just the normal inputs.

---

### Day 3 Quick Summary

- `if`, `else-if` and `else` pick one path; conditions must be `boolean`
- `switch` suits one variable against fixed values; the traditional form needs `break`, the arrow form does not
- `for` when the count is known, `while` when it is not, `do-while` when the body must run at least once
- `break` exits a loop; `continue` skips to the next iteration
- Leap year rule: `(y % 4 == 0 && y % 100 != 0) || y % 400 == 0`
- Always test edge cases

---

## Day 4

### Topics Covered

1. [Sum of N Numbers](#1-sum-of-n-numbers)
2. [Sum of Even and Odd Numbers](#2-sum-of-even-and-odd-numbers)
3. [Reverse a Number](#3-reverse-a-number)
4. [Number Pattern](#4-number-pattern)
5. [Multiplication Table](#5-multiplication-table)
6. [Diamond Star Pattern](#6-diamond-star-pattern)
7. [Palindrome (started)](#7-palindrome-started)
8. [Mistakes I Found](#8-mistakes-i-found-day-4)

Day 4 was all about getting comfortable with `for` loops and nested loops. Code: [`Day4.java`](Day4.java).

---

### 1. Sum of N Numbers

Add every number from 1 to `a`.

```java
int sum = 0;
if (a > 0) {
    for (int i = 1; i <= a; i++) {
        sum = sum + i;
    }
}
System.out.println("Sum Value: " + sum);
```

For `a = 5`: 1 + 2 + 3 + 4 + 5 = **15**.

### 2. Sum of Even and Odd Numbers

Keep two running totals and use `%` to decide which one to add to.

```java
int even = 0, odd = 0;
for (int i = 1; i <= a; i++) {
    if (i % 2 == 0) {
        even = even + i;
    } else {
        odd = odd + i;
    }
}
```

For `a = 5`: even = 2 + 4 = **6**, odd = 1 + 3 + 5 = **9**.

### 3. Reverse a Number

Peel off the last digit with `% 10`, build the reversed number, then drop the digit with `/ 10`.

```java
int v = 0;
for (int i = rev; i > 0; i /= 10) {
    int digit = i % 10;
    v = v * 10 + digit;
}
System.out.println(v);
```

Trace for `123`:

| i | digit (i % 10) | v = v * 10 + digit |
|---|----------------|--------------------|
| 123 | 3 | 3 |
| 12 | 2 | 32 |
| 1 | 1 | 321 |

### 4. Number Pattern

Outer loop = rows, inner loop = columns. Row `i` prints numbers `1` to `i`.

```java
for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= i; j++) {
        System.out.print(j + "  ");
    }
    System.out.println();
}
```

Output for `n = 4`:

```
1
1  2
1  2  3
1  2  3  4
```

### 5. Multiplication Table

```java
for (int i = 1; i <= 10; i++) {
    System.out.println(table + "*" + i + "=" + table * i);
}
```

`5*1=5`, `5*2=10`, ... `5*10=50`.

### 6. Diamond Star Pattern

Each row prints leading spaces, then stars. The star count grows by 2 on the way down to the middle row, then shrinks by 2. The space count does the opposite.

```java
int k = 1;           // stars in the current row
int space = n / 2;   // leading spaces in the current row
for (int i = 0; i < n; i++) {
    for (int j = 0; j < space; j++) System.out.print("  ");
    for (int j = 0; j < k; j++)     System.out.print("* ");
    k     += i < n / 2 ? 2 : -2;
    space += i < n / 2 ? -1 : 1;
    System.out.println();
}
```

Output for `n = 5`:

```
    *
  * * *
* * * * *
  * * *
    *
```

The ternary operator from Day 2 is doing the "grow, then shrink" switch.

### 7. Palindrome (started)

The palindrome section is only a comment so far. It builds on the reverse-a-number program: reverse the number and check `reversed == original`.

---

### 8. Mistakes I Found (Day 4)

| Program | Mistake | Fix |
|---------|---------|-----|
| Number pattern | Labelled "Star Pattern 1" but prints numbers, not stars | Rename the label, or print `"* "` instead of `j` |
| Reverse a number | Prints `v` on every loop pass (leftover debug output) | Print once after the loop |
| Reverse a number | Trailing zeros disappear (`100` becomes `1`) and negative numbers give `0` | Fine for a number; use a `String` if the zeros matter |
| Diamond | Only symmetrical for **odd** `n` (even `n` looks lopsided) | Ask for an odd number |
| Palindrome | Not finished | Reuse the reverse-a-number code |

---

### Day 4 Quick Summary

- Outer loop controls rows, inner loop controls columns
- `% 10` gets the last digit, `/ 10` removes it
- Running totals (`sum`, `even`, `odd`) start at 0 before the loop
- For patterns, work out how spaces and stars change per row
- Test with awkward inputs (numbers ending in 0, even sizes)

---

## Day 5

### Topics Covered

1. [Arrays](#1-arrays)
2. [2D Arrays](#2-2d-arrays)
3. [Practice Programs](#3-practice-programs-day-5)
4. [Mistakes I Found](#4-mistakes-i-found-day-5)

Code: [`Day5.java`](Day5.java). In the file, each finished program is commented out so I can run one at a time.

---

### 1. Arrays

An array stores a fixed number of values of the **same type** in one block of memory. Indexes start at **0**.

```java
int[] arr = {2, 33, 4, 6, 18, 22};   // declare + initialize
int[] nums = new int[5];             // 5 slots, all default to 0

arr[0]        // first element -> 2
arr.length    // 6 (a field, not a method; no parentheses)
```

- Arrays are objects, so they live in the **heap** (see Day 1) and the variable holds a reference.
- Size cannot change after creation.
- Going outside `0` to `length - 1` throws `ArrayIndexOutOfBoundsException`.
- `Arrays.toString(arr)` prints the contents. Printing `arr` directly prints a reference like `[I@1b6d3586`.

Useful `java.util.Arrays` methods: `toString`, `sort`, `fill`, `copyOf`, `equals`, `deepToString`.

### 2. 2D Arrays

A 2D array is an **array of arrays**: a table with rows and columns.

```java
int[][] m = new int[3][4];                    // 3 rows, 4 columns, all 0
int[][] g = { {1, 2, 3},
              {4, 5, 6} };                    // 2 rows, 3 columns

g[1][2]          // row 1, column 2 -> 6
g.length         // number of rows -> 2
g[0].length      // number of columns -> 3
```

Traversing with nested loops (outer = rows, inner = columns, just like the Day 4 patterns):

```java
for (int i = 0; i < g.length; i++) {
    for (int j = 0; j < g[i].length; j++) {
        System.out.print(g[i][j] + " ");
    }
    System.out.println();
}
```

Printing a 2D array: use `Arrays.deepToString(g)`, since `Arrays.toString` only works one level deep.

#### Transpose (swap rows and columns)

```java
int[][] t = new int[3][2];
for (int i = 0; i < 2; i++)
    for (int j = 0; j < 3; j++)
        t[j][i] = g[i][j];

System.out.println(Arrays.deepToString(t));   // [[1, 4], [2, 5], [3, 6]]
```

#### Jagged arrays

Rows can have different lengths because each row is its own array.

```java
int[][] jag = new int[3][];
jag[0] = new int[2];
jag[1] = new int[4];
jag[2] = new int[1];
```

---

### 3. Practice Programs (Day 5)

#### Program 1: Print an array in reverse

```java
int arr[] = {2, 33, 4, 6, 18, 22};
for (int i = arr.length - 1; i >= 0; i--) {
    System.out.println(arr[i]);
}
```

#### Program 2: Copy an array into a reversed array

Read 5 values, then write each one into the mirror position of a second array.

```java
int arr[] = new int[5];
int rarr[] = new int[5];
for (int i = 0; i < arr.length; i++) {
    arr[i] = sc.nextInt();
}
for (int j = arr.length - 1, i = 0; j >= 0; j--, i++) {
    rarr[j] = arr[i];
}
System.out.println(Arrays.toString(arr));
System.out.print(Arrays.toString(rarr));
```

A `for` loop can run two variables at once (`j` goes down while `i` goes up).

#### Program 3: Minimum and Maximum (two ways)

Start `max` and `min` at the first element, then compare each element.

```java
int arr[] = {10, 33, -2, 45, -62, 26, 33, 41};
int max = arr[0];
int min = arr[0];
for (int i = 0; i < arr.length; i++) {
    max = Math.max(max, arr[i]);
    min = Math.min(min, arr[i]);
}
```

My first version used `if / else if` comparisons instead of `Math.max` and `Math.min`. Both work, and the `Math` version is shorter. Result: max = **45**, min = **-62**.

#### Program 4: Second largest

Track the largest and second largest together. When a new largest arrives, the old largest becomes the second.

```java
int arr[] = {10, 33, 2, 45, 62, 26, 33, 41};
int max1 = 0, max2 = 0;
for (int i = 0; i < arr.length; i++) {
    if (max1 < arr[i]) {
        max2 = max1;
        max1 = arr[i];
    } else if (max2 < arr[i]) {
        max2 = arr[i];
    }
}
System.out.println(max2);   // 45
```

#### Program 5: Second smallest

Same idea in reverse, starting both from `arr[0]`.

```java
int min1 = arr[0], min2 = arr[0];
for (int i = 0; i < arr.length; i++) {
    if (min1 > arr[i]) {
        min2 = min1;
        min1 = arr[i];
    } else if (min2 > arr[i]) {
        min2 = arr[i];
    }
}
System.out.println(min2);   // 10
```

---

### 4. Mistakes I Found (Day 5)

I ran edge cases against my own code:

| Program | Input | What happens | Fix |
|---------|-------|--------------|-----|
| Second largest | `{-5, -2, -9}` (all negative) | Prints `0`, because `max1` and `max2` start at 0 | Start both at `Integer.MIN_VALUE` |
| Second smallest | `{1, 5, 3}` | Prints `1` instead of `3`, because `min2` starts at `arr[0]`, which is already the smallest | Start both at `Integer.MAX_VALUE` |
| Second largest | `{5, 5, 3}` | Gives `5`: a duplicate of the max counts as "second" | Add `&& arr[i] != max1` if you want the second **distinct** value |

Corrected second largest:

```java
int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
for (int v : arr) {
    if (v > max1) {
        max2 = max1;
        max1 = v;
    } else if (v > max2 && v != max1) {
        max2 = v;
    }
}
```

---

### Day 5 Quick Summary

- Array indexes run from `0` to `length - 1`; `length` has no parentheses
- A 2D array is an array of arrays; use nested loops and `deepToString`
- Initialize min/max trackers from the data or from `Integer.MIN_VALUE` / `MAX_VALUE`, not from `0`
- `Math.max` and `Math.min` keep comparisons short
- Test with negatives and duplicates

---

## Day 6

### Topics Covered

1. [Strings](#1-strings)
2. [String Methods](#2-string-methods)
3. [Array Problems](#3-array-problems)
4. [Mistakes I Found](#4-mistakes-i-found-day-6)

Code: [`Day6.java`](Day6.java). Again, solved problems are commented out so I run one at a time.

---

### 1. Strings

A `String` is a sequence of characters. It is a **class** (a reference type from Day 1), not a primitive.

```java
String s1 = "Java";               // string literal -> goes to the String Pool
String s2 = "Java";               // reuses the same pooled object
String s3 = new String("Java");   // always creates a new object on the heap
```

Key points:
- Strings are **immutable**: once created, the content never changes. Methods like `toUpperCase()` return a **new** string.
- Identical literals share one object in the **String Pool**.
- Compare content with `.equals()`, not `==` (see the relational operators section on Day 2).

```java
s1 == s2          // true  (same pooled object)
s1 == s3          // false (different objects)
s1.equals(s3)     // true  (same content)
```

```java
String s = "hello";
s.toUpperCase();              // returns "HELLO" but s is unchanged
s = s.toUpperCase();          // now s is "HELLO"
```

For heavy editing (many appends in a loop), use `StringBuilder`, which is **mutable**.

### 2. String Methods

| Method | What it does | Example | Result |
|--------|--------------|---------|--------|
| `length()` | Number of characters | `"Hello".length()` | `5` |
| `charAt(i)` | Character at index `i` | `"Hello".charAt(1)` | `'e'` |
| `substring(a, b)` | From index `a` up to (not including) `b` | `"Hello".substring(1, 4)` | `"ell"` |
| `substring(a)` | From index `a` to the end | `"Hello".substring(2)` | `"llo"` |
| `indexOf(x)` | First index of `x`, or `-1` | `"banana".indexOf("an")` | `1` |
| `lastIndexOf(x)` | Last index of `x`, or `-1` | `"banana".lastIndexOf("an")` | `3` |
| `contains(x)` | Does it contain `x`? | `"Hello".contains("ell")` | `true` |
| `startsWith(x)` / `endsWith(x)` | Check the start or end | `"Hello".startsWith("He")` | `true` |
| `equals(x)` | Content equality (case sensitive) | `"a".equals("A")` | `false` |
| `equalsIgnoreCase(x)` | Content equality, ignoring case | `"a".equalsIgnoreCase("A")` | `true` |
| `compareTo(x)` | Dictionary order: negative, 0 or positive | `"apple".compareTo("banana")` | `-1` |
| `toUpperCase()` / `toLowerCase()` | Change case | `"Hello".toUpperCase()` | `"HELLO"` |
| `trim()` / `strip()` | Remove leading and trailing spaces | `"  Hi  ".trim()` | `"Hi"` |
| `replace(a, b)` | Replace every `a` with `b` | `"banana".replace("a", "o")` | `"bonono"` |
| `split(regex)` | Split into a `String[]` | `"a,b,c".split(",")` | `[a, b, c]` |
| `concat(x)` | Join two strings (same as `+`) | `"Hi".concat("!")` | `"Hi!"` |
| `repeat(n)` | Repeat `n` times | `"ab".repeat(2)` | `"abab"` |
| `String.join(sep, ...)` | Join with a separator | `String.join("-", "a", "b")` | `"a-b"` |
| `isEmpty()` | Is the length 0? | `"".isEmpty()` | `true` |
| `isBlank()` | Empty or only spaces? | `"   ".isBlank()` | `true` |
| `toCharArray()` | Convert to `char[]` | `"Hi".toCharArray()` | `['H', 'i']` |
| `String.valueOf(x)` | Convert any value to a string | `String.valueOf(42)` | `"42"` |

Notes:
- `length()` has parentheses for strings, but `.length` has none for arrays.
- `substring(a, b)` includes `a` and excludes `b`.
- `compareTo` returns the difference between the first pair of characters that differ (`'a'` minus `'b'` is `-1`).

#### Example: reverse a string

Same idea as reversing an array, using `charAt`:

```java
String s = "java";
String rev = "";
for (int i = s.length() - 1; i >= 0; i--) {
    rev += s.charAt(i);
}
System.out.println(rev);   // avaj
```

---

### 3. Array Problems

Practice problems from `Day6.java`, all solved with loops and arrays.

#### Problem 1: Count how many times a value occurs

```java
int count = 0;
int arr[] = {10, 20, 10, 40, 20, 20, 10};
for (int i : arr) {
    if (i == 10) count += 1;
}
System.out.println("10 occurs " + count + " times");   // 3
```

Uses the enhanced `for` loop from Day 3.

#### Problem 2: Smallest missing positive integer

Try `n = 1, 2, 3...` and search the array for each one. The first `n` not found is the answer.

```java
int arr[] = {5, 3, -1, 2, 1, 8};
int n = 1;
while (true) {
    boolean found = false;
    for (int i : arr) {
        if (i == n) { found = true; break; }
    }
    if (!found) { System.out.println(n); break; }
    n++;
}
// prints 4
```

Uses `while`, `break` and a `boolean` flag together.

#### Problem 3: Sort by swapping

Compare every pair (`i`, `j`) and swap when they are out of order. This is a simple exchange sort.

```java
int arr[] = {5, 3, -1, 2, 1, 8};
for (int i = 0; i < arr.length; i++) {
    for (int j = i + 1; j < arr.length; j++) {
        if (arr[j] < arr[i]) {
            int temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }
    }
}
// [-1, 1, 2, 3, 5, 8]
```

The `temp` variable holds one value while the two swap places.

#### Problem 4: First and last index of a value

```java
int a[] = {10, 20, 30, 20, 40, 20};
int target = 20;
int first = -1, last = -1;
for (int i = 0; i < a.length; i++) {
    if (a[i] == target) {
        if (first == -1) first = i;
        last = i;
    }
}
// first = 1, last = 5
```

(Shown with the fix described in the mistakes section below.)

#### Problem 5: Move Zeroes (LeetCode 283)

```java
int num[] = {0, 1, 0, 3, 12};
int arr[] = new int[num.length];
int c = 0;
for (int i = 0; i < num.length; i++) {
    if (num[i] != 0) arr[c++] = num[i];
}
// [1, 3, 12, 0, 0]
```

A new array starts full of zeros, so copying the non-zero values to the front leaves the zeros at the end.

#### Problem 6: Wildcard Matching (LeetCode 44)

Not solved yet. This is a harder problem, and I left it as a placeholder to come back to.

#### Problem 7: Single Number (LeetCode 136)

Every number appears twice except one. Count each value with a nested loop and print the one that appears once.

```java
int nums[] = {4, 1, 2, 1, 2};
for (int i = 0; i < nums.length; i++) {
    int count = 0;
    for (int j = 0; j < nums.length; j++) {
        if (nums[i] == nums[j]) count++;
    }
    if (count == 1) System.out.print(nums[i]);   // 4
}
```

This is O(n^2). A faster way uses the XOR operator from Day 2, since `x ^ x = 0` and `x ^ 0 = x`, so pairs cancel out:

```java
int result = 0;
for (int n : nums) result ^= n;   // 4
```

#### Problem 8: Separate odd and even numbers into two arrays

Count first so the two new arrays can be created with exactly the right size, then fill them.

```java
int arr[] = {6, 1, 0, 2, 9, 5, 6, 3, 7, 4};
int ec = 0, oc = 0;
for (int i : arr) {
    if (i % 2 == 0) ec++; else oc++;
}
int Even[] = new int[ec];
int Odd[]  = new int[oc];
ec = 0; oc = 0;
for (int i = 0; i < arr.length; i++) {
    if (arr[i] % 2 == 0) Even[ec++] = arr[i];
    else                 Odd[oc++]  = arr[i];
}
// Even: [6, 0, 2, 6, 4]   Odd: [1, 9, 5, 3, 7]
```

Arrays cannot grow, which is why the counting pass is needed.

#### Problem 9: Count odd and even numbers

```java
int even = 0, odd = 0;
for (int i : arr) {
    if (i % 2 == 0) even++; else odd++;
}
```

My version used a ternary (`int c = i % 2 == 0 ? even++ : odd++;`). It works, but the variable `c` is never used, so a plain `if / else` is clearer.

---

### 4. Mistakes I Found (Day 6)

| Problem | Mistake | Fix |
|---------|---------|-----|
| 4: First and last index | Used `first = 0` as the "not set" marker. If the target is at index 0 (`{20, 10, 20, 30}`), `first` gets overwritten and ends up as `2`. If the target is missing, it reports `0` and `0` | Start both at `-1` |
| 5: Move zeroes | Used `num[i] > 0`, which also drops **negative** numbers (`{0, -1, 0, 3}` becomes `[3, 0, 0, 0]`) | Use `!= 0` |
| 5: Move zeroes | LeetCode expects the change **in place**, but this builds a new array | Move non-zeros forward inside `num` itself, then fill the rest with 0 |
| 3: "Swap" | It is really a sorting algorithm | Name it "Sort by swapping" |
| 6: Wildcard Matching | Still empty | Come back after learning more |
| `IO.print` / `IO.println` | These are newer Java helpers (recent JDKs); older versions will not compile them | Use `System.out.print` / `System.out.println` on older JDKs |

---

### Day 6 Quick Summary

- A `String` is an immutable object; methods return new strings
- Use `.equals()` for content, not `==`
- Key methods: `length`, `charAt`, `substring`, `indexOf`, `split`, `replace`, `trim`, `toCharArray`
- `length()` for strings, `.length` for arrays
- Use `-1` (not `0`) as the "not found" marker, because `0` is a valid index
- XOR (`^`) cancels out pairs, which solves Single Number in one pass
- Arrays cannot grow, so count first when you need exact-size arrays

---

*Notes from Days 1 to 6 of my Java learning journey. More to come.*
