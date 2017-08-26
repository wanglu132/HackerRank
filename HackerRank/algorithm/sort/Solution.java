package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	
	private static void swap(int[] a, int j) {
		int temp=a[j];  
        a[j]=a[j+1];  
        a[j+1]=temp;
	}

    public static void main(String[] args) {
    	
    	try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); ) {
    		
			int n = Integer.parseInt(br.readLine().trim());
			int[] a = new int[n];
			
			String[] as = br.readLine().trim().split(" ");
			for(int i = 0; i < as.length; i++) {
				a[i] = Integer.parseInt(as[i]);
			}
			
			int swaps = 0;
			
			for(int i=0;i<n-1;i++){  
		        for(int j=0;j<n-1-i;j++){  
			        if(a[j]>a[j+1]){  
			        	swap(a, j);
			        	swaps++;
			        }  
		        }  
		    }  
			
			System.out.println(String.format("Array is sorted in %d swaps.", swaps));
			System.out.println(String.format("First Element: %d", a[0]));
			System.out.println(String.format("Last Element: %d", a[n-1]));
			
			
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
}