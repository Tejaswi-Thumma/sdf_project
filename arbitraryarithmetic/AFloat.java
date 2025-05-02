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

        boolean isNegative1 = num1.startsWith("-");
        boolean isNegative2 = num2.startsWith("-");
    
        if (isNegative1) {
            num1 = num1.substring(1);
        }
        if (isNegative2) {
            num2 = num2.substring(1);
        }


        // Handle negative cases
        if (isNegative1 && isNegative2) { // -a - (-b) = b - a
            return new AFloat(num2).subtract(new AFloat(num1));
        } else if (isNegative1) {
            // (-a) - b == -(b + a)
            return new AFloat("-" + new AFloat(num2).add(new AFloat(num1)).toString());
        } else if (isNegative2) {
            // a - (-b) == a + b
            return new AFloat(num1).add(new AFloat(num2));
        }

           // Splitting the given string into integer and decimal parts
        String[] parts1 = num1.split("\\.");
        String[] parts2 = num2.split("\\.");

        String integerPart1 = parts1[0]; // integer part goes to integerPart1
        String decimalPart1 = parts1.length > 1 ? parts1[1] : "0"; // if parts1 have length greater than 1, then decimal part1 = parts1[1] or else "0"
        String integerPart2 = parts2[0];
        String decimalPart2 = parts2.length > 1 ? parts2[1] : "0";

        // making decimal digits equal for both strings so we can easily add or subtract 
        int maxDecimalLength = Math.max(decimalPart1.length(), decimalPart2.length());
        while (decimalPart1.length() < maxDecimalLength) decimalPart1 += "0";
        while (decimalPart2.length() < maxDecimalLength) decimalPart2 += "0";

        // Subtracting decimal parts
        AInteger decimalDiff= new AInteger(decimalPart1).subtract(new AInteger(decimalPart2));
        String decimalDiffStr = decimalDiff.toString();

        // Subtracting integer parts
        AInteger integerDiff = new AInteger(integerPart1).add(new AInteger(integerPart2));
        String integerDiffStr = integerDiff.toString();

        // if subtraction of decimal numbers is negative that means we need a borrow so we subtract one from integer difference
        //for decimal diff as it is negative and we took a borrow we add no. of zeros as in no. of decimal places for 1 and add 1 and that negative decimaldifference
        if(decimalDiffStr.startsWith("-")) {
            integerDiff = integerDiff.subtract(new AInteger("1"));
            decimalDiff = new AInteger(generateOneFollowedByZeros(maxDecimalLength)).add(new AInteger(decimalDiffStr));

        }

        // Remove zeros from last, but keep at least one digit as we need a float
        int end = decimalDiffStr.length();
        while (end > 1 && decimalDiffStr.charAt(end - 1) == '0') end--;
        decimalDiffStr = decimalDiffStr.substring(0, end);


        String finalResult = integerDiffStr + "." + decimalDiffStr;

        if (finalResult.equals("-0.0") || finalResult.equals("0.0") || finalResult.equals("-0")) {
            finalResult = "0.0";
        }

        return new AFloat(finalResult);

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






public static void main (String [] args) {
    AFloat[] dividends = {
        new AFloat("10"),
        new AFloat("1"),
        new AFloat("0.01"),
        new AFloat("0.0000001"),
        new AFloat("123456.789"),
        new AFloat("-10"),
        new AFloat("10"),
        new AFloat("-10"),
        new AFloat("10"),
        new AFloat("123456789123456789")
    };

    AFloat[] divisors = {
        new AFloat("2"),
        new AFloat("3"),
        new AFloat("100"),
        new AFloat("1000"),
        new AFloat("0.001"),
        new AFloat("2"),
        new AFloat("-2"),
        new AFloat("-2"),
        new AFloat("0"),
        new AFloat("0.00001")
    };


    String[] descriptions = {
        "10 / 2",
        "1 / 3",
        "0.01 / 100",
        "0.0000001 / 1000",
        "123456.789 / 0.001",
        "-10 / 2",
        "10 / -2",
        "-10 / -2",
        "10 / 0",
        "123456789123456789 / 0.00001"
    };

    for (int i = 0; i < dividends.length; i++) {
        AFloat result = dividends[i].divide(divisors[i]);
        System.out.println(result.float_value);
    }
}
}

