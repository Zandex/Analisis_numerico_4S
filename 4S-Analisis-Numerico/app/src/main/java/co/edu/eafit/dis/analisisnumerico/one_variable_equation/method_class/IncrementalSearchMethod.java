package co.edu.eafit.dis.analisisnumerico.one_variable_equation.method_class;


import java.math.BigDecimal;
import java.util.ArrayList;

import co.edu.eafit.dis.analisisnumerico.utility_class.ExpressionEvalUtil;

public class IncrementalSearchMethod {

    public static ArrayList<ArrayList<String>> tableArray = new ArrayList<>();

    public static String incrementalSearch(String f, int iter, BigDecimal delta, BigDecimal x0)
            throws Exception{
        if(iter < 0 || delta.compareTo(BigDecimal.ZERO) < 1){
            throw new Exception();
        }
        BigDecimal y0 = ExpressionEvalUtil.functionEval(f,x0);
        int n=0;
        int contArray=0;
        if (y0.compareTo(BigDecimal.ZERO) == 0){
            return x0.toString()+ " Es raiz";
        }else{
            BigDecimal x1 = x0.add(delta);
            BigDecimal y1 = ExpressionEvalUtil.functionEval(f,x1);
            tableArray.add(new ArrayList<String>());
            tableArray.get(contArray).add(""+n);
            tableArray.get(contArray).add(""+x0);
            tableArray.get(contArray).add(""+y0);
            tableArray.get(contArray).add(""+x1);
            tableArray.get(contArray).add(""+y1);
            int count = 1;
            while (((y0.multiply(y1)).compareTo(BigDecimal.ZERO) > 0) && (count < iter)){
                n++;
                contArray++;
                tableArray.add(new ArrayList<String>());
                x0 = x1;
                y0 = y1;
                x1 = x0.add(delta);
                y1 = ExpressionEvalUtil.functionEval(f,x1);
                tableArray.get(contArray).add(""+n);
                tableArray.get(contArray).add(""+x0);
                tableArray.get(contArray).add(""+y0);
                tableArray.get(contArray).add(""+x1);
                tableArray.get(contArray).add(""+y1);
                count = count + 1;
            }
            if (y1.compareTo(BigDecimal.ZERO) == 0){
                return x1.toString()+ " Es raiz";
            }else if ((y1.multiply(y0)).compareTo(BigDecimal.ZERO) < 0){
                return x0.toString()+", "+ x1.toString()+" Es un intervalod donde la raiz existe";
            }else{
                return "Falla, Ha Exedido el numero maximo de interaciones";
            }
        }
    }

}
