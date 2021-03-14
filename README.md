# AlgorithmStudy

## 처음 해야할 일

### 1. Clone

```
git clone https://github.com/HandsOnTheVim/AlgorithmStudy.git .
```

### 2. 브런치 생성

```
git checkout -b [브런치명]

# 브런치 생성과 이동이 한번에 됨
# ex) git checkout -b BSJ
```



## 매주 해야할 일

### 1. 폴더 생성해서 소스복사

```
mkdir yyyy_MM_dd
cd yyyy_MM_dd
mkdir [개인별폴더명]
# 폴더에 소스코드 복사

# ex) 
# mkdir 2021_03_14
# cd 2021_03_14
# mkdir BSJ
```



### 2. 문제 풀고 원격Repo에 업로드

```
git add .
git commit -m '[메시지]'
git push origin [본인의 브런치명]
```



### 2. Pull requests 생성 ( == merge request)

- https://github.com/HandsOnTheVim/AlgorithmStudy 에서 본인의 브런치를 선택하면 pull request를 하라는 메뉴가 상단에 보여짐
- pull request를 선택하고 assignes에 본인 reviewers에 팀원들을 선택하고 완료





## 참고

- master 브런치에 직접 push X

### 컨벤션

- 자바 컨벤션 ide(이클립스, 인텔리제이)에 적용 : https://github.com/naver/hackday-conventions-java
- 파이썬 컨벤션(pep8) vscode에 적용 : https://m.blog.naver.com/wideeyed/222012432788
- 파이썬 컨벤션(pep8) pycharm에 적용 : http://guswnsxodlf.github.io/check-pep8-on-pycharm

- 읽기쉬운코드가 좋은코드다 : https://www.slideshare.net/ddayinhwang9/ss-61286734