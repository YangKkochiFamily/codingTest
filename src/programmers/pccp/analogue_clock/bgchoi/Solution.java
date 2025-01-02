package programmers.pccp.analogue_clock.bgchoi;

class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        // 시작 시간과 종료 시간을 초 단위로 변환
        int startSeconds = h1 * 3600 + m1 * 60 + s1;
        int endSeconds = h2 * 3600 + m2 * 60 + s2;

        int alarmCount = 0;

        // 초침, 분침, 시침의 각도 계산을 위한 상수
        double secondHandSpeed = 6.0; // 초침: 360도 / 60초
        double minuteHandSpeed = 0.1; // 분침: 360도 / 3600초
        double hourHandSpeed = 1.0 / 120.0; // 시침: 360도 / 43200초

        // 시작 시각이 0시 또는 12시 정각인 경우 미리 카운트 추가
        if (startSeconds == 0 || startSeconds == 12 * 3600) {
            alarmCount++;
        }

        while (startSeconds < endSeconds) {
            // 현재 초에 대한 각도 계산
            double secondAngle = (startSeconds * secondHandSpeed) % 360;
            double minuteAngle = (startSeconds * minuteHandSpeed) % 360;
            double hourAngle = (startSeconds * hourHandSpeed) % 360;

            // 다음 초의 각도 계산 (360도가 0도로 바뀌는 문제 방지)
            double nextSecondAngle = ((startSeconds + 1) * secondHandSpeed) % 360;
            double nextMinuteAngle = ((startSeconds + 1) * minuteHandSpeed) % 360;
            double nextHourAngle = ((startSeconds + 1) * hourHandSpeed) % 360;

            if (nextSecondAngle == 0) nextSecondAngle = 360;
            if (nextMinuteAngle == 0) nextMinuteAngle = 360;
            if (nextHourAngle == 0) nextHourAngle = 360;

            // 초침과 시침이 겹치는 경우
            if (secondAngle < hourAngle && nextSecondAngle >= nextHourAngle) {
                alarmCount++;
            }

            // 초침과 분침이 겹치는 경우
            if (secondAngle < minuteAngle && nextSecondAngle >= nextMinuteAngle) {
                alarmCount++;
            }

            // 초침, 분침, 시침이 동시에 겹치는 경우 중복 카운트 제거
            if (nextSecondAngle == nextMinuteAngle && nextMinuteAngle == nextHourAngle) {
                alarmCount--;
            }

            // 초 단위 증가
            startSeconds++;
        }

        return alarmCount;
    }
}
