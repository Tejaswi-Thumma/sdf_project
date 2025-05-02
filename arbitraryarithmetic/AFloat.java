package arbitraryarithmetic;

public class AFloat {
    public String float_value;

     // defalut constructor
     public AFloat () {
        this.float_value = "0.0";

    }

    // constructor
    public AFloat (String s) {
        this.float_value = s;
    }

    // copy constructor
    public AFloat(AFloat other) {
        this.float_value = other.float_value;
    }

    // parse(String s) - a static function that returns an instance of AFloat class.
    public static AFloat parse(String s) {
        return new AFloat(s);
    }

    @Override
    public String toString() {
        return float_value;
    }

    public AFloat add(AFloat other) {
        String num1 = this.float_value;
        String num2 = other.float_value;
    
        boolean isNegative1 = num1.startsWith("-");
        boolean isNegative2 = num2.startsWith("-");
    
        if (isNegative1) {
            num1 = num1.substring(1);
        }
        if (isNegative2) {
            num2 = num2.substring(1);
        }
    
        // Handle negative cases
        if (isNegative1 && isNegative2) { // -a + -b = -(a +b)
            return new AFloat("-" + new AFloat(num1).add(new AFloat(num2)).toString());
        } else if (isNegative1) {
            // (-a) + b == b - a
            return new AFloat(num2).subtract(new AFloat(num1));
        } else if (isNegative2) {
            // a + (-b) == a - b
            return new AFloat(num1).subtract(new AFloat(num2));
        }
    
        // Splitting the given string into integer and decimal parts
        String[] parts1 = num1.split("\\.");
        String[] parts2 = num2.split("\\.");
    
        String integerPart1 = parts1[0]; // integer part goes to integerPart1
        String decimalPart1 = parts1.length > 1 ? parts1[1] : "0"; // if parts1 have length greater than 1, then decimal part1 = parts1[1] or else "0"
        String integerPart2 = parts2[0];
        String decimalPart2 = parts2.length > 1 ? parts2[1] : "0";
    
        // making decimal digits equal for both strings so we can easily add or subtract 
        int maxDecimalLength;
            if (decimalPart1.length() > decimalPart2.length()) {
                maxDecimalLength = decimalPart1.length();
            } 
            else {
                 maxDecimalLength = decimalPart2.length();
            }

        while (decimalPart1.length() < maxDecimalLength) decimalPart1 += "0";
        while (decimalPart2.length() < maxDecimalLength) decimalPart2 += "0";
    
        // Adding decimal parts
        AInteger decimalSum = new AInteger(decimalPart1).add(new AInteger(decimalPart2));
        String decimalSumStr = decimalSum.toString();
    
        // Adding integer parts
        AInteger integerSum = new AInteger(integerPart1).add(new AInteger(integerPart2));
        String integerSumStr = integerSum.toString();
    
        // if decimal sum string have a number greater than maxdecimal length then we add it to integer sum and write decimal sum only from substring(1)
        if (decimalSumStr.length() > maxDecimalLength) {
            integerSum = integerSum.add(new AInteger("1"));
            integerSumStr = integerSum.toString();
            decimalSumStr = decimalSumStr.substring(1); // remove carry digit
        } else {
            // put zeros at front if needed like in case of subtraction it may be needed
            while (decimalSumStr.length() < maxDecimalLength) decimalSumStr = "0" + decimalSumStr;
        }
    
        // Remove zeros from last, but keep at least one digit as we need a float
        int end = decimalSumStr.length();
        while (end > 1 && decimalSumStr.charAt(end - 1) == '0') end--;
        decimalSumStr = decimalSumStr.substring(0, end);
    
        String finalResult = integerSumStr + "." + decimalSumStr; // add 2 strings and also decimal point
    
        if (finalResult.equals("-0.0")|| finalResult.equals("0.0") ||finalResult.equals("-0")) {
            finalResult = "0.0";
        }
    
        return new AFloat(finalResult);
    }


    public static String generateOneFollowedByZeros(int x) {
        if (x <= 0) return "0"; 
        StringBuilder sb = new StringBuilder("1");
            for (int i = 1; i < x; i++) {
                sb.append("0");
            }
            return sb.toString();
    }
    
