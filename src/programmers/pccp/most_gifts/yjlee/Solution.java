package programmers.pccp.most_gifts.yjlee;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int solution(String[] friends, String[] gifts) {
        Map<String, Map<String, Integer>> giftCounts = new HashMap<>();
        Map<String, Integer> giftScore = new HashMap<>();

        // 주고받은 선물 횟수 기록
        for (String gift : gifts) {
            String[] split = gift.split(" ");
            String giver = split[0];
            String receiver = split[1];

            Map<String, Integer> giverGifts = giftCounts.getOrDefault(giver, new HashMap<>());
            giverGifts.put(receiver, giverGifts.getOrDefault(receiver, 0) + 1);
            giftCounts.put(giver, giverGifts);
        }

        // 선물 지수
        for (String friend : friends) {
            int give = giftCounts.getOrDefault(friend, new HashMap<>()).values().stream().mapToInt(Integer::intValue).sum();
        }

        return 0;
    }
}
