package programmers.practice.cutting_rollcake;

import java.util.*;
//https://school.programmers.co.kr/learn/courses/30/lessons/132265
public class Solution {
    Set<Integer> cheolsu = new HashSet<>();
    Queue<Integer> brother = new LinkedList<>();
    Map<Integer, Integer> brothersToppingCount = new HashMap<>();

    //1 | 2, 1, 3, 1, 4, 1, 2
    //2 - 1개
    //1 - 2개
    //3 - 1
    //4 - 1
    //초기화
    //12 | 1, 3, 1, 4, 1, 2
    //초기화
    //121 | 3, 1, 4, 1, 2

    //1  234
    //12 34
    public void init(int[] topping){
        cheolsu.add(topping[0]);

        for(int i=1; i<topping.length; i++){
            brother.add(topping[i]);
            brothersToppingCount.put(topping[i], brothersToppingCount.getOrDefault(topping[i],0)+1);
        }
    }
    public int solution(int[] topping) {
        int answer = 0;

        init(topping);
        long count =  brothersToppingCount.keySet().stream()
                .filter(key ->  brothersToppingCount.get(key) > 0)
                .count();

        for(int i=1; i<topping.length-1; i++){
            if(isEqualToppingCount(count)){
                // System.out.println(i);
                answer++;
            }

            int brothersTopping = brother.poll();

            cheolsu.add(brothersTopping);
            brothersToppingCount.put(brothersTopping, brothersToppingCount.get(brothersTopping)-1);

            if(brothersToppingCount.get(brothersTopping) == 0){
                count--;
            }
        }

        return answer;
    }
    public boolean isEqualToppingCount(long count){
        return (long) cheolsu.size() == count;
    }
}