    public AFloat subtract(AFloat other) {
        String num1 = this.float_value;
        String num2 = other.float_value;
        boolean neg1 = num1.startsWith("-");
        boolean neg2 = num2.startsWith("-");
        boolean negative = false;

        if (neg1) num1 = num1.substring(1);
        if (neg2) num2 = num2.substring(1);
        if (num1.startsWith(".")) num1 = "0" + num1;
        if (num2.startsWith(".")) num2 = "0" + num2;
        if (num1.endsWith(".")) num1 += "0";
        if (num2.endsWith(".")) num2 += "0";


        if (neg1 && !neg2) {
            return new AFloat("-" + new AFloat(num1).add(new AFloat(num2)).float_value);
        } else if (!neg1 && neg2) {
            return this.add(new AFloat(num2)); 
        } else if (neg1 && neg2) {
            return new AFloat(num2).subtract(new AFloat(num1)); 
        }

        if (!num1.contains(".")) num1 += ".0";
        if (!num2.contains(".")) num2 += ".0";
    
        int dec1 = num1.length() - 1 - num1.indexOf('.');
        int dec2 = num2.length() - 1 - num2.indexOf('.');
        while (dec1 < dec2) {
            num1 += "0";
            dec1++;
        }
        while (dec2 < dec1) {
            num2 += "0";
            dec2++;
        }
    
        int int1 = num1.indexOf('.');
        int int2 = num2.indexOf('.');
        while (int1 < int2) {
            num1 = "0" + num1;
            int1++;
        }
        while (int2 < int1) {
            num2 = "0" + num2;
            int2++;
        }
    
        String num1Comp = num1.replace(".", "");
        String num2Comp = num2.replace(".", "");
        if (num1Comp.compareTo(num2Comp) < 0) {
            negative = true;
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }
    
        int len = num1.length();
        int carry = 0;
        StringBuilder result = new StringBuilder();
    
        for (int i = len - 1; i >= 0; i--) {
            char c1 = num1.charAt(i);
            char c2 = num2.charAt(i);
            if (c1 == '.') {
                result.append('.');
                continue;
            }
    
            int digit1 = (c1 - '0') + carry;
            int digit2 = c2 - '0';
    
            if (digit1 < digit2) {
                digit1 += 10;
                carry = -1;
            } else {
                carry = 0;
            }
    
            result.append((char) ((digit1 - digit2) + '0'));
        }
    
        if (negative) result.append('-');
    
        String output = result.reverse().toString();
    
        int start = 0;
        while (start < output.length() - 1 && output.charAt(start) == '0' && output.charAt(start + 1) != '.') {
            start++;
        }
        output = output.substring(start);
    
        if (output.contains(".")) {
            while (output.endsWith("0")) output = output.substring(0, output.length() - 1);
            if (output.endsWith(".")) output = output.substring(0, output.length() - 1);
        }
        
        if (output.isEmpty()) output = "0";
        if (negative && !output.equals("0")) output = "-" + output;
        return new AFloat(output);
    }
    
    
    
    

    public AFloat multiply(AFloat other) {
        String num1 = this.float_value;
        String num2 = other.float_value;

        boolean isNegative1 = num1.startsWith("-");
        boolean isNegative2 = num2.startsWith("-");
    
        if (isNegative1) {
            num1 = num1.substring(1);
        }
        if (isNegative2) {
            num2 = num2.substring(1);
        }

        // Handle negative cases
        if (isNegative1 && isNegative2) { // (-a)*(-b) = a*b
            return new AFloat(num1).multiply(new AFloat(num2));
        } else if (isNegative1) {
            // (-a)*(b) = -a*b
            return new AFloat("-" + new AFloat(num1).multiply(new AFloat(num2)).toString());
        } else if (isNegative2) {
            // a *(-b) = -a*b
            return new AFloat("-" + new AFloat(num1).multiply(new AFloat(num2)).toString());
        }

        int no_of_decimal_digits_in_num1 = 0;
        int no_of_decimal_digits_in_num2 = 0;
        //removing decimal points from strings and storing the number of decimal digits
        if(num1.contains(".")) {
            no_of_decimal_digits_in_num1 = num1.length() -num1.indexOf('.') -1;
            num1 = num1.replace(".", "");
        }
        if(num2.contains(".")) {
            no_of_decimal_digits_in_num2 = num2.length() -num2.indexOf('.') -1;
            num2 = num2.replace(".", "");
        }

        //multiplying numbers without decimal points
        AInteger multiplication= new AInteger(num1).multiply(new AInteger(num2));
        String multiplication_String = multiplication.toString();

        // adding decimal point after multiplying
        int total_no_of_decimal_places = no_of_decimal_digits_in_num1 + no_of_decimal_digits_in_num2;
        // if we have 0.0003 and 0.05 if we multiply them we will get 0.000015 so to get enough zeros to place the decimal point we add zeros.
        while (multiplication_String.length() <= total_no_of_decimal_places) {
            multiplication_String = "0" + multiplication_String;
        }
        // we need to put decimal at k+1th position from end
        int k = multiplication_String.length() - total_no_of_decimal_places;
        String integer_part = multiplication_String.substring(0, k);
        String decimal_part = multiplication_String.substring(k);

        // to check precision upto 30 decimal points
        if(decimal_part.length()<30) {
            decimal_part += "0"; // if len<30 we add zeros to make it 30 digits
        }
        else {
            decimal_part = decimal_part.substring(0, 30); // if len>= 30 we only take first 30 digits
        }

        String finalresult = integer_part + "." + decimal_part;

        return new AFloat(finalresult);


    }


