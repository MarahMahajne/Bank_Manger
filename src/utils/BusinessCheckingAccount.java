package utils;

public class BusinessCheckingAccount extends CheckingAccount implements ManagementFeePayable, Profitable

{
    private double businessRevenue;
    private static final int COMMISSION = 3000;
    public static int businessCheckingAccountsCount = 0;

    public static Account[]  businessCheckingAccounts = new Account[2];



    public BusinessCheckingAccount(String accountNumber, int bankNumber, String managerName, double credit, double businessRevenue)
    {
        super(accountNumber, bankNumber, managerName, credit);
        this.businessRevenue = businessRevenue;
    }

    // Calculate yearly profit for the business checking account, considering the VIP status and commission
    @Override
    public double calculateYearlyProfit()
    {
        if (isVIP())
        {
            return 0;
        }
        return (this.getCredit() * AccountsManger.rateDifference) + COMMISSION;
    }


    // Print the details of the business checking account
    @Override
    public void printDetails()
    {
        System.out.println("-----------------------------------------------------");
        System.out.println("Account Type: Business Checking Account");
        System.out.println("Account Number: " + this.getAccountNumber());
        System.out.println("bank_system.Bank Number: " + this.getBankNumber());
        System.out.println("Manager Name: " + this.getManagerName());
        System.out.println("Credit Limit: " + this.getCredit());
        System.out.println("Business Revenue: " + this.getBusinessRevenue());
        this.printClients();
    }



    // Add the business checking account to the bank's records and perform additional operations
    @Override
    public void addToBank()
    {
        AccountsManger.addProfitable(this);
        AccountsManger.addManagementFeePayableAccount(this);
        AccountsManger.addAccount(this);
        this.addbusinessCheckingAccounts();
        this.addCheckingAccount();
        if (this.isVIP())
        {
            System.out.println("This account is VIP!");
        }
    }


    // Check if the business checking account qualifies as VIP based on revenue and client rank
    public boolean isVIP()
    {
        Client[] clients;
        if (businessRevenue >= 10000000)
        {
            clients = getClients();
            for (int i = 0; i < this.getClientCount(); i++)
            {
                if (clients[i].getRank() != 10)
                    return false;
            }
            return true;
        }
        return false;
    }



    // Calculate the management fee for the business checking account based on account age
    @Override
    public double calculateManagementFee()
    {
        if (getAccountAgeInYears() <= 2)
        {
            return 1000;
        }
        return 0;
    }

    // Add the business checking account to the array of business checking accounts
    public void addbusinessCheckingAccounts()
    {

        if (businessCheckingAccountsCount == businessCheckingAccounts.length - 1)
        {
            businessCheckingAccounts = AccountsManger.resizeAccountsArray(businessCheckingAccounts);
        }
        businessCheckingAccountsCount += 1;
        businessCheckingAccounts[businessCheckingAccountsCount - 1] = this;

    }


    public double getBusinessRevenue()
    {
        return businessRevenue;
    }

    public void setBusinessRevenue(double businessRevenue)
    {
        this.businessRevenue = businessRevenue;
    }



}
