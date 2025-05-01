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