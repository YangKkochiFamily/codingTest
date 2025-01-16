from datetime import datetime, timedelta

zero = datetime(year=2025, month=1, day=1, minute=0, second=0)

def solution(video_len, pos, op_start, op_end, commands):
    video = Video(op_start, op_end, video_len, pos)
    for command in commands:
        video.input(command)
    return video.time.strftime("%M:%S")

def get_time(time):
    t = time.split(":")
    return timedelta(minutes=int(t[0]), seconds=int(t[1]))

class Video:
    def __init__(self, op_start, op_end, video_len, pos):
        self.op_start = zero + get_time(op_start)
        self.op_end = zero + get_time(op_end)
        self.video_len = zero + get_time(video_len)
        self.time = zero + get_time(pos)

    def input(self, command):
        self.skip_check()
        self.do(command)
        self.skip_check()

    def skip_check(self):
        if self.op_start <= self.time < self.op_end:
            self.time = self.op_end

    def do(self, command):
        if "next" == command:
            self.next()
        else:
            self.prev()

    def next(self):
        self.time = self.time + timedelta(seconds=10)
        if self.time > self.video_len:
            self.time = self.video_len

    def prev(self):
        if zero <= self.time <= (zero + timedelta(seconds=10)):
            self.time = zero
        else:
            self.time = self.time - timedelta(seconds=10)

if __name__ == '__main__':
    print(solution("34:33", "13:00", "00:55", "02:55", ["next", "prev"]) == "13:00")
    print(solution("10:55", "00:05", "00:15", "06:55", ["prev", "next", "next"]) == "06:55")
    print(solution("07:22", "04:05", "00:15", "04:07", ["next"]) == "04:17")
