# SUBMISSION - Exit Exam MVC 1/2569 (อาทิตย์เช้า)

## 1. วิธีเปิดโปรแกรม
- ภาษา/เฟรมเวิร์ก: Java 
- Entry point / คำสั่งเปิดโปรแกรม: รัน class `view.Main` (มี method main())
- หมายเหตุที่จำเป็น: ข้อมูลตั้งต้น (seed data) ถูกแปลงเป็นโค้ด hardcode ไว้ใน Main.java ตรงตาม seed_data.json ที่โจทย์กำหนด (ไม่ได้เขียน JSON parser), โปรแกรมรันลำดับ T1-T6 อัตโนมัติเมื่อเปิดโปรแกรม (ไม่มีเมนูให้พิมพ์โต้ตอบ)

## 2. ตารางเชื่อมโยง Requirements

| Requirement | Model / Domain | Controller / Action | View / Screen |
|---|---|---|---|
| R1 | election, voters, candidates, ballots, pattern | vottingController | Main |
| R2 | candidates, voters, ballots | checkProcessVoteSuccess() | Main |
| R3 | ballots (status), pattern | processClose(), checkPattern() | showCloseResult() |
| R4 | pattern, ballots | decisionPatternGroup(), calculateResults() | showFinalStatus() |
| R5 | election (status), pattern, ballots | showCard(), showCloseResult(), showFinalStatus() | Main |

## 3. ผลการทดสอบ

| กรณี | ผ่าน/ไม่ผ่าน | หมายเหตุ |
|---|---|---|
| T1 | ผ่าน | |
| T2 | ผ่าน | |
| T3 | ผ่าน | |
| T4 | ผ่าน | |
| T5 | ผ่าน | |
| T6 | ผ่าน | คะแนนตรงกับที่กำหนด C01=10, C02=9, C03=5, C04=4, C05=2 |

## 4. ความแตกต่างระหว่างแบบที่ออกกับโปรแกรมจริง (ถ้ามี)
ระบุไม่เกิน 3 ข้อ
1. 
2. 
3. 

## 5. บันทึกการใช้ Generative AI

| เวลาโดยประมาณ | เครื่องมือ | ใช้เพื่ออะไร | นำคำแนะนำไปใช้อย่างไร |
|---|---|---|---|
| ตลอดช่วงทำข้อสอบ | Claude | ขอคำอธิบายหลักการ MVC ไม่แน่ใจว่าส่วนของ controller สามารถทำได้แค่ อันเดียวได้ไหม, ถามหาสาเหตุ bug (เช่น constructor ไม่ assign field, ชื่อ method/ตัวแปรตัวพิมพ์เล็ก-ใหญ่ไม่ตรงกัน, array equals ผิด, string เทียบไม่ตรงกัน อย่างคำว่า "รอตรวจ" ตอนแรกไปตั้งไว้ว่า "รอเช็ค" ผลลัพธ์เพี้ยนพอสมควร ), ถามหลักการเลือก data structure (List vs Array, HashSet ) , ให้ช่วยแก้ error โลจิคบางส่วน รู้สึกว่าที่เขียนเองบางอันมันเป็น hardcode พอสมควร, ให้เอไอช่วยเขียนช่วยเช็ค test โดยเฉพาะส่วนแสดงผล(ใช้เอไอมากพอสมควร)ให้ช่วยจัดรูปแบบให้| นำคำแนะนำที่เอไอเตือนว่าอันนี้จะพัง อันนี้ชื่อไม่ตรงกัน มาแก้ในโค้ดตัวเอง ให้มันรันได้|