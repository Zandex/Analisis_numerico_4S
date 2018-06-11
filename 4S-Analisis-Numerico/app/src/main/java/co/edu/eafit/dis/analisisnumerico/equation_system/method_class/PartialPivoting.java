package co.edu.eafit.dis.analisisnumerico.equation_system.method_class;

import java.math.BigDecimal;

import co.edu.eafit.dis.analisisnumerico.utility_class.MethodsUtil;

public class PartialPivoting{

    public static BigDecimal[][] partialPivoting(BigDecimal[][]mA, int n)throws Exception{

        BigDecimal [][]A= new BigDecimal[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) {
                A[i][j]= mA[i][j];
            }
        }

        for(int k=n-1;k>=0;k--) {
            BigDecimal highest = new BigDecimal(Math.abs(A[k][k].doubleValue()));
            int highestR = k;
            for (int i = 0; i < n; i++) {
                if (new BigDecimal(Math.abs(A[i][k].doubleValue())).compareTo(highest) == 1) {
                    highest = new BigDecimal(Math.abs(A[i][k].doubleValue()));
                    highestR = i;
                }
            }
            if (highest.compareTo(BigDecimal.ZERO) == 0) {
                new Exception("The system has no unique solution");
            } else {
                if (highestR != k) {
                    A = MethodsUtil.swapRows(A, highestR, k, n);
                }
            }
        }
        return A;
    }
}