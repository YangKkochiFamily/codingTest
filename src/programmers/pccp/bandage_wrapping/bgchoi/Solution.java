package programmers.pccp.bandage_wrapping.bgchoi;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int t = bandage[0]; // 붕대 감기 시전 시간
        int x = bandage[1]; // 초당 회복량
        int y = bandage[2]; // 추가 회복량

        int currentHealth = health;
        int consecutiveSuccess = 0;
        int attackIndex = 0;
        int maxTime = attacks[attacks.length - 1][0]; // 마지막 공격후 끝나기 때문에 마지막 시간

        for (int currentTime = 1; currentTime <= maxTime; currentTime++) {
            if (attacks[attackIndex][0] == currentTime) {
                // 공격을 받았을 때
                currentHealth -= attacks[attackIndex][1];
                consecutiveSuccess = 0;
            } else {
                // 공격을 받지 않았을 때
                if (currentHealth < health) {
                    currentHealth = Math.min(currentHealth + x, health);
                }
                consecutiveSuccess++;
                // 추가 회복
                if (consecutiveSuccess == t) {
                    currentHealth = Math.min(currentHealth + y, health);
                    consecutiveSuccess = 0;
                }
            }

            // 캐릭터가 죽었는지 확인
            if (currentHealth <= 0) {
                return -1;
            }
        }

        return currentHealth;
    }
}
