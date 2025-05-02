ARBITARY ARITHMETIC LIBRARY

OVERVIEW

Arbitrary Arithmetic Library is a Java-based utility designed to perform arbitrary-precision arithmetic on both integers and floating-point numbers. It features two primary classes: AInteger for handling large integer operations and AFloat for high-precision floating-point computations. The library supports fundamental arithmetic operations—addition, subtraction, multiplication, and division—capable of processing numbers significantly larger and more precise than those supported by Java's standard data types.

A command-line interface is provided through the MyInfArith class, allowing users to interactively perform calculations. This project was developed as part of a software engineering course to illustrate the implementation of custom high-precision arithmetic logic in Java.

PREREQUASITES

To build and Script the project, ensure the following tools are installed:
- Java Development Kit (JDK): Version 8 or higher (e.g., OpenJDK or Oracle JDK).
- Apache Ant: For compiling the project using the "build.xml" file.
- Python 3: For Scriptning the "script.py" script to compile and execute the program.
- Git: For cloning the repository and managing version control.
- A command-line interface (e.g.,VS Code Terminal or Terminal on macOS/Linux, Git Bash or PowerShell on Windows).

Verify Installation:

java -version
ant -version
python3 --version
git --version


Installation
Follow these steps to set up the project:

Clone the Repository:
git clone <repository-url>
cd <repository-directory>

Compile the Project:Use the Ant build script to compile the Java source files:
ant

This creates a build directory containing compiled .class files.
you can remove them by deleting .class files.


Usage
The library can be used via the MyInfArith command-line driver or by integrating the AInteger and AFloat classes into your Java code.
Using the Command-Line Driver (MyInfArith)
The MyInfArith class performs arithmetic operations on integers or floating-point numbers via command-line arguments.
Syntax (Preferred Method):
python3 Script.py <type> <operation> <num1> <num2>


<type>: int (for AInteger) or float (for AFloat).
<operation>: add, sub, mul, or div.
<num1>: First number (e.g.= "-2314312", "-50.678").
<num2>: Second number.

Examples:

Integer addition:
python3 Script.py int add 1234 8765

Output: 9999

Integer division:
python3 Script.py int div 178 12

Output: 14

Float subtraction:
python3 Script.py float sub 144.9 54.6

Output: 90.30000(..30 digits after decimal)

Float multiplication:
python3 Script.py float mul 1.38 4.2

Output: 5.796


Alternative (Direct Java Command):After compiling, Script directly:
java -cp build arbitraryarithmetic.MyInfArith int add 123 456

Output: 579
Notes:

The Script.py script automatically compiles the project if the build directory is missing.
Division by zero outputs: Division by zero.
Invalid inputs (e.g., non-numeric strings) may cause exceptions, displayed as Error: <message>.

Using the Library in Code
To use AInteger or AFloat in your Java program:

Add the Library:Ensure the compiled .class files in the build directory are in your classpath, or include the source files in your project.

Example Code:
import arbitraryarithmetic.AInteger;
import arbitraryarithmetic.AFloat;

public class Example {
    public static void main(String[] args) {
        // Integer arithmetic
        AInteger a = new AInteger("123");
        AInteger b = new AInteger("456");
        System.out.println("Sum: " + a.add(b)); // Output: 579
        System.out.println("Product: " + a.mul(b)); // Output: 56088

        // Float arithmetic
        AFloat x = new AFloat("3.14");
        AFloat y = new AFloat("2.0");
        System.out.println("Product: " + x.mul(y)); // Output: 6.28
        System.out.println("Difference: " + x.subtract(y)); // Output: 1.14
    }
}


Compile and Script:
javac -cp build Example.java
java -cp .:build Example

On Windows, use ; instead of : in the classpath.


Directory Structure
<repository-root>/
├── arbitraryarithmetic/        
│   ├── AInteger.java           
│   ├── AFloat.java             
│   └── MyInfArith.java         
├── build.xml                   
├── Script.py                      
├── build/                      
└── README.md                  


Git Usage
The project uses Git for version control. To view commits and tags:
git log --oneline
git tag

Tags were assigned for major milestones (e.g., v1.0-initial, v1.3-final).