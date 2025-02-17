package programmers.pccp.park.bgchoi;

import java.util.Arrays;

class Solution {
    public int solution(int[] mats, String[][] park) {
        // 큰 크기의 돗자리부터 확인
        Arrays.sort(mats);
        for (int i = mats.length - 1; i >= 0; i--) {
            int size = mats[i];

            // 가능한 위치 찾기
            for (int parkX = 0; parkX <= park.length - size; parkX++) {
                for (int parkY = 0; parkY <= park[0].length - size; parkY++) {
                    boolean place = true;

                    // size x size 범위를 체크 ex) 5 이면 0 ~ 4까지 체크 하면서 사람이 있으면 다음으로 바로 넘어가게 1~5
                    for (int x = parkX; x < parkX + size; x++) {
                        for (int y = parkY; y < parkY + size; y++) {
                    //for (int x = parkX; x < parkX + size && x < park.length; x++) {
                      //  for (int y = parkY; y < parkY + size && y < park[0].length; y++) {
                            if (!park[x][y].equals("-1")) {
                                place = false;
                                break;
                            }
                        }
                        if (!place) break;
                    }

                    if (place) return size;
                }
            }
        }

        return -1;
    }
}