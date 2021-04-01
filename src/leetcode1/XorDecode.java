package leetcode1;

import util.Utils;

public class XorDecode {
    //https://leetcode-cn.com/problems/decode-xored-array/
    public static void main(String[] args) {
        XorDecode test = new XorDecode();
        int[] arr = test.decode(new int[]{1, 2, 3}, 1);
        Utils.printIntArray(arr);
    }

    public int[] decode(int[] encoded, int first) {
        // first 是原数组的第一个元素
//        int n0 = first;
        // 原数组第二个元素
//        int n1 = 0;
        // encode[0] == n0 ^ n1
        // n1 = n0 ^ encode[0]
        int[] arr = new int[encoded.length + 1];
        arr[0] = first;
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i - 1] ^ encoded[i - 1];
        }
        return arr;
    }

    private void encodeTest() {
        int[] arr = new int[]{1, 0, 2, 1};
        int[] a2 = new int[arr.length - 1];
        for (int i = 0; i < a2.length; i++) {
            a2[i] = arr[i] ^ arr[i + 1];
        }
        Utils.printIntArray(arr);
        Utils.printIntArray(a2);
    }
}
