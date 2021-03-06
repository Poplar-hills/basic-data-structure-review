package MaxHeap;

import MaxHeap.L347_Top_K_Frequent_Elements.Solution1;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Solution1Test {
    @Test
    void should_return_top_k_frequent_elements1() {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        Solution1 r = new Solution1();
        List<Integer> result = r.topKFrequent(nums, k);
        assertEquals(Arrays.asList(2, 1), result);
    }

    @Test
    void should_return_top_k_frequent_elements2() {
        int[] nums = {1};
        int k = 1;
        Solution1 r = new Solution1();
        List<Integer> result = r.topKFrequent(nums, k);
        assertEquals(Arrays.asList(1), result);
    }

    @Test
    void should_return_top_k_frequent_elements3() {
        int[] nums = {4, 1, -1, 2, -1, 2, 3};
        int k = 2;
        Solution1 r = new Solution1();
        List<Integer> result = r.topKFrequent(nums, k);
        assertEquals(Arrays.asList(-1, 2), result);
    }
}