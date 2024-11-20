package programmers.pccp.find_collision.seoklee;

import static programmers.pccp.find_collision.Practice.point1;
import static programmers.pccp.find_collision.Practice.point2;
import static programmers.pccp.find_collision.Practice.point3;
import static programmers.pccp.find_collision.Practice.routes1;
import static programmers.pccp.find_collision.Practice.routes2;
import static programmers.pccp.find_collision.Practice.routes3;

import java.util.*;
import java.util.stream.*;

public class Solution {

  public static void main(String[] args) {
    Solution solution = new Solution();
    System.out.println(solution.solution(point1, routes1)); // 1
    System.out.println(solution.solution(point2, routes2)); // 9
    System.out.println(solution.solution(point3, routes3)); // 0
  }

  public int solution(int[][] points, int[][] routes) {
    int answer = 0;

    List<Robot> robots = Arrays.stream(routes)
        .map(robotRoutes -> new Robot(points, robotRoutes))
        .collect(Collectors.toList());

    while (robots.size() != 0) {
      HashMap<Point, Integer> warningPointMap = new HashMap<Point, Integer>();

      robots.forEach(robot ->
          warningPointMap.put(robot.getPoint(), (warningPointMap.getOrDefault(robot.getPoint(), 0) + 1))
      );

      answer += ((int) warningPointMap.values().stream().filter(samePosRobotCount -> samePosRobotCount > 1).count());
      robots = robots.stream()
          .peek(Robot::move)
          .filter(Robot::isNotFinished)
          .collect(Collectors.toList());
    }
    return answer;
  }


}
