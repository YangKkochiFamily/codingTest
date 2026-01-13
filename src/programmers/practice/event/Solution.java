package programmers.practice.event;

import java.util.HashMap;
import java.util.Map;

//https://school.programmers.co.kr/learn/courses/30/lessons/131127
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;

        //discount 정렬하면 안되고
        //discount 무조건 맨 마지막까지는 봐야됨

        //want - number Map으로
        Map<String, Integer> wantMap = new HashMap<>();
        for(int i =0; i<want.length; i++){
            wantMap.put(want[i], number[i]);
        }

        // 1. 바깥쪽 루프의 종료 조건을 수정합니다. (<=)
        for(int i=0; i <= discount.length - 10; i++){

            // 2. 10일짜리 윈도우를 확인할 때마다 사용할 임시 맵을 생성합니다.
            Map<String, Integer> discountMap = new HashMap<>();

            // 3. i번째 날부터 10일 동안의 할인 품목을 집계합니다.
            for(int j=0; j<10; j++){ //10일연속
                String discountProduct = discount[i+j];
                discountMap.put(discountProduct, discountMap.getOrDefault(discountProduct, 0) + 1);
            }

            // 4. 10일간의 집계가 끝난 후, 원하는 물품/수량과 일치하는지 확인합니다.
            boolean isMatch = true;
            for (String key : wantMap.keySet()) {
                // 원하는 물품의 수량과, 10일간 할인한 수량이 다르거나
                // 아예 할인한 적이 없다면 isMatch는 false가 됩니다.
                if (!wantMap.get(key).equals(discountMap.get(key))) {
                    isMatch = false;
                    break; // 하나라도 다르면 더 볼 필요 없이 종료
                }
            }

            // 5. 모든 원하는 물품과 수량이 정확히 일치했다면 정답을 1 증가시킵니다.
            if (isMatch) {
                answer++;
            }
        }


        return answer;
    }
}