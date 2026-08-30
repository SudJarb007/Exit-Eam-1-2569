package model;

public class ballots
{
    private String id;
    
    private voters voter_id ;
    
    private String[] ranking;
    private String status; // อันนี้จะทำเป็น รอเช้ค, รับรองแล้ว, ไม่นับ
    
    public voters(String id, voters voter_id,String[] ranking, String status)
    {
        this.id = id;
        this.voter_id = name;
        this.ranking = ranking;
        this.status = "บันทึกเรียบร้อย";
    }
      public String getId()
    {
        return id;
    }
    public voters getVoter()
    {
        return voter;
    }
    public String[] ranking()
    {
        return ranking;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    public String getStatus()
    {
        return status;
    }
  
    
}