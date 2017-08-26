package tree;

/**
 * 区间树（CLRS）
 */
public class IntervalTree extends RedBlackTree {

	public static class INode extends Node {
		
		Interval i;
		
		int max;
		
		INode(int low, int high) {
			super(low);
			this.i = new Interval(low, high);
			this.max = high;
		}
		
		boolean resetMax() {
			if(this.max > this.i.high) {
				this.max = this.i.high;
				return true;
			}
			return false;
		}
		
		public Interval getI() {
			return i;
		}

		public int getMax() {
			return max;
		}

		@Override
		public String toString() {
			return String.format("[key=%d\tmax=%d\tlow=%d\thigh=%d]", this.key, this.max, this.i.low, this.i.high);
		}
	}
	
	public static class Interval {
		int low, high;

		public Interval(int low, int high) {
			this.low = low;
			this.high = high;
		}

		public int getLow() {
			return low;
		}

		public int getHigh() {
			return high;
		}
	}
	
	public Node insert(int low, int high) {
		return super.insert(new INode(low, high));
	}
	
	/**
	 * @return 被删除的重叠的区间. 没有找到重叠区间时返回null
	 */
	public Interval delete(int low, int high) {
		Node z = search(ROOT, low, high);
		return (z = super.delete(z)) == null ? null : ((INode)z).i;
	}
	
	/**
	 * 查找重叠的区间
	 */
	public Node search(Node t, int low, int high) {
		if(t == null) t = ROOT;
		while(t != NIL && (high < ((INode)t).i.low || ((INode)t).i.high < low)) {
			if(t.left != NIL && ((INode)t.left).max >= low)
				t = t.left;
			else
				t = t.right;
		}
		return t;
	}
	
	private static int maxChild(INode z) {
		int max = Integer.MIN_VALUE;
		if(z.left != NIL) {
			max = ((INode)z.left).max;
		}else if(z.right != NIL) {
			max = Math.max(((INode)z.right).max, max);
		}
		return max;
	}
	
	@Override
	protected void copySatelliteData(Node y, Node z) {
		super.copySatelliteData(y, z);
		
		INode yi = (INode)y, zi = (INode)z;
		zi.i.low = yi.i.low;
		zi.i.high = yi.i.high;
	}
	
	@Override
	protected void afterInsert(Node z) {
		if(z.p == NIL) return;
		
		INode p = (INode)z.p;
		int maxChild = maxChild(p);
		if(maxChild > p.max) {
			p.max = maxChild;
			afterInsert(p);
		}
	}
	
	@Override
	protected void afterDelete(Node z) {
		if(z.p == NIL) return;
		
		INode p = (INode)z.p;
		boolean changed = p.resetMax();
		int maxChild = maxChild(p);
		if(maxChild > p.max) {
			p.max = maxChild;
			changed = true;
		}
		if(changed)
			afterDelete(p);
	}
	
	private static void afterRotate(Node z) {
		INode x = (INode)z;
		INode y = (INode)z.p;
		
		y.max = x.max;
		
		x.resetMax();
		int xMax = maxChild(x);
		if(x.max < xMax) x.max = xMax;
	}
	
	@Override
	protected void afterLeftRotate(Node z) {
		afterRotate(z);
	}
	
	@Override
	protected void afterRightRotate(Node z) {
		afterRotate(z);
	}
	
//	@Override
//	void inOrderHandler(Node x) {
//		INode z = (INode)x;
//		System.out.printf("[key=%d\tmax=%d\tlow=%d\thigh=%d]%n", z.key, z.max, z.i.low, z.i.high);
//	}
	
	@Override
	void inOrderHandler(Node x) {
		INode z = (INode)x;
		System.out.printf("[key=%d\tmax=%d\tlow=%d\thigh=%d]%n", z.key, z.max, z.i.low, z.i.high);
	}
	
	/**
	 * 验证二叉树、红黑树、区间树性质
	 */
	public int verify(Node t) {return 0; }
	
	public static void main(String[] args) {
		IntervalTree it = new IntervalTree();
		it.insert(16, 21);
		it.insert(8, 9);
		it.insert(25, 30);
		it.insert(5, 8);
		it.insert(15, 23);
		it.insert(17, 19);
		it.insert(26, 26);
		it.insert(0, 3);
		it.insert(6, 10);
		it.insert(19, 20);
		
		it.inOrder(null);
		
		Node x = it.search(null, 22, 24);
		
		System.out.println();
		System.out.println(x.toString());
	}
}

