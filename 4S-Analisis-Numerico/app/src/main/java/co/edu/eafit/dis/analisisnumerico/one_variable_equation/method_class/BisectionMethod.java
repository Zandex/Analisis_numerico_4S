package co.edu.eafit.dis.analisisnumerico.one_variable_equation.method_class;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import co.edu.eafit.dis.analisisnumerico.utility_class.ExpressionEvalUtil;

public class BisectionMethod {

    public static ArrayList<ArrayList<String>> tableArray = new ArrayList<>();

    public static String bisection(String f, int iter, BigDecimal tol, BigDecimal xi, BigDecimal xs)
            throws Exception{
        if(iter < 0 || tol.doubleValue() > 1 || tol.doubleValue() < 0){
           throw new Exception();
        }
        System.out.println("***"+tol);
        BigDecimal yi = ExpressionEvalUtil.functionEval(f,xi);
        BigDecimal ys = ExpressionEvalUtil.functionEval(f,xs);
        int n=0;
        int contArray=0;
        if(yi.compareTo(BigDecimal.ZERO)==0){
            return xi.toString();
        }else if(ys.compareTo(BigDecimal.ZERO) == 0){
            return xs.toString();
        }else if ((yi.multiply(ys)).compareTo(BigDecimal.ZERO) > 0){
            return "Error, There is no a root in the interval";
        }else{
            tableArray.add(new ArrayList<String>());
            tableArray.get(contArray).add(""+n);
            tableArray.get(contArray).add(""+xi);
            tableArray.get(contArray).add(""+xs);
            BigDecimal xm = (xi.add(xs)).divide(new BigDecimal("2"),32,BigDecimal.ROUND_HALF_EVEN);
            tableArray.get(contArray).add(""+xm);
            BigDecimal ym = ExpressionEvalUtil.functionEval(f,xm);
            tableArray.get(contArray).add(""+ym);
            BigDecimal E = tol.add(BigDecimal.ONE);
            tableArray.get(contArray).add(""+E);
            int count = 1;
            while((ym.compareTo(BigDecimal.ZERO) != 0) && (E.compareTo(tol)>0) && (count < iter)){
                n++;
                contArray++;
                System.out.println("Por aqui pase");
                tableArray.add(new ArrayList<String>());
                tableArray.get(contArray).add(""+n);
                if((ym.multiply(yi)).compareTo(BigDecimal.ZERO) < 0){
                    xs = xm;
                    ys = ym;
                    tableArray.get(contArray).add(""+xi);
                    tableArray.get(contArray).add(""+xs);
                }else{
                    xi = xm;
                    yi = ym;
                    tableArray.get(contArray).add(""+xi);
                    tableArray.get(contArray).add(""+xs);
                }
                BigDecimal xaux = xm;
                xm = (xi.add(xs)).divide(new BigDecimal("2"),32, RoundingMode.HALF_EVEN);
                tableArray.get(contArray).add(""+xm);
                ym = ExpressionEvalUtil.functionEval(f,xm);
                tableArray.get(contArray).add(""+ym);
                E = (xm.subtract(xaux).abs());
                System.out.println("+++"+E);
                System.out.println("----"+xm+" ym: " +ym);
                tableArray.get(contArray).add(""+E);
                count++;
            }

            if(ym.compareTo(BigDecimal.ZERO) == 0){
                return xm.toString();
            }else if(E.compareTo(tol) < 0){
                return xm.toString() + " is an approximate root, E < tolerance";
            }else{
                return "Failure, has exceeded the maximum number of iterations";
            }
        }
    }
}
