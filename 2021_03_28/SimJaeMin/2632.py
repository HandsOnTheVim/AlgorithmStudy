# 피자판매
# https://www.acmicpc.net/problem/2632

import sys

input = sys.stdin.readline

target = int(input())
m, n = map(int, input().split())
a = []
b = []

for i in range(m):
    a.append(int(input()))

for i in range(n):
    b.append(int(input()))

a_list = [0] * 2000001
b_list = [0] * 2000001
a_list[0] = 1
b_list[0] = 1

# i 자리부터 하나씩 더해가며 target보다 작은지 체크
for i in range(m):
    s = 0
    for j in range(m):
        s += a[(i+j) % m]
        if s > target:
            break
        else:
            a_list[s] += 1

for i in range(n):
    s = 0
    for j in range(n):
        s += b[(i+j) % n]
        if s > target:
            break
        else:
            b_list[s] += 1

a_list[sum(a)] = 1
b_list[sum(b)] = 1

ans = 0
for i in range(target + 1):
    ans += (a_list[i] * b_list[target - i])

print(ans)