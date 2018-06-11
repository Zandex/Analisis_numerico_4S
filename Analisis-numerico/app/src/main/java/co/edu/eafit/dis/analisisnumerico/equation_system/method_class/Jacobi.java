package co.edu.eafit.dis.analisisnumerico.equation_system.method_class;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import co.edu.eafit.dis.analisisnumerico.utility_class.MethodsUtil;

public class Jacobi {

    public static ArrayList<ArrayList<String>> tableArray = new ArrayList<ArrayList<String>>();

    public static BigDecimal[] jacobi(BigDecimal[][] mA, BigDecimal[] xVal, BigDecimal mb[],
                                      int iter, BigDecimal tol, int n) throws Exception {

        tableArray.clear();
        BigDecimal [][]A= new BigDecimal[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) {
                A[i][j]= mA[i][j];
            }
        }
        BigDecimal []x0= new BigDecimal[n];
        for(int i=0;i<n;i++){
            x0[i]= xVal[i];
        }
        BigDecimal []b= new BigDecimal[n];
        for(int i=0;i<n;i++){
            b[i]= mb[i];
        }

        int count =0;
        BigDecimal error = tol.add(BigDecimal.ONE);
        BigDecimal []x=new BigDecimal[n];
        BigDecimal aux;
        int nTable=0;
        int contArray=0;
        tableArray.add(new ArrayList<String>());
        tableArray.get(contArray).add(""+nTable);
        for(int i=0;i<n;i++){
            tableArray.get(contArray).add(""+x0[i]);
        }
        tableArray.get(contArray).add(" ");
        while(error.compareTo(tol)>0 && count < iter){
            error=BigDecimal.ZERO;
            nTable++;
            contArray++;
            tableArray.add(new ArrayList<String>());
            tableArray.get(contArray).add(""+nTable);
            for(int i=0; i<n;i++){
                BigDecimal sum=BigDecimal.ZERO;
                for(int j=0;j<n;j++){
                    if(i!=j){
                        sum=sum.add(A[i][j].multiply(x0[j]));
                    }
                }
                x[i]=(b[i].subtract(sum)).divide(A[i][i],32, RoundingMode.HALF_UP);
                aux=x[i].subtract(x0[i]);
                error = error.add(new BigDecimal(Math.sqrt(Math.abs(aux.doubleValue()))));
                tableArray.get(contArray).add(x[i].toString());

            }
            error= new BigDecimal(Math.sqrt(error.doubleValue()));
            tableArray.get(contArray).add(error.toString());
            for(int i=0;i<n;i++){
                x0[i]=x[i];
            }
            count++;
        }

        if(error.compareTo(tol)>0){
            new Exception("Failure, maximum number of iterations reached");
        }
        return x;

    }
}
