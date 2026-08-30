package model;

public class voters
{
    private String id;
    private String name;
    private boolean active;
    private boolean hasVoted = false;
    
    public voters(String id, String name, boolean active)
    {
        this.id = id;
        this.name = name;
        this.active = active;
    }
    public String getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public boolean Isactive()
    {
        return active;
    }
    public void setHasVoted(boolean hasVoted)
    {
        this.hasVoted = hasVoted;
    }
    
}