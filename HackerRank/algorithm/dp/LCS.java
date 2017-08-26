package dp;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class LCS {

    static int commonChild(String s1, String s2){
      char[] a1 = s1.toCharArray();
		char[] a2 = s2.toCharArray();
		int[][] m = new int[a1.length][a2.length];
		int max = 0;
		int ma = 0, mb = 0;
        
		for(int i = 0; i < a1.length; i++) {
			for(int j = 0; j < a2.length; j++){
				b: 
				if(a1[i] == a2[j]){
					m[j][i] = 1;
					for(int k = i - 1; k >= 0; k--) {
						for(int v = j - 1; v >= 0; v--){
							if(m[v][k] > 0){
								m[j][i] = m[j][i] + m[v][k];
								if(m[j][i] > max) {
									max = m[j][i];
								}
								break b;
							}
						}
					}
				}
				
			}
		}
		
		System.out.print("\t");
		for(int i = 0; i < a1.length; i++) {
			System.out.print(a1[i] + "\t");
		}
		System.out.println();
		for(int i = 0; i < a1.length; i++) {
			System.out.print(a2[i] + "\t");
			for(int j = 0; j < a2.length; j++){
				System.out.print(m[i][j] + "\t");
			}
			System.out.println();
		}
		
	   return max;
    }
    
    static int commonChild2(String s1, String s2){
        char[] a1 = s1.toCharArray();
  		char[] a2 = s2.toCharArray();
  		int[][] m = new int[a1.length][a2.length];
          
  		for(int i = 0; i < a1.length; i++) {
  			for(int j = 0; j < a2.length; j++){
  				if(a1[i] == a2[j]){
  					m[i][j] = (i == 0 || j == 0) ? 1 : m[i-1][j-1] + 1;
  				}else{
  					if((i == 0 ? 0 : m[i-1][j]) >= (j == 0 ? 0 : m[i][j-1])) {
  						m[i][j] = i == 0 ? 0 : m[i-1][j];
  					}else{
  						m[i][j] = j == 0 ? 0 : m[i][j-1];
  					}
  				}
  				
  			}
  		}
  		
  		System.out.print("\t");
  		for(int i = 0; i < a1.length; i++) {
  			System.out.print(a1[i] + "\t");
  		}
  		System.out.println();
  		for(int i = 0; i < a1.length; i++) {
  			System.out.print(a2[i] + "\t");
  			for(int j = 0; j < a2.length; j++){
  				System.out.print(m[i][j] + "\t");
  			}
  			System.out.println();
  		}
  		
  	   return m[a1.length-1][a2.length-1];
      }
    
    static int commonChild3(String s1, String s2){
        char[] a1 = s1.toCharArray();
  		char[] a2 = s2.toCharArray();
  		int[][] m = new int[a1.length + 1][a2.length + 1];
          
  		for(int i = 1; i <= a1.length; i++) {
  			for(int j = 1; j <= a2.length; j++){
  				if(a1[i - 1] == a2[j - 1]){
  					m[i][j] = m[i-1][j-1] + 1;
  				}else{
  					if(m[i-1][j] >= m[i][j-1]) {
  						m[i][j] = m[i-1][j];
  					}else{
  						m[i][j] = m[i][j-1];
  					}
  				}
  				
  			}
  		}
  		
  		System.out.print("\t");
  		for(int i = 0; i < a1.length; i++) {
  			System.out.print(a1[i] + "\t");
  		}
  		System.out.println();
  		for(int i = 0; i < a1.length; i++) {
  			System.out.print(a2[i] + "\t");
  			for(int j = 0; j < a2.length; j++){
  				System.out.print(m[i][j] + "\t");
  			}
  			System.out.println();
  		}
  		
  	   return m[a1.length-1][a2.length-1];
      }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s1 = in.next();
        String s2 = in.next();
        int result = commonChild2(s1, s2);
        System.out.println(result);
    }
}
