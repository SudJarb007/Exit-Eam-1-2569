package model;

public class election
{
    private String id;
    private String title;
    private String status;
    private int[] ranking_points;
    private int duplicatePatternThreshold;

    public election(String id, String title, String status, 
        int[] ranking_points, int duplicatePatternThreshold)
    {
        this.id = id;
        this.title = title;
        this.status = status;
        this.ranking_points = ranking_points;
        this.duplicatePatternThreshold = duplicatePatternThreshold;
    }
    public String getStatus()
    {
        return status;
    }
    public int[] ranking_points()
    {
        return ranking_points;
    }
    public int getDuplicatePatternThreshold()
    {
        return duplicatePatternThreshold;
    }
    public void setStatus(String status) //อันนี้ตอน close
    {
        this.status = status;
    }
}