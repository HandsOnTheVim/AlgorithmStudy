# from collections import deque
# cro = ["c=","c-","dz=","d-","lj","nj","s=","z="]

# q = deque()

# n = str(input())

# answer = 0

# for i in range(len(n)):
#     q.append(n[i])
#     print(n[i])
#     if len(q) == 2:
#         if "".join(q) in cro:
#             # print("".join(q))
#             q.popleft()
#             q.popleft()
#         elif "".join(q) =="dz" and i + 1 < len(n) and n[i+1] == "=":
#             # print("".join(q))
#             q.popleft()
#             q.popleft()
#             continue
#             # print(i)
#         else:
#             q.popleft()
#         answer += 1
# answer += len(q)
# print(answer)

a = ['c=', 'c-', 'dz=', 'd-', 'lj', 'nj', 's=', 'z=']
alpha = input()
for t in a:
    alpha = alpha.replace(t, '*')
print(len(alpha))
