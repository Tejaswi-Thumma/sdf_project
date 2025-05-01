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
