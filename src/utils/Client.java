package utils;

public class Client
{
    private String name;
    private int rank;

    public Client(String name, int rank)
    {
        this.name = name;
        this.rank = rank;
    }

    public int getRank()
    {
        return rank;
    }

    public void setRank(int rank)
    {
        this.rank = rank;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
