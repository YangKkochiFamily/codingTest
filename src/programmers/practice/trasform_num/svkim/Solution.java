package programmers.practice.trasform_num.svkim;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public int solution(int x, int y, int n) {
        int answer = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {x,0});

        while(!queue.isEmpty()) {
            int[] number = queue.poll();
            if(number[0] == y) {
                answer = number[1];
                break;
            }

            if(number[0] > y) {
                continue;
            }

            queue.add(new int[] {number[0]*2, number[1]+1});
            queue.add(new int[] {number[0]*3, number[1]+1});
            queue.add(new int[] {number[0]+n, number[1]+1});
        }

        return answer;
    }
}
