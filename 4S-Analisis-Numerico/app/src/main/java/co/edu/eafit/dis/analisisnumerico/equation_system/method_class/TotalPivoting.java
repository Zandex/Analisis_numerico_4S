package co.edu.eafit.dis.analisisnumerico.equation_system.method_class;


import java.math.BigDecimal;

import co.edu.eafit.dis.analisisnumerico.utility_class.MethodsUtil;

public class TotalPivoting {

    public static BigDecimal matrixA[][];
    public static int vecMarks[];

    public static BigDecimal[][] totalPivoting(BigDecimal[][]mA, int n)throws Exception{
        BigDecimal [][]A= new BigDecimal[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                A[i][j]= mA[i][j];
            }
        }
        BigDecimal highest= BigDecimal.ZERO;
        int highestR=0;
        int highestC=0;
        int []marks = new int[n];
        marks = MethodsUtil.fillMarks(marks,n);
        for (int i=0; i<n;i++){
            for(int j=0;j<n;j++){
                if((A[i][j].abs()).compareTo(highest)== 1){
                    highest = A[i][j].abs();
                    highestR=i;
                    highestC=j;
                }
            }
        }
        if(highest.compareTo(BigDecimal.ZERO)==0){
            new Exception("The system has no unique solution");
        }else{
            if(highestR!=0){
                A= MethodsUtil.swapRows(A,highestR,0,n);
            }
            if(highestC!=0){
                A = MethodsUtil.swapColumns(A,highestC,0,n);
                marks=MethodsUtil.swapMarks(marks,highestC,0);
            }
        }
        vecMarks=marks;
        return A;
    }
}
