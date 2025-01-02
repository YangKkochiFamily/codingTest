package programmers.pccp.oildrilling.yjlee;

import java.util.*;

public class Solution {
    public int solution(int[][] land) {
        int n = land.length;
        int m = land[0].length;

        boolean[][] visited = new boolean[n][m];
        Map<Integer, Integer> oilSize = new HashMap<>();
        int[][] oilIdMap = new int[n][m]; // 각 땅이 속해 있는 석유 덩어리 Id
        int oilId = 1;

        // 석유 덩어리 크기 계산
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1 && !visited[i][j]) {
                    int size = dfs(land, visited, oilIdMap, i, j, oilId);
                    oilSize.put(oilId, size);
                    oilId++;
                }
            }
        }

        // 열 단위 석유량 계산
        int maxOil = 0;
        for (int col = 0; col < m; col++) {
            Set<Integer> visitedOilIds = new HashSet<>();
            int totalOil = 0;

            for (int row = 0; row < n; row++) {
                if (oilIdMap[row][col] > 0) {
                    int currentId = oilIdMap[row][col];
                    if (!visitedOilIds.contains(currentId)) { // 방문 하지 않은 땅이면
                        totalOil += oilSize.get(currentId);
                        visitedOilIds.add(currentId);
                    }
                }
            }
            maxOil = Math.max(maxOil, totalOil);
        }

        return maxOil;
    }

    private final Stack<int[]> stack = new Stack<>();
    private int dfs(int[][] land, boolean[][] visited, int[][] oilIdMap, int x, int y, int oilId) {
        int n = land.length;
        int m = land[0].length;

        int[] dx = {-1, 1, 0, 0}; // 위 아래 현재 현재
        int[] dy = {0, 0, -1, 1}; // 현재 현재 왼 오른

        stack.clear();
        stack.push(new int[]{x, y});
        visited[x][y] = true;
        oilIdMap[x][y] = oilId; // 현재 위치가 속하는 oilId 기록

        int size = 0;

        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            size++;

            for (int i = 0; i < 4; i++) { // 네 방향으로 이동 => 위 아래 왼 오른
                int nextX = current[0] + dx[i]; // 다음 x 위치로
                int nextY = current[1] + dy[i]; // 다음 y 위치로

                if (nextX >= 0 && nextY >= 0 && nextX < n && nextY < m && land[nextX][nextY] == 1 && !visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    oilIdMap[nextX][nextY] = oilId;
                    stack.push(new int[]{nextX, nextY});
                }
            }
        }

        return size;
    }
}
