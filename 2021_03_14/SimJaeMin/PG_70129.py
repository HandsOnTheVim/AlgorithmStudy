# 이진 변환 반복하기
# https://programmers.co.kr/learn/courses/30/lessons/70129

def solution(s):
    answer = []
    ans_cnt = 0
    ans_zero = 0
    while s != '1':
        cnt = s.count('0')
        s = format(len(s) - cnt, 'b')
        ans_zero += cnt
        ans_cnt += 1

    answer.append(ans_cnt)
    answer.append(ans_zero)
    return answer