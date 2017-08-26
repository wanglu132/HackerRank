package recursion;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class ThePowerSum {
	
	int way = 0;
	
	public static int getMax(int x, int n) {
		int max = 0;
		for(int i = 1; i < 1000; i++) {
			if(Math.pow(i, n) <= x) {
				max = i;
			}else{
				break;
			}
		}
		return max;
	}
	
	static int findPowerSum(int total, int power, int num) {
	    int value = total - (int)Math.pow(num, power);
	    System.out.printf("%d\t%d\t%d\t%d%n", total, value, num, power);
	    if(value < 0) return 0;
	    else if(value == 0) return 1;
	    else return findPowerSum(value , power, num + 1) +
	    			findPowerSum(total, power, num + 1);
	}
	
	
//	public static boolean recursion(int max, int n, int x) {
//		int rest = x - (int)Math.pow(max, n);
//		if(rest == 0) {
//			return true;
//		}else if(max == 1){
//			return false;
//		}else{
//			return recursion(nMax, n, rest);
//		}
//	}
	
	
	
    public static void main(String args[] ) throws Exception {
    	
    	int x = 0, n = 0;
    	try (Scanner s = new Scanner(System.in);) {
    		x = Integer.parseInt(s.nextLine());
        	n = Integer.parseInt(s.nextLine());
    	}
    	
    	
    	
//    	int way = 0;
//    	int max = getMax(x, n);
//    	for(int i = max; i > 1; i--) {
//    		if(recursion(i, n, x)) {
//    			way++;
//    			System.out.println(i);
//    		}
//    	}
    	
    	System.out.println(findPowerSum(x, n, 1));
    }
}