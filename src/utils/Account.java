package utils;
import java.util.Date;

public abstract class Account
{
    private String accountNumber;
    private Date openDate;
    private int bankNumber;
    private int balance;
    private String managerName;
    private Client[] clients;
    private int clientCount;


    public Account(String accountNumber, int bankNumber, String managerName)
    {
        this.accountNumber = accountNumber;
        this.openDate = new Date();
        this.bankNumber = bankNumber;
        this.balance = 20;
        this.managerName = managerName;
        this.clients = new Client[2];
        this.clientCount = 0;
    }


    // Add a new client to the account. Resizes client array if necessary.
    public void addClient(Client client)
    {
        if (clientCount == this.clients.length - 1)
        {
            resizeClientsArray();
        }
        clientCount++;
        this.clients[clientCount - 1] = client;
    }


    // Resize the clients array when it becomes full
    private void resizeClientsArray()
    {
        Client[] newArray = new Client[clients.length + 2];
        System.arraycopy(clients, 0, newArray, 0, clients.length);
        clients = newArray;
    }


    // Calculate the age of the account in years by comparing the open date with the current date
    public int getAccountAgeInYears()
    {
        Date currentDate = new Date();
        long ageInMillis = currentDate.getTime() - openDate.getTime();
        return (int) (ageInMillis / (1000L * 60 * 60 * 24 * 365));
    }

    // Abstract method to print account details (to be implemented in subclasses)
    public abstract void printDetails();

    // Abstract method to add account to the bank system (to be implemented in subclasses)
    public abstract void addToBank();


    // Check if a client with the given name exists in the account
    public boolean checkClientsExist(String clientName)
    {
        for(int i = 0; i < this.clientCount; i++)
        {
            if (this.clients[i].getName().equals(clientName))
            {
                return true;
            }
        }
        return false;
    }

    // Print the list of clients associated with the account
    public void printClients()
    {
        int pos;
        System.out.println("Clients List: ");
        for ( int i = 0; i < this.clientCount; i++)
        {
            pos = i +1;
            System.out.println("\t\tclient "+ pos + ": " + "Name: " + this.clients[i].getName() + ", Rank: "+ this.clients[i].getRank());
            System.out.println("\t\t--------------------------------------");
        }
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    public Date getOpenDate()
    {
        return openDate;
    }

    public int getBankNumber()
    {
        return bankNumber;
    }


    public String getManagerName()
    {
        return managerName;
    }

    public Client[] getClients()
    {
        return clients;
    }

    public int getClientCount()
    {
        return clientCount;
    }

    public void setClients(Client[] clients)
    {
        this.clients = clients;
    }
}
