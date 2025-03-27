function solution(n, w, num) {
    const columns = Array.from({ length: w }, () => []);
    let current = 1;
    let layerIndex = 0;

    while (current <= n) {
        if (layerIndex % 2 === 0) {
            //일반적으로 왼 -> 오
            for (let col = 0; col < w && current <= n; col++) {
                columns[col].push(current);
                current++;
            }
        } else {
            for (let col = w - 1; col >= 0 && current <= n; col--) {
                columns[col].push(current);
                current++;
            }
        }
        layerIndex++;
    }

    for (let col = 0; col < w; col++) {
        const rowIndex = columns[col].indexOf(num);
        if (rowIndex !== -1) {
            //return rowIndex + 1; 아래서 부터가 아니라 위에서 부터 없애야 함
            //return columns[col].length - rowIndex; 세로로 쌓았는데 지그재그 방식임 변경 필요
            return columns[col].length - rowIndex;
        }
    }

    return 0;
}

const testCases = [

    // 그룹 #3: 제한 조건 없음 (복합 케이스)
    { n: 22, w: 6, num: 8, expected: 3 },
    { n: 13, w: 3, num: 6, expected: 4 }
];

testCases.forEach(({ n, w, num, expected }, index) => {
    const result = solution(n, w, num);
    const pass = result === expected ? "✅ PASS" : "❌ FAIL";
    console.log(
        `Test Case #${index + 1}: n=${n}, w=${w}, num=${num} → 결과: ${result}, 기대값: ${expected} → ${pass}`
    );
});