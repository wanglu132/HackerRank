package combination;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * generate combinations of a array.
 * return indexes of the array.
 */
public class CombinationIterator implements Iterator<int[]> {

    private final int n, r;

    private int[] a;

    private int t = 1;


    private CombinationIterator(int n, int r) {
        this.n = n;
        this.r = r;
    }

    @Override
    public boolean hasNext() {

        if(a == null) {
            a = new int[r];
            for (int i = 0; i < r; i++) {
                a[i] = i;
            }
            return true;
        }

        for (; t <= r; t++) {
            while (a[r - t] < n - t){
                if(t == 1) {
                    a[r - t]++;
                }else{
                    for (int i = 0, init = a[r - t]; i < t; i++) {
                        a[r - t + i] = init + i + 1;
                    }
                    t = 1;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public int[] next() {
        return Arrays.copyOf(a, a.length);
    }

    public static Iterator<int[]> createIterator(final int n, final int r) {
        return new CombinationIterator(n, r);
    }

    public static Stream<int[]> ofStream(final int n, final int r) {
        Iterable<int[]> iterable = () -> new CombinationIterator(n, r);
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    public static void main(String[] args) {

        CombinationIterator.ofStream(5,3).limit(5).forEach((c) -> {
            for(int e : c) {
                System.out.print(e + " ");
            }
            System.out.println();
        } );
    }

}
