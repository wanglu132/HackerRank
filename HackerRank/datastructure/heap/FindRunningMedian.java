package heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class FindRunningMedian {

	/* 1,max一定小于等于min
	 * 2,max与min 元素个数之差的范围<=1
	 * 
	 */
	private static class Median {
		
		private PriorityQueue<Integer> minQ = new PriorityQueue<Integer>();
		private PriorityQueue<Integer> maxQ = new PriorityQueue<Integer>(Comparator.reverseOrder());
		
		private void offer(int e) {
			
			if(maxQ.size() == 0) {
				maxQ.offer(e);
				return;
			}
			
			if(e < maxQ.peek()) {
				maxQ.offer(e);
				if(maxQ.size() - minQ.size() > 1) {
					minQ.offer(maxQ.poll());
				}
			}else{
				minQ.offer(e);
				if(minQ.size() - maxQ.size() > 1) {
					maxQ.offer(minQ.poll());
				}
			}
		}
		
		private double getMedian() {
			if(maxQ.size() == minQ.size()) {
				return (maxQ.peek() + minQ.peek()) / 2.0D;
			}else if(maxQ.size() > minQ.size()) {
				return maxQ.peek();
			}else{
				return minQ.peek();
			}
		}
	}
	
	
    public static void main(String[] args) {
    	
    	Median median = new Median();
    	try( Scanner in = new Scanner(System.in); ) {
    		
    		int n = in.nextInt();
            
            int[] a = new int[n];
            for(int a_i = 0; a_i < n; a_i++){
                a[a_i] = in.nextInt();
                
                median.offer(a[a_i]);
                
            	System.out.println(median.getMedian());
            }
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
        
    }
}