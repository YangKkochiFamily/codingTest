package programmers.pccp.bandage_wrapping.thseo;

//https://school.programmers.co.kr/learn/courses/30/lessons/250137?language=java

/*
 * 공격 - 2초 - 10
 *   치료가 됐냐? 치료 중이냐?
 *   치료중 2 < 5
 *       -> 치료중0 -> 체력 10 깍기
 *   치료가 됐다 9 - 2 > 5
 *
 *   1초 -> 3 > 1
 *       -> 치료중 -> 15 깍기
 *   5초 -> 5-1-1=3 >= 3
 *       -> 치료 됨 -> 16 깍기 + 3초 *2회복 +7
 *   8초 -> 8 -5 -1 =2 >= 3
 *       -> 치료중 -> 6깍기 2초*2 회복
 *
 * */
public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
//        System.out.println(s.solution(new int[]{5,1,5}, 30, new int[][]{{2, 10},{9,15}, {10,5}, {11,5}}));
//        System.out.println(s.solution(new int[]{4,2,7}, 20, new int[][]{{1, 15},{5,16}, {8,6}}));
        System.out.println(s.solution(new int[]{1,1,1}, 20, new int[][]{{1, 5},{4,1}}));
    }

    public static int healingTime;
    public static int healPerSec;
    public static int plusHeal;
    public int solution(int[] bandage, int health, int[][] attacks) {
        Player player  = new Player(health);

        healingTime = bandage[0];
        healPerSec = bandage[1];
        plusHeal = bandage[2];

        int preAttackTime = 0;
        for (int i = 0; i < attacks.length; i++) {

            player.heal((attacks[i][0] - preAttackTime -1) * healPerSec);
            player.heal(((attacks[i][0]-preAttackTime - 1)/healingTime) * plusHeal);
            preAttackTime = attacks[i][0];

            player.attacked(attacks[i][1]);

            if (player.getNowHealth() <= 0) {
                return -1;
            }

        }

        return player.getNowHealth();
    }

    private class Player {
        int nowHealth;
        int maxHealth;

        private Player(int health) {
            nowHealth = health;
            maxHealth = health;
        }

        private void heal(int health) {
            nowHealth = Math.min(nowHealth + health, maxHealth);
        }

        private void attacked(int damage) {
            nowHealth -= damage;
        }
        private int getNowHealth() {
            return nowHealth;
        }

    }
}
