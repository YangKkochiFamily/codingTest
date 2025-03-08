def solution(info, n, m):
  """
  완전탐색으로 해결할 수는 있지만 시간복잡도가 2^n 으로 너무 높다
  다이나믹 프로그래밍 (dp) 으로 시간복잡도를 최적화 해보자.

  point
  1. 이전 결과를 저장 하는 memorization 사용
    --  1번 아이템 훔칠 경우의 수 추가-> 1번 결과들 + 2번 아이템 훔치기 추가 ...
  2. 경우의 수 중 목표 달성 못하는 경우는 memo에 추가 x
  3. memo된 내용 중 b의 흔적이 동일한 경우 더 작은 a 만 저장
    -- 복잡도는 b 최대 저장 가능 값  * 물건 갯수
    -- O((b - 1) * len(info))
  """

  # 초깃값을 설정해준다.
  # key : 해당 경우의 수의 b 값 총합
  # value : 해당 경우의 수의 a 총합
  # memo는 현재까지 유효한 최적의 경우의 수들의 결과 값을 저장 한다.
  memo = {0:0}
  for A, B in info:
    tmp_memo = {}
    for b, a in memo.items():
      # 이전 경우의 수에서 새로운 경우의 수를 만들어 추가 한다.
      # key가 중복되는 경우가 발생 하는데 더 작은 value로 가져가도록 하여 경우의 수를 최소화 한다.
      if a + A < n:
        tmp_memo[b] = min(a + A, tmp_memo.get(b, 121))
      if b + B < m:
        tmp_memo[b + B] = min(a, tmp_memo.get(b + B, 121))
    memo = tmp_memo
  return min(memo.values(), default=-1)

if __name__ == '__main__':
  print(solution([[1, 2], [2, 3], [2, 1]],4,4))
  print(solution([[1, 2], [2, 3], [2, 1]],1,7))
  print(solution([[3, 3], [3, 3]],7,1))
  print(solution([[3, 3], [3, 3]],6,1))
