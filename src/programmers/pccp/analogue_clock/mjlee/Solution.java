package programmers.pccp.analogue_clock.mjlee;

public class Solution {
    public int solution(int startHour, int startMinute, int startSecond, int endHour, int endMinute, int endSecond) {
        int totalAlarms = 0;

        // 시작 시간이 0시 0분 0초인 경우, 정각부터 끝 시간까지의 알람 횟수를 반환
        if (startHour == 0 && startMinute == 0 && startSecond == 0) {
            return calculateAlarmCount(endHour, endMinute, endSecond);
        }

        // 끝 시간까지의 알람 횟수에서 시작 시간까지의 알람 횟수를 뺌
        totalAlarms = calculateAlarmCount(endHour, endMinute, endSecond)
                - calculateAlarmCount(startHour, startMinute, startSecond);

        // 시작 시간이 정각일 경우, 시작 시간의 알람도 포함되도록 +1
        if (startMinute == 0 && startSecond == 0) {
            totalAlarms++;
        }

        return totalAlarms;
    }

    // 0시 0분 0초부터 주어진 시간까지 발생한 알람 횟수를 계산
    int calculateAlarmCount(int hour, int minute, int second) {
        // 12시간 이상인 경우, 12시간 단위로 나누어 계산
        if (hour >= 12) {
            return calculateAlarmCount(11, 59, 59)
                    + calculateAlarmCount(hour - 12, minute, second);
        }

        // 기본 알람 횟수 계산
        // 1시간마다: (분침이 초침을 59번 초과 + 초침이 정각에 분침과 겹침)
        // 시침과 초침의 겹침은 시간 단위로 추가
        // 0-58 까지 + 2
        // 59-0까지는 초침이 분침을 정각 전에 따라잡지 못함 +1
        // 11~12시의 경우 시침도 따라잡지 못함
        // 0시 0분 0초 시침/분침 동시 겹침 1회 차감
        // 따라서 1시간 당 알람 횟수 = 시간*2 + 시간 + 분*2 - 1
        // 시/분/초침 속도 비교
        // 시침 1 : 분침 12 : 초침 720
        //분 단위에서 겹친 횟수
        int alarmCount = hour * 2 * 59  // 분침과 초침의 59번 겹침
                + hour           // 시침과 초침의 겹침
                + minute * 2     // 분침과 초침의 추가 겹침, 초침은 매 초당 분침보다 5.9도 더 빠르게 이동
                - 1;             // 0시 0분 0초의 중복 겹침 조정

        // 침의 현재 위치를 계산하여 추가 겹침 여부 확인
        int secondPosition = second * 720; // 초침의 위치 (720배 속도)
        int minutePosition = (hour * 3600 + minute * 60 + second) * 12; // 분침 위치
        int hourPosition = hour * 3600 + minute * 60 + second; // 시침 위치

        // 초침이 분침을 지나쳤다면 알람 추가, 43200은 아날로그 시계의 12시간 동안 초침의 총 이동 거리
        //초침이 분침의 현재 위치를 초과하면, 초침이 분침을 따라잡아 겹친 것으로 간주
        if (secondPosition >= minutePosition % 43200) {
            alarmCount++;
        }

        // 초침이 시침을 지나쳤다면 알람 추가
        //초침이 시침의 현재 위치를 초과하면, 초침이 시침을 따라잡아 겹친 것으로 간주
        if (secondPosition >= hourPosition % 43200) {
            alarmCount++;
        }

        return alarmCount;
    }
}

