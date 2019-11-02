public interface Expression{
    public static final int ADDSUB = 1;
    public static final int MULTDIV = 2;
    public static final int VAR = 3;
    public static final int PSTRING = 4;

    public int getType();

    public double getValue();

}