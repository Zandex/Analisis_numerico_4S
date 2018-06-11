package co.edu.eafit.dis.analisisnumerico.equation_system.method_class;

import java.math.BigDecimal;

import co.edu.eafit.dis.analisisnumerico.utility_class.MethodsUtil;

public class ScaledPivoting {

    public static BigDecimal[][] scaledPivoting(BigDecimal [][]mA, int n, int p)throws Exception{

        BigDecimal [][]A= new BigDecimal[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) {
                A[i][j]= mA[i][j];
            }
        }

        for(;p>=0;p--) {
            BigDecimal S[] = new BigDecimal[n - p];
            BigDecimal highest;
            int highestR = 0;
            for (int i = p; i < n; i++) {
                highest = new BigDecimal(Math.abs(A[i][p].doubleValue()));
                for (int j = p + 1; j < n; j++) {
                    if (highest.compareTo(new BigDecimal(Math.abs(A[i][j].doubleValue()))) == -1) {
                        highest = new BigDecimal(Math.abs(A[i][j].doubleValue()));
                    }
                }
                S[i - p] = highest;
            }
            highest = BigDecimal.ZERO;
            highestR = p;
            int i = p;
            BigDecimal[] M;
            M = MethodsUtil.fillVector(n);
            while (i < n && M[i - p].compareTo(BigDecimal.ZERO) != 0) {
                BigDecimal aux = A[i][p].divide(S[i - p]);
                if (highest.compareTo(aux) == -1) {
                    highest = aux;
                    highestR = i;
                }
            }
            if (S[i - p].compareTo(BigDecimal.ZERO) == 0) {
                new Exception("The system has no unique solution");
            } else {
                A = MethodsUtil.swapRows(A, highestR, p, n);
            }

        }
        return A;
    }
}