package co.edu.eafit.dis.analisisnumerico.utility_class;


import java.math.BigDecimal;

public class MethodsUtil {

    public static BigDecimal[][] augmentedMatrix(BigDecimal [][]A,BigDecimal []b, int n){
        BigDecimal [][]Ab = new BigDecimal[n][n+1];
        int aux=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n+1;j++){
                if(j<=n) {
                    Ab[i][j] = A[i][j];
                }else{
                    Ab[i][j] = b[aux];
                    aux++;
                }
            }
        }
        return Ab;
    }


    public static void printMatrix(BigDecimal[][] A, int n){
        for(int i=0; i<n;i++){
            for(int j=0; j<n;j++){
                System.out.print(A[i][j]+"  |  ");
            }
            System.out.println("");
        }
        return;
    }


    public static void printVector(BigDecimal[]x, int n){
        for(int i=0; i<n;i++){
            System.out.print(x[i]+"  |  ");
        }
        return;
    }


    public static BigDecimal[][] fillMatrix(int n){
        BigDecimal[][]matrix = new BigDecimal[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                matrix[i][j]=BigDecimal.ZERO;
            }
        }
        return matrix;
    }


    public static BigDecimal[] fillVector(int n){
        BigDecimal[]matrix = new BigDecimal[n];
        for(int i=0;i<n;i++){
            matrix[i]=BigDecimal.ZERO;
        }
        return matrix;
    }



    public static BigDecimal[][] swapRows(BigDecimal [][]A, int highest,int k,int n){
        for (int i = 0; i < n; i++) {
            BigDecimal temp = A[highest][i];
            A[highest][i] = A[k][i];
            A[k][i] = temp;
        }
        return A;
    }

    public static BigDecimal[][] swapColumns(BigDecimal [][]A, int highest,int k,int n){
        for (int i = 0; i < n; i++) {
            BigDecimal temp = A[i][highest];
            A[i][highest] = A[i][k];
            A[i][k] = temp;
        }
        return A;
    }

    public static int[] swapMarks(int []M, int k,int n){
            int []temp = M;
            M[k] = temp[n];
            M[n] = temp[k];
        return M;
    }

    public static int[] fillMarks(int []M,int n){
        for (int i=0; i<n; i++){
            M[i] = i+1;
        }
        return M;
    }

}
