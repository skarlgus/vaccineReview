# Toyroject
vaccinReview for README.md

### 📌방향성
> #### 접해보지 못한 새로운 기술에 도전하고 현재의 능력치를 점검 및 보완
---  

<br>

### 📌 개발 목표
#### ▶ 지난 1년간의 실무 경험을 토대로 개발환경구축부터 CI/CD까지 진행
#### ▶ 기존 1년간 사용하던 sping, mybatis, ui Tool(websquare5,nexactro17), egov를 제외하고 <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;새로운 툴 및 기술을 접하기 위해 spring-boot, jpa, thymeleaf, intelliJ, docker를 이용하여 진행
---

<br>

### 📌진행순서
#### 1. 프로젝트 방향성 및 개발목표 수립
#### 2. spring-boot maven 프로젝트 intelliJ 개발환경 구축
#### 3. oracle서버 구축
#### 4. 개발
#### 5. CI/CD ( docker jenkins 자동 배포 )
---

<br>

### 📌기술스택
#### skills
<img src="https://img.shields.io/badge/java8-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/jpa-6DB33F?style=for-the-badge&logo=html5&logoColor=white"> <img src="https://img.shields.io/badge/thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white">

#### os
<img src="https://img.shields.io/badge/centOS-262577?style=for-the-badge&logo=centOS&logoColor=white"> <img src="https://img.shields.io/badge/widnows-0078D6?style=for-the-badge&logo=Windows&logoColor=white">

#### ide
<img src="https://img.shields.io/badge/intelliJ-000000?style=for-the-badge&logo=IntelliJ IDEA&logoColor=white">

#### ci/cd
<img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=Docker&logoColor=white"> <img src="https://img.shields.io/badge/jenkins-D24939?style=for-the-badge&logo=Jenkins&logoColor=white">

#### Collaboration & tools
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=Git&logoColor=white"> <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=GitHub&logoColor=white"> <img src="https://img.shields.io/badge/Sourcetree-0052CC?style=for-the-badge&logo=Sourcetree&logoColor=white"> <img src="https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=Slack&logoColor=white"> <img src="https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white"> <img src="https://img.shields.io/badge/ngrok-1F1E37?style=for-the-badge&logo=ngrok&logoColor=white">

#### db
<img src="https://img.shields.io/badge/Oracle19c-F80000?style=for-the-badge&logo=Oracle&logoColor=white">

#### framework
<img src="https://img.shields.io/badge/Spring Boot2.6.6-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white">


---  

<br>

### 📌개발
#### 대시보드
- 코로나19 감염 현황 API
- 코로나19 연령별·성별감염 감염 현황 현황 API
- 코로나19 백신 누적 API

#### 로그인
- 시큐리티 로그인 인증
- 시큐리티를 통한 pw암호화 db 저장

#### 소셜로그인
- 구글, 카카오
- 처음 소셜로그인 사용자의 경우 db유저 저장 그 후 로그인시 사진 및 이름 업데이트

#### 회원가입
- 필수입력 사항 체크
- 중복 회원 체크
- 비밀번호 체크

#### 비밀번호 찾기
- goole smtp를 통한 비번 초기화

#### 기타
- error핸들러를 통한 404페이지 컨트롤
---

<br>

### 📌배포
#### - centOS환경
#### - docker 에 jenkins이미지 컨테이너 구축후 github repository 연결하여 배포
#### - webhook을 이용하여 repository push 이벤트 발생시 jenkins자동배포
#### - jenkins 빌드 성공 및 실패시 slack을 통해 알림 메세지 발송
---

<br>

### 👨‍💻토이프로젝트 정리 velog
[CentOS 환경 세팅](https://velog.io/@rlgus0419/CentOS-%EC%84%B8%ED%8C%85)

[오라클 서버 구축](https://velog.io/@rlgus0419/Oracle-%EC%84%9C%EB%B2%84-%EA%B5%AC%EC%B6%95)

[docker 컨테이너 만들기](https://velog.io/@rlgus0419/docker-%EC%BB%A8%ED%85%8C%EC%9D%B4%EB%84%88-%EB%A7%8C%EB%93%A4%EA%B8%B0)

[Jenkins 자동배포 후 Slack 알람](https://velog.io/@rlgus0419/Jenkins-%EC%9E%90%EB%8F%99%EB%B0%B0%ED%8F%AC-%ED%9B%84-Slack-%EC%95%8C%EB%9E%8C)

