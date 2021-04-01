package leetcode1;

import java.util.LinkedList;

public class ImmutableListPrint {
    public void printLinkedListInReverse(ImmutableListNode head) {

        if(head!=null){
            printLinkedListInReverse(head.getNext());
            head.printValue();
        }

    }
}

interface ImmutableListNode {
      public void printValue(); // print the value of this node.
      public ImmutableListNode getNext(); // return the next node.
  };

class Impl implements ImmutableListNode {

    private LinkedList<Integer> list;

    public Impl(LinkedList<Integer> list) {
        this.list = list;
    }

    @Override
    public void printValue() {
//        if (list != null && list.)
    }

    @Override
    public ImmutableListNode getNext() {
        return null;
    }
}
