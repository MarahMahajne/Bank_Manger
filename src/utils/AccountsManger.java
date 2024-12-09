package utils;

public class AccountsManger
{
    public static Account[] accounts = new Account[2];
    public static int accountCount = 0;
    public static Account[] profitableAccounts = new Account[2];
    public static int profitableAccountsCount = 0;
    public static Account[] managementFeePayableAccounts = new Account[2];
    public static int managementFeePayableAccountsCount = 0;
    public static String lastAccountNumber = "1000000";
    public static double rateDifference = 0.1;



    // Resize the given array to accommodate more accounts
    public static Account[] resizeAccountsArray(Account[] array)
    {
        Account[] newAccounts = new Account[accounts.length + 2];
        System.arraycopy(accounts, 0, newAccounts, 0, accounts.length);
        array = newAccounts;
        return array;
    }


    // Add a new account to the accounts array
    public static void addAccount(Account account)
    {
        if (accountCount == accounts.length - 1)
        {
            accounts = resizeAccountsArray(accounts);
        }
        accountCount += 1;
        accounts[accountCount - 1] = account;
    }


    // Search for an account by its account number and return the account, or null if not found
    public static Account searchForAccount(String accountNumber)
    {
        for(int i = 0; i < accountCount; i++)
        {
            if (accounts[i].getAccountNumber().equals(accountNumber))
            {
                return accounts[i];
            }
        }
        return null;
    }


    // Get the next available account number by incrementing the last account number
    public static String getNextNumber()
    {
        int num = Integer.parseInt(lastAccountNumber);
        int nextNum = num + 1;
        String accountNumber = Integer.toString(nextNum);
        lastAccountNumber = accountNumber;
        return accountNumber;
    }


    // Add a profitable account to the profitable accounts array
    public static void addProfitable(Account account)
    {
        if (profitableAccountsCount == profitableAccounts.length - 1)
        {
            profitableAccounts = resizeAccountsArray(profitableAccounts);
        }
        profitableAccountsCount += 1;
        profitableAccounts[profitableAccountsCount - 1] = account;

    }


    // Add an account that pays a management fee to the corresponding array
    public static void addManagementFeePayableAccount(Account account)
    {
        if (managementFeePayableAccountsCount == managementFeePayableAccounts.length - 1)
        {
            managementFeePayableAccounts = resizeAccountsArray(managementFeePayableAccounts);
        }
        managementFeePayableAccountsCount += 1;
        managementFeePayableAccounts[managementFeePayableAccountsCount - 1] = account;

    }


    // Sort the accounts array by account number in ascending order
    public static void sortAccessioningByAccountNumber()
    {
        for (int i = 0; i < accountCount - 1; i++)
        {
            for (int j = 0; j < accountCount - i - 1; j++)
            {
                if (Integer.parseInt(accounts[j].getAccountNumber()) > Integer.parseInt(accounts[j + 1].getAccountNumber()))
                {
                    Account temp = accounts[j];
                    accounts[j] = accounts[j + 1];
                    accounts[j + 1] = temp;
                }
            }
        }

    }

    // Sort accounts by yearly profit in descending order
    public static void sortDecidendiByYearlyProfit()
    {
        for (int i = 0; i < accountCount - 1; i++)
        {
            for (int j = 0; j < accountCount - i - 1; j++)
            {
                if (accounts[j] != null && accounts[j + 1] != null
                        && accounts[j] instanceof Profitable && accounts[j + 1] instanceof Profitable)
                {

                    if (((Profitable) accounts[j]).calculateYearlyProfit()
                            < ((Profitable) accounts[j + 1]).calculateYearlyProfit())
                    {
                        Account temp = accounts[j];
                        accounts[j] = accounts[j + 1];
                        accounts[j + 1] = temp;
                    }
                }
            }
        }
    }








}
