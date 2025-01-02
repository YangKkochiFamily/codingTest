package programmers.pccp.find_collision.mjlee;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Solution {
    static final int[] dr = {1,0,-1,0};
    static final int[] dc = {0,1,0,-1};

    public int solution(int[][] land) {
        int answer = 0;
        int x = land.length;
        int y = land[0].length;
        Map<Integer,Integer> map = new HashMap<>();

        boolean[][] visited = new boolean[x][y];
        Queue<int[]> q = new ArrayDeque<>();
        int cnt=0;
        int idx=2;
        for(int r=0; r<x; r++){

            for(int c=0; c<y; c++){
                if(land[r][c]!=1) continue;
                if(!visited[r][c] && land[r][c]==1){
                    cnt=1;
                    q.offer(new int[]{r,c});
                    visited[r][c] = true;
                    land[r][c]=idx;
                    while(!q.isEmpty()){
                        int[] poll = q.poll();
                        int rr = poll[0];
                        int cc = poll[1];
                        for(int i=0; i<4; i++){
                            int nr = rr+dr[i];
                            int nc = cc+dc[i];
                            if(nr< 0 || nc<0 || nr>=x || nc>=y || visited[nr][nc] || land[nr][nc]==0) continue;
                            cnt++;
                            q.offer(new int[]{nr,nc});
                            visited[nr][nc] = true;
                            land[nr][nc]=idx;
                        }
                    }

                }
                map.put(idx++, cnt);
            }

        }

        for(int i=0; i<y; i++){
            int max=0;
            Set<Integer> set = new HashSet<>();
            for(int r=0; r<x; r++){
                if(land[r][i]>1){
                    set.add(land[r][i]);
                }
            }

            for(Integer s  : set){
                max+=map.get(s);
            }
            answer= Math.max(answer,max);
        }

        return answer;
    }
}

