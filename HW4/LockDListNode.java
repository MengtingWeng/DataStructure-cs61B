package HW4;

public class LockDListNode extends DListNode {
    protected boolean isLocked;
    LockDListNode(Object i, DListNode p, DListNode n) {
        super(i, p, n);
        isLocked = false;
    }
}
