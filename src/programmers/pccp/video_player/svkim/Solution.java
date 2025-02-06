package programmers.pccp.video_player.svkim;

public class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = pos;

        for(String command: commands) {
            if(betweenOpening(op_start, op_end, answer)) {
                answer = op_end;
            }

            switch(command) {
                case "prev":
                    answer = goPrev(answer, "00:00");
                    break;
                case "next":
                    answer = goNext(answer, video_len);
                    break;
            }

            if(betweenOpening(op_start, op_end, answer)) {
                answer = op_end;
            }
        }

        return answer;
    }

    public String goPrev(String pos, String minPos) {
        int times = parseToInt(pos) - 10;
        if(times < 0) return minPos;

        return formatTime(times);
    }

    public String goNext(String pos, String maxPos) {
        int times = parseToInt(pos) + 10;
        if(times > parseToInt(maxPos)) return maxPos;

        return formatTime(times);
    }

    public boolean betweenOpening(String start, String end, String pos) {
        return parseToInt(pos) >= parseToInt(start) && (parseToInt(end) >= parseToInt(pos));
    }

    public int parseToInt(String times) {
        String[] times_array = times.split(":");
        return Integer.parseInt(times_array[0]) * 60 + Integer.parseInt(times_array[1]);
    }

    public String formatTime(int times) {
        return String.format("%02d:%02d", times / 60, times % 60);
    }
}
