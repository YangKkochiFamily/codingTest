from itertools import chain
def solution(mats, park):
  parsed_park = [[1 if '-1' == i else 0 for i in row] for row in park ]
  for y, row in enumerate(parsed_park[1:], start = 1):
    for x, value in [item for item in (enumerate(row[1:], start = 1)) if item[1] == 1]:
      parsed_park[y][x] = min(row[x - 1], parsed_park[y - 1][x], parsed_park[y - 1][x - 1]) + 1
  return max([r for r in mats + [-1] if r <= max(chain(*parsed_park))])

if __name__ == '__main__':
  print(solution([1,5, 3, 2], [["A", "A", "-1", "B", "B", "B", "B", "-1"], ["A", "A", "-1", "B", "B", "B", "B", "-1"], ["-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"], ["D", "D", "-1", "-1", "-1", "-1", "E", "-1"], ["D", "D", "-1", "-1", "-1", "-1", "-1", "F"], ["D", "D", "-1", "-1", "-1", "-1", "E", "-1"]]))