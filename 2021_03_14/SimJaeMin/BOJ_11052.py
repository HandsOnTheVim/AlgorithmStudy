# 카드 구매하기
# https://www.acmicpc.net/problem/11052

n = int(input())
p = list(map(int, input().split()))

d = [0] * (n + 1)
d[1] = p[0]

for i in range(1, n + 1):
    d[i] = p[i - 1]
    for j in range(1, i // 2 + 1):
        d[i] = max(d[i], d[i - j] + d[j])

print(d[n])

# for i in range(len(p)):
#     p[i] = (i + 1, p[i] / (i + 1))

# p.sort(key=lambda x:-x[1])
# ans = 0
# idx = 0

# while n != 0:
#     if n - p[idx][0] >= 0:
#         n -= p[idx][0]
#         ans += p[idx][1] * p[idx][0]
#     else:
#         idx += 1

# print(int(ans))