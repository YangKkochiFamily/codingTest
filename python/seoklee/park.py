def solution(mats, park):
  max_size = 0
  for y, row in enumerate(park):
    for x, value in enumerate(row):
      if value == '-1':
        if x > 0 and y > 0:
          before_row = park[y - 1]
          park[y][x] = min(row[x - 1], before_row[x], before_row[x - 1]) + 1
          max_size = max(max_size, park[y][x])
        else:
          park[y][x] = 1
      else:
        park[y][x] = 0
  return max(list(filter(lambda r: r <= max_size, mats + [-1])))


if __name__ == '__main__':
  print(solution([1,5, 3, 2], [["A", "A", "-1", "B", "B", "B", "B", "-1"], ["A", "A", "-1", "B", "B", "B", "B", "-1"], ["-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"], ["D", "D", "-1", "-1", "-1", "-1", "E", "-1"], ["D", "D", "-1", "-1", "-1", "-1", "-1", "F"], ["D", "D", "-1", "-1", "-1", "-1", "E", "-1"]]))
