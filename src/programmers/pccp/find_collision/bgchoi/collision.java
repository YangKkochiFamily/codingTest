package programmers.pccp.find_collision.bgchoi;
import java.util.*;

public class collision {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        ArrayList<ArrayList<int[]>> path = new ArrayList<>();
        for (int i = 0; i < routes.length; i++) {
            path.add(new ArrayList<>());

            int[] point = points[routes[i][0] - 1]; // 첫 위치
            int firstX = point[0];
            int firstY = point[1];
            path.get(i).add(new int[] {firstX, firstY});

            for (int j = 1; j < routes[i].length; j++) {  // 하나의 점이 아닌 중간 단계가 있을수도 있음 ㅠㅠ
                int[] last = points[routes[i][j] - 1]; // 목표 위치
                int lastX = last[0];
                int lastY = last[1];

                while (firstX != lastX) {
                    firstX += (lastX > firstX) ? 1 : -1;
                    path.get(i).add(new int[]{firstX, firstY}); //  x 가 먼저 이므으로 x 부터 위치 조정
                }
                while (firstY != lastY) {
                    firstY += (lastY > firstY) ? 1 : -1;
                    path.get(i).add(new int[]{firstX, firstY}); // y 위치 조정
                }
            }

        }
        int count = 0;
        while (count != routes.length) {
            int[][] map = new int[101][101];  //한번 이동 할때마다 각 로봇 위치
            count = 0;

            for (int i = 0; i < routes.length; i++) {
                if (path.get(i).isEmpty()) {
                    count++;   // 리스트가 없으면 count++ 최종적으로 모든 리스트가 비워질 경우 routes.length와 값이 같기 때문에 while 탈출
                    continue;
                }
                int[] temp = path.get(i).remove(0); // 큐로 풀면 좀 더 좋았을수도......
                map[temp[0]][temp[1]]++;
            }

            for (int i = 0; i < 101; i++) {
                for (int j = 0; j < 101; j++) {
                    if (map[i][j] > 1) answer++;
                }
            }
        }

        return answer;
    }
}
