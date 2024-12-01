package programmers.pccp.oildrilling;

import programmers.pccp.oildrilling.seoklee.Solution;

public class Practice {

  public static int[][] land1 = new int[][]
      {
          {0, 0, 0, 1, 1, 1, 0, 0}
          , {0, 0, 0, 0, 1, 1, 0, 0}
          , {1, 1, 0, 0, 0, 1, 1, 0}
          , {1, 1, 1, 0, 0, 0, 0, 0}
          , {1, 1, 1, 0, 0, 0, 1, 1}
      };//9
  public static int[][] land2 = new int[][]
      {
          {1, 0, 1, 0, 1, 1}
          , {1, 0, 1, 0, 0, 0}
          , {1, 0, 1, 0, 0, 1}
          , {1, 0, 0, 1, 0, 0}
          , {1, 0, 0, 1, 0, 1}
          , {1, 0, 0, 0, 0, 0}
          , {1, 1, 1, 1, 1, 1}
      }; //16

  public static void main(String[] args) {
    /**
     * 세로길이가 n 가로길이가 m인 격자 모양의 땅 속에서 석유가 발견되었습니다.
     * 석유는 여러 덩어리로 나누어 묻혀있습니다. 당신이 시추관을 수직으로 단 하나만 뚫을 수 있을 때, 가장 많은 석유를 뽑을 수 있는 시추관의 위치를 찾으려고 합니다.
     * 시추관은 열 하나를 관통하는 형태여야 하며, 열과 열 사이에 시추관을 뚫을 수 없습니다.
     */
    Solution solution = new Solution();
    System.out.println(solution.solution(land1));
    System.out.println(solution.solution(land2));
  }
}
