package programmers.pccp.analogue_clock.seoklee;

public class Solution {    
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        // 1초가 움직이면 분침은 얼마나 움직이는가? 
        // 60초 ->  6도 1초에 0.1도 움직임 
        // 한칸은 360 / 60 = 6도
        // 1분이 움직이면 시침은 얼마나 움직이는가?
        // 1분에 0.1도 움직임
        // 시침은 1시간 움직일 때 30도 
        // 1분 움직일 때 0.1도 움직임
        // 1초 움직일 때 1 / 120 도 
        
        // 0. 시작할 때 12시 0분 0초인 경우 1 더해야함..
        
        // 1. 시간이 지나간다..
        // 2. 지나쳤나? 
        // 2-1 시침 분침 둘 다 지나칠 수도있음
        // 2-2 둘 다 지나치는데 분침이랑 시침이 같을 수 있음
        
        int answer = 0;
        Time now = new Time(h1 % 12, m1, s1, h1 >= 12);
        Time end = new Time(h2 % 12, m2, s2, h2 >= 12);
        int cnt = 0;
        
        if((now.h == 0 || now.h == 12) && now.m == 0 && now.s == 0) {
            answer++;
        }
            
           
        while(!now.equals(end)) {
            Angles beforeAngle = now.getAngles();
            now.next();
            Angles afterAngle = now.getAfterAngles();
                      
            if(beforeAngle.s < beforeAngle.h && afterAngle.s >= afterAngle.h) {
                answer++;
            }
            
            if(beforeAngle.s < beforeAngle.m && afterAngle.s >= afterAngle.m) {
                answer++;
            }
            
            if(afterAngle.h == afterAngle.m) {
                answer--;
            }
        }
        return answer;
    }
}

