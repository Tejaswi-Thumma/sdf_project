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