package co.edu.eafit.dis.analisisnumerico.equation_system.method_class;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import co.edu.eafit.dis.analisisnumerico.utility_class.MethodsUtil;

public class LUFactorizationCrout {

    public static ArrayList<ArrayList<String>> tableArrayL = new ArrayList<ArrayList<String>>();
    public static ArrayList<ArrayList<String>> tableArrayU = new ArrayList<ArrayList<String>>();
    static BigDecimal [][]Lresult;
    static BigDecimal [][]Uresult;

    public static void crout(BigDecimal[][]mA, int n)throws Exception{
        tableArrayL.clear();
        tableArrayU.clear();
        BigDecimal [][]A= new BigDecimal[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) {
                A[i][j]= mA[i][j];
            }
        }
        int contArrayL=0;
        int contArrayU=0;

        BigDecimal[][]L=MethodsUtil.fillMatrix(n);
        BigDecimal[][]U=MethodsUtil.fillMatrix(n);

        contArrayL = printViewDataL(L,n,contArrayL);
        contArrayU = printViewDataU(U,n,contArrayU);
        for(int k=0; k<n;k++){
            BigDecimal sum= BigDecimal.ZERO;
            for(int p=0; p<k;p++) {
                sum = sum.add(L[k][p].multiply(U[k][p]));
            }
            L[k][k]=A[k][k].subtract(sum);
            U[k][k]= BigDecimal.ONE;
            contArrayL = printViewDataL(L,n,contArrayL);
            contArrayU = printViewDataU(U,n,contArrayU);
            for(int i=k+1; i<n;i++){
                sum=BigDecimal.ZERO;
                for(int r=0; r<k;r++) {
                    if (L[k][k].compareTo(BigDecimal.ZERO) == 0) {
                        throw new Exception("Error, division by zero");
                    }
                    sum = sum.add(L[i][r].multiply(U[r][k]));
                }
                L[i][k]=(A[i][k].subtract(sum)).divide(U[k][k],32, RoundingMode.HALF_UP);
                contArrayL = printViewDataL(L,n,contArrayL);
            }
            for(int j=k+1; j<n;j++){
                sum=BigDecimal.ZERO;
                for(int s=0; s<k;s++) {
                    if (L[k][k].compareTo(BigDecimal.ZERO) == 0) {
                        throw new Exception("Error, division by zero");
                    }
                    sum = sum.add(L[k][s].multiply(U[s][j]));
                }
                U[k][j]=(A[k][j].subtract(sum)).divide(L[k][k],32, RoundingMode.HALF_UP);
                contArrayU = printViewDataU(U,n,contArrayU);
            }
        }
        Lresult=L;
        Uresult=U;
    }


    public static int printViewDataL(BigDecimal [][]A,int n,int contArrayL){
        for(int i=0; i<n;i++){
            tableArrayL.add(new ArrayList<String>());
            for(int j=0; j<n;j++){
                tableArrayL.get(contArrayL).add(""+A[i][j]);
            }
            contArrayL++;
        }
        tableArrayL.add(new ArrayList<String>());
        for(int i=0; i<n;i++) {
            tableArrayL.get(contArrayL).add(" ");
        }
        return ++contArrayL;
    }

    public static int printViewDataU(BigDecimal [][]A,int n,int contArrayU){
        for(int i=0; i<n;i++){
            tableArrayU.add(new ArrayList<String>());
            for(int j=0; j<n;j++){
                tableArrayU.get(contArrayU).add(""+A[i][j]);
            }
            contArrayU++;
        }
        tableArrayU.add(new ArrayList<String>());
        for(int i=0; i<n;i++) {
            tableArrayU.get(contArrayU).add(" ");
        }
        return ++contArrayU;
    }
}