    public AFloat divide(AFloat other) {
        String num1 = this.float_value;
        String num2 = other.float_value;

        boolean isNegative1 = num1.startsWith("-");
        boolean isNegative2 = num2.startsWith("-");
    
        if (isNegative1) {
            num1 = num1.substring(1);
        }
        if (isNegative2) {
            num2 = num2.substring(1);
        }

        // Handle negative cases
        if (isNegative1 && isNegative2) { // (-a)/(-b) = a/b
            return new AFloat(num1).divide(new AFloat(num2));
        } else if (isNegative1) {
            // (-a)/(b) = -a/b
            return new AFloat("-" + new AFloat(num1).divide(new AFloat(num2)).toString());
        } else if (isNegative2) {
            // a /(-b) = -a/b
            return new AFloat("-" + new AFloat(num1).divide(new AFloat(num2)).toString());
        }

        int no_of_decimal_digits_in_num1 = 0;
        int no_of_decimal_digits_in_num2 = 0;
        //removing decimal points from strings and storing the number of decimal digits
        if(num1.contains(".")) {
            no_of_decimal_digits_in_num1 = num1.length() -num1.indexOf('.') -1;
            num1 = num1.replace(".", "");
        }
        if(num2.contains(".")) {
            no_of_decimal_digits_in_num2 = num2.length() -num2.indexOf('.') -1;
            num2 = num2.replace(".", "");
        }
        // writing 30 decimal places after num1 for precision
        for (int i = 0; i < 30; i++) {
            num1 = num1 + "0";
        }

        try{
            if(new AInteger(num2).toString().equals("0")) {
                throw new ArithmeticException("Division by zero error");
            }
            }catch(ArithmeticException e){
                System.out.println(e.getMessage());
                System.exit(0);
            }



        //subtracting number of decimals in numerator and number of decimals in denominator
        int diff_of_decimals = no_of_decimal_digits_in_num1 - no_of_decimal_digits_in_num2;
        
        //divide numbers without decimal points
        AInteger division= new AInteger(num1).divide(new AInteger(num2));
        String division_String = division.toString();
        
        // decimal point should be places at k+1th position from end
        int k = division_String.length() - diff_of_decimals - 30;


        while(k <= 0) { // if length of division_string is smaller than no>of decimal places we add zeros in the start to make it upto decimal point
            division_String = "0" + division_String;
            k++;
        }


        String integer_part = division_String.substring(0, k);
        String decimal_part = division_String.substring(k);

        // to check precision upto 30 decimal points
        if(decimal_part.length()<30) {
            int zerosToAdd = 30 - decimal_part.length();
                for (int i = 0; i < zerosToAdd; i++) {
                decimal_part += "0"; // if len<30 we add zeros to make it 30 digits
        }
    }
        else {
            decimal_part = decimal_part.substring(0, 30); // if len>= 30 we only take first 30 digits
        }

        String finalresult = integer_part + "." + decimal_part;

        return new AFloat(finalresult);
    
    }


}

