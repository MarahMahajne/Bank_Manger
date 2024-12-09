package bank_system;

import bank_system.AccountsFactory;
import utils.*;


public class Bank
{


    AccountsFactory accountsFactory;


    public Bank()
    {
        this.accountsFactory = new AccountsFactory();
    }

    // Creates a Regular Checking Account and adds it to the bank.
    public void createRegularCheckingAccount(String accountNumber, int bankNumber, String managerName, double credit)
    {
        RegularCheckingAccount regularCheckingAccount = new RegularCheckingAccount(accountNumber, bankNumber, managerName, credit);
        regularCheckingAccount.addToBank();
    }

    // Creates a Business Checking Account and adds it to the bank.
    public void createBusinessCheckingAccount(String accountNumber, int bankNumber, String managerName, double credit, double businessRevenue)
    {
        BusinessCheckingAccount businessCheckingAccount = new BusinessCheckingAccount(accountNumber, bankNumber, managerName, credit, businessRevenue);
        businessCheckingAccount.addToBank();
    }

    // Creates a Mortgage Account and adds it to the bank.
    public void createMortgageAccount(String accountNumber, int bankNumber, String managerName, double originalMortgageAmount, int years, double monthlyPayment)
    {
        MortgageAccount mortgageAccount = new MortgageAccount(accountNumber, bankNumber, managerName, originalMortgageAmount, years, monthlyPayment);
        mortgageAccount.addToBank();
    }

    // Creates a Savings Account and adds it to the bank.
    public void createSavingsAccount(String accountNumber, int bankNumber, String managerName, double depositAmount, int years)
    {
        SavingsAccount savingsAccount = new SavingsAccount(accountNumber, bankNumber, managerName, depositAmount, years);
        savingsAccount.addToBank();
    }

    // Adds a client to an existing account using the account number.
    public void addClientToAccount(String accountNumber, Client client)
    {
        Account account = AccountsManger.searchForAccount(accountNumber);
        if (account != null)
        {
            account.addClient(client);
        }
    }



    // Fills predefined accounts using bank_system.AccountsFactory.
    public void fillPredefinedAccounts()
    {
        accountsFactory.fillPredefinedAccounts();
    }



    // Checks and prints the yearly profit of a VIP Business Checking Account.
    public void checkProfitVIP(BusinessCheckingAccount account)
    {
        double profit;
        BusinessCheckingAccount accountCopy = new BusinessCheckingAccount(account.getAccountNumber(), account.getBankNumber(),
                account.getManagerName(), account.getCredit(), account.getBusinessRevenue());

        Client[] clients;
        if (account.isVIP())
        {
            clients = account.getClients();
            for (int i = 0; i < account.getClientCount(); i++)
            {
                clients[i].setRank(0);
            }
            accountCopy.setClients(clients);
            profit = accountCopy.calculateYearlyProfit();
            System.out.println("The profit for this business account if it was not vip is: " + profit);
        }
        else
        {
            System.out.println("the account is not VIP so there is no profit change!");
        }
    }



    // Prints management fees for all accounts that are payable, along with the CEO's yearly bonus.
    public void printManagementFees(Account[] accounts)
    {
        double totalBonus = 0;
        System.out.println("Management Fee for the accounts:");
        System.out.println("----------------------------------");
        for (int i = 0; i < AccountsManger.managementFeePayableAccountsCount; i++)
        {
            Account account = AccountsManger.managementFeePayableAccounts[i];
            double fee = ((ManagementFeePayable) account).calculateManagementFee();
            System.out.println("account with the number " + account.getAccountNumber() + ": " + fee);
            totalBonus += fee;
        }
        System.out.println("=> CEO's yearly bonus: " + totalBonus);
    }

