package HW4;

public class LockDList extends DList{
    protected DListNode newNode(Object item, DListNode prev, DListNode next) {
        return new LockDListNode(item, prev, next);
    }
    public void lockNode(DListNode node) {
        ((LockDListNode) node).isLocked = true;
    }
    public void remove(DListNode node) {
        // Your solution here.
        if (node == null) {
            return;
        }
        if (!((LockDListNode) node).isLocked) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }
    }
}
