package bank_system;

import bank_system.Bank;
import utils.*;

public class Main
{
    /**
     * Prompts the user to enter a valid number between 1 and 4.
     */
    public static int getType()
    {
        java.util.Scanner input = new java.util.Scanner(System.in);
        int choice;

        while (true) {
            System.out.print("Enter a number between 1 and 4: ");
            if (input.hasNextInt())
            {
                choice = input.nextInt();
                if (choice >= 1 && choice <= 4)
                {
                    return choice;
                } else {
                    System.out.println("Invalid choice! Please enter a number between 1 and 4.");
                }
            } else {
                System.out.println("Invalid input! Please enter a numeric value.");
                input.next();
            }
        }
    }

    /**
     * Displays available account types for the user to choose from.
     */
    public static void displayTypeList()
    {
        System.out.println("Choose Account Type:");
        System.out.print("\t1.Regular Checking Account\n" +
                "\t2.Business Checking Account\n" +
                "\t3.Mortgage Account\n" +
                "\t4.Savings Account\n");
    }

    /**
     * Handles creating an account number, either automatically or manually, with duplication check.
     */
    private static String getAccountNumber() throws DuplicationException {
        String accountNumber;
        java.util.Scanner input = new java.util.Scanner(System.in);

        while (true) {
            try {
                System.out.println("How do you want to create the account number?");
                System.out.print("\t1. Automatically\n" +
                        "\t2. Manually\n");
                System.out.print("Enter '1' for automatic or '2' for manual: ");

                int choice;

                if (!input.hasNextInt()) {
                    input.next();
                    System.out.println("Invalid input! Please enter a number (1 or 2).");
                    continue;
                }
                choice = input.nextInt();

                if (choice == 1) {
                    accountNumber = getAccountNumberAutomaticlly();
                    break;
                } else if (choice == 2) {
                    while (true) {
                        accountNumber = getAccountNumberManually();
                        if (accountNumber.matches("\\d{7}")) {
                            Account account = AccountsManger.searchForAccount(accountNumber);
                            if (account == null) {
                                return accountNumber;
                            } else {
                                throw new DuplicationException(accountNumber);
                            }
                        } else {
                            System.out.println("Invalid account number! Please enter a 7-digit numeric value.");
                        }
                    }
                } else {
                    System.out.println("Invalid choice! Please enter '1' or '2'.");
                }
            } catch (DuplicationException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return accountNumber;
    }


    /**
     * Prompts the user to input a manually entered 7-digit account number.
     */
    private static String getAccountNumberManually()
    {
        String accountNumber;
        java.util.Scanner input = new java.util.Scanner(System.in);
        while (true)
        {
            System.out.print("Enter the account number (should be a 7 digit number for example 1234567):");
            accountNumber = input.nextLine();
            if (accountNumber.length() == 7)
            {
                for (int i = 0; i < accountNumber.length(); i++)
                {
                    char c = accountNumber.charAt(i);
                    if (c < '0' || c > '9') {
                        System.out.print("The account number should be a 7 digit number for example 1234567:");
                        break;
                    }
                }
            }
            return accountNumber;
        }
    }

    /**
     * Generates a unique account number automatically.
     */
    public static String getAccountNumberAutomaticlly()
    {
        String accountNumber = AccountsManger.getNextNumber();
        Account account = AccountsManger.searchForAccount(accountNumber);
        while (account != null)
        {
            accountNumber = AccountsManger.getNextNumber();
            account = AccountsManger.searchForAccount(accountNumber);
        }
        return accountNumber;
    }

    /**
     * Prompts the user to enter a valid 4-digit bank number.
     */
    private static int getBankNumber()
    {
        int bankNumber;
        java.util.Scanner input = new java.util.Scanner(System.in);
        System.out.println("Enter bank_system.Bank Number (4-digit number Exp: 1234):");
        while (true)
        {
            try
            {
                bankNumber = input.nextInt();
                int numDigits = String.valueOf(bankNumber).length();
                if (numDigits == 4) {
                    return bankNumber;
                } else {
                    System.out.println("The bank_system.Bank Number should be a 4-digit number!");
                }
            }
            catch (java.util.InputMismatchException e)
            {
                System.out.println("Invalid input! Please enter a numeric 4-digit bank_system.Bank Number.");
                input.next();
            }

            System.out.println("Renter bank_system.Bank Number (4-digit number Exp: 1234):");
        }
    }

    /**
     * Prompts the user to enter a valid manager's name.
     */
    private static String getMangerName()
    {
        String managerName;
        java.util.Scanner input = new java.util.Scanner(System.in);

        while (true) {
            System.out.println("Enter Manager Name (e.g., John Smith):");
            managerName = input.nextLine().trim();

            if (!managerName.isEmpty() && !managerName.matches(".*\\d.*")) {
                return managerName;
            } else {
                System.out.println("Invalid input! Manager name cannot be empty or contain numbers. Please try again.");
            }
        }
    }

    /**
     * Allows the user to add clients to an account, repeating the process if necessary.
     */
    private static void getClients(String accountNumber, Bank bank) {
        String choice;
        Client client;
        java.util.Scanner input = new java.util.Scanner(System.in);
        while (true) {
            client = getClient(accountNumber);
            bank.addClientToAccount(accountNumber, client);
            System.out.println("Would you like to add another client? (Enter y / n)");
            choice = input.next();
            if (choice.equalsIgnoreCase("y")) {
                System.out.println("New Client Information:");
            } else if (choice.equalsIgnoreCase("n")) {
                return;
            } else {
                System.out.println("Invalid choice. Please enter 'y' or 'n'.");
            }
        }
    }

    /**
     * Prompts the user to input valid client details (name and rank).
     */
    private static Client getClient(String accountNumber) {
        String clientName;
        int clientRank;
        Account account = AccountsManger.searchForAccount(accountNumber);
        java.util.Scanner input = new java.util.Scanner(System.in);


        while (true) {
            System.out.println("Enter Client Name:");
            clientName = input.nextLine().trim();
            if (!clientName.isEmpty() && !clientName.matches(".*\\d.*")) {
                if (!account.checkClientsExist(clientName)) {
                    break;
                } else {
                    System.out.println("This client already exists! Enter another Client Name:");
                }
            } else {
                System.out.println("Invalid input! Client name cannot be empty or contain numbers. Please try again.");
            }
        }

        System.out.println("Enter Client Rank (between 0 and 10):");
        while (!input.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number between 0 and 10:");
            input.next();
        }
        clientRank = input.nextInt();
        while (clientRank < 0 || clientRank > 10) {
            System.out.println("Rank must be between 0 and 10. Try again.");
            System.out.println("Enter Client Rank:");
            clientRank = input.nextInt();
        }

        return new Client(clientName, clientRank);
    }


    /**
     * Prompts the user to input a valid credit value.
     */
    private static int getCredit() {
        int credit;
        java.util.Scanner input = new java.util.Scanner(System.in);

        while (true) {
            System.out.println("Enter Credit (a numeric value):");

            if (input.hasNextInt()) {
                credit = input.nextInt();
                return credit;
            } else {
                System.out.println("Invalid input! Please enter a numeric value for credit.");
                input.next();
            }
        }
    }


    /**
     * Prompts the user to input a valid business revenue.
     */
    private static double getBusinessRevenue() {
        double businessRevenue;
        java.util.Scanner input = new java.util.Scanner(System.in);

        while (true) {
            System.out.println("Enter Business Revenue (a numeric value):");

            if (input.hasNextDouble()) {
                businessRevenue = input.nextDouble();
                return businessRevenue;
            } else {
                System.out.println("Invalid input! Please enter a numeric value for Business Revenue.");
                input.next();
            }
        }
    }

    /**
     * Prompts the user to input a valid Original Mortgage Amount.
     */
    private static double getOriginalMortgageAmount() {
        double originalMortgageAmount;
        java.util.Scanner input = new java.util.Scanner(System.in);

        while (true) {
            System.out.println("Enter Original Mortgage Amount (a numeric value):");

            if (input.hasNextDouble()) {
                originalMortgageAmount = input.nextDouble();
                return originalMortgageAmount;
            } else {
                System.out.println("Invalid input! Please enter a numeric value for Original Mortgage Amount.");
                input.next();
            }
        }
    }

    /**
     * Prompts the user to input a valid number of years for the mortgage.
     */
    private static int getYears() {
        int years;
        java.util.Scanner input = new java.util.Scanner(System.in);

        while (true) {
            System.out.println("Enter The Number Of Years (a numeric value):");

            if (input.hasNextInt()) {
                years = input.nextInt();
                return years;
            } else {
                System.out.println("Invalid input! Please enter a valid numeric value for the number of years.");
                input.next();
            }
        }
    }


    /**
     * Prompts the user to input a valid deposit amount for savings account.
     */
    private static double getDepositAmount()
    {
        double depositAmount;
        java.util.Scanner input = new java.util.Scanner(System.in);

        while (true) {
            System.out.println("Enter The Deposit Amount (a numeric value):");

            if (input.hasNextDouble()) {
                depositAmount = input.nextDouble();
                return depositAmount;
            } else {
                System.out.println("Invalid input! Please enter a valid numeric value for the deposit amount.");
                input.next();
            }
        }
    }


    /**
     * Collects account details based on the account type and creates the account.
     */
    public static void getAccountInformation(Bank bank, int accountType) throws DuplicationException
    {
        String accountNumber = getAccountNumber();
        int bankNumber = getBankNumber();
        String managerName = getMangerName();

        // utils.RegularCheckingAccount
        if (accountType == 1)
        {
            double credit = getCredit();
            bank.createRegularCheckingAccount(accountNumber, bankNumber, managerName, credit);
        }
        // businessCheckingAccount
        else if (accountType == 2)
        {
            int credit = getCredit();
            double businessRevenue = getBusinessRevenue();
            bank.createBusinessCheckingAccount(accountNumber, bankNumber, managerName, credit, businessRevenue);
        }
        // mortgageAccount
        else if (accountType == 3)
        {
            double originalMortgageAmount = getOriginalMortgageAmount();
            int years = getYears();
            double monthlyPayment = originalMortgageAmount / (years * 12);
            bank.createMortgageAccount(accountNumber, bankNumber, managerName, originalMortgageAmount, years, monthlyPayment);
        }
        // savingsAccount();
        else if (accountType == 4)
        {
            double depositAmount = getDepositAmount();
            int years = getYears();
            bank.createSavingsAccount(accountNumber, bankNumber, managerName, depositAmount, years);
        }
        getClients(accountNumber, bank);
    }

    /**
     * The main method handles user input for various bank operations.
     */
    public static void main(String[] args) throws DuplicationException {
        java.util.Scanner input = new java.util.Scanner(System.in);
        Bank bank = new Bank();
        String Choice;

        while (true)
        {
            System.out.println("\n--- bank_system.Bank System Menu ---");
            System.out.println("1. Fill accounts automatically with predefined data");
            System.out.println("2. Add new account");
            System.out.println("3. Add client to an existing account");
            System.out.println("4. Display all accounts");
            System.out.println("5. Display profitable accounts");
            System.out.println("6. Display accounts by type");
            System.out.println("7. Display yearly profit for a specific account");
            System.out.println("8. Display total yearly profit for all accounts");
            System.out.println("9. Display most profitable checking account");
            System.out.println("10. Verify business account profit");
            System.out.println("11. Display management fees");
            System.out.println("e/E. Exit");
            System.out.print("Enter your choice: ");

            Choice = input.nextLine().trim();


            try {
                if (Choice.equalsIgnoreCase("e")) {
                    System.out.println("Exiting...");
                    System.exit(0);
                }

                int choiceNum = Integer.parseInt(Choice);

                if (choiceNum >= 1 && choiceNum <= 11)
                {

                    switch (choiceNum) {
                        case 1:
                            bank.fillPredefinedAccounts();
                            break;
                        case 2:
                            int accountType;
                            displayTypeList();
                            accountType = getType();
                            getAccountInformation(bank, accountType);
                            break;
                        case 3:
                            Client client = null;
                            String accountNumber = getAccountNumberManually();
                            Account account = AccountsManger.searchForAccount(accountNumber);
                            while (account == null) {
                                System.out.println("There is no account with this number! Please enter another account number:");
                                accountNumber = getAccountNumberManually();
                                account = AccountsManger.searchForAccount(accountNumber);
                            }

                            if (account != null) {
                                client = getClient(accountNumber);
                                bank.addClientToAccount(accountNumber, client);
                            }
                            break;
                        case 4:
                            bank.displayAllAccounts();
                            break;
                        case 5:
                            bank.displayProfitableAccounts();
                            break;
                        case 6:
                            displayTypeList();
                            accountType = getType();
                            bank.displayAccountsByType(accountType);
                            break;
                        case 7:
                            accountNumber = getAccountNumberManually();
                            account = AccountsManger.searchForAccount(accountNumber);
                            while (account == null) {
                                System.out.println("There is no account with this number! Please enter another account number:");
                                accountNumber = getAccountNumberManually();
                                account = AccountsManger.searchForAccount(accountNumber);
                            }
                            bank.displayYearlyProfitForAccount(account);
                            break;
                        case 8:
                            bank.displayTotalYearlyProfit();
                            break;
                        case 9:
                            bank.displayMostProfitableCheckingAccount();
                            break;
                        case 10:
                            String businessAccountNumber = getAccountNumberManually();
                            Account businessAccount = AccountsManger.searchForAccount(businessAccountNumber);
                            while (businessAccount == null) {
                                System.out.println("There is no account with this number! Please enter another account number:");
                                businessAccountNumber = getAccountNumberManually();
                                businessAccount = AccountsManger.searchForAccount(businessAccountNumber);
                            }
                            while (!(businessAccount instanceof BusinessCheckingAccount)) {
                                System.out.println("This is not a business account!");
                                businessAccountNumber = getAccountNumberManually();
                                businessAccount = AccountsManger.searchForAccount(businessAccountNumber);
                            }
                            bank.checkProfitVIP((BusinessCheckingAccount) businessAccount);
                            break;
                        case 11:
                            bank.printManagementFees(AccountsManger.accounts);
                            break;
                        default:
                            break;
                    }
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 11, or 'e' to exit.");
                }
            } catch (NumberFormatException e) {

                System.out.println("Invalid choice. Please enter a number between 1 and 11, or 'e' to exit.");
            }
        }
    }

}

