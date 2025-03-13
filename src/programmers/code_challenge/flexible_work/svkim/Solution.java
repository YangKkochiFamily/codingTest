package programmers.code_challenge.flexible_work.svkim;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(new int[]{730, 855, 700, 720},
                new int [][] {{710, 700, 650, 735, 700, 931, 912}, {908, 901, 805, 815, 800, 831, 835}, {705, 701, 702, 705, 710, 710, 711}, {707, 731, 859, 913, 934, 931, 905}},
                1);
    }

    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;

        for(int i=0;i < schedules.length; i++) {
            int schedule = schedules[i];
            boolean check = true;
            for(int j=0;j < timelogs.length; j++) {
                if(j+startday == 6 || j+startday == 7) { //주말이면 skip
                    continue;
                }

                if(!checkTime(schedule, timelogs[i][j])) { //지각이면 false
                    check = false;
                }

                if(!check) break; //한번이라도 false가 되면 break
            }

            if(check) answer++; //check가 true -> 한번도 지각 안한 사람이면 answer +1
        }

        return answer;
    }

    private boolean checkTime(int schedule, int log) {
        int hour = schedule / 100;
        int minute = schedule % 100;
        int log_hour = log / 100;
        int log_minute = log % 10;

        int startTime = hour * 60 + minute;
        int logTime = log_hour * 60 + log_minute;

        return logTime - startTime <= 10;
    }
}