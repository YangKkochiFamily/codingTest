package programmers.pccp.analogue_clock.bgchoi;

class Solution_fail {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int startSeconds = h1 * 3600 + m1 * 60 + s1;
        int endSeconds = h2 * 3600 + m2 * 60 + s2;

        int alarmCount = 0;

        // 초침, 분침, 시침의 각도 계산을 위한 상수
        double secondHandSpeed = 6.0; // 초침: 360도 / 60초
        double minuteHandSpeed = 0.1; // 분침: 360도 / 3600초
        double hourHandSpeed = 1.0 / 120.0; // 시침: 360도 / 43200초

        while (startSeconds < endSeconds) {

            double secondAngle = (startSeconds * secondHandSpeed) % 360;
            double minuteAngle = (startSeconds * minuteHandSpeed) % 360;
            double hourAngle = (startSeconds * hourHandSpeed) % 360;

            if (secondAngle == hourAngle) {
                alarmCount++;
            }

            if (secondAngle == minuteAngle) {
                alarmCount++;
            }

            if (secondAngle == minuteAngle && minuteAngle == hourAngle) {
                alarmCount--;
            }

            startSeconds++;
        }

        return alarmCount;
    }
}
