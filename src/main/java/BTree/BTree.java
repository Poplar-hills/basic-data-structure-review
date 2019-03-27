package BTree;

/*
* B-Tree（B 树）
*
* - B-tree 是：
*   1. 一种多路自平衡查找树；
*   2. 根节点至少有2个子节点；
*   3. 一棵 m 阶 B-tree 中每个非叶子节点：
*      - 最多有 m 个子节点（阶数 == 路数 == 一个节点最多的子节点数）。
*      - 节点内最多有 m-1 个键（即元素），最少有 ceil(m/2)-1 个键。
*      - 节点内的键从小到大顺序排列。
*   4. 所有叶子节点都位于同一层（类似完美二叉树）。
*
*   注：
*     - B-tree 的定义可能有多种，不同定义中的一个节点的子节点数和键数范围不同。
*     - 之前学习过的2-3树其实是一种3阶 B-tree
*
*   一棵 B-tree of order 3：             一棵 B-tree of order 5：
*                 9                                7,16
*            /        \                        /     |     \
*         2,6          12                 1,2,5,6   9,12   18,21
*      /   |   \     /    \
*     1   3,5  8   11   13,15
*
* - B-tree 的应用：
*   - B-tree 主要用于文件系统、部分数据库索引的实现（如 MongoDB）（而 MySQL 使用的是 B+ 树）。
*   - Q: 数据库索引为什么要使用树结构存储？
*     A: ∵ 1.树的查询效率高 2.可以保持有序性
*   - Q: 那为什么数据库索引使用 B-tree 而不使用二叉树来实现呢？
*     A: - 在树的查询过程中，有两种操作：
*          1. 访问节点（从父节点到子节点的过程）
*          2. 比较节点（比较子节点中应该访问哪个）
*        - ∵ 数据库索引树上的每一个节点都对应着硬盘上的一个磁盘页 ∴ 一次访问节点就意味着一次磁盘 IO 操作。而比较节点是发生在内存
*          中 ∴ 比较节点的速度远快于访问节点的速度。
*        - ∵ 二叉树是两路查找，而 B-tree 是多路查找 ∴ 对于相同数量的元素，B-tree 的高度 < 二叉树的高度。
*        - ∵ B-tree 的高度较小 ∴ 在查询过程中比较节点的次数多，而访问节点的次数少，即磁盘 IO 的次数少 ∴ 作为数据库索引的查找效
*          率高于二叉树 ∴ 说 B-tree 是为外存而生的。
*
* */

import javafx.util.Pair;

public class BTree<K extends Comparable<K>, V> {
    private int m;  // B-tree 的阶（即一个节点最多有多少个子节点）
    private Node root;
    private int size;

    private class Node {
        private Entry[] entries;
        private Node[] children;

        public Node() {
            entries = (Entry[]) new Object[m - 1];
            children = (Node[]) new Object[m];
        }

        public Pair<Integer, V> search(K key) {
            for (int i = 0; i < entries.length; i++) {
                Entry en = entries[i];
                if (en.key.compareTo(key) == 0)
                    return new Pair<>(i, en.value);
                if (en.key.compareTo(key) > 0)
                    return new Pair<>(i, null);
            }
            return null;
        }

        @Override
        public String toString() {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < entries.length; i++) {
                s.append(entries[i] == null ? "" : entries[i].toString());
                if (i != entries.length - 1)
                    s.append(",");
            }
            return "[" + s.toString() + "]";
        }
    }

    private class Entry {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + ":" + value;
        }
    }

    public BTree(int m) {
        this.m = m;
        root = null;
    }

    /*
    * 增操作
    * */
    public void insert() {

    }

    /*
    * 查操作
    * */
    public V search(K key) {
        if (key == null)
            throw new IllegalArgumentException("search failed.");
        return search(root, key);
    }

    private V search(Node node, K key) {
        if (node == null)
            return null;
        Pair<Integer, V> result = node.search(key);
        if (result.getValue() != null) {
            return result.getValue();
        } else {
            Node childNode = node.children[result.getKey()];
            return search(childNode, key);
        }
    }

    public int getSize() { return size; }

    public boolean isEmpty() { return size == 0; }

    public static void main(String[] args) {
        BTree<Integer, Integer> bTree = new BTree<Integer, Integer>(5);
        System.out.println(bTree.root);
    }
}