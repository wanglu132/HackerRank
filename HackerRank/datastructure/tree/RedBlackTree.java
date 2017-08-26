package tree;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 红黑树（CLRS）
 */
public class RedBlackTree {
	
	public static class Node {
		
		protected int key;
		
		protected boolean color = RED;
		
		protected Node p = NIL, left = NIL, right = NIL;
		
		private Node(boolean color) {
			this.color = color;
		}
		
		protected Node(int key) {
			this.key = key;
		}
		
		@Override
		public String toString() {
			return String.format("[key=%d\tcolor=%s]", this.key, this.color ? "BLACK" : "RED");
		}
	}
	
	protected static final boolean BLACK = true, RED = false;
	
	protected static final Node NIL = new Node(BLACK);
	
	protected Node ROOT = NIL;
	
	/**
	 * 6个引用需改变
	 * (与rightRotate有3个引用相同，3个相反)
	 */
	void leftRotate(Node x) {
		Node y = x.right;
		x.right = y.left;
		if(y.left != NIL) {
			y.left.p = x;
		}
		y.p = x.p;
		if(x.p == NIL) {
			ROOT = y;
		}else if(x.p.left == x) {
			x.p.left = y;
		}else {
			x.p.right = y;
		}
		y.left = x;
		x.p = y;
		
		this.afterLeftRotate(x);
	}
	
	protected void afterLeftRotate(Node x) {}
	
	/**
	 * 6个引用需改变
	 * (与leftRotate有3个引用相同，3个相反)
	 */
	void rightRotate(Node x) {
		Node y = x.left;
		x.left = y.right;
		if(y.right != NIL) {
			y.right.p = x;
		}
		y.p = x.p;
		if(x.p == NIL) {
			ROOT = y;
		}else if(x.p.left == x) {
			x.p.left = y;
		}else {
			x.p.right = y;
		}
		y.right = x;
		x.p = y;
		
		this.afterRightRotate(x);
	}
	
	protected void afterRightRotate(Node x) {}
	
	protected Node insert(Node z){
		Node p = NIL;
		for(Node e = ROOT; e != NIL; ) {
			p = e;
			if(z.key <= e.key) 
				e = e.left;
			else
				e = e.right;
		}
		z.p = p;
		if(p == NIL) 
			ROOT = z;
		else if(z.key <= p.key)
			p.left = z;
		else
			p.right = z;
		z.left = z.right = NIL;
		z.color = RED;
		
		afterInsert(z);
		
		insertFixup(z);
		
		return z;
	}
	
	public Node insert(int key){
		return this.insert(new Node(key));
	}
	
	void insertFixup(Node z) {
		//z的父为RED
		while(z.p.color == RED) {
			//z的父为左子
			if(z.p == z.p.p.left) {
				//y设为z的伯父
				Node y = z.p.p.right;
				//CASE 1: z的伯父为RED
				if(y.color == RED) {
					//z的父设为BLACK
					z.p.color = BLACK;
					//z的伯父设为BLACK
					y.color = BLACK;
					//z的爷爷设为RED
					z.p.p.color = RED;
					/* z设为z的爷爷 */
					z = z.p.p;
				//CASE 2: z的伯父为BLACK && z为右子
				}else if(z == z.p.right) {
					/* z设为z的父 */
					z = z.p;
					//左旋(z的父)
					leftRotate(z);
				//CASE 3: z的伯父为BLACK && z为左子
				}else {
					//z的父设为BLACK
					z.p.color = BLACK;
					//z的爷爷设为RED
					z.p.p.color = RED;
					//右旋(z的爷爷)
					rightRotate(z.p.p);
				}
			//z的父为右子
			}else {
				Node y = z.p.p.left;
				if(y.color == RED) {
					z.p.color = BLACK;
					y.color = BLACK;
					z.p.p.color = RED;
					z = z.p.p;
				}else if(z == z.p.left) {
					z = z.p;
					rightRotate(z);
				}else {
					z.p.color = BLACK;
					z.p.p.color = RED;
					leftRotate(z.p.p);
				}
			}
		}
		ROOT.color = BLACK;
	}
	
