package linkedlist;

import util.Utils;

public class ReverseTest2 {
    public static void main(String[] args) {
        N2 n0 = new N2(1);
        N2 n1 = new N2(2);
        N2 n2 = new N2(3);
        N2 n3 = new N2(4);
        N2 n4 = new N2(5);
        n0.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        N2 t = n0;
        Utils.print("before reverse:");
        while(t != null) {
            System.out.print("--> " + t.data);
            t = t.next;
        }
        Utils.print("");
        Utils.print("start reverse:");

        t = reverse(n0);
        Utils.print("after reverse:");
        while(t != null) {
            System.out.print("--> " + t.data);
            t = t.next;
        }
    }


    public static N2 reverse(N2 h) {
        if (h == null || h.next == null) {
            return h;
        }

        N2 ret = reverse(h.next);
        h.next.next = h;
        h.next = null;
        return ret;
    }

}

class N2 {
    public int data;
    public N2 next;

    public N2(int data) {
        this.data = data;
    }
}
