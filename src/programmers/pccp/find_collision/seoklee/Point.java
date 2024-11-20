package programmers.pccp.find_collision.seoklee;

import java.util.Objects;

class Point {
  public int x;
  public int y;

  public Point(int y, int x) {
    this.x = x;
    this.y = y;
  }

  public boolean equals(Object obj) {
    Point oth = (Point) obj;
    return this.x == oth.x && this.y == oth.y;
  }

  public int hashCode() {
    return Objects.hash(x, y);
  }

  public String toString() {
    return x + " : " + y;
  }
}