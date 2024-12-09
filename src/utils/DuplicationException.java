package utils;

// Custom exception class to handle cases where a duplicate account number is detected.
public class DuplicationException extends Exception
{
    private String duplicateAccountNumber;

    public DuplicationException(String duplicateAccountNumber) {
        super("Duplicate account number: " + duplicateAccountNumber);
        this.duplicateAccountNumber = duplicateAccountNumber;
    }

    public String getDuplicateAccountNumber()
    {
        return duplicateAccountNumber;
    }
}
