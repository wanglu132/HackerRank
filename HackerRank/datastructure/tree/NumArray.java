package tree;

public class NumArray {

    private final Node root;

    private static class Node {
        private final int begin;
        private final int end;
        private final Node left;
        private final Node right;
        private int sum;

        public Node(int begin, int end, int sum, Node left, Node right) {
            this.begin = begin;
            this.end = end;
            this.sum = sum;
            this.left = left;
            this.right = right;
        }

        public boolean isSingle() {
            return begin == end;
        }

        public boolean contains(int i) {
            return i >= begin && i <= end;
        }

        public boolean inside(int i, int j) {
            return i <= begin && j >= end;
        }

        public boolean outside(int i, int j) {
            return i > end || j < begin;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }
    }

    public NumArray(int[] nums) {
        if (nums.length == 0) {
            root = null;
        } else {
            root = buildNode(nums, 0, nums.length - 1);
        }
    }

    private Node buildNode(int[] nums, int begin, int end) {
        if (begin == end) {
            return new Node(begin, end, nums[begin], null, null);
        } else {
            int mid = (begin + end) / 2 + 1;
            Node left = buildNode(nums, begin, mid - 1);
            Node right = buildNode(nums, mid, end);
            return new Node(begin, end, left.sum + right.sum, left, right);
        }
    }

    public void update(int i, int val) {
        if (root == null) {
            return;
        }
        if (!root.contains(i)) {
            throw new IllegalArgumentException("i not in range");
        }
        update(root, i, val);
    }

    private int update(Node node, int i, int val) {
        if (node.isSingle()) {
            node.setSum(val);
        } else {
            Node nodeToUpdate = node.left.contains(i) ? node.left : node.right;
            int withoutNode = node.sum - nodeToUpdate.sum;
            node.setSum(withoutNode + update(nodeToUpdate, i, val));
        }
        return node.sum;
    }

    public int sumRange(int i, int j) {
        if (root == null) {
            return 0;
        }
        return sumRange(root, i, j);
    }

    private int sumRange(Node node, int i, int j) {
        if (node.outside(i, j)) {
            return 0;
        } else if (node.inside(i, j)) {
            return node.sum;
        } else {
            return sumRange(node.left, i, j) + sumRange(node.right, i, j);
        }
    }

}