package co.edu.eafit.dis.analisisnumerico.interpolation.method_class;

import java.math.BigDecimal;
import java.util.ArrayList;

import co.edu.eafit.dis.analisisnumerico.equation_system.method_class.GaussianElimination;
import co.edu.eafit.dis.analisisnumerico.equation_system.method_class.ProgressiveSubstitution;
import co.edu.eafit.dis.analisisnumerico.utility_class.ExpressionEvalUtil;
import co.edu.eafit.dis.analisisnumerico.utility_class.MethodsUtil;

public class QuadraticSpline {

    public static ArrayList<ArrayList<String>> tableArray = new ArrayList<ArrayList<String>>();

    public static BigDecimal quadraticSpline(String mf, BigDecimal []mx, BigDecimal y, int n) throws Exception {
        tableArray.clear();
        int contArray=0;
        n--;
        int k=0;
        int var=0;
        int exp;
        BigDecimal [][]A= new BigDecimal[n*3][n*3];
        A = MethodsUtil.fillMatrix(A.length);
        BigDecimal []b= new BigDecimal[n*3];
        for(int i=0;i<n*2;i++){
            exp=2;
            for(int j=0;j<3;j++){
                A[i][k]=new BigDecimal(Math.pow(mx[var].doubleValue(),exp));
                exp--;
            }
            b[i]= ExpressionEvalUtil.functionEval(mf,new BigDecimal(var));
            if(i%2==1){
                k=k+3;
            }else{
                var++;
            }
        }
        k=0;
        int kaux=k+3;
        int m=n*2;
        var=1;
        for(int i=0;i<n-1;i++){
            exp=1;
            for(int j=0;j<2;j++){
                A[m+i][k+j]= new BigDecimal(Math.pow(mx[var].doubleValue(),exp)).multiply(new BigDecimal(2-j));
                A[m+i][kaux+j]= new BigDecimal(Math.pow(mx[var].doubleValue(),exp)).multiply(new BigDecimal(-(2-j)));
                exp--;
            }
            k+=3;
            kaux+=3;
            b[m+i] = BigDecimal.ZERO;
            var++;
        }

        A[A.length-1][0] = new BigDecimal(2);
        b[b.length-1] = BigDecimal.ZERO;

        contArray = printViewData(A,A.length,contArray);
        contArray = printViewDataB(b,b.length,contArray);
        /*BigDecimal [][]auxR = GaussianElimination.gaussianElimination(A,n);
        BigDecimal []R= ProgressiveSubstitution.progressiveSubstitution(auxR,b,n);
        int ind=1;
        if(y.compareTo(mx[1])>=0){
            if(y.compareTo(mx[n])>=0){
                for(int i=1;i<n;i++){
                    if(y.compareTo(mx[i])>=0 && y.compareTo(mx[i+1])<0){
                        ind =i;
                    }
                }
            }else{
                ind =n-1;
            }
        }
        BigDecimal result =BigDecimal.ZERO;
        for(int i=1;i<3;i++){
            result=result.add(R[ind*3+i].multiply(y.pow(2-i)));
        }
        return result;
        */
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