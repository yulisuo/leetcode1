package util;

import linkedlist.Node;

import java.util.List;

public class Utils {

    public static void print(String s) {
        System.out.println(s);
    }

    public static void print(List<String> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("list [");
        for (String s: list) {
            sb.append(s).append(",");
        }
        sb.append("]");
        print(sb.toString());
    }

    public static void printIntArray(int[] a) {
        print("array:");
        for (int n : a){
            System.out.print(n + " ");
        }
        print("\n");
    }

    public static void printSingleEndLinkedList(Node head) {
        StringBuilder sb = new StringBuilder();
        Node cur = head;
        if (cur != null) {
            sb.append(cur.getData());
            while (cur.getNext() != null) {
                cur = cur.getNext();
                sb.append("-->").append(cur.getData());
            }
        }
        print("单链表：" + sb.toString());
    }
}
