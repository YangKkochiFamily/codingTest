def solution(info, n, m):
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
  return -1 if len(memo) == 0 else min(memo[b] for b  in memo)

if __name__ == '__main__':
  print(solution([[1, 2], [2, 3], [2, 1]],4,4))
  print(solution([[1, 2], [2, 3], [2, 1]],1,7))
  print(solution([[3, 3], [3, 3]],7,1))
  print(solution([[3, 3], [3, 3]],6,1))
