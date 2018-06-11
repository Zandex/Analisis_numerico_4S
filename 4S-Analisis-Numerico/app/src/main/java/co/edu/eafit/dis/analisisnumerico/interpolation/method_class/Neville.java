package co.edu.eafit.dis.analisisnumerico.interpolation.method_class;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import co.edu.eafit.dis.analisisnumerico.utility_class.ExpressionEvalUtil;
import co.edu.eafit.dis.analisisnumerico.utility_class.MethodsUtil;

public class Neville {


    public static ArrayList<ArrayList<String>> tableArray = new ArrayList<ArrayList<String>>();

    public static BigDecimal neville(String mf, BigDecimal []mx, BigDecimal y, int n) throws Exception {
        tableArray.clear();
        int contArray=0;
        BigDecimal []x= new BigDecimal[n];
        for(int i=0;i<n;i++){
            x[i]= mx[i];
        }
        String f= new String();
        f= mf;

        BigDecimal [][]A= MethodsUtil.fillMatrix(n);
        for(int j=0;j<n;j++){
            A[j][0]= ExpressionEvalUtil.functionEval(f,new BigDecimal(j));
            contArray = printViewData(A,n,contArray);
        }

        for(int k=0; k<n-1;k++){
            int p = 0;
            for(int i=k+1;i<n;i++){
                A[i][k+1]=(((y.subtract(x[p]).multiply(A[i][k])).subtract((y.subtract(x[i]))
                        .multiply(A[i-1][k])))).divide((x[i]).subtract(x[p]),32, RoundingMode.HALF_EVEN);
                contArray = printViewData(A,n,contArray);
                p++;
            }
        }
        return A[n-1][n-1];
    }

    public static int printViewData(BigDecimal [][]A,int n,int contArray){
        for(int i=0; i<n;i++){
            tableArray.add(new ArrayList<String>());
            for(int j=0; j<n;j++){
                tableArray.get(contArray).add(""+A[i][j]);
            }
            contArray++;
        }
        tableArray.add(new ArrayList<String>());
        for(int i=0; i<n;i++) {
            tableArray.get(contArray).add(" ");
        }
        return ++contArray;
    }

}
