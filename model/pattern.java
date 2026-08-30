package model;

import java.util.*;

public class pattern
{
    private String[] patternResult;
    private List<ballots> ballotGroup = new ArrayList<>(); //อันนี้ปรึกษาเไว่าเก็บเป็นอะไรดี 
    private String status; // ถ้ากุ่มนั้นไม่นับ -> ทั้งกุ่มไม่นับ, ถ้ากุ่มนั้นนับ -> ทั้งกลุ่มนับ

    public pattern(String[] patternResult)
    {
        this.patternResult = patternResult;
        this.status = "รอตรวจ";
    }
    public String[] getPatternResult()
    {
        return patternResult;
    }
    public List<ballots> getBallotGroup()
    {
        return ballotGroup;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    public String getStatus()
    {
        return status;
    }
    public void addBallot(ballots b)
    {
        ballotGroup.add(b); // อันนี้ถามเไ วิธีเอาข้อมูลใส่
    }
}