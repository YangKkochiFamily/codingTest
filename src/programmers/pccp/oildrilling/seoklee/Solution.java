package programmers.pccp.oildrilling.seoklee;

import java.util.*;


public class Solution {

  private final int checked = -1;
  private int[][] arounds = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
  private int width;
  private int height;
  private Map<Integer, Integer> answerMap;

  public int solution(int[][] land) {
    // init
    width = land.length;
    height = land[0].length;
    answerMap = new HashMap<>();
    for (int x = 0; x < land.length; x++) {
      for (int y = 0; y < land[x].length; y++) {
        if (land[x][y] == 1) {
          bfs(land, new Point(y, x));
        }
      }
    }
    return answerMap.values()
        .stream()
        .mapToInt(i -> i)
        .max()
        .getAsInt();
  }

  private void bfs(int[][] land, Point now) {
    int size = 1;
    Set<Integer> ys = new HashSet<>();
    Queue<Point> queue = new LinkedList<>();
    queue.add(new Point(now.y, now.x));
    ys.add(now.y);
    land[now.x][now.y] = checked;

    while (!queue.isEmpty()) {
      Point next = queue.poll();
      for (int[] around : arounds) {
        Point p = new Point(next.y + around[1],next.x + around[0]);
        if (p.x >= 0
            && p.x < width
            && p.y >= 0
            && p.y < height
            && land[p.x][p.y] == 1) {
          land[p.x][p.y] = checked;
          queue.add(new Point (p.y, p.x));
          ys.add(p.y);
          size++;
        }
      }
    }
    for (int y : ys) {
      answerMap.put(y, answerMap.getOrDefault(y, 0) + size);
    }
  }
}