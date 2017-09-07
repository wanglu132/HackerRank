package graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

class Node {
	
	int key;
	
	Node p;
	
	int rank, size;

	public Node(int key) {
		this.key = key;
		this.p = this;
		this.rank = 0;
		this.size = 1;
	}

	public int getSize() {
		return size;
	}
	
}

class DisjointSet {
	
	Node[] nodes;
	
	int forestSize;
	
	
	public DisjointSet(int size) {
		nodes = new Node[size];
		forestSize = size;
	}

	public void makeSet(int k) {
		nodes[k] = new Node(k);
	}
	
	public Node findSet(int k) {
		return findSet(nodes[k]);
	}
	
	Node findSet(Node x) {
		if(x != x.p)
			x.p = findSet(x.p);
		return x.p;
	}
	
	/**
	 * 连接两个树
	 * @param x 代表
	 * @param y 代表
	 */
	public void link(Node x, Node y) {
		if(x.rank > y.rank) {
			y.p = x;
			x.size += y.size;
		}
		else {
			x.p = y;
			y.size += x.size;
		}
		if(x.rank == y.rank)
			y.rank++;
		forestSize--;
	}
	
	public void union(int x, int y) {
		link(findSet(x), findSet(y));
	}
	
	public int getForestSize() {
		return forestSize;
	}

	public Iterator<Node> representatives() {
		return new RepresentativeIterator();
	}
	
	class RepresentativeIterator implements Iterator<Node> {
		
		int v = 0;
		
		Node next = null;

		public RepresentativeIterator() {
			for(v = 0; v < nodes.length; v++) {
				if(nodes[v] != null && nodes[v].p == nodes[v]) {
					next = nodes[v];
					break;
				}
			}
		}

		@Override
		public boolean hasNext() {
			
			return next != null;
		}

		@Override
		public Node next() {
			Node n = next;
			next = null;
			for(v++; v < nodes.length; v++) {
				if(nodes[v] != null && nodes[v].p == nodes[v]) {
					next = nodes[v];
					break;
				}
			}
			return n;
		}
		
	}
}

public class JourneyToTheMoon {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int p = sc.nextInt();
		
		DisjointSet djs = new DisjointSet(n);
		
		for(int v = 0; v < n; v++) {
			djs.makeSet(v);
		}
		
		for(int i = 0; i < p; i++) {
			Node u = djs.findSet(sc.nextInt());
			Node v = djs.findSet(sc.nextInt());
			if(u != v) {
				djs.link(u, v);
			}
		}
		
		sc.close();
		
		ArrayList<Integer> a = new ArrayList<Integer>();
		long zero = 0;
		for(Iterator<Node> it = djs.representatives(); it.hasNext(); ) {
			int s = it.next().getSize();
			if(s > 1) {
				a.add(s);
			}else {
				zero++;
			}
		}
		
		long s = 0;
		for(int i = 0; i < a.size() - 1; i++) {
			for(int j = i + 1; j < a.size(); j++) {
				s += a.get(i) * a.get(j);
			}
		}
		
		for(int i = 0; i < a.size(); i++) {
			s += a.get(i) * zero;
		}
		
		s += (zero * (zero - 1)) >> 1;
		
		System.out.println(s);
	}
}
