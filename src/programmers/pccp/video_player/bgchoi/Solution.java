package programmers.pccp.video_player.bgchoi;
/*
class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String[] video_list = video_len.split(":");
        String[] pos_list = pos.split(":");
        String[] op_list = op_start.split(":");
        String[] op_end_list = op_end.split(":");

        int curMin = Integer.parseInt(pos_list[0]);
        int curSec = Integer.parseInt(pos_list[1]);

        int videoMin = Integer.parseInt(video_list[0]);
        int videoSec = Integer.parseInt(video_list[1]);

        int opStartMin = Integer.parseInt(op_list[0]);
        int opStartSec = Integer.parseInt(op_list[1]);

        int opEndMin = Integer.parseInt(op_end_list[0]);
        int opEndSec = Integer.parseInt(op_end_list[1]);

        // 오프닝 스킵 로직
        if ((curMin > opStartMin || (curMin == opStartMin && curSec >= opStartSec)) &&
                (curMin < opEndMin || (curMin == opEndMin && curSec <= opEndSec))) {
            curMin = opEndMin;
            curSec = opEndSec;
        }
        for (String command : commands) {
            if (command.equals("prev")) {
                if (curSec < 10) {  // 초가 10초 미만이면 분을 줄이고 60 + (curSec - 10)
                    if (curMin > 0) {
                        curMin--;
                        curSec = 60 + (curSec - 10);
                    } else {
                        curSec = 0;  // 이미 0분 0초라면 그대로 유지
                    }
                } else {
                    curSec -= 10;
                }
            } else { // "next"
                curSec += 10;
                if (curSec >= 60) {  // 초가 60을 넘으면 분 증가
                    curSec -= 60;
                    curMin++;
                }
                // 비디오 끝을 초과하면 마지막 위치로 이동
                if (curMin > videoMin || (curMin == videoMin && curSec > videoSec)) {
                    curMin = videoMin;
                    curSec = videoSec;
                }
            }
            // 오프닝 스킵 로직
            if ((curMin > opStartMin || (curMin == opStartMin && curSec >= opStartSec)) &&
                    (curMin < opEndMin || (curMin == opEndMin && curSec <= opEndSec))) {
                curMin = opEndMin;
                curSec = opEndSec;
            }
        }
        //return curMin + ":" + curSec;
        // mm:ss 형식
        return String.format("%02d:%02d", curMin, curSec);
    }
}

 코드 정리 */
class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int[] videoTime = parseTime(video_len);
        int[] curTime = parseTime(pos);
        int[] opStart = parseTime(op_start);
        int[] opEnd = parseTime(op_end);

        // 오프닝 스킵 (초기 위치)
        skipOpening(curTime, opStart, opEnd);

        for (String command : commands) {
            if (command.equals("prev")) {
                if(curTime[1] < 10) {
                    if(curTime[0] > 0) {
                        curTime[0]--;
                        curTime[1] = 50 + curTime[1];
                    } else {
                        curTime[1] = 0;
                    }
                }else {
                    curTime[1] -= 10;
                }
            } else { // "next"
                curTime[1] += 10;
                if (curTime[1] >= 60) {
                    curTime[1] -= 60;
                    curTime[0]++;
                }
            }

            // 비디오 끝을 초과하면 마지막 위치로 이동
            if (curTime[0] > videoTime[0] || (curTime[0] == videoTime[0] && curTime[1] > videoTime[1])) {
                curTime[0] = videoTime[0];
                curTime[1] = videoTime[1];
            }

            // 오프닝 스킵
            skipOpening(curTime, opStart, opEnd);
        }

        return String.format("%02d:%02d", curTime[0], curTime[1]);
    }

    // "mm:ss" 형식을 [분, 초] 배열로 변환하는 함수
    private int[] parseTime(String time) {
        String[] parts = time.split(":");
        return new int[]{Integer.parseInt(parts[0]), Integer.parseInt(parts[1])};
    }

    // 오프닝 구간 체크
    private void skipOpening(int[] curTime, int[] opStart, int[] opEnd) {
        if ((curTime[0] > opStart[0] || (curTime[0] == opStart[0] && curTime[1] >= opStart[1])) &&
                (curTime[0] < opEnd[0] || (curTime[0] == opEnd[0] && curTime[1] <= opEnd[1]))) {
            curTime[0] = opEnd[0];
            curTime[1] = opEnd[1];
        }
    }
}
