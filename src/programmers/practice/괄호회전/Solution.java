package programmers.practice.괄호회전;


import java.util.EmptyStackException;
import java.util.Map;
import java.util.Stack;

public class Solution {

    private static final char SQUARE_BRACKET_CLOSE =']';
    private static final char CURLY_BRACKET_CLOSE ='}';
    private static final char PARENTHESIS_CLOSE =')';

    private static final Map<Character, Character> BRACKET_PAIRS = Map.of(
         '[',']',
         '{','}',
         '(',')'
    );
    public int solution(String s) {
        int answer = 0;
        StringBuilder bracketString = new StringBuilder(s);

        if(isAllRightBracket(bracketString))
        {
            answer++;
        }
        for(int i=0; i<s.length()-1; i++){
            if(isAllRightBracket(bracketString.append(bracketString.charAt(0)).deleteCharAt(0)))
                answer++;
        }

        return answer;
    }

    private boolean isAllRightBracket(StringBuilder bracketString) {

        //가장 처음이 CLOSE 괄호이면 틀림
        if(bracketString.charAt(0) == SQUARE_BRACKET_CLOSE || bracketString.charAt(0) == CURLY_BRACKET_CLOSE || bracketString.charAt(0) == PARENTHESIS_CLOSE){
            return false;
        }

        Stack<Character> stack = new Stack<>();

        //시작이 열린 괄호인 보장되는 문자열이고,
        //열린 괄호일 때 스택에 넣고, 닫힌 괄호일때  POP해서 마지막에 남아있는 괄호가 있으면 틀림

        for(int i=0; i<bracketString.length(); i++){
            char c = bracketString.charAt(i);

            try {

                if(c == SQUARE_BRACKET_CLOSE || c == CURLY_BRACKET_CLOSE || c == PARENTHESIS_CLOSE){
                    if (validBracket(stack.peek(), c)) {
                        //올바른 짝이 맞는 경우
                        stack.pop();
                    }
                    else{
                        //올바른 짝이 아닌 경우
                        return false;
                    }

                }
                else{
                    stack.push(c);
                }
            }
            catch (EmptyStackException e){
                //엉뚱한 괄호가 나와서 peek할 때 예외가 발생하는 경우
                return false;
            }
        }

        if(!stack.isEmpty()){
            //남아있는 괄호가 있는 경우
            return false;
        }


        return true;
    }

    /*
    * 괄호가 올바른 짝인지 구분한다*/
    private boolean validBracket(Character peek, char c) {
        return BRACKET_PAIRS.get(peek).equals(c);
    }
}

