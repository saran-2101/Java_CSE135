# Java_CSE135

My Java learning journey, documented day by day as part of CSE coursework.

## Progress

| Day | Topics | Status |
|-----|--------|--------|
| [Day 1](#day-1) | Java basics, data types, variables, type conversion, JVM internals | Done |
| [Day 2](#day-2) | Operators in Java | Done |
| [Day 3](#day-3) | Conditional statements, switch case, loops, practice programs | Done |


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
