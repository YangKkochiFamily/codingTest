package programmers.pccp.bandage_wrapping.svkim;

public class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int maxHealth = health;

        int healingTime = bandage[0];
        int healthPerSecond = bandage[1];
        int extraHealth = bandage[2];

        int answer = maxHealth;
        int endTime = attacks[attacks.length-1][0];
        int success = 0;
        int index = 0;

        for(int t=1;t<=endTime;t++) {
            if(attacks[index][0] == t) {
                answer -= attacks[index][1];
                index++;
                success = 0;
            } else {
                if(answer < maxHealth) {
                    answer += healthPerSecond;
                    success++;
                } else {
                    continue;
                }

                if(success == healingTime)
                {
                    answer += extraHealth;
                    success = 0;
                }
            }

            if(answer < 0) {
                return -1;
            } else if(answer >= maxHealth) {
                answer = maxHealth;
            }
        }

        return answer == 0 ? -1 : answer;
    }
}
