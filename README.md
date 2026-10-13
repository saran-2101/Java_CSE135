# Java_CSE135

My Java learning journey, documented day by day as part of CSE coursework.

## Progress

| Day | Topics | Status |
|-----|--------|--------|
| [Day 1](#day-1) | Java basics, data types, variables, type conversion, JVM internals | Done |
| [Day 2](#day-2) | Operators in Java | Done |
| [Day 3](#day-3) | Operators in Java | Done |


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

*Notes from Days 1 and 2 of my Java learning journey. More to come.*
