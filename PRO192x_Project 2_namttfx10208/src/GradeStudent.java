
import java.util.Scanner;
public class GradeStudent {
    public static final int MAX = 100;//khai báo biến hằng
    public static void main (String[] args){
        Scanner input = new Scanner(System.in);
        begin(); /* Thông điệp chào mừng */
        //Nhập và tính toán điểm thi giữa kỳ
        System.out.println("");
        System.out.println("Midterm: ");
        System.out.print("Weight (0-100)? ");
        int weightedMidTerm = input.nextInt();
        while (!(weightedMidTerm >0 && weightedMidTerm < (MAX-2)) ) {   /* không được nhập số âm và phải nhỏ hơn 98 */
            System.out.println("Something wrong here, please input again ");
            weightedMidTerm = input.nextInt();
        }
        double weightedMidTermScore = midterm(weightedMidTerm, input);// gọi hàm và trả về giá trị biến

        //Nhập và tính toán điểm thi cuối kỳ
        System.out.println("Final: ");
        System.out.print("Weight (0-100)? ");
        int weightedFinalExam = input.nextInt();
        while (weightedFinalExam < 0 || (weightedMidTerm + weightedFinalExam)>=MAX  ) { /* không được nhập số âm, tổng <100*/
            System.out.println("Something wrong here, it is >0 and under " + (MAX-weightedMidTerm));
            weightedFinalExam = input.nextInt();
        }
        //gọi hàm và trả giá trị về biến
        double weightedFinalExamScore = finalexam(weightedFinalExam, input);

        //Nhập và tính toán điểm bài tập
        System.out.println("Homework: ");
        System.out.print("Weight (0-100)? ");
        int weightedHomework = input.nextInt();
        // Tổng trọng số phải bằng 100
        while (weightedHomework != (MAX - weightedMidTerm - weightedFinalExam)){
            System.out.print("Please input again. It must be "+ (MAX - weightedMidTerm - weightedFinalExam) +" ");
            weightedHomework = input.nextInt();
        }
        //gọi hàm và trả giá trị về biến
        double weightedHomeworkScore = homework(weightedHomework, input);

        //Báo cáo kết quả
        report(weightedMidTermScore,weightedFinalExamScore,weightedHomeworkScore);

    }
   /* thông điệp chương trình */
    public static void begin(){
        System.out.println("");
        System.out.println("This program reads exam/homework scores and reports your overall course grade.");
    }
    // Tính giá trị midterm Weight
    public static double midterm(int weightedMidTerm, Scanner input){
        System.out.print("Score earned? ");
        int midtermScoreEarn = input.nextInt();
        while (midtermScoreEarn < 0 || midtermScoreEarn >MAX  ) {   /* không được nhập số âm, và không lớn hơn 100 */
            System.out.println("Something wrong here, please input again ");
            midtermScoreEarn = input.nextInt();
        }
        System.out.print("Were scores shifted (1=yes, 2=no)? ");
        int midtermScoreShifted = input.nextInt();
        int midtermTotalPoint = midtermScoreEarn;
        if (midtermScoreShifted == 1) {
            System.out.print("Shift amount? ");
            int midtermShiftAmount = input.nextInt();
            while (midtermShiftAmount < 0 || midtermShiftAmount >MAX ) {   /* không được nhập số âm */
                System.out.println("Something wrong here, please input again ");
                midtermShiftAmount = input.nextInt();
            }
            midtermTotalPoint += midtermShiftAmount;
        }

        //Giới hạn điều kiện midterm total point <=100
        if (midtermTotalPoint > MAX){
            midtermTotalPoint = MAX;
        }
        System.out.println("Total points = " + midtermTotalPoint + "/ 100");
        double weightedMidTermScore = Math.round((double)midtermTotalPoint * weightedMidTerm / MAX * 10)/10.0;
        System.out.println("Weigthed score = " + weightedMidTermScore +" / " +weightedMidTerm);
        System.out.println();
        return weightedMidTermScore;
    }