	protected void afterInsert(Node z) {}
	
	/**
	 * @return 被删除的Node
	 */
	protected Node delete(Node z) {
		
		if(z == NIL) return null;
		
		Node x = NIL, y = NIL;
		if(z.left == NIL || z.right == NIL)
			y = z;
		else
			y = successor(z);
		if(y.left != NIL)
			x = y.left;
		else
			x = y.right;
		x.p = y.p;
		if(y.p == NIL)
			ROOT = x;
		else if(y == y.p.left)
			y.p.left = x;
		else
			y.p.right = x;
		if(y != z)
			//copy y's satellite data into z
			copySatelliteData(y, z);
		if(y.color == BLACK)
			deleteFixup(x);
		
		this.afterDelete(z);
		return y;
    }
	
	/**
	 * y的卫星数据复制到z中
	 */
	protected void copySatelliteData(Node y, Node z) {
		z.key = y.key;
	}
	
	protected void afterDelete(Node z) {}
	
	public Node delete(int k) {
		return this.delete(search(ROOT, k));
    }
	
	void deleteFixup(Node x) {
		//x不为根 && x为BLACK
		while(x != ROOT && x.color == BLACK) {
			//x为左子
			if(x == x.p.left) {
				//w设为x的兄
				Node w = x.p.right;
				//CASE 1: w为RED
				if(w.color == RED){
					//w设为BLACK
					w.color = BLACK;
					//x的父设为RED
					x.p.color = RED;
					//左旋(x的父)
					leftRotate(x.p);
					//w设为x的兄
					w = x.p.right;
				}
				//CASE 2: w为BLACK && w的左子为BLACK && w的右子为BLACK 
				if(w.left.color == BLACK && w.right.color == BLACK) {
					//w设为RED
					w.color = RED;
					/* x设为x的父 */
					x = x.p;
				//CASE 3: w为BLACK && w的左子为RED && w的右子为BLACK
				}else if(w.right.color == BLACK) {
					//w的左子设为BLACK
					w.left.color = BLACK;
					//w设为RED
					w.color = RED;
					//右旋(w)
					rightRotate(w);
					//w设为x的兄
					w = x.p.right;
				//CASE 4: w为BLACK && w的左子为RED && w的右子为RED
				}else {
					//w的颜色设为x的父的颜色
					w.color = x.p.color;
					//x的父设为BLACK
					x.p.color = BLACK;
					//w的右子设为BLACK
					w.right.color = BLACK;
					//左旋(x的父)
					leftRotate(x.p);
					//x设为根
					x = ROOT;
				}
			//x为右子
			}else {
				Node w = x.p.left;
				if(w.color == RED){
					w.color = BLACK;
					x.p.color = RED;
					rightRotate(x.p);
					w = x.p.left;
				}
				if(w.right.color == BLACK && w.left.color == BLACK) {
					w.color = RED;
					x = x.p;
				}else if(w.left.color == BLACK) {
					w.right.color = BLACK;
					w.color = RED;
					leftRotate(w);
					w = x.p.left;
				}else {
					w.color = x.p.color;
					x.p.color = BLACK;
					w.left.color = BLACK;
					rightRotate(x.p);
					x = ROOT;
				}
			}
		}
		x.color = BLACK;
	}
	
