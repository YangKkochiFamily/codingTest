package pccp.find_collision;

import pccp.find_collision.seoklee.Solution;

public class Sample {
  static int [][] point1 = new int [][] {{3, 2}, {6, 4}, {4, 7}, {1, 4}};
  static int [][] routes1 = new int [][] {{4, 2}, {1, 3}, {2, 4}};

  static int [][] point2 = new int [][]  {{3, 2}, {6, 4}, {4, 7}, {1, 4}};
  static int [][] routes2 = new int [][] {{4, 2}, {1, 3}, {4, 2}, {4, 3}};

  static int [][] point3 = new int [][] {{2, 2}, {2, 3}, {2, 7}, {6, 6}, {5, 2}};
  static int [][] routes3 = new int [][] {{2, 3, 4, 5}, {1, 3, 4, 5}};

  public static void main(String[] args) {
    Solution solution = new Solution();
    System.out.println(solution.solution(point1, routes1)); // 1 
    System.out.println(solution.solution(point2, routes2)); // 9
    System.out.println(solution.solution(point3, routes3)); // 0

  }
}
