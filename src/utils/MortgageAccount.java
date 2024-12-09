package utils;
public class MortgageAccount extends Account implements ManagementFeePayable, Profitable
{
    private double originalMortgageAmount;
    private int years;
    private double monthlyPayment;

    public static Account[] mortgageAccounts = new Account[2];
    public static int mortgageAccountsCount = 0;



    public MortgageAccount(String accountNumber, int bankNumber, String managerName, double originalMortgageAmount, int years, double monthlyPayment)
    {
        super(accountNumber,bankNumber, managerName);
        this.originalMortgageAmount = originalMortgageAmount;
        this.years = years;
        this.monthlyPayment = monthlyPayment;
    }

    // This method calculates the yearly profit for the mortgage account.
    // It uses the original mortgage amount, the number of years, and a rate difference factor from the AccountsManger class to calculate the profit.
    @Override
    public double calculateYearlyProfit()
    {
        AccountsManger.rateDifference = 0.10;
        return (originalMortgageAmount * 0.8 / years) * AccountsManger.rateDifference;
    }


    // This method prints the details of the mortgage account, including account number, bank number, manager name,
    // original mortgage amount, years, and monthly payment, as well as the clients associated with the account.
    @Override
    public void printDetails()
    {
        System.out.println("-----------------------------------------------------");
        System.out.println("Account Type: Mortgage Account");
        System.out.println("Account Number: " + this.getAccountNumber());
        System.out.println("bank_system.Bank Number: " + this.getBankNumber());
        System.out.println("Manager Name: " + this.getManagerName());
        System.out.println("Original Mortgage Amount: " + this.originalMortgageAmount);
        System.out.println("Years: " + this.years);
        System.out.println("Monthly Payment: " + this.monthlyPayment);
        this.printClients();

    }

    // This method adds the mortgage account to the bank system, registering it as both profitable and management fee payable,
    // and then adds it to the list of accounts.
    @Override
    public void addToBank()
    {
        AccountsManger.addProfitable(this);
        AccountsManger.addManagementFeePayableAccount(this);
        AccountsManger.addAccount(this);
        this.addMortgageAccount();
    }

    // This method calculates the management fee for the mortgage account based on its age.
    // If the account age is 2 years or less, a management fee is calculated as 0.001 times the original mortgage amount.
    @Override
    public double calculateManagementFee()
    {
        if (getAccountAgeInYears() <= 2)
        {
            return (0.001 * this.originalMortgageAmount);
        }
        return 0;
    }



    // This method adds the mortgage account to the mortgage accounts array, resizing it if necessary.
    public void addMortgageAccount()
    {
        if (mortgageAccountsCount == mortgageAccounts.length - 1)
        {
            mortgageAccounts = AccountsManger.resizeAccountsArray(mortgageAccounts);
        }
        mortgageAccountsCount += 1;
        mortgageAccounts[mortgageAccountsCount - 1] = this;

    }

}
