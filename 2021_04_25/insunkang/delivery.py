# n, maxC = map(int, input().split(" "))
#
# truck = {}
#
# city = [[] for i in range(n)]
#
#
# now = 0
#
# answer = 0
a =[[3,4,20],
[1,2,10],
[1,3,20],
[1,4,30],
[2,3,10],
[2,4,20]]
# m = int(input())
#
# for _ in range(m):
#     a, b, c = map(int, input().split(" "))
#
#     city[a].append([b, c])

# city.sort()
# for i in range(1,n):
#     city[i].sort()
# city[1].sort()
a.sort(key=lambda x:x[1])
print(a)


# for i in range(1, n):
#
#     if i != 1 and truck.get(i) is not None:
#         answer += truck.get(i)
#         now -= truck.get(i)
#         truck[i] = 0
#     for j in city[i]:
#         if now == maxC:
#             continue
#         if now + j[1] < maxC:
#             now += j[1]
#             if truck.get(j[0]) is not None:
#                 truck[j[0]] = truck.get(j[0]) + j[1]
#             else:
#                 truck[j[0]] = j[1]
#         else:
#             if truck.get(j[0]) is not None:
#                 truck[j[0]] = truck.get(j[0]) + maxC - now
#             else:
#                 truck[j[0]] = maxC - now
#             now = maxC
#     # print(i)
#     # print(truck)
#     # print(answer)
# answer += truck.get(n)
#
# print(answer)