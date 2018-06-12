package co.edu.eafit.dis.analisisnumerico.one_variable_equation.method_class;


import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;

import co.edu.eafit.dis.analisisnumerico.utility_class.ExpressionEvalUtil;

public class FixedPointMethod {

    public static ArrayList<ArrayList<String>> tableArray = new ArrayList<>();

    public static String fixedPoint(String f, String g, int iter, BigDecimal tol, BigDecimal x0)
            throws Exception{
        if(iter < 0 || tol.doubleValue() > 1 || tol.doubleValue() < 0){
            throw new Exception();
        }
        BigDecimal y = ExpressionEvalUtil.functionEval(f,x0);
        int count = 0;
        int n=0;
        int contArray=0;
        BigDecimal E = tol.add(BigDecimal.ONE);
        while ((y.compareTo(BigDecimal.ZERO) != 0) && (E.compareTo(tol) > 0) && (count < iter)){
            tableArray.add(new ArrayList<String>());
            tableArray.get(contArray).add(""+n);
            BigDecimal x1 = ExpressionEvalUtil.functionEval(g,x0)
                    .round(new MathContext(32, RoundingMode.HALF_UP));
            tableArray.get(contArray).add(""+x1);
            y = ExpressionEvalUtil.functionEval(f,x1);
            tableArray.get(contArray).add(""+y);
            E = x1.subtract(x0).abs();
            tableArray.get(contArray).add(""+E);
            x0 = x1;
            count ++;
            n++;
            contArray++;
        }
        if (y.compareTo(BigDecimal.ZERO) == 0) {
            return x0.toString()+ " Es raiz";
        }else if (E.compareTo(tol) < 0){
            return x0.toString()+" Esta aproximado a la raiz, E < tolerancia";
        }else{
            return "Falla, Ha Exedido el numero maximo de interaciones";
        }
    }

}
