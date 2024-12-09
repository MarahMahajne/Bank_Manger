package utils;


public abstract class CheckingAccount extends Account
{
    private double credit;
    public static Account[] checkingAccounts = new Account[2];
    public static int checkingAccountsCount = 0;

    public CheckingAccount(String accountNumber, int bankNumber, String managerName, double credit)
    {
        super(accountNumber,bankNumber, managerName);
        this.credit = credit;
    }

    public double getCredit()
    {
        return credit;
    }

    public void setCredit(int credit)
    {
        this.credit = credit;
    }


    // Adds the checking account to the checkingAccounts array, resizing the array if necessary
    public void addCheckingAccount()
    {
        if (checkingAccountsCount == checkingAccounts.length - 1)
        {
            checkingAccounts = AccountsManger.resizeAccountsArray(checkingAccounts);
        }
        checkingAccountsCount += 1;
        checkingAccounts[checkingAccountsCount - 1] = this;
    }
}
