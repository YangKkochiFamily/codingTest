package programmers.pccp.oildrilling.bgchoi;

import java.util.*;

public class Solution {
    private int n, m;
    private int[][] land;
    private boolean[][] visited;
    private final Map<Integer, Integer> oilSizes = new HashMap<>();
    private int currentOilId = 2;

    public int solution(int[][] land) {
        this.land = land;
        this.n = land.length;
        this.m = land[0].length;
        this.visited = new boolean[n][m];

        //오일 size
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1 && !visited[i][j]) {
                    int size = findOilDeposit(i, j, currentOilId);
                    oilSizes.put(currentOilId, size);
                    currentOilId++;
                }
            }
        }

        // 드릴 한번에 얼만큼 가져올수 있는지
        int maxOil = 0;
        for (int col = 0; col < m; col++) {
            Set<Integer> seenDeposits = new HashSet<>();
            int totalOil = 0;
            for (int row = 0; row < n; row++) {
                int depositId = land[row][col];
                if (depositId > 1 && !seenDeposits.contains(depositId)) {
                    totalOil += oilSizes.get(depositId);
                    seenDeposits.add(depositId);
                }
            }
            maxOil = Math.max(maxOil, totalOil);
        }

        return maxOil;
    }

    //DFS
    private int findOilDeposit(int x, int y, int oilId) {
        int size = 0;
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{x, y});
        visited[x][y] = true;
        land[x][y] = oilId;

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        while (!stack.isEmpty()) {
            int[] cell = stack.pop();
            int cx = cell[0], cy = cell[1];
            size++;

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && land[nx][ny] == 1 && !visited[nx][ny]) {
                    stack.push(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    land[nx][ny] = oilId;
                }
            }
        }

        return size;
    }
}