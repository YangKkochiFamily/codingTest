package programmers.pccp.oildrilling.thseo;

import java.util.*;

//https://school.programmers.co.kr/learn/courses/30/lessons/250136
public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(new int[][]{
                {1, 0, 1, 0, 1, 1},
                {1, 0, 1, 0, 0, 0},
                {1, 0, 1, 0, 0, 1},
                {1, 0, 0, 1, 0, 0},
                {1, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1}}
        );
    }
    public int solution(int[][] land) {

        int maxOilCount = 0;

        Map<Integer, Integer> oilBlockSizeMap = new HashMap<>();

        calculateOilBlock(land, oilBlockSizeMap);

        for (int i = 0; i < land[0].length; i++) {//옆으로 이동
            int oilCount = 0;
            Set<Integer> keySet = new HashSet<>();
            for (int j = 0; j < land.length; j++) {
                if (land[j][i] > 1) {
                    keySet.add(land[j][i]);
                }
            }
            oilCount = keySet.stream().mapToInt(oilBlockSizeMap::get).sum();

            maxOilCount = Math.max(maxOilCount, oilCount);
        }

        return maxOilCount;
    }

    private void calculateOilBlock(int[][] land, Map<Integer, Integer> oilBlockSizeMap) {
        int number = 2;

        for (int i = 0; i < land[0].length; i++) {
            int oilCount = 0;
            for (int j = 0; j < land.length; j++) {
                if (land[j][i] != 1) {//석유 아님
                    continue;
                }
                oilCount = drilling(land, j, i, number);
                oilBlockSizeMap.put(number,oilCount);
                number++;
            }

        }
    }

    private int drilling(int[][] clonedLand, int j, int i, int number) {
        int oilCount = 1;

        //뽑기 완료
        clonedLand[j][i] = number;

        //오른쪽 이동
        if (clonedLand[j].length - 1 > i && clonedLand[j][i+1] == 1) {
            oilCount += drilling(clonedLand, j, i+1, number);
        }

        //아래로 이동
        if (clonedLand.length - 1 > j && clonedLand[j + 1][i] == 1) {
            oilCount += drilling(clonedLand, j+1, i, number);
        }

        //왼쪽 이동
        if (0 < i && clonedLand[j][i-1] == 1) {
            oilCount += drilling(clonedLand, j, i-1, number);
        }

        //위로 이동
        if (0 < j && clonedLand[j-1][i] == 1) {
            oilCount += drilling(clonedLand, j-1, i, number);
        }

        return oilCount;
    }
}
