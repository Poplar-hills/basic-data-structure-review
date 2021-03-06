package HashTable;

public class L387_First_Unique_Character_in_a_String {
    public int firstUniqChar(String s) {
        int[] freq = new int[26];

        for (int i = 0; i < s.length(); i++)
            freq[s.charAt(i) - 'a']++;  // freq 本质上是一个哈希表，其哈希函数是从字母到索引的映射（f(ch) = ch - 'a'）

        for (int i = 0; i < s.length(); i++)
            if (freq[s.charAt(i) - 'a'] == 1)
                return i;

        return -1;
    }
}
