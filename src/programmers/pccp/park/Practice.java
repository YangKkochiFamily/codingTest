package programmers.pccp.park;

import programmers.pccp.park.svkim.Solution;

import java.util.Objects;

public class Practice {
    public static void main(String[] args) {
        /**
         지민이는 다양한 크기의 정사각형 모양 돗자리를 가지고 공원에 소풍을 나왔습니다.
         공원에는 이미 돗자리를 깔고 여가를 즐기는 사람들이 많아 지민이가 깔 수 있는 가장 큰 돗자리가 어떤 건지 확인하려 합니다.
         예를 들어 지민이가 가지고 있는 돗자리의 한 변 길이가 5, 3, 2 세 종류이고, 사람들이 다음과 같이 앉아 있다면 지민이가 깔 수 있는 가장 큰 돗자리는 3x3 크기입니다.

         지민이가 가진 돗자리들의 한 변의 길이들이 담긴 정수 리스트 mats,
         현재 공원의 자리 배치도를 의미하는 2차원 문자열 리스트 park가 주어질 때 지민이가 깔 수 있는 가장 큰 돗자리의 한 변 길이를 return 하도록 solution 함수를 완성해 주세요.
         아무런 돗자리도 깔 수 없는 경우 -1을 return합니다.
         **/

        Solution solution = new Solution();
        print(3, solution.solution(new int[]{5,3,2}, new String[][]{{"A", "A", "-1", "B", "B", "B", "B", "-1"}, {"A", "A", "-1", "B", "B", "B", "B", "-1"}, {"-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"}, {"D", "D", "-1", "-1", "-1", "-1", "E", "-1"}, {"D", "D", "-1", "-1", "-1", "-1", "-1", "F"}, {"D", "D", "-1", "-1", "-1", "-1", "E", "-1"}}));
    }

    public static void print(int expect, int answer) {
        System.out.println("입력하신 값은 [" + answer + "] 예제의 답은 [" + expect + "] 입니다.");
        if (Objects.equals(expect, answer)) {
            System.out.println("정답입니다.");
        } else {
            System.out.println("다시 확인 해주세요.");
        }
    }
}
