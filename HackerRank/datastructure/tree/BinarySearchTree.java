package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉查找树（CLRS）
 */
public class BinarySearchTree {
	
	/**
	 * 树节点
	 */
	static class Node {
		
	    int key;
	    
	    Node p = null, left = null, right = null;
	    
		Node(int key) {
			this.key = key;
		}
	}
	
	static Node ROOT = null;
	
	/**
	 * 前序
	 */
	void preOrder(Node t) {
		if(t == null) {
			return;
		}else{
			System.out.printf("%d ", t.key);
		}
		preOrder(t.left);
		preOrder(t.right);
	}
	
	/**
	 * 后序
	 */
	void postOrder(Node t) {
		if(t == null) {
			return;
		}
		postOrder(t.left);
		postOrder(t.right);
		
		System.out.printf("%d ", t.key);
	}
	
	/**
	 * 中序
	 */
	void inOrder(Node t) {
		if(t == null) {
			return;
		}
		inOrder(t.left);
		System.out.printf("%d ", t.key);
		inOrder(t.right);
	}
	
	/**
	 * 子树的高度
	 */
	int height(Node t) {
		if(t == null) {
			return -1;
		}
		return Math.max(height(t.left), height(t.right)) + 1;
	}
	
	/**
	 * 层遍历
	 */
	void levelOrder(Node t) {
	      
		Queue<Node> q = new LinkedList<Node>();
		q.offer(t);
		
		while((t = q.poll()) != null) {
			System.out.printf("%d ", t.key);
			if(t.left != null)
				q.offer(t.left);
			if(t.right != null)
				q.offer(t.right);
		}
	}
	
	void topView(Node t) { }
	
	public Node search_recursion(Node t, int key) {
		if(t == null)
			return null;
		if(key == t.key)
			return t;
		else if(key < t.key)
			return search_recursion(t.left, key);
		else 
			return search_recursion(t.right, key);
	}
	
	/**
	 * 在子树里查找key
	 */
	public Node search(Node t, int key) {
		
		while(t != null) {
			if(key == t.key)
				return t;
			else if(key < t.key)
				t = t.left;
			else 
				t = t.right;
		}
		return t;
	}
	
	/**
	 * 子树最小值
	 */
	Node min(Node t) {
		for(; t.left != null; t = t.left);
		return t;
	}
	
	/**
	 * 子树最大值
	 */
	Node max(Node t) {
		for(; t.right != null; t = t.right);
		return t;
	}
	
	/**
	 * 后一个数
	 */
	Node successor(Node e) {
		if(e.right != null) 
			return min(e.right);
		for(; e.p != null && e.p.right == e; e = e.p);
		return e.p;
	}
	
	/**
	 * 前一个数
	 */
	Node predecessor(Node e) {
		if(e.left != null) 
			return max(e.left);
		for(; e.p != null && e.p.left == e; e = e.p);
		return e.p;
	}
	
	public void insert(Node z){
		Node p = null;
		for(Node e = ROOT; e != null; ) {
			p = e;
			if(z.key <= e.key) 
				e = e.left;
			else
				e = e.right;
		}
		z.p = p;
		if(p == null) 
			ROOT = z;
		else if(z.key <= p.key) 
			p.left = z;
		else
			p.right = z;
	}
	
	/**
	 * 查找第i小关键字
	 * (顺序统计)
	 */
	int osSelect(Node root, int i) {
		return 0;
	}
	
	/**
	 * 查找某一关键字排名
	 * (顺序统计)
	 */
	int osRank(Node k) {
		return 0;
	}
	
	/**
	 * 从根节点开始插入
	 */
	Node insert_recursion(Node root, int key) {
		if(root == null) 
			return new Node(key);
		if(key < root.key) {
			root.left = insert_recursion(root.left, key);
			root.left.p = root;
		}else{
			root.right = insert_recursion(root.right, key);
			root.right.p = root;
		}
		return root;
    }
	
	/**
	 * 从根节点开始插入
	 */
	void insert(int key){
		this.insert(new Node(key));
	}
	
