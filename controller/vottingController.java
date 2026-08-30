// package model;
package controller;
import model.*;
import java.util.*;
import java.util.regex.Pattern;

public class vottingController
{
    private List<ballots> ballotList;
    private List<voters> voterList;
    private election elecStatus;
    private List<candidates> candidateList;
    private List<pattern> patternGroups = new ArrayList<>();
    
    /* note : 
ตอนลงคะแนน -> ลงในใบ 
เช้คว่า active ไหม, 
เคยลงมาก่อนยัง, 
election = open */
/** ตอนตรวจสอบ -> เพื่อปฏิเสธ + แต้งเหตุผล
   * case : เลือกซ้ำ
   * case : เลือกไม่ครบ
   * case : เคยลงคะแนน (มีแค่เคสนี้ที่กลับมาลงคะแนนเสียงต่อไม่ได้)
   * case : election status != open */
    public vottingController(List<ballots> ballotList, 
        List<voters> voterList,election elecStatus, List<candidates> candidateList)
    {
        this.ballotList = ballotList;
        this.voterList = voterList;
        this.elecStatus = elecStatus;
        this.candidateList = candidateList;
    }
    public String checkProcessVoteSuccess(String voterId, String[] ranking)
    {
        if (!elecStatus.getStatus().equals("OPEN"))
            return "deny : election close";

        // เช้คคนมาโหวต
        voters voter = findVoterById(voterId);
        if (!voter.IsActive())
            return "deny : you are not active";
        if (voter.hasVoted() == true)
            return ("deny : เคยลงคะแนนแล้ว");
        if (ranking.length != 3)
            return "deny : ลงไม่ครบ 3 คน";
        if (ranking[0].equals(ranking[1]) || ranking[0].equals(ranking[2]) || ranking[1].equals(ranking[2]))
            return "deny : ลงคะแนนเสียงซ้ำคน";

        //ให้เไช่วยตอนต้องสรางบัตร
        String ballotId = "B" + String.format("%02d", ballotList.size() + 1);
        ballots newBallot = new ballots(ballotId, voter, ranking);
        ballotList.add(newBallot);
        voter.setHasVoted(true); 
        return "success";
    }
    public voters findVoterById(String id)
    {
        for (voters item : voterList)
        {
            if (item.getId().equals(id))
                return item;
        }
        return null;
    }
/*
note :
* (R3)ตรวจหารูปแบบที่ซ้ำ + การปิดรับคะแนน
   * election = close
   * check : รูปแบบการจัดอันดับของบัตร
*/
    private boolean checkPattern(String[] r1, String[] r2)
    {
        for (int idx = 0; idx < 3; idx = idx + 1)
        {
            if (!r1[idx].equals(r2[idx]))
                return false;
        }
        return true;
    }
    public void processClose()
    {
        //อันนี้จะอัปเดตค่าในอิเล้ค
        elecStatus.setStatus("close");

        List<ballots> keep = new ArrayList<>();
        for (int i = 0; i < ballotList.size(); i = i + 1)
        {
            if (keep.contains(ballotList.get(i)))
            {
                continue; //เคยเจอแล้วจะให้ข้าม
            }
            List<ballots> sameGroup = new ArrayList<>();
            sameGroup.add(ballotList.get(i));
            
            for (int j = i + 1; j < ballotList.size(); j = j + 1)
            {
                ballots other = ballotList.get(j);
                if (keep.contains(other))
                {
                    continue;
                }
                if (checkPattern(ballotList.get(i).getRanking(), other.getRanking()))
                    sameGroup.add(other);
            }

            if (sameGroup.size() >= elecStatus.getDuplicatePatternThreshold())
            {
                pattern group = new pattern(ballotList.get(i).getRanking());
                for (ballots b : sameGroup)
                {
                    b.setStatus("รอตรวจ");
                    group.addBallot(b);
                }
                patternGroups.add(group);
            }
            else
                {
                    for (ballots b : sameGroup)
                    {
                        b.setStatus("รับรองแล้ว");
                    }

                }
            keep.addAll(sameGroup);
        }
    }


    public String decisionPatternGroup(pattern group, String decision)
    {
        if (!group.getStatus().equals("รอตรวจ"))
            return "deny : ไม่อยู่ใน state รอตรวจ";
        group.setStatus(decision);
        String ballotStatus = decision.equals("รับรอง") ? "รับรองแล้ว" : "ไม่นับ";
        for (ballots b : group.getBallotGroup())
        {
            b.setStatus(ballotStatus);
        }
        if (isAllGroupsResolved()) {
            elecStatus.setStatus("สรุปผลแล้ว");
        }
        return "success";
    }
    private boolean isAllGroupsResolved() {
        for (pattern g : patternGroups) {
            if (g.getStatus().equals("รอตรวจ")) 
                return false;
        }
        return true;
    }



    public Map<String, Integer> calculateResults() {
        Map<String, Integer> scores = new HashMap<>();
        for (candidates c : candidateList) {
            scores.put(c.getId(), 0);
        }
        
        for (ballots b : ballotList) {
            if (b.getStatus().equals("รับรองแล้ว")) {
                String[] ranking = b.getRanking();
                
                int[] rankingPoints = elecStatus.ranking_points();
                // scores.put(ranking[0], scores.get(ranking[0]) + 3);
                // scores.put(ranking[1], scores.get(ranking[1]) + 2);
                // scores.put(ranking[2], scores.get(ranking[2]) + 1);
                scores.put(ranking[0], scores.get(ranking[0]) + rankingPoints[0]);
                scores.put(ranking[1], scores.get(ranking[1]) + rankingPoints[1]);
                scores.put(ranking[2], scores.get(ranking[2]) + rankingPoints[2]);
            }

        }
        return scores;
    }




    //เหลือตอนแสดงผล
    /*
    * บัตรที่รับแล้ว
    *  */
   public void showCard()
   {
        System.out.println("บัตรที่รับแล้ว" + ballotList.size());
   }
   /*โชวทกุ่มที่รอตรวจ จำนวนบัตรแต่ละกุ่ม ผลจากบัตรที่นับได้ */
   public void showCloseResult()
   {
        System.out.println("--- กลุ่มที่รอตรวจสอบ ---");
        for (pattern g : patternGroups)
        {
           if (g.getStatus().equals("รอตรวจ"))
           {//เไ ช่วยเขียน
            System.out.println("Pattern: " + String.join(">", g.getPatternResult()) 
                + " | จำนวนบัตร: " + g.getBallotGroup().size());
           }
        }
        System.out.println("--- ผลชั่วคราว (เฉพาะบัตรที่รับรองแล้ว) ---");
        Map<String, Integer> tempScores = calculateResults();
        for (Map.Entry<String, Integer> entry : tempScores.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
   }

   public void showFinalStatus() {
        int approvedCount = 0;
        int rejectedCount = 0;
        for (ballots b : ballotList) {
            if (b.getStatus().equals("รับรองแล้ว")) approvedCount++;
            if (b.getStatus().equals("ไม่นับ")) rejectedCount++;
        }

        System.out.println("--- คะแนนรวม (สรุปผลแล้ว) ---");
        Map<String, Integer> finalScores = calculateResults();
        for (Map.Entry<String, Integer> entry : finalScores.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        System.out.println("จำนวนบัตรที่รับรอง: " + approvedCount);
        System.out.println("จำนวนบัตรที่ไม่นับ: " + rejectedCount);
    }

    public List<pattern> getPatternGroups() 
    {
        return patternGroups;
    }
}
