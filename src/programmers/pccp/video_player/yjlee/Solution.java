package programmers.pccp.video_player.yjlee;

public class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int videoLen = toSeconds(video_len);
        int opStart = toSeconds(op_start);
        int opEnd = toSeconds(op_end);
        int currentPos = skipOpening(toSeconds(pos), opStart, opEnd);

        for (String command : commands) {
            if (command.equals("prev")) {
                currentPos = currentPos < 10 ? 0 : currentPos - 10;
            } else if (command.equals("next")) {
                currentPos = Math.min(currentPos + 10, videoLen);
            }

            currentPos = skipOpening(currentPos, opStart, opEnd);
        }

        return toStringTime(currentPos);
    }

    private int skipOpening(int currentPos, int opStart, int opEnd) {
        if (currentPos >= opStart && currentPos <= opEnd) {
            return opEnd;
        }
        return currentPos;
    }

    private int toSeconds(String time) {
        String[] times = time.split(":");
        int minutes = Integer.parseInt(times[0]);
        int seconds = Integer.parseInt(times[1]);
        return minutes * 60 + seconds;
    }

    private String toStringTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
