from datetime import datetime, timedelta


def solution(video_len, pos, op_start, op_end, commands):
  video = VideoPlayer(op_start=op_start, op_end=op_end, video_len=video_len, pos=pos)
  for command in commands:
    video.input(command)
  return video.get_form_time()

class VideoPlayer(object):
  def __init__(self, **kwargs):
    self._op_start = TimeUtil.to_datetime(kwargs.get('op_start'))
    self._op_end = TimeUtil.to_datetime(kwargs.get('op_end'))
    self._video_len = TimeUtil.to_datetime(kwargs.get('video_len'))
    self._time = TimeUtil.to_datetime(kwargs.get('pos'))

  def input(self, command):
    self.skip_check()
    self.do(command)
    self.skip_check()

  def skip_check(self):
    if self._op_start <= self._time < self._op_end:
      self._time = self._op_end

  def do(self, command):
    if "next" == command:
      self.next()
    else:
      self.prev()

  def next(self):
    self._time = TimeUtil.calculate_second(self._time, 10)
    if self._time > self._video_len:
      self._time = self._video_len

  def prev(self):
    if TimeUtil.zero <= self._time <= TimeUtil.calculate_second(TimeUtil.zero,
                                                                10):
      self._time = TimeUtil.zero
    else:
      self._time = TimeUtil.calculate_second(self._time, -10)

  def get_form_time(self):
    return self._time.strftime("%M:%S")

class TimeUtil(object):
  zero = datetime(year=2025, month=1, day=1, minute=0, second=0)

  @classmethod
  def to_datetime(cls, time):
    t = time.split(":")
    return cls.zero + timedelta(minutes=int(t[0]), seconds=int(t[1]))

  @staticmethod
  def calculate_second(time: datetime, second: int):
    return time + timedelta(seconds=second)

if __name__ == '__main__':
  print(
    solution("34:33", "13:00", "00:55", "02:55", ["next", "prev"]) == "13:00")
  print(solution("10:55", "00:05", "00:15", "06:55",
                 ["prev", "next", "next"]) == "06:55")
  print(solution("07:22", "04:05", "00:15", "04:07", ["next"]) == "04:17")
