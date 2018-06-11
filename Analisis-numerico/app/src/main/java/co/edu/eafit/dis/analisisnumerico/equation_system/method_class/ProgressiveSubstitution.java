package co.edu.eafit.dis.analisisnumerico.equation_system.method_class;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProgressiveSubstitution {

    public static BigDecimal[] progressiveSubstitution(BigDecimal[][] mA, BigDecimal[] mb, int n) throws Exception{
        BigDecimal [][]A= new BigDecimal[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) {
                A[i][j]= mA[i][j];
            }
        }
        BigDecimal []b= new BigDecimal[n];
        for(int i=0;i<n;i++){
            b[i]= mb[i];
        }

        BigDecimal sum;
        BigDecimal[] x= new BigDecimal[n];
        x[0]=b[0].divide(A[0][0],32,RoundingMode.HALF_UP);
        for(int i=0; i<n;i++){
            sum=BigDecimal.ZERO;
            for(int p=0; p<i;p++){
                sum= sum.add(A[i][p].multiply(x[p]));
            }
            if(A[i][i].compareTo(BigDecimal.ZERO)==0){
                throw new Exception("Error, division by zero");
            }
            x[i]=(b[i].subtract(sum)).divide(A[i][i],32, RoundingMode.HALF_UP);
        }
        return x;
    }
}
