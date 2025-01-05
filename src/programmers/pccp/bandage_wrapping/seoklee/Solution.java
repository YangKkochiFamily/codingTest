package programmers.pccp.bandage_wrapping.seoklee;

class Solution {

  static int maxHealth;
  static int x;
  static int t;
  static int y;

  public static void main(String[] args) {
    Solution solution = new Solution();

    System.out.println(
        5 == solution.solution(new int[]{5, 1, 5}, 30, new int[][]{{2, 10}, {9, 15}, {10, 5}, {11, 5}})
    );
    System.out.println(
        -1 == solution.solution(new int[]{3, 2, 7}, 20, new int[][]{{1, 15}, {5, 16}, {8, 6}})
    );
    System.out.println(
        -1 == solution.solution(new int[]{4, 2, 7}, 20, new int[][]{{1, 15}, {5, 16}, {8, 6}})
    );
    System.out.println(
        3 == solution.solution(new int[]{1, 1, 1}, 5, new int[][]{{1, 2}, {3, 2}})
    );

  }

  public int solution(int[] bandage, int health, int[][] attacks) {
    maxHealth = health;
    x = bandage[1];
    t = bandage[0];
    y = bandage[2];
    int attackIdx = 0;
    int count = 0;

    for (int i = attacks[0][0]; i <= attacks[attacks.length - 1][0]; i++) {
      if (attacks[attackIdx][0] == i) {
        health -= attacks[attackIdx][1];
        attackIdx++;
        count = 0;
        if (health <= 0) {
          return -1;
        }
      } else {
        health = healing(health, x);
        count++;
        if (count == t) {
          health = healing(health, y);
          count = 0;
        }

      }
    }
    return health;
  }

  public int healing(int health, int heal) {
    if (maxHealth <= health + heal) {
      return maxHealth;
    }
    return health + heal;
  }
}