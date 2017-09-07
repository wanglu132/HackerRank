package tree;

public class BinaryIndexedTree {

    private int[] tree; //Binary Indexed Tree
    private int[] nums; //原始数组

    public BinaryIndexedTree(int[] nums) {
        this.nums = nums;
        int sum = 0;
        int lowbit;
        tree = new int[nums.length + 1];
        for (int i = 1; i < tree.length; i++) {
            sum = 0;
            lowbit = i & ((i - 1) ^ i);
            for (int j = i; j > i - lowbit; j--) {
                sum = sum + nums[j - 1];
            }
            tree[i] = sum;
        }
    }

    //更新
    void update(int i, int val) {
        int tem = val - nums[i];
        nums[i] = val;
        i++;
        for (; i < tree.length; i = i + (i & ((i - 1) ^ i))) {
            tree[i] += tem;
        }
    }

    public int sumRange(int i, int j) {
        return getSum(j) - getSum(i - 1);
    }

    //求和
    public int getSum(int i) {
        int sum = 0;
        i++;
        while (i > 0) {
            sum = sum + tree[i];
            i = i - (i & ((i - 1) ^ i));
        }
        return sum;
    }
}
