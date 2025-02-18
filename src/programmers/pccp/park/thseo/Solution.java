package programmers.pccp.park.thseo;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(new int[]{5, 3, 2},
                new String[][]{{"A", "A", "-1", "B", "B", "B", "B", "-1"}
                        , {"A", "A", "-1", "B", "B", "B", "B", "-1"},
                        {"-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"},
                        {"D", "D", "-1", "-1", "-1", "-1", "E", "-1"},
                        {"D", "D", "-1", "-1", "-1", "-1", "-1", "F"},
                        {"D", "D", "-1", "-1", "-1", "-1", "E", "-1"}});
//        solution.solution(new int[]{2}, new String[][]{{"-1","-1","-1"}});
    }
    static String MAT_AVAILABLE_SPACE = "-1";
    static Set<Integer> dpCountSet = new HashSet<>(); //모든 정사각형의 경우의 수 값이 여기 들어있음


    public int solution(int[] mats, String[][] park) {

        //DP[Dynamic Programing] 동적계획법
        int[][] dp = new int[park.length][park[0].length];

        setDpTable(park, dp);

        int maxMatCount = -1;

        for(int m : mats) {
            maxMatCount = Math.max(maxMatCount, dpCountSet.contains(m) ? m : -1);
        }

        System.out.println(maxMatCount);
        return maxMatCount;
    }

    /*
    * DP 테이블 세팅*/
    private void setDpTable(String[][] park, int[][] dp) {
        for (int i = 0; i < park.length; i++) {
            for (int j = 0; j < park[i].length; j++) {
                if (MAT_AVAILABLE_SPACE.equals(park[i][j])) {
                    dp[i][j] = calculateCurrentPosition(dp, i, j);
                    dpCountSet.add(dp[i][j]);
                }
            }
        }
    }

    private int calculateCurrentPosition(int[][] dp, int i, int j) {

        //왼쪽
        int left = j - 1 >= 0 ? dp[i][j - 1] : 0;

        //위
        int up = i - 1 >= 0 ? dp[i - 1][j] : 0;

        //왼쪽 위 대각선
        int leftUp = (i - 1 >= 0) && (j - 1 >= 0) ? dp[i - 1][j - 1] : 0;

        return Math.min(Math.min(left, up), leftUp) + 1;

    }
}