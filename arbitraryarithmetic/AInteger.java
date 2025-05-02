package arbitraryarithmetic;

public class AInteger {
    public String integer;

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

    public String toString(){
        return integer;
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

    public static int[] stringToIntegerArray(String str) {
        // Creating an array to store the integers
        int[] intArray = new int[str.length()];

        for (int i = 0; i < str.length(); i++) {
            intArray[i] = str.charAt(i) - '0'; // Convert char to integer
        }

        return intArray;
    }

    public static String arrayTostring(int[] arr) {
        StringBuilder required_string = new StringBuilder();

        for(int i=0;i<arr.length;i++) {
            required_string.append(arr[i]);
        }
        return required_string.toString();

    }

    // adding two numbers which are positive 
    public String add_these_both_strings(String num1, String num2 ) {
        //removing leading zeros
        num1 = removing_leading_zeros(num1);
        num2 = removing_leading_zeros(num2);

        // creating 2 arrays and converting given strings into integer arrays
        int[] array1_num1 = stringToIntegerArray(num1);
        int[] array2_num2 = stringToIntegerArray(num2);

        // making num1 higher
        if(array1_num1.length < array2_num2.length ) {
            int [] temp = array1_num1;
            array1_num1 = array2_num2;
            array2_num2 = temp;
        }

        int[] result_array = new int[array1_num1.length + 1];
        int carry = 0;
        int diff = array1_num1.length - array2_num2.length;

        for(int i = array2_num2.length -1; i>= 0 ;i--) {
            int sum = array2_num2[i] + array1_num1[i + diff] + carry;
            result_array[i + diff + 1] = sum%10;
            carry = sum/10;
        }

        for(int j = diff -1 ;j>=0 ;j--) {
            int sum = array1_num1[j] + carry;
            result_array[j+1]  = sum %10;
            carry = sum/10;

        }
        result_array[0] = carry;

        String result_string = arrayTostring(result_array);
        String final_result = removing_leading_zeros(result_string);

        return final_result;

    }

    public boolean isSmaller(String str1, String str2) {
        if (str1.length() < str2.length()) return true;
        if (str1.length() > str2.length()) return false;
        return str1.compareTo(str2) < 0;
    }


    public String subtract_these_both_strings(String num1, String num2 ) {

        // Remove leading zeros
        num1 = removing_leading_zeros(num1);
        num2 = removing_leading_zeros(num2);

        // creating 2 integer arrays for given strings
        int[] array1_num1 = stringToIntegerArray(num1);
        int[] array2_num2 = stringToIntegerArray(num2);

        boolean isNegative = false;

        // making num1 higher than num2 , if it is smaller we will check with isSmaller and if yes we will swap to make sure num1>num2
        if(isSmaller(num1, num2)) {
            int[] temp = array1_num1;
            array1_num1 = array2_num2;
            array2_num2 = temp;
            isNegative = true;
        }


        int[] result_array = new int[array1_num1.length];
        int borrow = 0;
        int diff = array1_num1.length - array2_num2.length;

        for (int i = array2_num2.length - 1; i >= 0; i--) {
            int sub = array1_num1[i + diff] - array2_num2[i] - borrow;
            if (sub < 0) {
                sub += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            result_array[i + diff] = sub;
        }

        for (int j = diff - 1; j >= 0; j--) {
            int sub = array1_num1[j] - borrow;
            if (sub < 0) {
                sub += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            result_array[j] = sub;
        }

        String result_string = arrayTostring(result_array);
        String final_result = removing_leading_zeros(result_string);

        if(isNegative ) {
            final_result = "-" + final_result;
        }
        
        return final_result;

    }


    

    public String multiply_these_both_strings(String num1, String num2) {

        boolean negative = false;
        if (num1.equals("0") || num2.equals("0")) return new AInteger().toString();

        
        if (num1.charAt(0) == '-') {
            negative = !negative;
            num1 = num1.substring(1);
        }
        if (num2.charAt(0) == '-') {
            negative = !negative;
            num2 = num2.substring(1);
        }
        
        num1 = removing_leading_zeros(num1);
        num2 = removing_leading_zeros(num2);

        String output = "0";
        int len1 = num1.length();
        int len2 = num2.length();
        int i = len1-1;

        while(i>= 0){
            int[] sub = new int[len2+1];
            int carry = 0;
            int k=0;
            for(int j=len2-1;j >= 0;j--){
                int sum = (num1.charAt(i)-'0')*(num2.charAt(j)-'0') + carry;
                sub[k++]= sum%10;
                carry = sum/10;
            }
            if(carry != 0){
                sub[k++] = carry;
            }
            
            char[] string = new char[k];
            for(int m = k-1,n=0; m>=0;m--,n++){
                string[n] = (char)(sub[m]+'0');
            }
            String Str = new String(string);
            for(int m =(len1-1-i);m>0;m--){
                Str+="0";
            }
            AInteger a = new AInteger(output);
            output = a.add(new AInteger(Str)).integer;
            i--;
        }
        String finalStr =  negative ? "-" + output: output;
        return new AInteger(finalStr).toString();
    }

    

    public String divide_these_both_strings(String num1, String num2) {

        try {
        // Remove leading zeros
        num1 = removing_leading_zeros(num1);
        num2 = removing_leading_zeros(num2);


        if(num2.equals("0")) {
            throw new ArithmeticException("Division by zero is not allowed");
        }

        if(num1.equals("0") || isSmaller(num1,num2)) {
            return "0";
        }

        if(num1.equals(num2)) {
            return "1";
        }

        StringBuilder quotient = new StringBuilder();

        String current = "";

        for (int i = 0; i < num1.length(); i++) {
    
            current = current + num1.charAt(i);           
            current = removing_leading_zeros(current);  
            
            int count = 0;                         
            
            while (!isSmaller(current, num2)) {   
                current = subtract_these_both_strings(current, num2); 
                count++;                           
            }

        
            quotient.append(count);                 
        }
        
        return removing_leading_zeros(quotient.toString());
    }
        catch (ArithmeticException e) {
            return e.getMessage();
    }

    }

    
}