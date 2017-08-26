package sort;

import java.util.Scanner;

public class IntrotoTutorialChallenges {

	public static void main(String[] args) {
		
		try(Scanner sc = new Scanner(System.in)) {
			int v = sc.nextInt();
			int n = sc.nextInt();
			for(int i = 0; i < n; i++) {
				if(v == sc.nextInt()) {
					System.out.println(i);
					return;
				}
			}
		}
    }
}
