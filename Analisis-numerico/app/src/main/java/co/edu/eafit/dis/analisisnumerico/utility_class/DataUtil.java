package co.edu.eafit.dis.analisisnumerico.utility_class;


import java.math.BigDecimal;
import java.util.HashMap;

public class DataUtil {

    public static HashMap<String, String> dataMap = new HashMap<>();

    public static HashMap<String, String> equationSystemDataMap = new HashMap<>();

    public static HashMap<String, String> interpolation = new HashMap<>();

    public static BigDecimal[][] matrixA;
    public static BigDecimal[] vectorB;
    public static BigDecimal[] vectorX;


    public static String funcFInterpolation;
    public static BigDecimal[] vectorXInterpolation;


    //IDs for every value
    //Function_fx;
    //Iterations;
    //Tolerance;
    //Initial_Value;
    //Inferior_Limit;
    //Superior_Limit;
    //First_Derivative;
    //Second_Derivative;
    //Function_gx;
    //Delta;
    //Lambda;
    //Y_Value

}
