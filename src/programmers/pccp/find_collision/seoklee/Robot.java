package programmers.pccp.find_collision.seoklee;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Robot {

  private Point now = new Point(0, 0);
  private int finishPointIdx = 1;
  private List<Point> points;
  private boolean isFinished = false;

  public Robot (int[][] points, int[] robotRoutes) {
    this.points = Arrays.stream(robotRoutes)
        .boxed()
        .map(robotRoute -> new Point(points[robotRoute - 1][0], points[robotRoute - 1][1]))
        .collect(Collectors.toList());
    this.now = this.points.get(0);
    //System.out.println(this.points);
  }

  public void move() {
    if (points.size() == finishPointIdx)  {
      isFinished = true;
      return;
    }

    Point target = points.get(finishPointIdx);

    if (target.y != now.y) {
      now.y = (target.y > now.y) ? now.y + 1 : now.y - 1;
    } else {
      now.x = (target.x > now.x) ? now.x + 1 : now.x - 1;
    }
    if (target.equals(now)) {
      finishPointIdx += 1;
    }

  }

  public Point getPoint() {
    return now;
  }


  public boolean isNotFinished() {
    return !isFinished;
  }

}
