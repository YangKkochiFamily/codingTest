package programmers.pccp.park.mjlee;

import java.util.Arrays;

class Solution {
    public static int solution(int[] mats, String[][] park) {
        int rows = park.length;  // 공원의 행(row) 개수
        int cols = park[0].length;  // 공원의 열(column) 개수
        int[][] dp = new int[rows][cols];  // 각 위치에서 만들 수 있는 정사각형 크기 저장
        int maxSize = 0;  // 공원에서 만들 수 있는 가장 큰 정사각형 크기

        // 최대 정사각형 크기 찾기
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if ("-1".equals(park[i][j])) {  // 현재 위치가 빈공간 일 때
                    if (i == 0 || j == 0) {  // 첫 번째 행 또는 첫 번째 열이면
                        dp[i][j] = 1;  // 가장 작은 1x1 크기의 정사각형 가능
                    } else {
                        // 현재 위치를 오른쪽 아래 꼭짓점으로 하는 정사각형 크기 계산
                        // 왼쪽(dp[i][j-1]), 위쪽(dp[i-1][j]), 왼쪽 대각선(dp[i-1][j-1]) 중 최소값을 찾아 +1
                        // 위에서 부터 +1 하다 보면 현재 위치가 -1 일때 왼쪽, 위쪽, 대각선의 최솟값에 본인꺼 +1 한게 최대값
                        // 예시
                        // 0 0 1 0
                        // 1 1 1 1
                        // 0 0 1 2
                        // 0 0 1 2
                        dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                    }
                    maxSize = Math.max(maxSize, dp[i][j]);  // 최대 정사각형 크기 갱신
                }
            }
        }

        // 가능한 돗자리 중 가장 큰 크기 찾기
        //reverse는 Integer[]로 변환해야됨
        Arrays.sort(mats);  // mats 배열을 오름차순 정렬 (작은 크기부터 정렬)
        for (int i = mats.length - 1; i >= 0; i--) {  // 가장 큰 돗자리부터 확인
            if (mats[i] <= maxSize) {  // 현재 돗자리가 공원에서 가능한 크기 이하라면
                return mats[i];  // 해당 돗자리 크기를 반환
            }
        }

        return -1;  // 적절한 돗자리를 찾지 못하면 -1 반환
    }

    public static void main(String[] args) {
        int[] mats = {5, 3, 2};
        String[][] park = {
                {"A", "A", "-1", "B", "B", "B", "B", "-1"},
                {"A", "A", "-1", "B", "B", "B", "B", "-1"},
                {"-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"},
                {"D", "D", "-1", "-1", "-1", "-1", "E", "-1"},
                {"D", "D", "-1", "-1", "-1", "-1", "-1", "F"},
                {"D", "D", "-1", "-1", "-1", "-1", "E", "-1"}
        };

        System.out.println(solution(mats, park));
    }
}