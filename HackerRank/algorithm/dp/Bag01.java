package dp;

public class Bag01 {

	private static void DP(int n, int W, int[][] c, int[] v, int[] wei)  
	{  
	    for (int i = 1; i <= n; i++)  
	    {  
	        c[i][0] = 0;  
	        for (int w = 1; w <= W; w++)  
	        {  
	            if (wei[i-1] > w)    //此处比较是关键  
	            {  
	                c[i][w] = c[i-1][w];  
	            }  
	            else  
	            {  
	                int temp = c[i-1][w-wei[i-1]] + v[i-1]; //注意wei和v数组中的第i个应该为wei[i-1]和v[i-1]  
	                if (c[i-1][w] > temp)  
	                {  
	                    c[i][w] = c[i-1][w];  
	                }  
	                else   
	                    c[i][w] = temp;  
	            }  
	        }  
	    }  
	    
	    for(int i = 0; i <= n; i++){
	    	for(int j = 0; j <= W; j++) {
	    		System.out.print(c[i][j] + "\t");
	    	}
	    	System.out.println();
	    }
	}
	
	private static void findPath(int[][] c, int[] x, int[] wei, int n, int W)  
	{     
	    int w = W;  
	    for (int i = n; i >= 2; i--)  
	    {  
	        if (c[i][w] == c[i-1][w])  
	        {  
	            x[i-1] = 0;  
	        }  
	        else  
	        {  
	            x[i-1] = 1;  
	            w = w - wei[i-1];  
	        }  
	    }  
	    if (c[1][w] == 0)  
	        x[0] = 0;  
	    else  
	        x[0] = 1;  
	}
	
	public static void main(String[] args) {
		int n = 5;  
	    int W = 17;  
	    int w[] = {3, 4, 7, 8, 9};  
	    int v[] = {4, 5, 10, 11, 13};  
	    int[][] c = new int[6][18];  
	    DP(n, W, c, v, w);
	    System.out.println(c[5][17]);
	    int[] x = new int[5];  
	    findPath(c, x, w, n, W);  
	    for (int i = 0; i < n; i++)  
	    	System.out.print(x[i] + "\t");
	}
}
