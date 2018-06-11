package co.edu.eafit.dis.analisisnumerico.one_variable_equation.method_class;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import co.edu.eafit.dis.analisisnumerico.utility_class.ExpressionEvalUtil;

public class SecantMethod {

    public static ArrayList<ArrayList<String>> tableArray = new ArrayList<>();

    public static String secant(String f, int iter, BigDecimal tol, BigDecimal x1, BigDecimal x0)
            throws Exception {
        if(iter < 0 || tol.doubleValue() > 1 || tol.doubleValue() < 0){
            throw new Exception();
        }
        BigDecimal y0 = ExpressionEvalUtil.functionEval(f, x0);
        BigDecimal y1 = ExpressionEvalUtil.functionEval(f, x1);
        if (y0.compareTo(BigDecimal.ZERO) != 0) {
            int count = 0;
            BigDecimal E = tol.add(BigDecimal.ONE);
            BigDecimal div = y1.subtract(y0);
            BigDecimal y2 = BigDecimal.ONE;
            int n=0;
            int contArray=0;
            tableArray.add(new ArrayList<String>());
            tableArray.get(contArray).add(""+n);
            tableArray.get(contArray).add(""+x0);
            tableArray.get(contArray).add(""+y0);
            tableArray.get(contArray).add(""+x1);
            tableArray.get(contArray).add(""+y1);
            tableArray.get(contArray).add(""+E);
            while ((y1.compareTo(BigDecimal.ZERO) != 0) && (y2.compareTo(BigDecimal.ZERO) != 0)
                    && (E.compareTo(tol) > 0) && (count < iter)) {
                BigDecimal x2 = x1.subtract((y1.multiply(x1.subtract(x0))).divide(div, 32, RoundingMode.HALF_UP));
                E = x1.subtract(x0).abs();
                x0 = x1;
                x1 = x2;
                y0 = ExpressionEvalUtil.functionEval(f, x0);
                y1 = ExpressionEvalUtil.functionEval(f, x1);
                div = y1.subtract(y0);
                y2 = ExpressionEvalUtil.functionEval(f, x2);
                count++;
                n++;
                contArray++;
                tableArray.add(new ArrayList<String>());
                tableArray.get(contArray).add(""+n);
                tableArray.get(contArray).add(""+x0);
                tableArray.get(contArray).add(""+y0);
                tableArray.get(contArray).add(""+x1);
                tableArray.get(contArray).add(""+y1);
                tableArray.get(contArray).add(""+E);
            }
            if (y1.compareTo(BigDecimal.ZERO) == 0) {
                return x0.toString()+" is a root";
            } else if (y2.compareTo(BigDecimal.ZERO) == 0) {
                return x1.toString()+" is a root";
            } else if (E.compareTo(tol) < 0) {
                return x1.toString()+" is an approximate root, error < tolerance";
            } else if (div.compareTo(BigDecimal.ZERO) == 0) {
                return "Division by zero";
            } else {
                return "Failure, has exceeded the maximum number of iterations";
            }
        }else {
            return x0.toString()+" is a root";
        }
    }
}
