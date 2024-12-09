package utils;
public class RegularCheckingAccount extends CheckingAccount implements Profitable
{
    public static int regularCheckingAccountsCount = 0;
    public static Account[] regularCheckingAccounts = new Account[2];

    public RegularCheckingAccount(String accountNumber, int bankNumber, String managerName, double credit)
    {
        super(accountNumber, bankNumber, managerName, credit);
    }

    // Calculates the yearly profit for the regular checking account.
    // @return: The yearly profit calculated as credit multiplied by a predefined rate difference.
    @Override
    public double calculateYearlyProfit()
    {
        AccountsManger.rateDifference = 0.1;
        return this.getCredit() * AccountsManger.rateDifference;
    }

    // Prints the details of the regular checking account.
    @Override
    public void printDetails()
    {
        System.out.println("-----------------------------------------------------");
        System.out.println("Account Type: Regular Checking Account");
        System.out.println("Account Number: " + this.getAccountNumber());
        System.out.println("bank_system.Bank Number: " + this.getBankNumber());
        System.out.println("Manager Name: " + this.getManagerName());
        System.out.println("Credit Limit: " + this.getCredit());
        this.printClients();
    }

    // Adds this regular checking account to the bank system by updating the regularCheckingAccounts array and passing the account to AccountsManager.
    @Override
    public void addToBank()
    {
        AccountsManger.addProfitable(this);
        AccountsManger.addAccount(this);
        this.addRegularCheckingAccounts();
        this.addCheckingAccount();
    }

    // Adds this regular checking account to the static array of regular checking accounts.
    public void addRegularCheckingAccounts()
    {

        if (regularCheckingAccountsCount == regularCheckingAccounts.length - 1)
        {
            regularCheckingAccounts = AccountsManger.resizeAccountsArray(regularCheckingAccounts);
        }

        regularCheckingAccountsCount += 1;
        regularCheckingAccounts[regularCheckingAccountsCount - 1] = this;
    }
}
