package programmers.code_challenge.flexible_work.mjlee;

class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int n = schedules.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            int targetEnd = schedules[i] + 10;
            boolean gift = true;

            for (int j = 0; j < 7; j++) {
                int dayOfWeek = startday + j > 7 ? startday + j -7 : startday + j; // 요일계산
                if (dayOfWeek >= 6) continue; // 주말(토,일) 제외

                int checkInTime = timelogs[i][j];
                if (checkInTime > targetEnd) {
                    gift = false;
                    break;
                }
            }

            if (gift) count++;
        }

        return count;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] schedules = {700, 800, 1100};
        int[][] timelogs = {
                {710, 2359, 1050, 700, 650, 631, 659},
                {800, 801, 805, 800, 759, 810, 809},
                {1105, 1001, 1002, 600, 1059, 1001, 1100}
        };
        int startday = 5;

        System.out.println(sol.solution(schedules, timelogs, startday)); // 출력: 3
    }
}