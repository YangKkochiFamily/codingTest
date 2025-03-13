def solution(friends, gifts):
  dic = {x : Friend() for x, y in zip(friends, range(len(friends)))}

  # 대상보다 많이 줬으면 받을 것임
  # 대상과 주고받은 갯수가 동일하다면 덜 받은사람이 선물을 받기로함.

  for gift in gifts:
    giver, receiver = gift.split()
    dic[giver].give(receiver)
    dic[receiver].receive(giver)

  for me in friends:
    for friend in filter(lambda c: c != me,friends):
      if dic[me].get_give_cnt(friend) > dic[me].get_receive_cnt(friend) or (
          dic[me].get_give_cnt(friend) == dic[me].get_receive_cnt(friend) and dic[me].score > dic[friend].score ):
        dic[me].gift += 1

  return max([dic[k].gift for k in dic.keys()])


class Friend:
  def __init__(self):
    self.give_to = dict()
    self.gift_from = dict()
    self.score = 0
    self.gift = 0

  def receive(self, friend_name):
    self.gift_from[friend_name] = self.get_receive_cnt(friend_name) + 1
    self.score -= 1

  def give(self, friend_name):
    self.give_to[friend_name] = self.get_give_cnt(friend_name) + 1
    self.score += 1

  def get_give_cnt(self, compare):
    return self.give_to.get(compare, 0)

  def get_receive_cnt(self, compare):
    return self.gift_from.get(compare, 0)

if __name__ == '__main__':
  print(solution(["muzi", "ryan", "frodo", "neo"], ["muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"]) == 2)
  print(solution(["joy", "brad", "alessandro", "conan", "david"], ["alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"]) == 4)
  print(solution(["a", "b", "c"], 	["a b", "b a", "c a", "a c", "a c", "c a"]) == 0)