# 감시
# https://www.acmicpc.net/problem/15683

from itertools import product
import copy

def direction(graph, d, x, y):
    if d == 'u':
        while x >= 0:
            if graph[x][y] == 6:
                break
            if graph[x][y] == 0:
                graph[x][y] = -1
            x -= 1
    elif d == 'l':
        while y >= 0:
            if graph[x][y] == 6:
                break
            if graph[x][y] == 0:
                graph[x][y] = -1
            y -= 1
    elif d == 'd':
        while x < n:
            if graph[x][y] == 6:
                break
            if graph[x][y] == 0:
                graph[x][y] = -1
            x += 1
    elif d == 'r':
        while y < m:
            if graph[x][y] == 6:
                break
            if graph[x][y] == 0:
                graph[x][y] = -1
            y += 1
    return graph

def search(graph, cctv, dir_list):
    for i in range(len(cctv)):
        x = cctv[i][0]
        y = cctv[i][1]
        num = cctv[i][2]
        d = dir_list[i]

        if num == 1:
            graph = direction(graph, d, x, y)
        elif num == 2:
            if d == 'u' or d == 'd':
                graph = direction(graph, 'u', x, y)
                graph = direction(graph, 'd', x, y)
            if d == 'l' or d == 'r':
                graph = direction(graph, 'l', x, y)
                graph = direction(graph, 'r', x, y)
        elif num == 3:
            if d == 'u':
                graph = direction(graph, 'u', x, y)
                graph = direction(graph, 'r', x, y)
            elif d == 'r':
                graph = direction(graph, 'r', x, y)
                graph = direction(graph, 'd', x, y)
            elif d == 'd':
                graph = direction(graph, 'd', x, y)
                graph = direction(graph, 'l', x, y)
            elif d == 'l':
                graph = direction(graph, 'l', x, y)
                graph = direction(graph, 'u', x, y)
        elif num == 4:
            if d == 'u':
                graph = direction(graph, 'l', x, y)
                graph = direction(graph, 'r', x, y)
                graph = direction(graph, 'd', x, y)
            elif d == 'l':
                graph = direction(graph, 'u', x, y)
                graph = direction(graph, 'r', x, y)
                graph = direction(graph, 'd', x, y)
            elif d == 'd':
                graph = direction(graph, 'l', x, y)
                graph = direction(graph, 'r', x, y)
                graph = direction(graph, 'u', x, y)
            elif d == 'r':
                graph = direction(graph, 'l', x, y)
                graph = direction(graph, 'u', x, y)
                graph = direction(graph, 'd', x, y)
        if num == 5:
            graph = direction(graph, 'l', x, y)
            graph = direction(graph, 'r', x, y)
            graph = direction(graph, 'd', x, y)
            graph = direction(graph, 'u', x, y)
    return graph

n, m = map(int, input().split())
graph = []
cctv = []
ans = n * m
w_cnt = 0

for i in range(n):
    data = list(map(int, input().split()))
    for j in range(m):
        if data[j] in [1, 2, 3, 4, 5]:
            cctv.append((i, j, data[j]))
        if data[j] == 6:
            w_cnt += 1
    graph.append(data)

directions = ['u', 'l', 'd', 'r']

for i in list(product(directions, repeat=len(cctv))):
    copy_graph = copy.deepcopy(graph)
    result = search(copy_graph, cctv, i)
    cnt = 0
    for i in range(n):
        cnt += result[i].count(0)
    ans = min(ans, cnt)

print(ans)