def solution(schedules, timelogs, startday):
  return sum(1 for _ in filter(lambda log: is_success(log, startday, schedules), enumerate(timelogs)))

def is_success(log,  startday, schedules):
  return sum(1 for _ in filter(lambda day: is_weekdays(day[0], startday) and is_on_time(schedules[log[0]], day[1]), enumerate(log[1]))) == 5

def is_on_time(schedule: int, day_log):
  return schedule + (50 if int(str(schedule)[-2:]) >= 50 else  10) >= day_log

def is_weekdays(day, startday):
  return  0 < (day + startday) % 7 < 6



if __name__ == '__main__':
    print(solution([700, 800, 1100], [[710, 2359, 1050, 700, 650, 631, 659], [800, 801, 805, 800, 759, 810, 809], [1105, 1001, 1002, 600, 1059, 1001, 1100]],5))
    print(solution([730, 855, 700, 720], [[710, 700, 650, 735, 700, 931, 912], [908, 901, 805, 815, 800, 831, 835], [705, 701, 702, 705, 710, 710, 711], [707, 731, 859, 913, 934, 931, 905]],1))