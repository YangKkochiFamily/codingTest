package programmers.code_challenge.flexible_work.thseo;

import java.util.Arrays;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(new int[]{730, 855, 700, 720},
                new int [][] {{710, 700, 650, 735, 700, 931, 912}, {908, 901, 805, 815, 800, 831, 835}, {705, 701, 702, 705, 710, 710, 711}, {707, 731, 859, 913, 934, 931, 905}},
                1);
    }
    /*
    * schedules : 출근 희망 시각
    * timelogs : 직원들이 일주일 동안 출근한 시각
    * startday : 이벤트를 시작한 요일
    * */
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int [] winner = new int[schedules.length];

        //startday : 1,2,3,4,5,6,7
        // 1 <= startday % 7 <= 5


        for(int i = 0; i < schedules.length; i++){
            int workTime = schedules[i];

            workTime = timeProcess(workTime);

            int startdayTmp = startday;
            for(int j=0; j < timelogs[i].length; j++, startdayTmp++){
                if (startdayTmp % 7 >= 1 && startdayTmp % 7 <= 5) {
                    if (workTime < timelogs[i][j]) {
                        break;
                    }
                }

                if (j == timelogs[i].length - 1) {
                    winner[i] = 1;
                }
            }
        }

        return (int) Arrays.stream(winner)
                .filter(num -> num == 1)
                .count();
    }

    private int timeProcess(int workTime) {
        //750 + 10 = 760
        //755 + 10 = 805
        int hour = workTime / 100;
        int minute = workTime % 100 + 10;

        if (minute >= 60) {
            hour += 1;
            minute -= 60;
        }

        return hour * 100 + minute;
    }
}