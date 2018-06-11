package co.edu.eafit.dis.analisisnumerico.one_variable_equation.method_class;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import co.edu.eafit.dis.analisisnumerico.utility_class.ExpressionEvalUtil;

public class MultipleRootsMethod {

    public static ArrayList<ArrayList<String>> tableArray = new ArrayList<>();

    public static String multipleRoots(String f, String fp, String fpp, int iter,
                                       BigDecimal tol, BigDecimal x0)throws Exception{
        if(iter < 0 || tol.doubleValue() > 1 || tol.doubleValue() < 0){
            throw new Exception();
        }
        BigDecimal y = ExpressionEvalUtil.functionEval(f,x0);
        BigDecimal dy = ExpressionEvalUtil.functionEval(fp,x0);
        BigDecimal ddy = ExpressionEvalUtil.functionEval(fpp,x0);
        int count = 0;
        BigDecimal E = tol.add(BigDecimal.ONE);
        int n=0;
        int contArray=0;
        tableArray.add(new ArrayList<String>());
        tableArray.get(contArray).add(""+n);
        tableArray.get(contArray).add(""+x0);
        tableArray.get(contArray).add(""+y);
        tableArray.get(contArray).add(""+dy);
        tableArray.get(contArray).add(""+ddy);
        tableArray.get(contArray).add(""+E);
        while ((y.compareTo(BigDecimal.ZERO) != 0) && (dy.compareTo(BigDecimal.ZERO) != 0)
                && (ddy.compareTo(BigDecimal.ZERO) != 0) && (E.compareTo(tol) > 0)
                && (count < iter)){
            BigDecimal x1 = x0.subtract((y.multiply(dy))
                    .divide((dy.multiply(dy)).subtract(y.multiply(ddy)),32, RoundingMode.HALF_UP));
            E = x1.subtract(x0).abs();
            x0 = x1;
            y = ExpressionEvalUtil.functionEval(f,x0);
            dy = ExpressionEvalUtil.functionEval(fp,x0);
            ddy = ExpressionEvalUtil.functionEval(fpp,x0);
            count ++;
            n++;
            contArray++;
            tableArray.add(new ArrayList<String>());
            tableArray.get(contArray).add(""+n);
            tableArray.get(contArray).add(""+x0);
            tableArray.get(contArray).add(""+y);
            tableArray.get(contArray).add(""+dy);
            tableArray.get(contArray).add(""+ddy);
            tableArray.get(contArray).add(""+E);
        }
        if (y.compareTo(BigDecimal.ZERO) == 0) {
            return x0.toString()+" is a root";
        }else if (dy.compareTo(BigDecimal.ZERO) == 0) {
            return "First derivative equal to 0";
        }else if (ddy.compareTo(BigDecimal.ZERO) == 0) {
            return "Second derivative equal to 0";
        }else if (E.compareTo(tol) < 0){
            return x0.toString()+" is an approximate root, error < tolerance";
        }else{
            return "Failure, has exceeded the maximum number of iterations";
        }
    }
}
