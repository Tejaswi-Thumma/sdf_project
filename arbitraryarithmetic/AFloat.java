package arbitraryarithmetic;

public class AFloat {
    private String float_value;

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

    

}