	void delete_raw(Node t, int k) {
		
		if(t == null) 
			t = ROOT;
		
		Node node = search(t, k);
		
		if(node == null)
			return;
		
		if(node.left == null && node.right == null) {
			if(node.p != null) {
				if(node.p.left == node) 
					node.p.left = null;
				else 
					node.p.right = null;
			} else {
				ROOT = null;
			}
			node = null;
			return;
		}
		if(node.left == null && node.right != null) {
			if(node.p != null) {
				if(node.p.left == node) 
					node.p.left = node.right;
				else 
					node.p.right = node.right;
			} else {
				ROOT = node.right;
			}
			node.right.p = node.p;
			node = null;
			return;
		}
		if(node.left != null && node.right == null) {
			if(node.p != null) {
				if(node.p.left == node) 
					node.p.left = node.left;
				else 
					node.p.right = node.left;
			} else {
				ROOT = node.left;
			}
			node.left.p = node.p;
			node = null;
			return;
		}
		if(node.left != null && node.right != null) {
			Node s = successor(node);
			if(s.right != null) {
				if(s.p != node) {
					s.right.p = s.p;
					s.p.left = s.right;
				}
			}else {
				if(s != node.right) {
					s.p.left = null;
				}
			}
			if(node.p != null ) {
				if(node.p.left == node) 
					node.p.left = s;
				else 
					node.p.right = s;
			}else {
				ROOT = s;
			}
			
			if(s.p != node) {
				node.right.p = s;
				s.right = node.right;
			}
			
			s.p = node.p;
			
			node.left.p = s;
			s.left = node.left;
			
			node = null;
			return;
		}
    }
	
	/**
	 * 从子树删除
	 */
	Node delete(Node t, int k) {
		
		if(t == null) t = ROOT;
		
		Node z = search(t, k);
		
		if(z == null) return null;
		
		Node x = null, y = null;
		if(z.left == null || z.right == null)
			y = z;
		else
			y = successor(z);
		if(y.left != null)
			x = y.left;
		else
			x = y.right;
		if(x != null)
			x.p = y.p;
		if(y.p == null)
			ROOT = x;
		else if(y == y.p.left)
			y.p.left = x;
		else
			y.p.right = x;
		if(y != z)
			//copy y's satellite data into z
			z.key = y.key;
		return y;
    }
	
	

	public static void main(String[] args) {
		
		BinarySearchTree bst = new BinarySearchTree();
		
//		try ( Scanner s = new Scanner(System.in); ) {
//			int n = Integer.parseInt(s.nextLine());
//			String line = s.nextLine();
//			
//			String[] as = line.split(" ");
//			for(int i = 0; i < n; i++) {
//				bst.insert(Integer.parseInt(as[i]));
//			}
//		}
		
//		ROOT = new Node(16);
//		Node root = ROOT;
//		
//		root = bst.insert_recursion(root, 8);
//		root = bst.insert_recursion(root, 25);
//		root = bst.insert_recursion(root, 5);
//		root = bst.insert_recursion(root, 15);
//		root = bst.insert_recursion(root, 17);
//		root = bst.insert_recursion(root, 26);
//		root = bst.insert_recursion(root, 0);
//		root = bst.insert_recursion(root, 6);
//		root = bst.insert_recursion(root, 19);
		
		bst.insert(16);
		bst.insert(8);
		bst.insert(25);
		bst.insert(5);
		bst.insert(15);
		bst.insert(17);
		bst.insert(26);
		bst.insert(0);
		bst.insert(6);
		bst.insert(19);
		
		bst.inOrder(ROOT);
		
//		bst.delete(null, 5);
//		bst.delete(null, 6);
//		bst.delete(null, 7);
//		bst.delete(null, 9);
//		bst.delete(null, 1);
//		bst.delete(null, 3);
//		bst.delete(null, 2);
		
//		System.out.println();
//		
//		bst.inOrder(ROOT);
//		System.out.println(bst.height(root));
		
		
	}
}
