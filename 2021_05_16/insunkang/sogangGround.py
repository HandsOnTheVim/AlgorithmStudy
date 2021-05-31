import copy
from collections import deque
import heapq
n,m,r = map(int,input().split(" "))
INF = int(1e9)
items = list(map(int,input().split(" ")))

places = [[] for _ in range(n)]

for _ in range(r):
    a,b,c = map(int, input().split(" "))
    places[a-1].append([b-1,c])
    places[b-1].append([a-1,c])
# print(places)


answer = [0] * n
# result = copy.deepcopy(items)
for i in range(n):
    # cPlaces = copy.deepcopy(places)
    q = []
    heapq.heappush(q,(0,i))
    result = [INF] * n
    result[i] = 0
    while q:
        dist, now = heapq.heappop(q)
        if result[now] < dist:
            continue
        for j in places[now]:
            cost = dist + j[1]
            if cost < result[j[0]]:
                result[j[0]] = cost
                heapq.heappush(q,(cost,j[0]))
    # print(result)
    for p in range(len(result)):
        if result[p] <= m:
            answer[i] += items[p]


print(max(answer))
# INF = int(1e9)
# n,m,r = map(int,input().split(" "))
#
# items = list(map(int,input().split(" ")))
#
#
# places = [[INF] * n for _ in range(n)]
#
# # print(places)
#
# for _ in range(r):
#     a,b,c = map(int,input().split(" "))
#     places[a - 1][b - 1] = c
#     places[b - 1][a - 1] = c
#
# for i in range(n):
#     places[i][i] = 0
#
# for a in range(n):
#     for i in range(n):
#         for j in range(n):
#             places[i][j] = min(places[i][j], places[i][a]+places[a][j])
#
# result = [0] * n
# # print(items)
# # print(places)
# for i in range(n):
#     check = 0
#     for j in range(n):
#         if places[i][j] <= m:
#             check += items[j]
#     result[i] = check
#
# # print(places)
# print(max(result))
#
# # print(check)




