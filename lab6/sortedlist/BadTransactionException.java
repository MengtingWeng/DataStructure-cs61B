package lab6.sortedlist;

public class BadTransactionException extends Exception {
    /**
     *  Creates an exception object for nonexistent account "badAcctNumber".
     **/
    public BadTransactionException(int badTransactionNumber) {
        super("Invalid Transaction Amount: " + badTransactionNumber);
    }
}
