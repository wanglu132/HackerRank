package disjointset;

public class DisjointSet {

	static class Node {
		
		int key;
		
		Node p;
		
		int rank, size;

		public Node(int key) {
			this.key = key;
			this.p = this;
			this.rank = 0;
			this.size = 1;
		}
	}
	
	Node[] nodes;
	
	
	public DisjointSet(int size) {
		nodes = new Node[size];
	}

	void makeSet(int k) {
		nodes[k] = new Node(k);
	}
	
	Node findSet(int k) {
		return findSet(nodes[k]);
	}
	
	Node findSet(Node x) {
		if(x != x.p)
			x.p = findSet(x.p);
		return x.p;
	}
	
	void link(Node x, Node y) {
		if(x.rank > y.rank)
			y.p = x;
		else
			x.p = y;
		if(x.rank == y.rank)
			y.rank++;
	}
	
	void union(int x, int y) {
		link(findSet(x), findSet(y));
	}
}
