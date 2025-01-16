package programmers.pccp.bandage_wrapping.yjlee;

public class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int bandagingTime = bandage[0];
        int perRecovery = bandage[1];
        int addRecovery = bandage[2];

        int currentHealth = health;

        currentHealth -= attacks[0][1];
        if (currentHealth <= 0) {
            return -1;
        }

        for (int i = 1; i < attacks.length; i++) {
            int betweenTime = attacks[i][0] - attacks[i - 1][0] - 1;
            int damage = attacks[i][1];

            if (betweenTime > 0) {
                int fullCycles = betweenTime / bandagingTime;
                int remainingTime = betweenTime % bandagingTime;

                currentHealth += fullCycles * (perRecovery * bandagingTime + addRecovery);
                currentHealth += remainingTime * perRecovery;
                currentHealth = Math.min(currentHealth, health);
            }

            currentHealth -= damage;
            if (currentHealth <= 0) {
                return -1;
            }
        }

        return currentHealth;
    }
}
