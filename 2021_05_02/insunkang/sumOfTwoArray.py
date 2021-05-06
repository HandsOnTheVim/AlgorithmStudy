# t = int(input())
# n = int(input())
# a = list(map(int,input().split(" ")))
# m = int(input())
# b = list(map(int,input().split(" ")))
# answer = 0
#
# for i in range(n):
#     check = a[i]
#     if check > t:
#         continue
#     for j in range(i,n):
#         if j != i:
#             check += a[j]
#         if check > t:
#             break
#         for k in range(m):
#             check2 = b[k]
#             if check2 >t or check+check2 >t:
#                 continue
#             for l in range(k,m):
#                 if k != l:
#                     check2 += b[l]
#                 if check2> t or check+check2 > t:
#                     break
#                 if check + check2 == t:
#                     answer += 1
#                     break
#
#
#
# print(answer)
import sys
from _collections import defaultdict

T = int(sys.stdin.readline())

a = int(sys.stdin.readline())
listA = list(map(int, sys.stdin.readline().split()))
b = int(sys.stdin.readline())
listB = list(map(int, sys.stdin.readline().split()))

dictA = defaultdict(int)


ans = 0

for i in range(a):
    for j in range(i, a, 1):
        dictA[sum(listA[i:j+1])] += 1

for i in range(b):
    for j in range(i, b, 1):
        ans += dictA[T - sum(listB[i:j+1])]

print(ans)