package programmers.code_challenge.flexible_work.yjlee;

public class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int product = 0;

        for (int i = 0; i < schedules.length; i++) {
            int deadline = schedules[i] + 10;
            int min = deadline % 100;
            if(min >= 60) {
                deadline += 40;
            }

            boolean isSuccess = true;

            for (int j = 0; j < 7; j++ ) {
                int day = (startday + j - 1) % 7 + 1;
                if (day == 6 || day == 7) {
                    continue;
                }

                int checkInTime = timelogs[i][j];

                if (checkInTime < 600 || checkInTime > 2359 || checkInTime > deadline) {
                    isSuccess = false;
                    break;
                }
            }
            if (isSuccess) {
                product++;
            }
        }
        return product;
    }
}
