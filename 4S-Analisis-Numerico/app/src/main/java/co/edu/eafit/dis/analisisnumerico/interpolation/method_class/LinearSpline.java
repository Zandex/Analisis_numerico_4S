package co.edu.eafit.dis.analisisnumerico.interpolation.method_class;

import java.math.BigDecimal;
import java.util.ArrayList;

import co.edu.eafit.dis.analisisnumerico.equation_system.method_class.GaussianElimination;
import co.edu.eafit.dis.analisisnumerico.equation_system.method_class.ProgressiveSubstitution;
import co.edu.eafit.dis.analisisnumerico.utility_class.ExpressionEvalUtil;
import co.edu.eafit.dis.analisisnumerico.utility_class.MethodsUtil;

public class LinearSpline {

    public static ArrayList<ArrayList<String>> tableArray = new ArrayList<ArrayList<String>>();

    public static BigDecimal linearSpline(String mf, BigDecimal []mx, BigDecimal y, int n) throws Exception {
        tableArray.clear();
        int contArray=0;
        n--;
        int k=0;
        int var=0;
        BigDecimal [][]A= new BigDecimal[n*2][n*2];
        A = MethodsUtil.fillMatrix(A.length);
        BigDecimal []b= new BigDecimal[n*2];
        for(int i=0;i<n*2;i++){
            int exp=1;
            for(int j=0;j<2;j++){
                A[i][k+j]= new BigDecimal(Math.pow(mx[var].doubleValue(),exp));
                exp=exp--;
            }
            b[i]= ExpressionEvalUtil.functionEval(mf,new BigDecimal(var));
            if(i%2==1){
                k=k+2;
            }else{
                var = var++;
            }
        }
        contArray = printViewData(A,A.length,contArray);
        contArray = printViewDataB(b,b.length,contArray);
        /*BigDecimal [][]auxR = GaussianElimination.gaussianElimination(A,A.length);
        BigDecimal []R= ProgressiveSubstitution.progressiveSubstitution(auxR,b,A.length);
        int ind=1;
        if(y.compareTo(mx[0])>=0){
            if(y.compareTo(mx[n])>=0){
                for(int i=1;i<n;i++){
                    if(y.compareTo(mx[i])>=0 && y.compareTo(mx[i+1])<0){
                        ind =i;
                    }
                }
            }else{
                ind = n-1;
            }
        }
        BigDecimal result =BigDecimal.ZERO;
        for(int i=1;i<2;i++){
            result=result.add(R[ind*2+i].multiply(y.pow(1-i)));
        }
        */
        //return result;
        return BigDecimal.ZERO;
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


    public static int printViewDataB(BigDecimal []A,int n,int contArray){
        tableArray.add(new ArrayList<String>());
        for(int j=0; j<n;j++){
            tableArray.get(contArray).add(""+A[j]);
        }
        contArray++;
        tableArray.add(new ArrayList<String>());
        for(int i=0; i<n;i++) {
            tableArray.get(contArray).add(" ");
        }
        return ++contArray;
    }

}
