package stack;

import java.util.Arrays;

public class Stack {

	private int[] element;
	
	private int top;
	
	public Stack() {
		element = new int[10];
		top = 0;
	}

	public void push(int e) {
		ensureCapacity(top + 1);
		element[top++] = e;
	}
	
	public int pop() {
		return element[--top];
	}
	
	private void ensureCapacity(int needCapacity) {
		int newCapacity = needCapacity;
		if(needCapacity > element.length) {
			newCapacity = element.length << 1;
		}
		element = Arrays.copyOf(element, newCapacity);
	}
	
	public static void main(String[] args) {
		Stack s = new Stack();
		s.push(1);
		s.push(2);
		s.push(1);
		s.push(3);
		
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
	}
}
