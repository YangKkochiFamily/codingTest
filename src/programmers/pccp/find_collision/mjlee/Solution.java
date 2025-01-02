package programmers.pccp.find_collision.mjlee;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Solution {
    //dr과 dc는 상, 우, 하, 좌를 표현하는 배열. 이동할 수 있는 방향
    static final int[] dr = {1,0,-1,0};
    static final int[] dc = {0,1,0,-1};

    public int solution(int[][] land) {
        int answer = 0;
        //land의 세로, 가로 길이
        int x = land.length;
        int y = land[0].length;
        Map<Integer,Integer> map = new HashMap<>();

        //방문한 칸을 체크
        boolean[][] visited = new boolean[x][y];
        //BFS를 구현하기 위한 큐
        Queue<int[]> q = new ArrayDeque<>();
        int cnt=0;
        int idx=2;
        for(int r=0; r<x; r++){

            for(int c=0; c<y; c++){
                if(land[r][c]!=1) continue; // 석유가 없는 칸은 넘어간다.
                if(!visited[r][c] && land[r][c]==1){ // 석유 덩어리가 시작되는 칸을 찾았을 때
                    cnt=1;
                    q.offer(new int[]{r,c});
                    visited[r][c] = true;
                    land[r][c]=idx; // 해당 칸을 덩어리 번호로 변경
                    while(!q.isEmpty()){
                        int[] poll = q.poll();
                        int rr = poll[0];
                        int cc = poll[1];
                        for(int i=0; i<4; i++){ // 상, 우, 하, 좌로 인접한 칸을 확인
                            int nr = rr+dr[i];
                            int nc = cc+dc[i];
                            if(nr< 0 || nc<0 || nr>=x || nc>=y || visited[nr][nc] || land[nr][nc]==0) continue;
                            cnt++; // 덩어리에 포함된 석유의 개수 증가
                            q.offer(new int[]{nr,nc});
                            visited[nr][nc] = true;
                            land[nr][nc]=idx; // 해당 칸을 덩어리 번호로 변경
                        }
                    }

                }
                map.put(idx++, cnt); // 덩어리 번호와 크기를 맵에 저장
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
                max+=map.get(s); // 시추관이 지나가는 덩어리들의 석유량 합산
            }
            answer= Math.max(answer,max);
        }

        return answer;
    }
}

