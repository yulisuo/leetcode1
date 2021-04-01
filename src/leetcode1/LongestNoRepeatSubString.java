package leetcode1;

import util.Utils;

import java.util.HashSet;
import java.util.Set;

public class LongestNoRepeatSubString {

    public static void main(String[] args) {

        LongestNoRepeatSubString t = new LongestNoRepeatSubString();
        // abcabcbb 3
        // bbbbb 1
        // pwwkew 3
        // "" 0
        // a 1
        // " " 1
        // "aab" 2
        int n = t.lengthOfLongestSubstring("a");
        Utils.print("n = " + n);
    }

    // 官方的滑动窗口的解法
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int r = 0;
        int n = 0, m = -1;
        while (n < s.length()) {
            if (n != 0) {
                // 左指针向右移动一位的时候，set中移除（上一轮）左指针对应的字符
                set.remove(s.charAt(n - 1));
            }
            while (m + 1 < s.length() && !set.contains(s.charAt(m + 1))) {
                set.add(s.charAt(m + 1));
                m++;
            }

            r = Math.max(r, m -n + 1);
            n++;
        }

        return r;
    }
}
