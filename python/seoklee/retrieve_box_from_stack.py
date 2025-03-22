import math

def solution(n, w, num):
    h = math.ceil(n / w)
    arr = [[0 for _ in range(w) ] for _ in range(h) ]
    target_idx = 0
    for i in range(h):
        for j in range(w):
            if i % 2 == 1:
                v = w - j + (i * w)
            else:
                v = j + 1 + (i * w)

            if v == num:
                target_idx = j
            arr[i][j] =v


    return len([arr[i][target_idx] for i, ar in enumerate(arr) if num < arr[i][target_idx] <= n]) + 1

if __name__ == '__main__':
    print(solution(22,6,8 ) == 3)
    print(solution(13,3,6 ) == 4)