import java.util.*;

class MatrixMultiplication {
	
	public static double[][] multiply(double[][] m1, double[][] m2) {
		int N = m1.length;
		int M = m2.length;
		double[][] m3 = new double[N][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				for (int k=0; k<M; k++) {
					m3[i][j] = m3[i][j] + m1[i][k] * m2[k][j];
				}
			}
		}
		return m3;
	}
	
    
    public static void main(String[] args) {
    	
    	/* just to show you what will happen:  
    	 
    	double[][] matrix = new double[4][5];
    	for (int i = 0; i< 4; i++) {
    		System.out.println(Arrays.toString(matrix[i]));
    	}
    	
    	*/
    	
    	Scanner input = new Scanner(System.in);
    	System.out.print("Enter two integers spearated by a space for rows and columns: ");
    	int N = input.nextInt();
    	int M = input.nextInt();
    	if (N <= 0 || M <= 0) {
    		System.out.println("The dimensions of a two dimensional array need to be greater than 0");
    		return;
    	}
    	double[][] m1 = new double[N][M];
    	double[][] m2 = new double[M][N];
    	// The contents of M1 and M2 will be randomly generated doubles from 0 to less than 10.
    	for (int i=0; i<N; i++) {
    		for (int j=0; j<M; j++) {
    			m1[i][j] = Math.random()*10;
    		}
    	}
    	for (int i=0; i<M; i++) {
    		for (int j=0; j<N; j++) {
    			m2[i][j] = Math.random()*10;
    		}
    	}
    	
    	double[][] m3 = multiply(m1, m2);
    	// In a readable format, print out M1, M2 and the result M3.
    	
    	System.out.println("M1:");
    	for (int i=0; i<N; i++) {
    		for (int j=0; j<M; j++) {
    			System.out.print(m1[i][j] + " ");
    		}
    		System.out.println();
    	}
    	System.out.println();
    	
    	System.out.println("M2:");
    	for (int i=0; i<M; i++) {
    		for (int j=0; j<N; j++) {
    			System.out.print(m2[i][j] + " ");
    		}
    		System.out.println();
    	}
    	System.out.println();
    	
    	System.out.println("M3:");
    	for (int i=0; i<N; i++) {
    		for (int j=0; j<N; j++) {
    			System.out.print(m3[i][j] + " ");
    		}
    		System.out.println();
    	}
    }
}