    public static double finalexam(int weightedFinalExam, Scanner input){
        System.out.print("Score earned? ");
        int finalexamScoreEarn = input.nextInt();
        while (finalexamScoreEarn < 0 || finalexamScoreEarn >MAX ) {   /* không được nhập số âm, và không lớn hơn 100 */
            System.out.println("Something wrong here, please input again ");
            finalexamScoreEarn = input.nextInt();
        }
        System.out.print("Were scores shifted (1=yes, 2=no)? ");
        int finalexamShifted = input.nextInt();
        int finalexamTotalPoint = finalexamScoreEarn;
        if (finalexamShifted == 1) {
            System.out.print("Shift amount? ");
            int finalexamShiftAmout = input.nextInt();
            while (finalexamShiftAmout < 0 || finalexamShiftAmout >MAX ) {   /* không được nhập số âm, và không lớn hơn 100 */
                System.out.println("Something wrong here, please input again ");
                finalexamShiftAmout = input.nextInt();
            }
            finalexamTotalPoint += finalexamShiftAmout;
        }

        //Giới hạn điều kiện finalexam total point <=100
        if (finalexamTotalPoint > MAX){
            finalexamTotalPoint = MAX;
        }
        System.out.println("Total points = " + finalexamTotalPoint + "/ 100");
        double weightedFinalExamScore = Math.round((double) finalexamTotalPoint * weightedFinalExam / MAX * 10)/10.0;
        System.out.println("Weigthed score = " + weightedFinalExamScore +" / " + weightedFinalExam);
        System.out.println();
        return weightedFinalExamScore;
    }

    public static double homework(double weightedHomework, Scanner input){
        System.out.print("Number of assignments? ");
        int numberOfAssignment = input.nextInt();
        while (numberOfAssignment < 0 ) {   /* không được nhập số âm */
            System.out.println("Something wrong here, please input again ");
            numberOfAssignment = input.nextInt();
        }
        int totalAssignmentScore = 0, totalAssignmentMaxScore = 0, assignmentScore, assignmentMaxScore;
        for (int i = 1; i <= numberOfAssignment; i++){
            System.out.print("Assignment " + i + " score and max? ");
            assignmentScore = input.nextInt();
            assignmentMaxScore = input.nextInt();
            //không nhập assignmentMaxScore, assignmentScore số âm hoắc lớn hơn 100, assignmentScore >assignmentMaxScore
            while (assignmentScore < 0 || assignmentMaxScore <0 || assignmentScore > assignmentMaxScore || assignmentMaxScore > MAX ) {
                System.out.println("Something wrong here, please input again ");
                assignmentScore = input.nextInt();
                assignmentMaxScore = input.nextInt();
            }
            totalAssignmentScore += assignmentScore;
            totalAssignmentMaxScore += assignmentMaxScore;
        }

        //Giới hạn điều kiện điểm assignment
        if (totalAssignmentScore > 150){
            totalAssignmentScore = 150;
        }
        if (totalAssignmentMaxScore > 150){
            totalAssignmentMaxScore = 150;
        }
        System.out.print("How many sections did you attend? ");
        int numberAttend = input.nextInt();
        while (numberAttend < 0 ) {   /* không được nhập số âm */
            System.out.println("Something wrong here, please input again ");
            numberAttend = input.nextInt();
        }
        //Giới hạn điều kiện điểm attend
        if (numberAttend > 6){
            numberAttend = 6;
        }
        int sectionPoint = numberAttend * 5;

        int homeworkTotalPoint = totalAssignmentScore + sectionPoint;
        int homeworkTotalMaxPoint = totalAssignmentMaxScore + 30;
        System.out.println("Section points = " + sectionPoint + "/ 30");
        System.out.println("Total points = " + homeworkTotalPoint + "/" + homeworkTotalMaxPoint );
        double weightedHomeworkScore = Math.round((double) homeworkTotalPoint / homeworkTotalMaxPoint * weightedHomework * 10)/10.0;
        System.out.println("Weigthed score = " + weightedHomeworkScore +" / "+weightedHomework);
        System.out.println();
        return weightedHomeworkScore;
    }
    // Kết quả
    public static void report(double weightedMidTermScore, double weightedFinalExamScore, double weightedHomeworkScore){
        double overallPercentage = weightedMidTermScore + weightedFinalExamScore + weightedHomeworkScore;
        System.out.println("Overall Percentage = " + overallPercentage);
        double grade;

        //Xếp loại học lực
        if (overallPercentage >= 85.0){
            grade = 3.0;
            System.out.println("Your grade will be at least: " + grade);
            System.out.println("Classification: Excellent");
        } else if (overallPercentage >= 75.0){
            grade = 2.0;
            System.out.println("Your grade will be at least: " + grade);
            System.out.println("Classification: Very good");
        } else if (overallPercentage >= 60.0){
            grade = 0.7;
            System.out.println("Your grade will be at least: " + grade);
            System.out.println("Classification: Good");
        } else {
            grade = 0.0;
            System.out.println("Your grade will be at least: " + grade);
            System.out.println("Classification: Average");
        }
    }
}
