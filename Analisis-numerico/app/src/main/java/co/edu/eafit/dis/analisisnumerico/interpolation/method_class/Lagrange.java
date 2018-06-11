package co.edu.eafit.dis.analisisnumerico.interpolation.method_class;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import co.edu.eafit.dis.analisisnumerico.utility_class.ExpressionEvalUtil;

public class Lagrange {

    public static ArrayList<ArrayList<String>> tableArray = new ArrayList<ArrayList<String>>();

    public static BigDecimal lagrange(String mf, BigDecimal []mx, BigDecimal y, int n) throws Exception {
        tableArray.clear();
        int contArray=0;
        BigDecimal []x= new BigDecimal[n];
        for(int i=0;i<n;i++){
            x[i]= mx[i];
        }
        String f= new String();
        f= mf;

        BigDecimal p= BigDecimal.ZERO;
        for(int i=0; i<n;i++){
            BigDecimal L= BigDecimal.ONE;
            for(int j=1;j<n;j++){
                if(j!=i){
                    L=((y.subtract(x[i])).divide(x[i].subtract(x[j]),32, RoundingMode.HALF_EVEN)).multiply(L);
                    tableArray.add(new ArrayList<String>());
                    tableArray.get(contArray).add(""+L);
                    contArray++;
                }
            }
            p=p.add((L.multiply(ExpressionEvalUtil.functionEval(f,new BigDecimal(i)))));
        }
        return p;
    }

}
