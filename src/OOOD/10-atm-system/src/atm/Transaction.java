package atm;

public interface Transaction {
    TransactionType getType();

    boolean validateTransaction();

    void executeTransaction();
}
