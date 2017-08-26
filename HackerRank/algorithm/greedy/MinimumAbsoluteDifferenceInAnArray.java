package greedy;

import java.util.PriorityQueue;
import java.util.Scanner;

public class MinimumAbsoluteDifferenceInAnArray {

	static int minimumAbsoluteDifference(int n, int[] arr) {
		
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		for(int i = 0; i < arr.length; i++){
			q.offer(arr[i]);
		}
		
		int pre = q.poll(), min = Integer.MAX_VALUE;
		for(Integer a = 1; (a = q.poll()) != null; pre = a) {
			int c = a - pre;
			if(c == 0) {
				return 0;
			}
			min = Math.min(a - pre, min);
		}
		return min;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] arr = new int[n];
		for (int arr_i = 0; arr_i < n; arr_i++) {
			arr[arr_i] = in.nextInt();
		}
		int result = minimumAbsoluteDifference(n, arr);
		System.out.println(result);
		in.close();
	}
}
