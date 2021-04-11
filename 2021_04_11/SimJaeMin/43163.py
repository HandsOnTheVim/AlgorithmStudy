# 단어 변환
# https://programmers.co.kr/learn/courses/30/lessons/43163

from collections import deque

def bfs(begin, target, words):
    q = deque()
    q.append((begin, 0))
    visited = [False] * len(words)

    while q:
        now, ans = q.popleft()
        if now == target:
            begin = target
            return ans
        for idx in range(len(words)):
            cnt = 0
            for i in range(len(target)):
                if words[idx][i] == now[i]:
                    cnt += 1
            if cnt == len(target) - 1 and visited[idx] == False:
                ans += 1
                q.append((words[idx], ans))
                visited[idx] = True
    if begin != target:
        ans = 0
    return ans

def solution(begin, target, words):
    answer = 0
    answer = bfs(begin, target, words)
    return answer