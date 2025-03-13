package programmers.pccp.most_gifts.mjlee;

import java.util.*;

class Solution {
    public static int solution(String[] friends, String[] gifts) {
        Map<String, Map<String, Integer>> history = new HashMap<>();//준사람, 받은사람, 갯수
        Map<String, Integer> giftScore = new HashMap<>();//선물지수
        Map<String, Integer> nextGift = new HashMap<>();//다음달 받을 선물

        for (String friend : friends) {
            history.put(friend, new HashMap<>());
            giftScore.put(friend, 0);
            nextGift.put(friend, 0);
        }

        for(String gift : gifts) {
            String[] splitGift = gift.split(" ");
            String giver = splitGift[0];
            String receiver = splitGift[1];

            // 주고받은 횟수 기록
            history.get(giver).put(receiver, history.get(giver).getOrDefault(receiver, 0) + 1);

            // 선물지수 계산 (준 개수 - 받은 개수)
            giftScore.put(giver, giftScore.get(giver) + 1);
            giftScore.put(receiver, giftScore.get(receiver) - 1);
        }

        for (String giver : friends) {
            for (String receiver : friends) {
                if(giver.equals(receiver)) continue;

                int given = history.get(giver).getOrDefault(receiver, 0);
                int received = history.get(receiver).getOrDefault(giver, 0);

                if (given > received) {
                    nextGift.put(giver, nextGift.getOrDefault(giver, 0) + 1);
                } else if (given == received) {
                    int giverScore = giftScore.get(giver);
                    int receiverScore = giftScore.get(receiver);
                    if (giverScore > receiverScore) {
                        nextGift.put(giver, nextGift.getOrDefault(giver, 0) + 1);
                    }
                }
            }
        }

        return Collections.max(nextGift.values());
    }

    public static void main(String[] args) {
        String[] friends = new String[]{"joy", "brad", "alessandro", "conan", "david"};
        String[] gifts = new String[]{"alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"};

        solution(friends, gifts);
    }
}