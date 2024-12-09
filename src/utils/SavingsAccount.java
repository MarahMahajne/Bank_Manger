package utils;

// The SavingsAccount class extends the Account class and represents a specific type of bank account with a deposit amount and a duration (in years).
public class SavingsAccount extends Account
{
    private double depositAmount;
    private int years;
    public static int savingsAccountsCount = 0;
    public static Account[] savingsAccounts = new Account[2];



    public SavingsAccount(String accountNumber, int bankNumber, String managerName, double depositAmount, int years)
    {
        super(accountNumber, bankNumber, managerName);
        this.depositAmount = depositAmount;
        this.years = years;
    }

    // Prints the details of the savings account.
    @Override
    public void printDetails()
    {
        System.out.println("-----------------------------------------------------");
        System.out.println("Account Type: Savings Account");
        System.out.println("Account Number: " + this.getAccountNumber());
        System.out.println("bank_system.Bank Number: " + this.getBankNumber());
        System.out.println("Manager Name: " + this.getManagerName());
        System.out.println("Deposit Amount: " + this.depositAmount);
        System.out.println("Years: " + this.years);
        this.printClients();
    }

    // Adds this savings account to the bank system by updating the savingsAccounts array and passing the account to AccountsManager.
    @Override
    public void addToBank()
    {
        this.addSavingsAccounts();
        AccountsManger.addAccount(this);
    }

    // Adds this savings account to the static array of savings accounts.
    public void addSavingsAccounts()
    {

        if (savingsAccountsCount == savingsAccounts.length - 1)
        {
            savingsAccounts = AccountsManger.resizeAccountsArray(savingsAccounts);
        }

        savingsAccountsCount += 1;
        savingsAccounts[savingsAccountsCount - 1] = this;
    }
}
