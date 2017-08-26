package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Candies {
    public static void main(String args[] ) throws Exception {
    	
    	try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); ) {
    		
    		int n = Integer.parseInt(br.readLine()), min = 0;
    		
    		int[] a = new int[n], m = new int[n];
    		for(int i = 0; i < n; i++) {
    			a[i] = Integer.parseInt(br.readLine());
    			m[i] = 1;
    		}
    		
    		for(int i = 1; i < n; i++) {
    			if(a[i - 1] < a[i]) {
    				m[i] = m[i - 1] + 1;
    			}else if(a[i - 1] > a[i]){
    				if(m[i - 1] <= m[i]) {
    					m[i - 1] = m[i] + 1;
    					for(int j = i - 1; (j >= 1) && (a[j] < a[j - 1]); j--) {
    						if(m[j - 1] <= m[j]) {
    							m[j - 1] = m[j - 1] + 1;
    						}
    					}
    				}
    			}
    		}
    		
    		for(int i = 0; i < n; i++) {
    			System.out.print(a[i] + "\t");
    		}
    		
    		System.out.println();
    		
    		for(int i = 0; i < n; i++) {
    			System.out.print(m[i] + "\t");
    		}
    		System.out.println();
    		
    		for(int i = 0; i < n; i++) {
    			min += m[i];
    		}
    		
    		
    		System.out.println(min);
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }
}