    // Displays all accounts in the bank after sorting them by account number.
    public void displayAllAccounts()
    {
        if (AccountsManger.accountCount == 0)
        {
            System.out.println("No accounts available to display.");
            return;
        }
        AccountsManger.sortAccessioningByAccountNumber();
        for (int i = 0; i < AccountsManger.accountCount; i++)
        {
            Account account = AccountsManger.accounts[i];
            account.printDetails();
        }
    }

    // Displays all profitable accounts sorted by their yearly profit.
    public void displayProfitableAccounts()
    {
        AccountsManger.sortDecidendiByYearlyProfit();
        if (AccountsManger.profitableAccountsCount == 0)
        {
            System.out.println("No accounts available to display.");
            return;
        }

        AccountsManger.sortDecidendiByYearlyProfit();
        for (int i = 0; i < AccountsManger.profitableAccountsCount; i++)
        {
            Account account = AccountsManger.profitableAccounts[i];
            account.printDetails();
            double yearlyProfit = ((Profitable) account).calculateYearlyProfit();
            System.out.println("= > Yearly Profit: " + yearlyProfit);
        }
    }



    // Displays accounts by type (e.g., Regular Checking, Business Checking, Mortgage, Savings).
    public void displayAccountsByType(int accountType)
    {
        Account[] accounts = new Account[0];
        int count = 0;
        // Regular Checking Account
        if (accountType == 1)
        {
            accounts = RegularCheckingAccount.regularCheckingAccounts;
            count = RegularCheckingAccount.regularCheckingAccountsCount;
        }
        // Business Checking Account
        else if (accountType == 2)
        {
            accounts = BusinessCheckingAccount.businessCheckingAccounts;
            count = BusinessCheckingAccount.businessCheckingAccountsCount;
        }
        // Mortgage Account
        else if (accountType == 3)
        {
            accounts = MortgageAccount.mortgageAccounts;
            count = MortgageAccount.mortgageAccountsCount;
        }
        // Savings Account
        else if (accountType == 4)
        {
            accounts = SavingsAccount.savingsAccounts;
            count = SavingsAccount.savingsAccountsCount;
        }
        for (int i = 0; i < count; i++)
        {
            accounts[i].printDetails();
        }
    }



    // Displays the yearly profit for a specific account if it is profitable.
    public void displayYearlyProfitForAccount(Account account)
    {
        double yearlyProfit = 0;
        if (account instanceof Profitable)
        {
            yearlyProfit = ((Profitable) account).calculateYearlyProfit();
        }
        System.out.println("The yearly Profit for the account with number " + account.getAccountNumber() + " is: " + yearlyProfit);
    }



    // Displays the total yearly profit of all profitable accounts in the bank.
    public void displayTotalYearlyProfit()
    {
        double total = 0, accountYearlyProfit;
        Account account;
        for (int i = 0; i < AccountsManger.profitableAccountsCount; i++)
        {
            account = AccountsManger.profitableAccounts[i];
            accountYearlyProfit = ((Profitable) account).calculateYearlyProfit();
            total += accountYearlyProfit;
        }
        System.out.println("The Total Yearly Profit of the bank: " + total);
    }




    // Displays the most profitable Checking Account in the bank.
    public void displayMostProfitableCheckingAccount()
    {
        double accountYearlyProfit, maxAccountYearlyProfit = 0;
        Account mostProfitableCheckingAccount = null;
        Account account;
        for (int i = 0; i < CheckingAccount.checkingAccountsCount; i++)
        {
            account = CheckingAccount.checkingAccounts[i];
            accountYearlyProfit = ((Profitable) account).calculateYearlyProfit();
            System.out.println(accountYearlyProfit);
            if (maxAccountYearlyProfit < accountYearlyProfit)
            {
                maxAccountYearlyProfit = accountYearlyProfit;
                mostProfitableCheckingAccount = account;
            }
        }
        if (mostProfitableCheckingAccount != null)
        {
            System.out.println("Most Profitable Checking Account: ");
            mostProfitableCheckingAccount.printDetails();
            System.out.println("The profit is: " + maxAccountYearlyProfit);
        }
    }
}
