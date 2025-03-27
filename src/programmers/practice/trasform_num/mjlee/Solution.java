package programmers.practice.trasform_num.mjlee;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Solution {
    public int solution(int x, int y, int n) {
        if (x == y) return 0; // 이미 x와 y가 같다면 연산이 필요 없음 (최소 횟수 = 0)

        Queue<Integer> queue = new LinkedList<>(); // BFS 탐색을 위한 큐
        Set<Integer> visited = new HashSet<>(); // 방문한 숫자를 저장하여 중복 탐색 방지
        queue.offer(x); // 초기값 x를 큐에 추가
        visited.add(x); // x 방문 처리

        int count = 0; // 연산 횟수 카운트 (BFS 깊이)
        while (!queue.isEmpty()) { // 큐가 빌 때까지 반복 (BFS 탐색)
            int size = queue.size(); // 현재 레벨의 노드 개수 (한 번의 연산 단계)
            for (int i = 0; i < size; i++) { // 현재 레벨에 있는 모든 노드 처리
                int num = queue.poll(); // 큐에서 현재 숫자 꺼내기

                // 가능한 연산: num + n, num * 2, num * 3
                for (int next : new int[]{num + n, num * 2, num * 3}) {
                    if (next == y) return count + 1; // 목표 값(y)에 도달하면 연산 횟수 반환
                    if (next < y && visited.add(next)) // next가 y보다 작고 방문하지 않았다면
                        queue.offer(next); // 큐에 추가하여 다음 탐색 진행
                }
            }
            count++; // 한 단계의 BFS 레벨이 끝나면 연산 횟수 증가
        }
        return -1; // BFS를 모두 탐색해도 y를 만들 수 없는 경우 -1 반환
    }
}