import java.util.Scanner;
import java.util.Random;
public class LuckyNumber {
    public static final int max = 101;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rd = new Random();
        String playerAnswer;
        int totalGame = 0, totalGuess = 0, bestGame = 0, yourGuess=0, luckyNumber;
        //do...while vòng lặp toàn hệ thống
        do {
            luckyNumber = rd.nextInt(max);// tạo số ngẫu nhiên trong khoảng lựa chọn
            System.out.println("Welcome to the minigame called Lucky Number");
            System.out.println("I'm thinking of a number between 0 and " + (max -1));
            // Vòng lặp để tìm ra được đúng con số lucky number
            int guesstimes =0;
            while (yourGuess != luckyNumber) {
                System.out.print("Your guess? " );
                yourGuess = input.nextInt();
                input.nextLine();
                if (yourGuess > luckyNumber) {
                    System.out.println("It's lower.");
                } else if (yourGuess < luckyNumber) {
                    System.out.println("It's higher.");
                }
                guesstimes++; //số lần dự đoán trong 1 game chơi
            }
            //In ra kết quả chơi
            System.out.println("You got it right in " + guesstimes + " quesses");
            totalGuess += guesstimes;
            totalGame++;

            //Lấy ra dự đoán tốt nhất
            if (totalGame == 1) {
                bestGame = guesstimes;
            } else if (bestGame > guesstimes ) {
                bestGame = guesstimes;
            }
            System.out.println();
            System.out.print("Do you wanna play again? ");
            playerAnswer = input.next().toLowerCase();// chuyển dữ liệu nhập vào về chữ thường
            System.out.println();
        } while (playerAnswer.equals("y")|| playerAnswer.equals("yes") );// Điều kiện để vòng lặp tiếp tục
        input.close();
        // Kết quả chung cuộc
        double avgGuesstimes = totalGuess*1.0/totalGame; //nhân với 1.0 để kết quả ra số thực
        System.out.println("Overall results:");
        System.out.println("Total games    = " + totalGame);
        System.out.println("Total guess    = " + totalGuess);
        System.out.println("Guesses/game   = " + " " + (Math.round(avgGuesstimes * 10.0) / 10.0));
        System.out.println("Best game      = " + bestGame);
    }
}