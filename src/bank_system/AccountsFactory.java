package bank_system;

import utils.*;


public class AccountsFactory
{

    public void fillPredefinedAccounts()
    {
        //BusinessCheckingAccount Example
        String accountNumber = Main.getAccountNumberAutomaticlly();
        Account businessCheckingAccount1 = new BusinessCheckingAccount(accountNumber, 1234,"Tom Grey",8000, 10000);
        businessCheckingAccount1.addClient(new Client("John Doe",9));
        businessCheckingAccount1.addClient(new Client("Jane Smith",7));
        businessCheckingAccount1.addToBank();

        // RegularCheckingAccount Example
        accountNumber = Main.getAccountNumberAutomaticlly();
        Account regularCheckingAccount1 = new RegularCheckingAccount(accountNumber, 1234,"Tom Grey",4000);
        regularCheckingAccount1.addClient(new Client("Marah riad",5));
        regularCheckingAccount1.addClient(new Client("Haya riad",5));
        regularCheckingAccount1.addToBank();

        //MortgageAccount Example
        accountNumber = Main.getAccountNumberAutomaticlly();
        Account mortgageAccount1 = new MortgageAccount(accountNumber,1234,"Tom Grey", 100000, 20, 5000);
        mortgageAccount1.addClient(new Client("Chris Brown", 9));
        mortgageAccount1.addClient(new Client("Bobby Brown", 9));
        mortgageAccount1.addToBank();

        // SavingsAccount Example
        accountNumber = Main.getAccountNumberAutomaticlly();
        Account savingsAccount = new SavingsAccount(accountNumber, 1234, "Tom Grey", 1000, 4);
        savingsAccount.addClient(new Client("Tom Green",8));
        savingsAccount.addClient(new Client("Jerry Blue", 7));
        savingsAccount.addToBank();

        System.out.println("Predefined accounts have been added.");
    }
}
