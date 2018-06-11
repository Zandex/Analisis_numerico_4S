package co.edu.eafit.dis.analisisnumerico.interpolation.method_class;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import co.edu.eafit.dis.analisisnumerico.utility_class.ExpressionEvalUtil;
import co.edu.eafit.dis.analisisnumerico.utility_class.MethodsUtil;

public class NewtonDividedDifference {

    public static ArrayList<ArrayList<String>> tableArray = new ArrayList<ArrayList<String>>();

    public static int newtonDivided(String mf, BigDecimal []mx, BigDecimal y, int n) throws Exception {
        tableArray.clear();
        int contArray=0;
        int p;
        BigDecimal [][]A= MethodsUtil.fillMatrix(n);
        for(int j=0;j<n;j++){
            A[j][0]= ExpressionEvalUtil.functionEval(mf,new BigDecimal(j));
            contArray = printViewData(A,n,contArray);
        }

        for(int k=0; k<n-1;k++){
            p = 0;
            for(int i=k+1;i<n;i++){
                if(mx[i].subtract(mx[0]).compareTo(BigDecimal.ZERO)==0){
                    new Exception("Error, division by zero");
                }
                A[i][k+1]=(A[i][k].subtract(A[i-1][k])).divide(mx[i].subtract(mx[p]),32, RoundingMode.HALF_EVEN);
                contArray = printViewData(A,n,contArray);
                p=p++;
            }
        }
        p=0;
        for(int s=0;s<n;s++){
            BigDecimal aux= BigDecimal.ONE;
            for(int r=0;r<s;r++){
                aux=aux.multiply(y.subtract(mx[r]));
            }
            p = p + (A[s][s].multiply(aux).intValue());
        }
        return p;
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
