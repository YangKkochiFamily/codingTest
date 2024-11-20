package pccp.find_collision.seoklee;

import java.util.*;
import java.util.stream.*;

public class Solution {

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