	/**
	 * 在子树里查找key
	 */
	public Node search(Node t, int key) {
		
		while(t != NIL) {
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
	public Node min(Node t) {
		if(t == null) t = ROOT;
		for(; t.left != NIL; t = t.left);
		return t;
	}
	
	/**
	 * 子树最大值
	 */
	public Node max(Node t) {
		if(t == null) t = ROOT;
		for(; t.right != NIL; t = t.right);
		return t;
	}
	
	/**
	 * 后一个数
	 */
	public Node successor(Node e) {
		if(e.right != NIL) 
			return min(e.right);
		for(; e.p != NIL && e.p.right == e; e = e.p);
		return e.p;
	}
	
	/**
	 * 前一个数
	 */
	public Node predecessor(Node e) {
		if(e.left != NIL) 
			return max(e.left);
		for(; e.p != NIL && e.p.left == e; e = e.p);
		return e.p;
	}
	
	/**
	 * 中序
	 */
	public void inOrder(Node t) {
		if(t == null) t = ROOT;
		if(t == NIL) {
			return;
		}
		inOrder(t.left);
		inOrderHandler(t);
		inOrder(t.right);
	}
	
	/**
	 * 子树的高度
	 */
	public int height(Node t) {
		if(t == null) t = ROOT;
		if(t == NIL) return 0;
		return Math.max(height(t.left), height(t.right)) + 1;
	}
	
	/**
	 * 子树的黑高度
	 */
	public int blackHeight(Node t) {
		if(t == null) t = ROOT;
		int bh = 0;
		for(; t != NIL; t = t.left) {
			if(t.color == BLACK) bh++;
		};
		return bh;
	}
	
	void inOrderHandler(Node x) {
		System.out.printf("%d ", x.key);
	}
	
	Nodes nodes = null;
	
	public Iterable<Node> nodes() {
		Iterable<Node> vs = nodes;
        return (vs != null) ? vs : (nodes = new Nodes());
    }
	
	class Nodes implements Iterable<Node> {

		@Override
		public Iterator<Node> iterator() {
			return new NodeIterator(min(null));
		}
    }
	
	class NodeIterator implements Iterator<Node> {
		
        Node next;

        NodeIterator(Node first) {
            next = first;
        }

        public final boolean hasNext() {
            return next != NIL;
        }
        
        public Node next() {
        	Node e = next;
            if (e == null)
                throw new NoSuchElementException();
            next = successor(e);
            return e;
        }
    }
	
	/**
	 * 验证二叉树、红黑树性质
	 */
	public int verify(Node t) {
		if(t == null) t = ROOT;
		if(t == NIL) {
			return 0;
		}
		int lbh = verify(t.left);
		int rbh = verify(t.right);
		if(t.left != NIL && t.left.key > t.key) {
			throw new RuntimeException(String.format("Not a binary search tree. Left child: %s. Parent: %s", t.left.toString(), t.toString()));
		}else if(t.right != NIL && t.key > t.right.key) {
			throw new RuntimeException(String.format("Not a binary search tree. Parent: %s. Right child: %s", t.toString(), t.right.toString()));
		}
		if(lbh != rbh) 
			throw new RuntimeException(String.format("BLACK height not equal. NODE: %s", t.toString()));
		if(t.color == BLACK) 
			lbh++;
		else if(t.left.color == RED || t.right.color == RED){
			throw new RuntimeException(String.format("RED's childs have RED color. NODE: %s", t.toString()));
		}
		if(t.p == NIL && t.color == RED) {
			throw new RuntimeException(String.format("ROOT's color is RED. NODE: %s", t.toString()));
		}
		return lbh;
	}
	
	public static void main(String[] args) {
		
		RedBlackTree rbt = new RedBlackTree();
		
		rbt.insert(16);
		rbt.insert(8);
		rbt.insert(25);
		rbt.insert(5);
		rbt.insert(15);
		rbt.insert(17);
		rbt.insert(26);
		rbt.insert(0);
		rbt.insert(6);
		rbt.insert(19);
		
		rbt.verify(null);
//		
		rbt.inOrder(null);
//		
		rbt.delete(16);
//		rbt.delete(17);
//		rbt.delete(8);
//		rbt.delete(9);
//		rbt.delete(1);
//		rbt.delete(3);
//		rbt.delete(2);
		
		System.out.println();
		
		rbt.verify(null);
//		
		rbt.inOrder(null);
		
//		System.out.println(rbt.blackHeight(null));
		
		
	}
}
