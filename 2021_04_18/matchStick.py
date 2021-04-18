
global match_stick
match_stick = [6,2,5,5,4,5,6,3,7,6]
global answer
answer = [0, int(1e9)]


def findMax(number, index, now):

    if index == 0:
        for i in range(9,0,-1):
            if match_stick[i] <= number:
                if number == match_stick[i]:
                    answer[0] = max(answer[0], i)
                else:
                    findMax(number-match_stick[i], index + 1, i)
    else:
        for i in range(9,-1,-1):
            if match_stick[i] <= number:
                if number == match_stick[i]:
                    answer[0] = max(answer[0], int(str(now)+str(i)))
                else:
                    findMax(number-match_stick[i], index + 1, int(str(now)+str(i)))


def findMin(number, index, now):

    if index == 0:
        for i in range(1,10):
            if match_stick[i] <= number:
                if number == match_stick[i]:
                    answer[1] = min(answer[1], i)
                else:
                    findMin(number-match_stick[i], index + 1, i)
    else:
        for i in range(0,10):
            if match_stick[i] <= number:
                if number == match_stick[i]:
                    answer[1] = min(answer[1], int(str(now)+str(i)))
                else:
                    if int(str(now)+str(i)) < answer[1]:
                        findMin(number-match_stick[i], index + 1, int(str(now)+str(i)))


value = int(input())
inputs = []
for _ in range(value):
    inputs.append(int(input()))

for i in inputs:
    findMin(i,0,0)
    findMax(i,0,0)
    result = str(answer[1])+" "+str(answer[0])
    print(result)
    answer[0] = 0
    answer[1] = int(1e9)






