# import copy
#
#
# def checkOrder(n, order):
#     check = 0
#     for i in range(len(order)):
#         if n in order[i]:
#             if order[i].index(n) == 1 and order[i][0] == -1:
#                 return [i, order[i].index(n)]
#             elif order[i].index(n) == 0:
#                 return [i, order[i].index(n)]
#             else:
#                 return [-1, -1]
#         else:
#             check += 1
#     if check == len(order):
#         return [-2, -2]
#     return [-1, -1]
#
#
# def findPath(now, graph, order, check, sun, before):
#     print(now)
#     if sum(check) == len(check) * -1:
#         return "yes"
#
#     print("YEEE222")
#     for i in graph[now]:
#         if now not in sun and i == before:
#             print("continue")
#             continue
#         nowCheck = checkOrder(i, order)
#         print(nowCheck)
#         copyCheck = copy.deepcopy(check)
#         copyOrder = copy.deepcopy(order)
#         print("YEEE3333")
#
#         if sum(nowCheck) == -4:
#             print("YEEE")
#             copyCheck[i] = 1
#             findPath(i, graph, copyOrder, copyCheck, sun, now)
#         elif sum(nowCheck) != -2:
#             print("YEEE")
#             copyCheck[i] = 1
#             copyOrder[nowCheck[0]][nowCheck[1]] = -1
#             findPath(i, graph, copyOrder, copyCheck, sun, now)
#
#     return "no"
#
#
# def solution(n, path, order):
#
#     graph = [[] for _ in range(n)]
#     check = [1] * n
#     graphSun = [[] for _ in range(n)]
#     findSun = []
#     sun = []
#     for i in path:
#         graph[i[0]].append(i[1])
#         graphSun[i[0]].append(i[1])
#         graph[i[1]].append(i[0])
#     for i in graphSun:
#         for j in i:
#             if j not in findSun:
#                 findSun.append(j)
#     print(graph)
#     print(graphSun)
#     print(findSun)
#     for i in range(len(graphSun)):
#         if len(graphSun[i]) == 0:
#             sun.append(i)
#     for i in range(1,n):
#         if i not in findSun:
#             sun.append(i)
#     print(sun)
#     answer = findPath(0, graph, order, check, sun, 0)
#
#     print(answer)
#
#
# solution(9,[[0,1],[0,3],[0,7],[8,1],[3,6],[1,2],[4,7],[7,5]],[[8,5],[6,7],[4,1]])
from collections import  deque
def solution(n, path, order):
    q = deque()
    answer = False
    visit = [0] * n
    cango = [0] * n
    answer = False
    visit[0] = 1
    cango[0] = 1
    must = {}
    must2 = {}
    for i in order:
        must[i[1]] = i[0]
        must2[i[0]] = i[1]
    matrix = [[] for _ in range(n)]
    for i in path:
        matrix[i[0]].append(i[1])
        matrix[i[1]].append(i[0])
    if must.get(0) is None:
        q.extend(matrix[0])
    while q:
        p = q.popleft()
        cango[p] = 1
        if must.get(p) is None:
            for i in matrix[p]:
                if visit[i] == 0:
                    q.append(i)
            if must2.get(p) is not None and cango[must2[p]]:
                q.append(must2[p])
        else:
            if visit[must.get(p)] == 1:
                visit[p] = 1
                for i in matrix[p]:
                    if visit[i] == 0:
                        q.append(i)
    if sum(visit) == n:
        answer = True

    return answer
