package disjointset;

import java.util.Iterator;

/**
 * CLRS
 */
public class DisjointSet {

	public static class Node {
		
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
