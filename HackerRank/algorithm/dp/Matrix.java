package dp;

/**
 * 对维数为序列<5, 10, 3, 12, 5, 50, 6>的各矩阵，找出其矩阵链乘积的一个最优加全部括号。<br>
 * 算法导论 15.2
 * @author june
 *
 */
public class Matrix {

	public static int[][] matrix_chain_order(int[] p) {
		int n = p.length-1;
		int[][] m = new int[n][n], s = new int[n][n];
		for(int i = 0; i < n; i++) {
			m[i][i] = 0;
		}
		for(int l = 1; l < n; l++) {
			for(int i = 0; i < n - l; i++){
				int j = i + l;
				m[i][j] = Integer.MAX_VALUE;
				for(int k = i; k < j; k++) {
					int q = m[i][k] + m[k + 1][j] + p[i] * p[k + 1] * p[j + 1];
					if(q < m[i][j]) {
						m[i][j] = q;
						s[i][j] = k;
					}
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++){
				System.out.print(m[i][j] + "\t");
			}
			System.out.print(System.getProperty("line.separator"));
		}
		
		System.out.print(System.getProperty("line.separator"));
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++){
				System.out.print(s[i][j] + "\t");
			}
			System.out.print(System.getProperty("line.separator"));
		}
		
		System.out.print(System.getProperty("line.separator"));
		
		return s;
	}
	
	public static void print_optimal_parens(int[][] s, int i, int j) {
		if(i == j) {
			System.out.print("A" + i);
		}else {
			System.out.print("(");
			print_optimal_parens(s, i, s[i][j]);
			print_optimal_parens(s, s[i][j] + 1, j);
			System.out.print(")");
		}
	}
	
	public static void main(String[] args) {
		
		int[] p = {30, 35, 15, 5, 10, 20, 25};
		
		int [][] s = matrix_chain_order(p);
		
		print_optimal_parens(s, 0, 5);
	}
	
	
}
