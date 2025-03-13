package programmers.pccp.most_gifts.bgchoi;

import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int n = friends.length;

        // 친구 이름과 인덱스를 매핑
        Map<String, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(friends[i], i);
        }

        // 주고 받은 선물 횟수를 저장
        int[][] giftMatrix = new int[n][n];
        // 각 친구의 선물 지수를 저장할 배열 선물을 주고 받지 않았을때 이걸로 비교해서 선물 부여
        int[] giftIndex = new int[n];

        // 선물 기록
        for (String gift : gifts) {
            String[] parts = gift.split(" ");
            int giver = indexMap.get(parts[0]);
            int receiver = indexMap.get(parts[1]);

            giftMatrix[giver][receiver]++;
            giftIndex[giver]++;
            giftIndex[receiver]--;
        }

        // 다음 달에 받는 선물 횟수를 저장할 배열
        int[] receiveNextMonth = new int[n];

        // 선물 주고 받기 비교
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j ++) { //int j = 0; j < n; j++) { 0 부터 시작 하면 중복 발생
                if (i == j) continue; // 자기 자신과 비교 X

                if (giftMatrix[i][j] > giftMatrix[j][i]) {
                    receiveNextMonth[j]++;
                } else if (giftMatrix[i][j] < giftMatrix[j][i]) {
                    receiveNextMonth[i]++;
                } else {
                    // 선물 횟수가 같을 때만 선물 지수 비교
                    if (giftIndex[i] > giftIndex[j]) {
                        receiveNextMonth[j]++;
                    } else if (giftIndex[i] < giftIndex[j]) {
                        receiveNextMonth[i]++;
                    }
                }
            }
        }

        // 가장 많은 선물을 받는 친구의 선물 수 반환
        return Arrays.stream(receiveNextMonth).max().getAsInt();
    }
}