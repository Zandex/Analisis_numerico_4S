package co.edu.eafit.dis.analisisnumerico.equation_system.method_class;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import co.edu.eafit.dis.analisisnumerico.utility_class.MethodsUtil;

public class GaussianElimination {

    public static ArrayList<ArrayList<String>> tableArray = new ArrayList<ArrayList<String>>();

    public static BigDecimal[][] gaussianElimination(BigDecimal [][]mA, int n)throws Exception{
        tableArray.clear();
        BigDecimal [][]A= new BigDecimal[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) {
                A[i][j]= mA[i][j];
            }
        }
        BigDecimal multiplier;
        int contArray=0;
        for(int k=0;k<n;k++){
            for(int i=k+1;i<n;i++){
                if((A[k][k].compareTo(BigDecimal.ZERO))==0){
                    throw new Exception("Error, division by zero");
                }
                multiplier = A[i][k].divide(A[k][k],32, RoundingMode.HALF_UP);

                for(int j=k; j<n;j++){
                    A[i][j]=A[i][j].subtract(multiplier.multiply(A[k][j]));
                }
                contArray = printViewData(A,n,contArray);
            }
        }
        return A;
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
