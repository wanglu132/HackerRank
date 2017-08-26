package hash;

import java.util.Iterator;

/**
 * 开放寻址散列表（CLRS）
 * 
 * 不支持删除操作
 * key不可重复，重复则覆盖
 * 
 * @param <T> value
 */
public class HashInt<T> {
	
	static class Node<T> {
		
		int key;
		
		T value;
		
		Node(int key, T value) {
			this.key = key;
			this.value = value;
		}
		
	}
	
	int M = 11;
	
	Node<T>[] table;
	
	int size;
	
	/****** 双重散列 ******/
	
	static int h1(int k, int m) {
		return k % m;
	}
	
	static int h2(int k, int m) {
		return 1 + (k % (m - 1));
	}
	
	static int doubleOpenHash(int h1, int h2, int i, int m) {
		return (h1 + i * h2) % m;
	}
	/****** END ******/
	
	@SuppressWarnings("unchecked")
	public HashInt(int m) {
		M = m;
		table = new Node[M];
	}

	/**
	 * @return 被覆盖的value，如没有覆盖则返回null
	 */
	public T put(int key, T value) {
		int h1 = h1(key, M);
		int h2 = h2(key, M);
		int i = -1, h = -1;
		
		do {
			i++;
			h = doubleOpenHash(h1, h2, i, M);
		}while(i < M && table[h] != null && table[h].key != key);
		
		if(i >= M)
			throw new RuntimeException(String.format("key: %d. can't find hash index", key));
		
		T v = null;
		//新的key
		if(table[h] == null)
			size++;
		//key值已存在
		else if(table[h].key == key)
			v = table[h].value;
		
		table[h] = new Node<T>(key, value);
		
		return v;
	}
	
	public T get(int key) {
		
		int h1 = h1(key, M);
		int h2 = h2(key, M);
		int i = -1, h = -1;
		
		T value = null;
		do {
			i++;
			h = doubleOpenHash(h1, h2, i, M);
		}while(i < M && table[h] != null && table[h].key != key);
		
		if(i < M && table[h] != null)
			value = table[h].value;
		
		return value;
	}
	
	public int getSize() {
		return size;
	}

	Values values = null;
	
	public Iterable<T> values() {
		Iterable<T> vs = values;
        return (vs != null) ? vs : (vs = new Values());
    }
	
	class Values implements Iterable<T> {

		@Override
		public Iterator<T> iterator() {
			return new ValueIterator();
		}
    }
	
	class ValueIterator implements Iterator<T> {
		
        T next;
        int i;

        //找到第一个元素
        ValueIterator() {
        	for(i = 0; i < table.length && table[i] == null; i++);
        	if(i < table.length)
        		next = table[i].value;
        }

        public final boolean hasNext() {
            return next != null;
        }
        
        public T next() {
        	T e = next;
        	next = null;
        	for(++i; i < table.length && table[i] == null; i++);
        	if(i < table.length)
        		next = table[i].value;
            return e;
        }
    }
	
	public static void main(String[] args) {
		
		long before = System.currentTimeMillis();
		
		HashInt<String> hash = new HashInt<String>(11);
		hash.put(5, "a");
		hash.put(7, "b");
		hash.put(3, "c");
		hash.put(9, "d");
		hash.put(4, "e");
		hash.put(6, "f");
		hash.put(8, "g");
		
		for(Iterator<String> it = hash.values().iterator(); it.hasNext(); ) {
			System.out.println(it.next());
		}
		
//		Scanner s = new Scanner(new BufferedInputStream(System.in));
//		int n = s.nextInt();
//		int m = s.nextInt();
//		int k = s.nextInt();
//		
//		HashInt<Object> hash = new HashInt<Object>(1399);
//		
//		for(int i = 0; i < k; i++) {
//			int r = s.nextInt();
//			int c1 = s.nextInt();
//			int c2 = s.nextInt();
//			
//			Object o = hash.get(r);
//			if(o == null) {
//				o = new Object();
//				hash.put(r, o);
//			}; 
//		}
//		
//		s.close();
		

		long after = System.currentTimeMillis();
		
		System.out.printf("%nused: %sms", after - before);
		
	}
}
