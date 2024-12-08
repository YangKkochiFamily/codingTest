package programmers.pccp.oildrilling.thseo;

import java.util.Arrays;

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
        for (int i = 0; i < land[0].length; i++) {//옆으로 이동
            int [][] clonedLand = deppCopy(land);
            int oilCount = 0;

            for (int j = 0; j < land.length; j++) {
                System.out.println(j +"-"+ i + " = " +land[j][i]);

                if (clonedLand[j][i] == 0) {//석유 아님
                    continue;
                }

                oilCount += drilling(clonedLand, j, i);//석유 시추 시작
            }
            maxOilCount = Math.max(maxOilCount, oilCount);
            System.out.println("maxOilCount = " + maxOilCount);
        }

        return maxOilCount;
    }

    private int[][] deppCopy(int[][] land) {
        int [][] copy = new int[land.length][];

        for (int i = 0; i < land.length; i++) {
            copy[i] = Arrays.copyOf(land[i], land[i].length);
        }
        return copy;

    }

    private int drilling(int[][] clonedLand, int j, int i) {
        int oilCount = 1;

        //뽑기 완료
        clonedLand[j][i] = 0;

        //오른쪽 이동
        if (clonedLand[j].length - 1 > i && clonedLand[j][i+1] == 1) {
            oilCount += drilling(clonedLand, j, i+1);
        }

        //아래로 이동
        if (clonedLand.length - 1 > j && clonedLand[j + 1][i] == 1) {
            oilCount += drilling(clonedLand, j+1, i);
        }

        //왼쪽 이동
        if (0 < i && clonedLand[j][i-1] == 1) {
            oilCount += drilling(clonedLand, j, i-1);
        }

        //위로 이동
        if (0 < j && clonedLand[j-1][i] == 1) {
            oilCount += drilling(clonedLand, j-1, i);
        }

        return oilCount;
    }
}
