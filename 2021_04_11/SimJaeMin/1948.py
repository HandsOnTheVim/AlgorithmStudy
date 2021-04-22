# 임계경로
# https://www.acmicpc.net/problem/1948

from collections import deque

n = int(input())
m = int(input())
indegree = [0] * (n + 1)
graph = [[] for _ in range(n + 1)]
reverse_graph = [[] for _ in range(n + 1)]
visited = [False] * (n + 1)

for i in range(m):
    a, b, c = map(int, input().split())
    graph[a].append((b, c))
    reverse_graph[b].append((a, c))
    indegree[b] += 1

start, end = map(int, input().split())

def topology_sort():
    result = [0] * (n + 1)
    q = deque()
    q.append(start)

    while q:
        now = q.popleft()
        for i in graph[now]:
            indegree[i[0]] -= 1
            result[i[0]] = max(result[i[0]], result[now] + i[1])
            if indegree[i[0]] == 0:
                q.append(i[0])
    
    q.append(end)
    cnt = 0
    
    while q:
        now = q.popleft()
        for i in reverse_graph[now]:
            # result에는 now에서 i[0]으로 갈수 있는 최대시간이 저장되어 있으므로
            # result[now] - result[i[0]]과 간선시간이 같을때가 조건 만족
            if result[now] - result[i[0]] == i[1]:
                cnt += 1
                # 중복된 도로를 제거
                if not visited[i[0]]:
                    visited[i[0]] = True
                    q.append(i[0])
    
    print(result[end])
    print(cnt)

topology_sort()