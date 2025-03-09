// function solution(schedules, timelogs, startday) {
//     const weekdays = [1, 2, 3, 4, 5];
//     let count = 0;
//
//     schedules.forEach((schedule, i) => {
//         const allow = schedule + 10;
//         let success = true;
//
//         for (let j = 1; j < 8; j++) {
//             const dayOfWeek = ((startday + j) % 7);
//             if (!weekdays.includes(dayOfWeek)) continue;
//
//             if (timelogs[i][j] > allow) {
//                 success = false;
//                 break;
//             }
//         }
//
//         if (success) count++;
//     });
//
//     return count;
// }
// 실패

function solution(schedules, timelogs, startday) {
    const weekdays = [1, 2, 3, 4, 5];  // 근무 요일
    let count = 0;

    // 분이 60이 지나면 시를 1 올려줘야 하기 때문에 나눠서 계산 필요 계산 후 다시 원래 형태로 복원
    const time = (workTime) => {
        let hour = Math.floor(workTime / 100);
        let minute = (workTime % 100) + 10;

        if (minute >= 60) {
            hour += 1;
            minute -= 60;
        }

        return hour * 100 + minute;
    };

    schedules.forEach((schedule, i) => { // 직원의 지각 체크
        const allow = time(schedule);
        let success = true;

        for (let j = 0; j <= 7; j++) {
            const dayOfWeek = (startday + j - 1) % 7; //시작 요일 부터 체크 해서 요일 확인
            if (!weekdays.includes(dayOfWeek)) continue; // 주말이면 넘어감

            if (timelogs[i][j - 1] > allow) { // 지각 체크
                success = false;
                break;
            }
        }

        if (success) count++; // 지각 아니면 count ++
    });

    return count;
}
