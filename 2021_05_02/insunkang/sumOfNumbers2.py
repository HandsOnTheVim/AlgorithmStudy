n, m = map(int, input().split(" "))

numbers = list(map(int,input().split(" ")))
answer = 0

for i in range(len(numbers)):
    check = numbers[i]
    if check == m:
        answer += 1
        continue
    for j in range(i + 1, len(numbers)):
        check += numbers[j]
        if check > m:
            break
        elif check == m:
            answer += 1
            break
print(answer)