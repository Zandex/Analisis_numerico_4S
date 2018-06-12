package co.edu.eafit.dis.analisisnumerico.one_variable_equation.method_class;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import co.edu.eafit.dis.analisisnumerico.utility_class.ExpressionEvalUtil;

public class NewtonMethod {

    public static ArrayList<ArrayList<String>> tableArray = new ArrayList<>();

    public static String newton(String f, String fp, int iter, BigDecimal tol, BigDecimal x0)
            throws Exception{
        if(iter < 0 || tol.doubleValue() > 1 || tol.doubleValue() < 0){
            throw new Exception();
        }
        BigDecimal y = ExpressionEvalUtil.functionEval(f,x0);
        BigDecimal dy = ExpressionEvalUtil.functionEval(fp,x0);
        int count = 0;
        int n=0;
        int contArray=0;
        BigDecimal E = tol.add(BigDecimal.ONE);
        tableArray.add(new ArrayList<String>());
        tableArray.get(contArray).add(""+n);
        tableArray.get(contArray).add(""+x0);
        tableArray.get(contArray).add(""+y);
        tableArray.get(contArray).add(""+dy);
        tableArray.get(contArray).add(""+E);
        while ((y.compareTo(BigDecimal.ZERO) != 0) && dy.compareTo(BigDecimal.ZERO)!=0
                && (E.compareTo(tol) > 0) && (count < iter)){

            BigDecimal x1 = x0.subtract(y.divide(dy,32, RoundingMode.HALF_UP));
            y = ExpressionEvalUtil.functionEval(f,x1);
            dy = ExpressionEvalUtil.functionEval(fp,x1);
            E = x1.subtract(x0).abs();
            x0 = x1;
            count ++;
            n++;
            contArray++;
            tableArray.add(new ArrayList<String>());
            tableArray.get(contArray).add(""+n);
            tableArray.get(contArray).add(""+x0);
            tableArray.get(contArray).add(""+y);
            tableArray.get(contArray).add(""+dy);
            tableArray.get(contArray).add(""+E);
        }
        if (y.compareTo(BigDecimal.ZERO) == 0) {
            return x0.toString()+" Es raiz";
        }else if (dy.compareTo(BigDecimal.ZERO) == 0){
            return "Division por cero";
        }else if (E.compareTo(tol) < 0){
            return x0.toString()+" Esta proxima a una raiz, error < tolerancia";
        }else{
            return "Falla, Ha Exedido el numero maximo de interaciones";
        }
    }

}
