package programmers.pccp.video_player.mjlee;

class Solution {
    public static String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int video_len_num = convertToSeconds(video_len);
        int pos_num = convertToSeconds(pos);
        int op_start_num = convertToSeconds(op_start);
        int op_end_num = convertToSeconds(op_end);

        for (String command : commands) {
            if (op_start_num <= pos_num && pos_num <= op_end_num) {
                pos_num = op_end_num;
            }

            if (command.equals("next")) {
                pos_num = Math.min(pos_num + 10, video_len_num);
            } else if (command.equals("prev")) {
                pos_num = Math.max(pos_num - 10, 0);
            }

            if (op_start_num <= pos_num && pos_num <= op_end_num) {
                pos_num = op_end_num;
            }
        }

        return convertToTime(pos_num);
    }

    public static int convertToSeconds(String time) {
        String[] parts = time.split(":");
        int seconds = 0;

        if (parts.length == 2) {
            int minutes = Integer.parseInt(parts[0]);
            int sec = Integer.parseInt(parts[1]);
            seconds = (minutes * 60) + sec;
        }

        return seconds;
    }

    public static String convertToTime(int seconds) {
        int remainingSeconds = seconds % 3600;
        int minutes = remainingSeconds / 60;
        int secs = remainingSeconds % 60;

        return String.format("%02d:%02d", minutes, secs); // MM:SS 형식
    }
    public static void main(String[] args) {
        String[] commands = new String[]{"prev"};

        solution( "30:00", "01:05", "01:00", "01:30", commands);
    }
}