package programmers.practice.trasform_num.thseo;
//https://school.programmers.co.kr/learn/courses/30/lessons/154538
/*자연수 x를 y로 변환하려고 합니다. 사용할 수 있는 연산은 다음과 같습니다.

x에 n을 더합니다
x에 2를 곱합니다.
x에 3을 곱합니다.
자연수 x, y, n이 매개변수로 주어질 때, x를 y로 변환하기 위해 필요한 최소 연산 횟수를 return하도록 solution 함수를 완성해주세요.
이때 x를 y로 만들 수 없다면 -1을 return 해주세요.
*/public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(10, 40, 5)); // 예시 출력
    }

    public int solution(int x, int y, int n) {
        int result = calculate(x, y, n, 0);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private int calculate(int x, int y, int n, int count) {
        if (x > y) return Integer.MAX_VALUE;
        if (x == y) return count;

        int addN = calculate(x + n, y, n, count + 1);
        int multiply2 = calculate(x * 2, y, n, count + 1);
        int multiply3 = calculate(x * 3, y, n, count + 1);

        return Math.min(addN, Math.min(multiply2, multiply3));
    }
}