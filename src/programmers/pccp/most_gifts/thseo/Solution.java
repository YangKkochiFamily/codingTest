package programmers.pccp.most_gifts.thseo;
//https://school.programmers.co.kr/learn/courses/30/lessons/258712

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Solution {
    static Map<String, Friend> friendMap;

    public static void main(String[] args) {
        Solution solution = new Solution();
//        solution.solution(new String[] {"muzi", "ryan", "frodo", "neo"}, new String[]{"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"});
        solution.solution(new String[]{"joy", "brad", "alessandro", "conan", "david"}, new String[]{"alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"});

    }

    public int solution(String[] friends, String[] gifts) {
        friendMap = Friend.toMap(friends);

        for (String gift : gifts) {
            giveGift(gift);
        }

        calculateNextGift();


        return maxNextGift();
    }

    private int maxNextGift() {
        return friendMap.values().stream()
                .mapToInt(Friend::getNextGift)
                .max()
                .orElse(0);

    }

    private void calculateNextGift() {
        friendMap.values().forEach(my -> {
            Map<String, Integer> sentHistory = my.sentHistory; //내가 친구들 각각 선물을 보낸 내역들
            sentHistory.entrySet().forEach(sent -> {
                Friend friend = friendMap.get(sent.getKey()); //내가 선물을 보낸 친구
                if (sent.getValue() > friend.sentHistory.get(my.name)) {//내가 친구에게 보낸 선물의 수 > 친구가 나에게 선물을 보낸 수
                    my.nextGift++;
                } else if ((sent.getValue() == 0 && friend.sentHistory.get(my.name) == 0) //서로 보낸게 없거나
                        || Objects.equals(sent.getValue(), friend.sentHistory.get(my.name))) {// 서로 보낸 선물의 수가 같으면
                    if (my.giftPoint > friend.giftPoint) {
                        my.nextGift++;
                    }
                }
            });
        });
    }


    private void giveGift(String gift) {
        String[] split = gift.split(" ");
        String from = split[0];
        String to = split[1];

        Friend fromFriend = friendMap.get(from);
        fromFriend.send(to);

        Friend toFriend = friendMap.get(to);
        toFriend.receive();
    }

    /*
     * 선물을 보내는 객체(나 자신)
     * */
    static class Friend {
        private final String name;
        private int totalSendCount;
        private int totalReceiveCount;
        private int giftPoint;
        private final Map<String, Integer> sentHistory = new HashMap<String, Integer>();

        private int nextGift;

        public Friend(String name, String[] friends) {
            this.name = name;

            setSentHistory(friends);
        }

        //친구들 별로 내가 선물을 보낸 내역들을 0으로 초기화
        //key : 친구 이름
        //value : 선물을 보낸 횟수
        public void setSentHistory(String[] friends) {
            Arrays.stream(friends)
                    .filter(name -> !this.name.equals(name))
                    .forEach(name ->
                            this.sentHistory.put(name, 0));
        }

        //친구 배열을 각각 객체로 만들고, 각각 이름과 객체로 map을 만ㄴ듬
        public static Map<String, Friend> toMap(String[] friends) {
            return Arrays.stream(friends)
                    .map(name ->
                            new Friend(name, friends))
                    .collect(
                            Collectors.toMap(
                                    friend -> friend.name,
                                    friend -> friend));
        }

        public void send(String toFriend) {
            totalSendCount++;
            giftPoint++;

            sentHistory.put(toFriend, sentHistory.get(toFriend) + 1);
        }

        public void receive() {
            totalReceiveCount++;
            giftPoint--;
        }

        public int getNextGift() {
            return nextGift;
        }
    }
}
