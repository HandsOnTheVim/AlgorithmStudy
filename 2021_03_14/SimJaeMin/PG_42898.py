# 등굣길
# https://programmers.co.kr/learn/courses/30/lessons/42898

def solution(m, n, puddles):
    answer = 0
    load = [[0] * m for i in range(n)]
    for i in puddles:
        y, x = i
        load[x - 1][y - 1] = -1
    
    load[0][0] = 1
    for i in range(n):
        for j in range(m):
            if load[i][j] == -1:
                continue
            else:
                if i - 1 >= 0 or j - 1 >= 0:
                    up = load[i - 1][j]
                    left = load[i][j - 1]
                    if up == -1:
                        up = 0
                    if left == -1:
                        left = 0
                    load[i][j] = up + left
    answer = load[n - 1][m - 1] % 1000000007
    return answer