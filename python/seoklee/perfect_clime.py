def solution(info, n, m):
  INF = float('inf')
  dp = [[INF] * m  for _ in range(len(info) + 1)]
  dp[0][0] = 0  # 초기 상태: 아무 흔적도 없는 상태

  for i in range(1, len(info) + 1):
    a, b = info[i - 1]  # 현재 물건이 남기는 A, B 도둑의 흔적 개수
    for j in range(m - 1, -1, -1):  # B 흔적을 역순 탐색 (덮어쓰는 문제 방지)
      before = dp[i - 1][j]
      if dp[i - 1][j] == INF:
        continue  # 이전 상태에서 도달할 수 없는 경우 건너뜀

      # A도둑이 훔치는 경우 이전값에서 a를 더함
      dp[i][j] = min(dp[i][j],  before+ a)

      # B도둑이 훔치면 이전 a값에 추가가 없으며 b만큼 j인덱스를 이동함
      if j + b < m:  # m을 초과하면 실패임, index를 넘어가면 m을 넘어가는것
        dp[i][j + b] = min(dp[i][j + b], before)
  min_cost = min(dp[len(info)])

  return min_cost if min_cost < n else -1


if __name__ == '__main__':
  print(solution([[1, 2], [2, 3], [2, 1]],4,4))
  print(solution([[1, 2], [2, 3], [2, 1]],1,7))
  print(solution([[3, 3], [3, 3]],7,1))
  print(solution([[3, 3], [3, 3]],6,1))
