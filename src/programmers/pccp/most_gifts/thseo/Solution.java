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
        return friendMap.values().stream().mapToInt(Friend::getNextGift).max().orElse(0);

    }

    private void calculateNextGift() {
        friendMap.values().forEach(my -> {
            Map<String, Integer> sentHistory = my.sentHistory;
            sentHistory.entrySet().forEach(sent -> {
                Friend friend = friendMap.get(sent.getKey());
                if (sent.getValue() > friend.sentHistory.get(my.name)) {
                    my.nextGift++;
                } else if ((sent.getValue() == 0 && friend.sentHistory.get(my.name) == 0) || Objects.equals(sent.getValue(), friend.sentHistory.get(my.name))) {
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

        public void setSentHistory(String[] friends) {
            Arrays.stream(friends).filter(name -> !this.name.equals(name)).forEach(name -> this.sentHistory.put(name, 0));
        }

        public static Map<String, Friend> toMap(String[] friends) {
            return Arrays.stream(friends).map(name -> new Friend(name, friends)).collect(Collectors.toMap(friend -> friend.name, friend -> friend));
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
