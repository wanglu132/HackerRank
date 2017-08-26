package search;

import java.util.HashMap;
import java.util.Scanner;

import tree.IntervalTree;
import tree.IntervalTree.Interval;

public class GridlandMetro {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int m = s.nextInt();
		int k = s.nextInt();
		
		HashMap<Integer, IntervalTree> hash = new HashMap<Integer, IntervalTree>();
		
		for(int i = 0; i < k; i++) {
			int r = s.nextInt();
			int c1 = s.nextInt();
			int c2 = s.nextInt();
				
			IntervalTree tree = hash.get(r);
			if(tree != null) {
				tree.delete(c1, c2);
			}
		}
	}
}
