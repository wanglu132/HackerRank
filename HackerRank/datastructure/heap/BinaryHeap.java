package heap;

import java.util.Arrays;

/**
 * Binary Heap (CLRS)
 */
public class BinaryHeap {
	
	int[] array;
	
	/**
	 * 数据长度(堆排序使用)
	 */
	int length;
	
	/**
	 * 堆数据长度
	 */
	int heapSize;
	
	boolean maxHeap = true;
	
	private static final int DEFAULT_INITIAL_CAPACITY = 11;
	
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
	
//	Comparator<Integer> comparator;
	
	public BinaryHeap() {
		this(true);
	}
	
	public BinaryHeap(boolean maxHeap) {
		this.array = new int[DEFAULT_INITIAL_CAPACITY];
		this.length = 0;
		this.heapSize = 0;
		this.maxHeap = maxHeap;
	}
	
	public BinaryHeap(int[] a) {
		this(a, 0, a.length);
	}
	
	public BinaryHeap(int[] a, int begin, int to) {
		this.array = Arrays.copyOfRange(a, begin, to);
		this.length = a.length;
		this.heapSize = a.length;
		
		build_heap();
	}
	
	public boolean isMaxHeap() {
		return maxHeap;
	}

	public void setMaxHeap(boolean maxHeap) {
		this.maxHeap = maxHeap;
	}

	public int[] getArray() {
		return array;
	}
	
	public int getLength() {
		return length;
	}

	public int getHeap_size() {
		return heapSize;
	}
	
	

	/**
	 * i索引的父索引
	 */
	static final int parent(int i) {
		return i >>> 1;
	}
	
	/**
	 * i索引的左子索引
	 */
	static final int left(int i) {
		return i << 1;
	}
	
	/**
	 * i索引的右子索引
	 */
	static final int right(int i) {
		return (i << 1) + 1;
	}
	
	static final void swap(int[] a, int x, int y) {
		int tmp = a[y];
		a[y] = a[x];
		a[x] = tmp;
	}

	/**
	 * i索引处的值改变后，调用此函数以维持堆性质(heapify)
	 * 
	 * @param i 元素值改变的数组索引(从1开始)
	 */
	void siftDown(int i) {
		int l = left(i);
		int r = right(i);
		int m = l;
		if(l > heapSize)
			return;
		else if(l < heapSize && (maxHeap ? array[l - 1] < array[r - 1] : array[l - 1] > array[r - 1])) {
			m = r;
		}
		if(maxHeap ? array[i - 1] < array[m - 1] : array[i - 1] > array[m - 1]) {
			swap(array, i - 1, m - 1);
			siftDown(m);
		}
	}
	
	/**
	 * 从最后一个内部节点开始，由底向上的构建堆
	 */
	void build_heap() {
		for(int i = length >>> 1; i > 0; i--){
			siftDown(i);
		}
	}
	
	void siftUp(int i) {
		if(i == 1)
			return;
		int p = parent(i);
		if(maxHeap ? array[i - 1] > array[p - 1] : array[i - 1] < array[p - 1]) {
			swap(array, i - 1, p - 1);
			siftUp(p);
		}
	}
	
	/**
	 * Queue
	 */
	public void offer(int k) {
		
		if (heapSize >= array.length)
            grow(heapSize + 1);
		
		length++;
		heapSize++;
		array[heapSize - 1] = k;
		
		siftUp(heapSize);
	}
	
	/**
	 * Queue
	 */
	public int poll() {
		if(heapSize == 0)
			return -1;
		int root = array[0];
		array[0] = array[length - 1];
		length--;
		heapSize--;
		siftDown(1);
		return root;
	}
	
	public int peek() {
		if(heapSize == 0)
			return -1;
		return array[0];
	}
	
	public void increaseKey(int i, int k) {
		if(k < array[i - 1])
			throw new IllegalArgumentException("k must >= a[i]");
		array[i - 1] = k;
		siftDown(i);
	}
	
	/**
	 * 删除索引i处的元素(i从1开始)
	 */
	public void delete(int i) {
		array[i - 1] = array[heapSize - 1];
		heapSize--;
		length--;
		siftDown(i);
	}
	
	/**
	 * 移除找到的第一个key(数组由后往前查找)
	 * O(n)
	 */
	public boolean remove(int k) {
		boolean ret = false;
		for(int i = heapSize; i > 0; i--) {
			if(k == array[i - 1]){
				array[i - 1] = array[heapSize - 1];
				heapSize--;
				length--;
				siftDown(i);
				ret = true;
				break;
			}
		}
		return ret;
	}
	
	void grow(int minCapacity) {
		int oldCapacity = array.length;
		// Double size if small; else grow by 50%
		int newCapacity = oldCapacity + ((oldCapacity < 64) ?
		                                 (oldCapacity + 2) :
		                                 (oldCapacity >> 1));
		// overflow-conscious code
		if (newCapacity - MAX_ARRAY_SIZE > 0)
		    newCapacity = hugeCapacity(minCapacity);
		array = Arrays.copyOf(array, newCapacity);
	}
	
	private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
            Integer.MAX_VALUE :
            MAX_ARRAY_SIZE;
    }
	
	/**
	 * 堆排序
	 */
	public void sort() {
		while(heapSize > 1) {
			swap(array, 0, --heapSize);
			siftDown(1);
		}
	}
	
	public static void main(String[] args) {
		int[] a = new int[]{4, 1, 3, 2, 16, 9, 10, 14, 8 ,7};
		
		BinaryHeap heap = new BinaryHeap(a);
		
//		Heap heap = new Heap();
//		for(int i = 0; i < a.length; i++) {
//			heap.offer(a[i]);
//		}
//		
		int[] array = heap.getArray();
		for(int i = 0; i < heap.getHeap_size(); i++) {
			System.out.printf("%d ", array[i]);
		}
		
//		int root = 0;
//		while((root = heap.poll()) != -1) {
//			System.out.printf("%d ", root);
//		}
		
//		heap.sort();
//		int[] array = heap.getArray();
//		for(int i = 0; i < heap.getLength(); i++) {
//			System.out.printf("%d ", array[i]);
//		}
		
		
	}
	
}
