def solution(x, y, n):
    now = [x]
    count = 0
    if x == y:
        return 0

    while len(now) != 0:
        next = set()
        count += 1
        for i in now:
            if i * 2 == y or i * 3 == y or i + n == y:
                return count
            if i * 2 < y:
                next.add(i * 2)
            if i * 3 < y:
                next.add(i * 3)
            if i + n < y:
                next.add(i + n)


        now = next
    return -1

if __name__ == '__main__':
    print (solution(10,40,5) == 2)
    print(solution(10, 40, 30) == 1)
    print(solution(2,5,4) == -1)