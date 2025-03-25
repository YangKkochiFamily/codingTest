/*
function solution(x, y, n) {
    const visited = new Set();
    const queue = [[x, 0]];

    while (queue.length > 0) {
        const [cur, count] = queue.shift();

        if (cur === y) return count;
        /*
        if (cur > y || visited.has(cur)) continue;
        y보다 크거나 이미 방문한 숫자이면 패스(크면 이미 소용 없고, 방문한 숫자여도 count 낮게 더 좋은 방식이 있기 때문에 )

        visited.add(cur);
        queue.push([cur + n, count + 1]);
        queue.push([cur * 2, count + 1]);
        queue.push([cur * 3, count + 1]);
    } 시간 초과

        const candidates = [cur + n, cur * 2, cur * 3];
        for (let next of candidates) {
            if (next <= y && !visited[next]) {  //큐랑 방문 체크에 넣기 전에 방문한거랑 중복 방지
                visited[next] = true;
                queue.push([next, count + 1]);
            }
        }
    }

    return -1;
}
*/


function solution(x, y, n) {
    const visited = new Array(y + 1).fill(false); // 방문 길이를 알기 위해 y+1길이의 배열을 false
    const queue = [[y, 0]]; // 큐 현재값, 횟수로 저장

    while (queue.length > 0) {
        const [cur, count] = queue.shift();
        if (cur === x) return count;

        const nextStates = [];

        //*이 아니여서 경우가 줄어듦 3부터 계산하는건 가능하면 3으로 나누는게 가장 작기 때문
        if (cur % 3 === 0) nextStates.push(cur / 3); //3으로 나눠 질때만 계산
        if (cur % 2 === 0) nextStates.push(cur / 2); // 2로 나눠 질때만 계산
        nextStates.push(cur - n);

        for (let next of nextStates) {
            if (next >= x && !visited[next]) {
                visited[next] = true;
                queue.push([next, count + 1]);
            }
        }
    }

    return -1;
}


const testCases = [
    { x: 10, y: 40, n: 5, expected: 2 },
    { x: 10, y: 40, n: 30, expected: 1 },
    { x: 2, y: 5, n: 4, expected: -1 },
];

testCases.forEach(({ x, y, n, expected }, index) => {
    const result = solution(x, y, n);
    const pass = result === expected ? "✅ PASS" : "❌ FAIL";
    console.log(
        `Test Case #${index + 1}: x=${x}, y=${y}, n=${n} → 결과: ${result}, 기대값: ${expected} → ${pass}`
    );
});