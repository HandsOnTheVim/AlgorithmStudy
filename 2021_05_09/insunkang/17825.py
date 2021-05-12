# basic = [2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,-1]
# corner10 = [13,16,19,25,30,35,40,-1]
# corner20 = [22,24,25,30,35,40,-1]
# corner30 = [28,27,26,25,30,35,40,-1]
#
#
#
def go(horse, count, result, state):
    global gridDict, scoreDict, answer, inputArr
    copyState = state.copy()
    nowDice = inputArr[count] - 1
    copyState[horse] = gridDict[copyState[horse]][nowDice]
    nowScore = scoreDict[copyState[horse]]

    if count == 9: answer = max(answer, result + nowScore); return

    nextDice = inputArr[count + 1] - 1
    for i in range(4):
        if copyState[i] == -1: continue
        if gridDict[copyState[i]][nextDice] != -1 and gridDict[copyState[i]][nextDice] in copyState: continue

        go(i, count + 1, result + nowScore, copyState)


inputArr = list(map(int, input().split()))
gridDict = {
    0: [1, 2, 3, 4, 5],
    1: [2, 3, 4, 5, 6],
    2: [3, 4, 5, 6, 7],
    3: [4, 5, 6, 7, 8],
    4: [5, 6, 7, 8, 9],
    5: [21, 22, 23, 24, 25],
    6: [7, 8, 9, 10, 11],
    7: [8, 9, 10, 11, 12],
    8: [9, 10, 11, 12, 13],
    9: [10, 11, 12, 13, 14],
    10: [27, 28, 24, 25, 26],
    11: [12, 13, 14, 15, 16],
    12: [13, 14, 15, 16, 17],
    13: [14, 15, 16, 17, 18],
    14: [15, 16, 17, 18, 19],
    15: [29, 30, 31, 24, 25],
    16: [17, 18, 19, 20, -1],
    17: [18, 19, 20, -1, -1],
    18: [19, 20, -1, -1, -1],
    19: [20, -1, -1, -1, -1],
    20: [-1, -1, -1, -1, -1],
    21: [22, 23, 24, 25, 26],
    22: [23, 24, 25, 26, 20],
    23: [24, 25, 26, 20, -1],
    24: [25, 26, 20, -1, -1],
    25: [26, 20, -1, -1, -1],
    26: [20, -1, -1, -1, -1],
    27: [28, 24, 25, 26, 20],
    28: [24, 25, 26, 20, -1],
    29: [30, 31, 24, 25, 26],
    30: [31, 24, 25, 26, 20],
    31: [24, 25, 26, 20, -1]
}
scoreDict = {
    -1: 0, 0: 0, 1: 2, 2: 4, 3: 6, 4: 8, 5: 10, 6: 12, 7: 14, 8: 16, 9: 18, 10: 20,
    11: 22, 12: 24, 13: 26, 14: 28, 15: 30, 16: 32, 17: 34, 18: 36, 19: 38, 20: 40, 21: 13, 22: 16,
    23: 19, 24: 25, 25: 30, 26: 35, 27: 22, 28: 24, 29: 28, 30: 27, 31: 26
}
answer = 0
state = [0, 0, 0, 0]
go(0, 0, 0, state)
print(answer)