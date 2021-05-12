# o, p = map(int, input().split(" "))
#
# global n, m
#
# n = o
# m = p
#
# target = []
# for _ in range(n):
#     a = []
#     b = str(input())
#     for i in range(m):
#         if i == "H":
#             a.append(i)
#         else:
#             a.append(int(i))
#     target.append(a)
#
# def find(a, b, result, targetList):
#     global answer
#     number = targetList[a][b]
#     check = 0
#     if a + number < n:
#         if number == targetList[a + number][b]:
#             answer = -1
#             return
#         if "H" == targetList[a + number][b]:
#             return
#         find(a + number, b, result + 1, targetList)
#         check += 1
#     if b + number < m:
#         if number == targetList[a ][b+ number]:
#             answer = -1
#             return
#         if "H" == targetList[a ][b+ number]:
#             return
#         find(a, b + number, result + 1, targetList)
#         check += 1
#     if a - number >= 0:
#         if number == targetList[a - number][b]:
#             answer = -1
#             return
#         if "H" == targetList[a - number][b]:
#             return
#         find(a - number, b, result + 1, targetList)
#         check += 1
#     if b - number >= 0:
#         if number == targetList[a ][b- number]:
#             answer = -1
#             return
#         if "H" == targetList[a ][b- number]:
#             return
#         find(a, b - number, result + 1, targetList)
#         check += 1
#     if check == 0:
#         if answer == -1:
#             return
#         else:
#             answer = max(answer, result)
#
# find(0,0,0,target)
# print(answer)

import sys
sys.setrecursionlimit(10000000)

def dpp(check,maze,dp,i,j,n,m,bi,bj,first):
    if(i < 0 or j < 0 or i > (n-1) or j > (m-1)):
        return 0
    if(maze[i][j] == 'H'):
        return 0
    if(check[i][j] == 0):
        check[i][j] = 1
    else:
        return "inf"
    maximum = 0
    if(dp[i][j] != -1):
        check[i][j] = 0
        return dp[i][j]
    tmp1 = dpp(check,maze,dp,i+maze[i][j],j,n,m,i,j,first)
    tmp2 = dpp(check,maze,dp,i,j+maze[i][j],n,m,i,j,first)
    tmp3 = dpp(check,maze,dp,i-maze[i][j],j,n,m,i,j,first)
    tmp4 = dpp(check,maze,dp,i,j-maze[i][j],n,m,i,j,first)
    if(tmp1 == "inf" or tmp2 == "inf" or tmp3 == "inf" or tmp4 == "inf"):
        check[i][j] = 0
        dp[i][j] = 'inf'
        return "inf"
        # "inf"는 무한루프가 생겼음을 의미하며 하나라도 inf가 있다면 어떤경로가 더 최대인지 상관없이 inf만
        # 리턴하게되며 최종 리턴도 inf가 되어 마지막에 -1을 출력하게 된다.
    maximum = max(tmp1,tmp2,tmp3,tmp4)+1
    dp[i][j] = maximum
    check[i][j] = 0
    return maximum

n,m = input().split(" ")
n = int(n)
m = int(m)
maze = [[0]* (m+1) for i in range(n+1)]
check = [[0]* (m+1) for i in range(n+1)]

for i in range(n):
    a = input()
    for j in range(len(a)):
        if(a[j] != "H"):
            maze[i][j] = int(a[j])
        else:
            maze[i][j] = a[j]

dp = [[-1]* (m+1) for i in range(n+1)]
first = False
final = dpp(check,maze,dp,0,0,n,m,0,0,first)
if(final == "inf"):
    print(-1)
else:
    print(final)