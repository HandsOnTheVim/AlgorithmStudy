# 폰 호석만
# https://www.acmicpc.net/problem/21275

def cal(x_reverse, i):
    x_cal = 0
    for idx in range(len(x_reverse)):
        res = 0
        if x_reverse[idx].isalpha():
            c = ord(x_reverse[idx]) - 87
            if c < i:
                res = c * (i**idx)
            else:
                return -1
        else:
            res = int(x_reverse[idx]) * (i**idx)
        x_cal += res
        if x_cal >= 2**63:
            return -1
    return x_cal

a, b = input().split()
a_reverse = []
b_reverse = []
answer = []

for i in range(len(a) - 1, -1, -1):
    a_reverse.append(a[i])

for i in range(len(b) - 1, -1, -1):
    b_reverse.append(b[i])

for i in range(2, 37):
    for j in range(2, 37):
        if i == j:
            continue
        if cal(a_reverse, i) == -1 or cal(b_reverse, j) == -1:
            continue
        if cal(a_reverse, i) == cal(b_reverse, j):
            answer.append((cal(a_reverse, i), i, j))

if len(answer) == 0:
    print('Impossible')
elif len(answer) >= 2:
    print('Multiple')
else:
    print(str(answer[0][0]) + ' ' + str(answer[0][1]) + ' ' + str(answer[0][2]))