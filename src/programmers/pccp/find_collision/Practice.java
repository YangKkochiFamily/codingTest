package programmers.pccp.find_collision;

import programmers.pccp.find_collision.seoklee.Solution;

public class Practice {
  public static int [][] point1 = new int [][] {{3, 2}, {6, 4}, {4, 7}, {1, 4}};
  public static int [][] routes1 = new int [][] {{4, 2}, {1, 3}, {2, 4}};

  public static int [][] point2 = new int [][]  {{3, 2}, {6, 4}, {4, 7}, {1, 4}};
  public static int [][] routes2 = new int [][] {{4, 2}, {1, 3}, {4, 2}, {4, 3}};

  public static int [][] point3 = new int [][] {{2, 2}, {2, 3}, {2, 7}, {6, 6}, {5, 2}};
  public static int [][] routes3 = new int [][] {{2, 3, 4, 5}, {1, 3, 4, 5}};

  public static void main(String[] args) {
    /**
      어떤 물류 센터는 로봇을 이용한 자동 운송 시스템을 운영합니다. 운송 시스템이 작동하는 규칙은 다음과 같습니다.

      물류 센터에는 (r, c)와 같이 2차원 좌표로 나타낼 수 있는 n개의 포인트가 존재합니다. 각 포인트는 1~n까지의 서로 다른 번호를 가집니다.
      로봇마다 정해진 운송 경로가 존재합니다. 운송 경로는 m개의 포인트로 구성되고 로봇은 첫 포인트에서 시작해 할당된 포인트를 순서대로 방문합니다.
      운송 시스템에 사용되는 로봇은 x대이고, 모든 로봇은 0초에 동시에 출발합니다. 로봇은 1초마다 r 좌표와 c 좌표 중 하나가 1만큼 감소하거나 증가한 좌표로 이동할 수 있습니다.
      다음 포인트로 이동할 때는 항상 최단 경로로 이동하며 최단 경로가 여러 가지일 경우, r 좌표가 변하는 이동을 c 좌표가 변하는 이동보다 먼저 합니다.
      마지막 포인트에 도착한 로봇은 운송을 마치고 물류 센터를 벗어납니다. 로봇이 물류 센터를 벗어나는 경로는 고려하지 않습니다.
      이동 중 같은 좌표에 로봇이 2대 이상 모인다면 충돌할 가능성이 있는 위험 상황으로 판단합니다.
       관리자인 당신은 현재 설정대로 로봇이 움직일 때 위험한 상황이 총 몇 번 일어나는지 알고 싶습니다. 만약 어떤 시간에 여러 좌표에서 위험 상황이 발생한다면 그 횟수를 모두 더합니다.

      운송 포인트 n개의 좌표를 담은 2차원 정수 배열 points와 로봇 x대의 운송 경로를 담은 2차원 정수 배열 routes가 매개변수로 주어집니다.
       이때 모든 로봇이 운송을 마칠 때까지 발생하는 위험한 상황의 횟수를 return 하도록 solution 함수를 완성해 주세요.
     **/
    Solution solution = new Solution();
    System.out.println(solution.solution(point1, routes1)); // 1 
    System.out.println(solution.solution(point2, routes2)); // 9
    System.out.println(solution.solution(point3, routes3)); // 0

  }
}
