package programmers.pccp.park.yjlee;

import java.util.Arrays;
import java.util.Collections;

public class Solution {
    public int solution(int[] mats, String[][] park) {
        int rows = park.length;
        int cols = park[0].length;

        // 내림차순 정렬
        Integer[] matList = Arrays.stream(mats).boxed().toArray(Integer[]::new);
        Arrays.sort(matList, Collections.reverseOrder());

        for (int mat : matList) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (park[i][j].equals("-1")) {
                        // mat 크기의 돗자리를 깔 수 있는지 확인
                        if (isMatPlacementValid(park, i, j, mat, rows, cols)) {
                            return mat;
                        }
                    }
                }
            }
        }
        return -1;
    }

    private boolean isMatPlacementValid(String[][] park, int i, int j, int mat, int rows, int cols) {
        if (i + mat <= rows && j + mat <= cols) { // 범위 체크
            for (int x = i; x < i + mat; x++) {
                for (int y = j; y < j + mat; y++) {
                    if (!park[x][y].equals("-1")) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
}
