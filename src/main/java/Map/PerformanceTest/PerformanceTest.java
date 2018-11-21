package Map.PerformanceTest;

import Map.LinkedListMap;
import Map.BSTMap;
import Map.AVLTreeMap;
import Map.Map;
import Set.PerformanceTest.FileOperation;

import java.util.ArrayList;

/*
 * - 经过和 Set 中一样的词频统计测试，BSTMap 的效率是 LinkedListMap 的50倍左右。
 * - 时间复杂度分析：
 *             LinkedListMap     BSTMap   BSTMap(平均)   BSTMap(最差)   AVLTreeMap
 *   add           O(n)           O(h)      O(logn)         O(n)         O(logn)
 *   remove        O(n)           O(h)      O(logn)         O(n)         O(logn)
 *   set           O(n)           O(h)      O(logn)         O(n)         O(logn)
 *   get           O(n)           O(h)      O(logn)         O(n)         O(logn)
 *   contains      O(n)           O(h)      O(logn)         O(n)         O(logn)
 *  (为什么 BST 的复杂度是 O(h)? h 与 n 的关系是什么? 这两个问题可以自己手动推导一遍)
 *
 * - 根据 Map 的底层实现分类，Map 也可以分为"有序映射"和"无序映射"（即映射中的 key 是否具有顺序性，是否可以方便地从小到大遍历）。
 *   - 有序映射可通过 BST 实现（我们实现的 BSTMap 就是有序映射）
 *   - 无序映射可通过哈希表实现（联想 JS 中的普通对象和 Map 的区别）
 *
 * - AVLTreeMap 比 BSTMap 大约快三分之一，因为 AVLTreeMap 通过保持平衡解决了 BSTMap 的可能退化成链表形态的问题，
 *   因此效率更高。具体 SEE: AVLTree.java
 * */

public class PerformanceTest {
    private static double testMap(Map<String, Integer> map) {
        String pathname = "/Users/myjiang/Library/Mobile Documents/com~apple~CloudDocs/Poplar_hills/Dev/java/DataStructure/src/main/java/Set/PerformanceTest/pride-and-prejudice.txt";
        long startTime = System.nanoTime();

        ArrayList<String> words = new ArrayList<String>();
        if (FileOperation.readFile(pathname, words)) {
            System.out.println("Total words: " + words.size());
            for (String word : words) {
                if (!map.contains(word))
                    map.add(word, 1);  // 只比较 add 操作的效率
                int a = map.get(word) + 1;
                map.set(word, a);
            }
            System.out.println("Total different words: " + map.getSize());
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        LinkedListMap<String, Integer> linkedListMap = new LinkedListMap<String, Integer>();
        double time1 = testMap(linkedListMap);
        System.out.println("LinkedListMap: " + time1 + " s\n");

        BSTMap<String, Integer> bstMap = new BSTMap<String, Integer>();
        double time2 = testMap(bstMap);
        System.out.println("BSTMap: " + time2 + " s\n");

        AVLTreeMap<String, Integer> avlTreeMap = new AVLTreeMap<String, Integer>();
        double time3 = testMap(avlTreeMap);
        System.out.println("AVLTreeMap: " + time3 + " s\n");
    }
}