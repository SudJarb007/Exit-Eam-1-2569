package view;

import model.*;
import controller.vottingController;
import java.util.*;

public class Main {
    // // ใช้เอไอเขียนเทสให้
    public static void main(String[] args) {
    // ---------- Seed Data ----------
    election e = new election("E01", "การเลือกตั้งประธานชมรม", "OPEN", 
                                new int[]{3, 2, 1}, 3);

    List<candidates> candidateList = new ArrayList<>();
    candidateList.add(new candidates("C01", "Null Pointer"));
    candidateList.add(new candidates("C02", "Merge Conflict"));
    candidateList.add(new candidates("C03", "Works on My Machine"));
    candidateList.add(new candidates("C04", "404 Policy Not Found"));
    candidateList.add(new candidates("C05", "Ctrl+Z Nation"));

    List<voters> voterList = new ArrayList<>();
    voterList.add(new voters("V01", "โพยอยู่ไหน", true));
    voterList.add(new voters("V02", "บังเอิญเหมือนกัน", true));
    voterList.add(new voters("V03", "เลือกเองจริง ๆ", true));
    voterList.add(new voters("V04", "ใจตรงกันเฉย ๆ", true));
    voterList.add(new voters("V05", "ขอดูอีกที", true));
    voterList.add(new voters("V06", "บัตรสุดท้าย", true));
    voterList.add(new voters("V07", "ไม่ได้อยู่กลุ่มไลน์", true));

    List<ballots> ballotList = new ArrayList<>();
    voters v01 = findById(voterList, "V01");
    voters v02 = findById(voterList, "V02");
    voters v03 = findById(voterList, "V03");
    v01.setHasVoted(true);
    v02.setHasVoted(true);
    v03.setHasVoted(true);
    ballotList.add(new ballots("B01", v01, new String[]{"C01","C02","C03"}));
    ballotList.add(new ballots("B02", v02, new String[]{"C01","C02","C03"}));
    ballotList.add(new ballots("B03", v03, new String[]{"C02","C03","C04"}));

    // ---------- Controller ----------
    vottingController controller = new vottingController(ballotList, voterList, e, candidateList);

    // ---------- R5: OPEN ----------
    controller.showCard();

    // ---------- T1-T4 ----------
    System.out.println("T1: " + controller.checkProcessVoteSuccess("V04", new String[]{"C01","C02","C03"}));
    System.out.println("T2: " + controller.checkProcessVoteSuccess("V04", new String[]{"C04","C05","C01"}));
    System.out.println("T3: " + controller.checkProcessVoteSuccess("V05", new String[]{"C04","C04","C02"}));
    System.out.println("T4: " + controller.checkProcessVoteSuccess("V05", new String[]{"C04","C05","C01"}));

    // ---------- T5 + R5: ปิดรับคะแนน ----------
    controller.processClose();
    controller.showCloseResult();

    // ---------- T6 ----------
    pattern targetGroup = controller.getPatternGroups().get(0);
    System.out.println("T6: " + controller.decisionPatternGroup(targetGroup, "รับรอง"));

    // ---------- R5: สรุปผลแล้ว ----------
    controller.showFinalStatus();
}

private static voters findById(List<voters> list, String id) {
    for (voters v : list) {
        if (v.getId().equals(id)) return v;
    }
    return null;
}
//     public static void main(String[] args) {

//         
//         election e = new election("E01", "การเลือกตั้งประธานชมรม", "OPEN", 
//                                     new int[]{3, 2, 1}, 3);

//         List<candidates> candidateList = new ArrayList<>();
//         candidateList.add(new candidates("C01", "Null Pointer"));
//         candidateList.add(new candidates("C02", "Merge Conflict"));
//         candidateList.add(new candidates("C03", "Works on My Machine"));
//         candidateList.add(new candidates("C04", "404 Policy Not Found"));
//         candidateList.add(new candidates("C05", "Ctrl+Z Nation"));

//         List<voters> voterList = new ArrayList<>();
//         voterList.add(new voters("V01", "โพยอยู่ไหน", true));
//         voterList.add(new voters("V02", "บังเอิญเหมือนกัน", true));
//         voterList.add(new voters("V03", "เลือกเองจริง ๆ", true));
//         voterList.add(new voters("V04", "ใจตรงกันเฉย ๆ", true));
//         voterList.add(new voters("V05", "ขอดูอีกที", true));
//         voterList.add(new voters("V06", "บัตรสุดท้าย", true));
//         voterList.add(new voters("V07", "ไม่ได้อยู่กลุ่มไลน์", true));

//         List<ballots> ballotList = new ArrayList<>();
//         // B01, B02, B03 ต้อง mark ว่า voter เคยโหวตแล้วด้วย ไม่งั้น validation จะงง
//         voters v01 = findById(voterList, "V01");
//         voters v02 = findById(voterList, "V02");
//         voters v03 = findById(voterList, "V03");
//         v01.setHasVoted(true);
//         v02.setHasVoted(true);
//         v03.setHasVoted(true);
//         ballotList.add(new ballots("B01", v01, new String[]{"C01","C02","C03"}));
//         ballotList.add(new ballots("B02", v02, new String[]{"C01","C02","C03"}));
//         ballotList.add(new ballots("B03", v03, new String[]{"C02","C03","C04"}));

//         // ---------- Controller ----------
//         vottingController controller = new vottingController(ballotList, voterList, e, candidateList);

//         // ---------- Test Cases ----------
//         System.out.println("T1: " + controller.checkProcessVoteSuccess("V04", new String[]{"C01","C02","C03"}));
//         // คาดหวัง: success

//         System.out.println("T2: " + controller.checkProcessVoteSuccess("V04", new String[]{"C04","C05","C01"}));
//         // คาดหวัง: deny (เคยลงคะแนนแล้ว)

//         System.out.println("T3: " + controller.checkProcessVoteSuccess("V05", new String[]{"C04","C04","C02"}));
//         // คาดหวัง: deny (เลือกซ้ำ)

//         System.out.println("T4: " + controller.checkProcessVoteSuccess("V05", new String[]{"C04","C05","C01"}));
//         // คาดหวัง: success

//         controller.processClose();
//         System.out.println("T5: ปิดรับคะแนนแล้ว (เช็ค status บัตรแต่ละใบเอง หรือ print PatternGroup ออกมาดู)");

//         System.out.println("--- สถานะบัตรหลังปิดรับคะแนน ---");
//         for (ballots b : ballotList) {
//             System.out.println(b.getId() + " -> " + b.getStatus());
// }
//     }

//     private static voters findById(List<voters> list, String id) {
//         for (voters v : list) {
//             if (v.getId().equals(id)) return v;
//         }
//         return null;
//     }
    // public static void main(String [] args)
    // {
    //     vottingController controller = new vottingController(null, null, null, null);
    //     controller.showCard();
    //     System.out.println("T1 : " + controller.checkProcessVoteSuccess(null, args));

    //     controller.processClose();
    //     controller.showCloseResult();

    //     pattern targetGroup = controller.getPatternGroups().get(0);
    //     System.out.println("T6: " + controller.decisionPatternGroup(targetGroup, "รับรอง"));
    //     controller.showFinalStatus();
    // }
}
