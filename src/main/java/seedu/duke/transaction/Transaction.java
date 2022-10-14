package seedu.duke.transaction;

import seedu.duke.id.IdGenerator;
import seedu.duke.parser.DateParser;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Transaction {
    private final String transactionId;
    private final String itemId;
    private final String borrower;
    private final int duration;
    private final LocalDate createdAt;
    private final LocalDate returnedAt;

    public Transaction(String itemId, String borrowerId, int duration, LocalDate createdAt) {
        this.transactionId = IdGenerator.generateId();
        this.itemId = itemId;
        this.borrower = borrowerId;
        this.duration = duration;
        this.createdAt = createdAt;
        this.returnedAt = createdAt.plusDays(duration);
    }

    public Transaction(String transactionId, String itemId, String borrowerId, int duration, LocalDate createdAt) {
        this.transactionId = transactionId;
        this.itemId = itemId;
        this.borrower = borrowerId;
        this.duration = duration;
        this.createdAt = createdAt;
        this.returnedAt = createdAt.plusDays(duration);
    }

    public String getTxId() {
        return transactionId;
    }

    public String getItemId() {
        return itemId;
    }

    public String getBorrower() {
        return borrower;
    }

    public LocalDate getReturnDate() {
        return returnedAt;
    }

    public boolean isFinished() {
        return returnedAt.isBefore(LocalDate.now());
    }

    public String convertTransactionToFileFormat() {
        String separator = " | ";
        return transactionId + separator + itemId + separator + borrower + separator + duration + separator + createdAt;
    }

    @Override
    public String toString() {
        String transactionIcon = "Status: [" + (isFinished() ? "Returned" : "On loan") + "] ";
        String transactionId = "TransactionID: " + this.transactionId + " ";
        String itemId = "ItemId: " + this.itemId + " ";
        String usersId = "BorrowerID: " + this.borrower + " ";

        if (!isFinished()) {
            String remainDays = " (" + ChronoUnit.DAYS.between(LocalDate.now(), getReturnDate()) + " day(s) remaining)";
            String returnDate = "ReturnDate: " + DateParser.formatDateToString(returnedAt) + remainDays;
            return transactionIcon + transactionId + itemId + usersId + returnDate;
        }
        String returnedDate = "ReturnedDate: " + DateParser.formatDateToString(returnedAt);
        return transactionIcon + transactionId + itemId + usersId + returnedDate;
    }
}
