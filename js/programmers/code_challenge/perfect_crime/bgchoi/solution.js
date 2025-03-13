// function solution(info, n, m) {
//     const itemCount = info.length;
//     let minTrace = Infinity;
//
//     function backtrack(index, aTrace, bTrace) {
//         if (aTrace >= n || bTrace >= m) return;
//         if (index === itemCount) {
//             minTrace = Math.min(minTrace, aTrace);
//             return;
//         }
//
//         const [a, b] = info[index];
//
//         backtrack(index + 1, aTrace + a, bTrace);
//
//         backtrack(index + 1, aTrace, bTrace + b);
//     }
//
//     backtrack(0, 0, 0);
//
//     return minTrace === Infinity ? -1 : minTrace;
// }
// 시간 초과 ㅠㅠ

function solution(info, n, m) {
    const itemCount = info.length;
    let minTrace = Infinity; // 숫자 무한대
    const memo = new Map(); // 중복 방지를 위한 Map

    // 백트래킹 방식 사용
    function backtrack(index, aTrace, bTrace) {
        if (aTrace >= n || bTrace >= m) return; // 잡히면 중단
        if (index === itemCount) { // 잡히지 않을경우 최소값 갱신
            if (aTrace < n && bTrace < m) {
                minTrace = Math.min(minTrace, aTrace);
            }
            return;
        }

        //이미 본 상태면 중복이니까 바로 중단. 요걸 하지 않으면 시간 초과
        const key = `${index},${aTrace},${bTrace}`;
        if (memo.has(key)) return;
        memo.set(key, true);

        const [a, b] = info[index];

        //A가 훔치는 경우
        backtrack(index + 1, aTrace + a, bTrace);

        //B가 훔치는 경우
        backtrack(index + 1, aTrace, bTrace + b);
    }

    backtrack(0, 0, 0);

    return minTrace === Infinity ? -1 : minTrace;
}