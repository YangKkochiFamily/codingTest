package programmers.pccp.find_collision.thseo;

import java.util.*;

//https://school.programmers.co.kr/learn/courses/30/lessons/340211
public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();

        int solution = s.solution(new int[][]{{3, 2}, {6, 4}, {4, 7}, {1, 4}}, new int[][]{{4, 2}, {1, 3}, {4, 2}, {4, 3}});
    }

    /*
    [[3, 2], [6, 4], [4, 7], [1, 4]]	[[4, 2], [1, 3], [2, 4]]	1
    [[3, 2], [6, 4], [4, 7], [1, 4]]	[[4, 2], [1, 3], [4, 2], [4, 3]]	9
    [[2, 2], [2, 3], [2, 7], [6, 6], [5, 2]]	[[2, 3, 4, 5], [1, 3, 4, 5]]	0
    ----------------------------------------------------------------
    1번 예제
    1번 로봇 : 4 -> 2로 이동
        1,4 -> 6,4
        2,4 -> 3,4 -> 4,4 -> 5,4 -> 6,4
    2번 로봇 : 1 -> 3로 이동
        3,2 -> 4,7
        아래로 먼저 이동!
        4,2 -> 4,3 -> 4,4 -> > 4,5 -4,6 -> 4,7
    3번 로봇 : 2 -> 4로 이동
        6,4 -> 1,4
        아래로 먼저 이동!
        5,4 -> 4,4 -> 3,4 -> 2,4 -> 1,4
    ----------------------------------------------------------------
    3번 예제
    1번 로봇 : 2->3->4->5로 이동
        2,3 -> 2,7 -> 6,6 -> 5,2
            2->3 이동 : 2,4 -> 2,5 -> 2,6 -> 2,7
            3->4 이동 : 3,7 -> 4,7 -> 5,7 -> 6,7 -> 6,6
    2번 로봇 : 1->3->4->5로 이돋ㅇ
        2,2 -> 2,7 -> 6,6 -> 5,2

    ======================================================================
    point 배열
    point[4] -> point[2]
    int row = point[4][0], column = point[4][1]
    -> point[6][0], point[6][1]

    초마다 모든 로봇의 경로를 검사할지??
    로봇을 객체로 만들어서 경로를 가지고 있다면?
    List<Robot>
    while -> pint -> poinit로 도착하면 끝
    충돌을 검사하는건, 맵을 하나 더 만들어서 체크 -> int[101][101]
    *
    * */
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;

        // 로봇 초기화
        List<Robot> robotList = initRobotRoute(points, routes);

        while (true) {
            boolean allFinished = true;

            // 현재 시간에 방문한 좌표를 기록하기 위한 Map
            Map<String, Integer> collisionMap = new HashMap<>();

            for (Robot robot : robotList) {
                if (!robot.isFinished()) {
                    allFinished = false;

                    // 로봇 이동
                    robot.nextSec(points);

                    //충돌을 기록하는 로직 필요
                    String positionKey = robot.getPositionKey();
                    collisionMap.put(positionKey, collisionMap.getOrDefault(positionKey, 0) + 1);
                }
            }

            for (int count : collisionMap.values()) {
                if (count > 1) {
                    answer += 1;
                }
            }


            // 모든 로봇이 경로를 완료하면 종료
            if (allFinished) {
                break;
            }
        }

        System.out.println(answer);
        return answer;
    }

    private List<Robot> initRobotRoute(int[][] points, int[][] routes) {
        List<Robot> robotList = new ArrayList<>();
        int count = 0;
        for (int[] route : routes) {
            Queue<Integer> routeQueue = routeToQueue(route);

            robotList.add(new Robot(routeQueue, count++ + ""));
        }
        return robotList;
    }

    private Queue<Integer> routeToQueue(int[] route) {
        Queue<Integer> routeQueue = new LinkedList<>();
        for (int r : route) {
            routeQueue.add(r);
        }
        return routeQueue;
    }

    static class Robot {
        private int[] currentPoint;
        private int[] nextPoint;
        private Queue<Integer> routeQueue = new LinkedList<>();
        private boolean finished = false;

        private String robotName;//객체 알아보기 위해

        public Robot(Queue<Integer> routeQueue, String num) {
            //로봇에 이동을 위한 경로를 넣어둠.
            this.routeQueue.addAll(routeQueue);
            robotName = "robot" + num;
        }

        public boolean isFinished() {
            return finished;
        }

        public void nextSec(int[][] points) {
            if (finished) return;

            if (this.currentPoint == null) {
                //처음 움직일 때
                currentPoint = points[this.routeQueue.poll() - 1].clone(); //clone으로 깊은 복사
                nextPoint = points[this.routeQueue.poll() - 1].clone(); //clone으로 깊은 복사
                return;
            }

            if (currentPoint[0] != nextPoint[0]) {
                currentPoint[0] += (nextPoint[0] > currentPoint[0]) ? 1 : -1;
            } else if (currentPoint[1] != nextPoint[1]) {
                currentPoint[1] += (nextPoint[1] > currentPoint[1]) ? 1 : -1;
            }

            if (currentPoint[0] == nextPoint[0] && currentPoint[1] == nextPoint[1]) {
                // 목적지 도착 시 다음 포인트로 이동
                if (this.routeQueue.isEmpty()) {
                    finished = true; // 이동 종료
                    return;
                }
                nextPoint = points[this.routeQueue.poll() - 1].clone();
            }
        }

        public String getPositionKey() {
            return currentPoint[0] + "," + currentPoint[1];
        }
    }
}

