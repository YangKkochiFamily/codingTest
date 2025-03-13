package programmers.code_challenge.perfect_crime.mjlee;

import java.util.Arrays;

public class Solution {
    public int solution(int[][] info, int n, int m) {
        int itemCount = info.length;
        int INF = 999999;

        // DP 테이블 정의: dp[i][a][b] -> i번째 물건까지 고려했을 때 A와 B가 남긴 흔적 값
        int[][][] dp = new int[itemCount + 1][n + 1][m + 1];

        // DP 배열을 큰 값으로 초기화
        for (int[][] arr2D : dp) {
            for (int[] arr1D : arr2D) {
                Arrays.fill(arr1D, INF);
            }
        }

        // 초기 상태 (아무 흔적도 남기지 않은 상태)
        dp[0][0][0] = 0;

        // DP 진행
        for (int i = 0; i < itemCount; i++) {
            int aTrace = info[i][0]; // A도둑이 남기는 흔적
            int bTrace = info[i][1]; // B도둑이 남기는 흔적

            for (int a = 0; a <= n; a++) {
                for (int b = 0; b <= m; b++) {
                    if (dp[i][a][b] == INF) continue; // 불가능한 경우 건너뜀

                    // A도둑이 i번째 물건을 훔치는 경우
                    if (a + aTrace < n) {
                        dp[i + 1][a + aTrace][b] = Math.min(dp[i + 1][a + aTrace][b], dp[i][a][b] + aTrace);
                    }

                    // B도둑이 i번째 물건을 훔치는 경우
                    if (b + bTrace < m) {
                        dp[i + 1][a][b + bTrace] = Math.min(dp[i + 1][a][b + bTrace], dp[i][a][b]);
                    }
                }
            }
        }

        // 최소 A도둑 흔적 찾기
        int minTrace = INF;
        for (int a = 0; a < n; a++) {
            for (int b = 0; b < m; b++) {
                minTrace = Math.min(minTrace, dp[itemCount][a][b]);
            }
        }

        return minTrace == INF ? -1 : minTrace;
    }
}
