package arbitraryarithmetic;

public class AInteger {
    protected String integer;

    // defalut constructor
    public AInteger () {
        this.integer = "0";

    }

    // constructor
    public AInteger (String s) {
        this.integer = s;
    }

    // copy constructor
    public AInteger(AInteger other) {
        this.integer = other.integer;
    }

    // parse(String s) - a static function that returns an instance of AInteger class.
    public static AInteger parse(String s) {
        return new AInteger(s);
    }

    //removing leading zeros and keeping the sign same
    public static String removing_leading_zeros ( String s) {
        boolean isNegative = false;
        String s2 = s;

        if(s.startsWith("-")) {
            isNegative = true;
            s2 = s2.substring(1);
            
        }
        String duplicate_s2 = s2;
        for(int i=0;i< duplicate_s2.length()-1;i++) {
            if(s2.charAt(0) == '0') {
                s2 = s2.substring(1);
            }
            else {
                break;
            }
        }
        if(isNegative && !s2.equals("0")) {
            return "-" + s2;
        }
        else {
            return s2;
        }
    }

    // adding based on sign of numbers in strings
    public AInteger add (AInteger other) {
        String firstNum = this.integer;
        String secondNum = other.integer;
        boolean firstNum_Neg = false;
        boolean secondNum_Neg = false;
    
        // If first number is negative we make bool true and remove the sign
        if (firstNum.charAt(0) == '-') {
            firstNum_Neg = true;
            firstNum = firstNum.substring(1);
        }

        // If second number is negative we make bool true and remove the sign
        if(secondNum.charAt(0) == '-') {
             secondNum_Neg = true;
             secondNum = secondNum.substring(1); 
        }

        // if both numbers are negative we add them normally and put negative sign at the start of the string
        if( firstNum_Neg && secondNum_Neg ) { 
            return new AInteger("-" + add_these_both_strings(firstNum, secondNum));
        }

        // if first number is negative and second number is positive, we subtract first number from second number
        else if( firstNum_Neg && !secondNum_Neg ) {
        return new AInteger(subtract_these_both_strings(secondNum, firstNum));
        }

        // if first number is positive and second number is negative, we subtract second number from first number
        else if (!firstNum_Neg && secondNum_Neg) {
        return new AInteger(subtract_these_both_strings(firstNum, secondNum));
        }

        //if both are positive, we add them
        else {
        return new AInteger(add_these_both_strings(firstNum, secondNum));
        }
    }

    // subtracting based on sign of numbers in strings
    public AInteger subtract (AInteger other) {
        String firstNum = this.integer;
        String secondNum = other.integer;
        boolean firstNum_Neg = false;
        boolean secondNum_Neg = false;
    
        // If first number is negative we make bool true and remove the sign
        if (firstNum.charAt(0) == '-') {
            firstNum_Neg = true;
            firstNum = firstNum.substring(1);
        }

        // If second number is negative we make bool true and remove the sign
        if(secondNum.charAt(0) == '-') {
             secondNum_Neg = true;
             secondNum = secondNum.substring(1); 
        }

        // if both numbers are negative we subtract first number from second number as -> -a - (-b) = b - a
        if( firstNum_Neg && secondNum_Neg ) { 
            return new AInteger(subtract_these_both_strings(secondNum, firstNum));
        }

        // if first number is negative and second number is positive, we add both of them and put a negative sign at start -> -a -b = -(a+b)
        else if( firstNum_Neg && !secondNum_Neg ) {
        return new AInteger("-" + add_these_both_strings(firstNum, secondNum));
        }

        // if first number is positive and second number is negative, we add both of them
        else if (!firstNum_Neg && secondNum_Neg) {
        return new AInteger(add_these_both_strings(firstNum, secondNum));
        }

        //if both are positive, we subtract them normally
        else {
        return new AInteger(subtract_these_both_strings(firstNum, secondNum));
        }
    }


    public AInteger multiply(AInteger other) {
        String firstNum = this.integer;
        String secondNum = other.integer;
        boolean firstNum_Neg = false;
        boolean secondNum_Neg = false;
    
        // If first number is negative we make bool true and remove the sign
        if (firstNum.charAt(0) == '-') {
            firstNum_Neg = true;
            firstNum = firstNum.substring(1);
        }
    
         // If second number is negative we make bool true and remove the sign
        if (secondNum.charAt(0) == '-') {
            secondNum_Neg = true;
            secondNum = secondNum.substring(1);
        }
    
        // multiplication without sign
        String product = multiply_these_both_strings(firstNum, secondNum);
    
        // if exactly one number is negative the product will be negative or else it will be positive, if it is 0 we return product.
        if (firstNum_Neg ^ secondNum_Neg && !product.equals("0")) {
            return new AInteger("-" + product);
        } else {
            return new AInteger(product);
        }
    }
    
    public AInteger divide(AInteger other) {
        String firstNum = this.integer;
        String secondNum = other.integer;
        boolean firstNum_Neg = false;
        boolean secondNum_Neg = false;
    
        // If first number is negative we make bool true and remove the sign
        if (firstNum.charAt(0) == '-') {
            firstNum_Neg = true;
            firstNum = firstNum.substring(1);
        }
    
        // If second number is negative we make bool true and remove the sign
        if (secondNum.charAt(0) == '-') {
            secondNum_Neg = true;
            secondNum = secondNum.substring(1);
        }
    
        // if second number is 0, we can't divide them so we need throw the message saying division with 0 is not possible.
         // if (secondNum.equals("0")) {
          //  throw new ArithmeticException("Division by zero is not allowed.");
         // }
    
        // division without sign
        String quotient = divide_these_both_strings(firstNum, secondNum); // assumes integer division
    
        // if exactly one number is negative the quotient will be negative or else it will be positive, if it is 0 we return product.
        if (firstNum_Neg ^ secondNum_Neg && !quotient.equals("0")) {
            return new AInteger("-" + quotient);
        } else {
            return new AInteger(quotient);
        }
    }