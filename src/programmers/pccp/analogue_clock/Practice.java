package programmers.pccp.analogue_clock;

import programmers.pccp.analogue_clock.seoklee.Solution;

public class Practice
{

    public static void main(String[] args)
    {
        /**
         * 시침, 분침, 초침이 있는 아날로그시계가 있습니다. 시계의 시침은 12시간마다, 분침은 60분마다, 초침은 60초마다 시계를 한 바퀴 돕니다.
         * 따라서 시침, 분침, 초침이 움직이는 속도는 일정하며 각각 다릅니다. 이 시계에는 초침이 시침/분침과 겹칠 때마다 알람이 울리는 기능이 있습니다.
         * 당신은 특정 시간 동안 알람이 울린 횟수를 알고 싶습니다.
         */
        Solution solution = new Solution();
        System.out.println("result : ");
        System.out.println(2 == solution.solution(0, 5, 30, 0, 7, 0)); // 2
        System.out.println(1 == solution.solution(12, 0, 0, 12, 0, 30)); // 1
        System.out.println(0 == solution.solution(0, 6, 1, 0, 6, 6)); // 0
        System.out.println(1 == solution.solution(11, 59, 30, 12, 0, 0)); // 1
        System.out.println(1 == solution.solution(11, 58, 59, 11, 59, 0)); // 1
        System.out.println(2 == solution.solution(1, 5, 5, 1, 5, 6)); // 2
        System.out.println(2852 == solution.solution(0, 0, 0, 23, 59, 59)); // 2852
    }

}
