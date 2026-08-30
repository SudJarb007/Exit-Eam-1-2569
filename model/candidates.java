package model;

public class candidates
{
    private String id;
    private String name;

    public candidates(String id, String name)
    {
        this.id = id;
        this.name = name;
    }
    public String getName()
    {
        return name;
    }
    public String getId()
    {
        return id;
    }
}