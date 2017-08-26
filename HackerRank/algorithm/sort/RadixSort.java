package sort;

import java.util.ArrayList;
import java.util.Scanner;

public class RadixSort {

	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] unsorted = new String[n];
        int max = 0;
        for(int unsorted_i=0; unsorted_i < n; unsorted_i++){
            unsorted[unsorted_i] = in.next();
            max = Math.max(unsorted[unsorted_i].length(), max);
        }
        
        ArrayList<String>[] a = new ArrayList[max];
        
        for(int i = 0; i < n; i++){
            if(a[unsorted[i].length() - 1] == null) {
            	a[unsorted[i].length() - 1] = new ArrayList<String>();
            };
            a[unsorted[i].length() - 1].add(unsorted[i]);
        }
        
        for(int i = 0; i < a.length; i++){
        	if(a[i] != null){
        		a[i] = radixSort(a[i]);
        	}
        }
        
        for(int i = 0; i < a.length; i++){
        	if(a[i] != null){
        		for(int j = 0; j < a[i].size(); j++) {
        			System.out.println(a[i].get(j));
        		}
        	}
        }
        
//        String[] r = radixSort(new String[]{"a", "d", "s", "h", "z", "b"});
//        
//        for(int i = 0; i < r.length; i++) {
//			System.out.println(r[i]);
//		}
    }
	
	private static String[] radixSort(String[] as) {
		int row = as.length < 26 ? 26 : as.length, column = as[0].length();
		String[][] bucket = new String[row][column];
		
		for(int j = 0; j < as.length; j++) {
			int base = as[j].charAt(column - 1) - 97;
			for(; bucket[base][column - 1] != null; base++);
			bucket[base][column - 1] = as[j];
		}
		
		if(column > 1) {
			for(int i = column - 2; i >= 0; i--) {
				for(int j = 0; j < row; j++) {
					if(bucket[j][i + 1] != null) {
						int base = bucket[j][i + 1].charAt(i) - 97;
						for(; bucket[base][i] != null; base++);
						bucket[base][i] = bucket[j][i + 1];
					}
				}
			}
		}
		
		String[] rs= new String[as.length];
		for(int i = 0, j = 0; i < row; i++) {
			if(bucket[i][0] != null) {
				rs[j++] = bucket[i][0];
			}
		}
		return rs;
	}
	
	private static ArrayList<String> radixSort(ArrayList<String> as) {
		int row = as.size() < 10 ? 10 : as.size(), column = as.get(0).length();
		String[][] bucket = new String[row][column];
		
		for(int j = 0; j < as.size(); j++) {
			int base = as.get(j).charAt(column - 1) - 48;
			for(; bucket[base][column - 1] != null; base++);
			bucket[base][column - 1] = as.get(j);
		}
		
		if(column > 1) {
			for(int i = column - 2; i >= 0; i--) {
				for(int j = 0; j < row; j++) {
					if(bucket[j][i + 1] != null) {
						int base = bucket[j][i + 1].charAt(i) - 48;
						for(; bucket[base][i] != null; base++);
						bucket[base][i] = bucket[j][i + 1];
					}
				}
			}
		}
		
		ArrayList<String> rs= new ArrayList<String>();
		for(int i = 0; i < row; i++) {
			if(bucket[i][0] != null) {
				rs.add(bucket[i][0]);
			}
		}
		return rs;
	}
	
}
