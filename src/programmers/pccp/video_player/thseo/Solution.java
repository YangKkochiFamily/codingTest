package programmers.pccp.video_player.thseo;

//https://school.programmers.co.kr/learn/courses/30/lessons/340213?language=java

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();

//        solution.solution("07:22","04:05","00:15","04:07", new String[]{"next"});
        solution.solution("10:55", "00:05", "00:15", "06:55", new String[]{"prev", "next", "next"});
    }
    public static final int SEEK_STEP = 10;
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {

        Video video = new Video(video_len, pos, op_start, op_end);

        for (String command : commands) {
            switch (command) {
                case "next":
                    video.next();
                    break;
                case "prev":
                    video.prev();
                    break;
            }
        }
        return video.positionToString();
    }

    static class Video {
        private final int length;
        private final int openingStart;
        private final int openingEnd;
        private int position;

        public Video(String length, String position, String openingStart, String openingEnd) {
            this.length = stringToSec(length);
            this.position = stringToSec(position);
            this.openingStart = stringToSec(openingStart);
            this.openingEnd = stringToSec(openingEnd);

            checkOpeningAndSetPosition();
        }

        private int stringToSec(String stringSec) {
            String[] split = stringSec.split(":");
            return (Integer.parseInt(split[0]) * 60) + Integer.parseInt(split[1]);
        }

        public String positionToString() {
            return String.format("%02d:%02d", position / 60, position % 60);
        }

        public void checkOpeningAndSetPosition() {
            if (openingStart <= position && position <= openingEnd) {
                setPositionToOpeningEnd();
            }
        }

        public void setPositionToOpeningEnd() {
            this.position = openingEnd;
        }

        public void next() {
            this.position = Math.min(position + SEEK_STEP, this.length);

            checkOpeningAndSetPosition();
        }

        public void prev() {
            this.position = Math.max(position - SEEK_STEP, 0);

            checkOpeningAndSetPosition();
        }
    }
}