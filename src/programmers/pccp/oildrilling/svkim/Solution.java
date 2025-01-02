package programmers.pccp.oildrilling.svkim;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public int solution(int[][] land) {
        int answer = 0;
        boolean[][] visited = new boolean[land.length][land[0].length];

        for(int i = 0; i < land[0].length; i++) {
            int oil = getOil(land, visited, i);

            if(oil > answer) {
                answer = oil;
            }
        }

        return answer;
    }

    public int getOil(int[][] land, boolean[][] visited, int location) {
        //bfs 구현
        int result = 0;
        Queue<int[]> queue = new LinkedList<>();
        int[] start = {location,0};

        for(int i=0; i<land.length; i++) {
            if(land[i][location] == 1) {
                start = new int[]{i, location};
                result++;
                break;
            }
        }

        queue.add(start);
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] node = queue.poll();

            Iterator<int[]> iterator = getAdj(node).listIterator();
            while (iterator.hasNext()) {
                int[] next = iterator.next(); //4,0

                if((next[0] < 0 || next[0] >= land.length) || (next[1] < 0 || next[1] >= land[0].length)) {
                    continue;
                }

                if(!visited[next[0]][next[1]]) {
                    visited[next[0]][next[1]] = true;
                    if(land[next[0]][next[1]] == 1)
                    {
                        queue.add(next);
                        result++;
                    }
                }
            }
        }

        return result;
    }

    public ArrayList<int[]> getAdj(int[] node) {
        ArrayList<int[]> adj = new ArrayList<>();

        adj.add(new int[]{node[0] - 1, node[1]});
        adj.add(new int[]{node[0], node[1] - 1});
        adj.add(new int[]{node[0] + 1, node[1]});
        adj.add(new int[]{node[0], node[1] + 1});

        return adj;
    }
}
