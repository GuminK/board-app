# 게시글 사이트

React와 Spring Boot를 사용해 만든 게시판 프로젝트입니다.  
회원가입, 로그인, 게시글 CRUD, 댓글 CRUD 기능을 구현했고, 세션 기반 인증과 권한 검증을 적용했습니다.  
Spring Boot 서버는 AWS EC2에 배포했고, 데이터베이스는 AWS RDS PostgreSQL을 사용했습니다.  
http://3.39.11.162:8080 (현재 중단 되었습니다 2026-04-29)

## 프로젝트 소개

이 프로젝트는 게시글과 댓글 기능이 있는 기본적인 게시판 서비스입니다.  
단순히 화면만 만드는 것에 그치지 않고, 로그인한 사용자만 글과 댓글을 작성할 수 있도록 했고, 작성자만 수정과 삭제를 할 수 있도록 구현했습니다.

또한 React와 Spring Boot를 분리해서 개발하면서 프론트엔드와 백엔드가 어떻게 연결되는지 직접 경험했고,
EC2와 RDS 를 연동해 외부 환경에서도 동작할 수 있도록 구성했습니다.

## 기술 스택

- Frontend: React, React Router, Axios
- Backend: Spring Boot, Spring Security, Spring Data JPA, Lombok
- Database: PostgreSQL
- Infra: AWS EC2, AWS RDS
- Build Tool: Gradle, npm


## 주요 기능

### 회원 기능
- 회원가입
- 로그인
- 로그아웃
- 현재 로그인 사용자 정보 조회

### 게시글 기능
- 게시글 목록 조회
- 게시글 상세 조회
- 게시글 작성
- 게시글 수정
- 게시글 삭제
- 게시글 조회수 증가
- 게시글 작성자만 수정/삭제 가능

### 댓글 기능
- 댓글 목록 조회
- 댓글 작성
- 댓글 수정
- 댓글 삭제
- 댓글 작성자만 수정/삭제 가능

## 구현하면서 신경 쓴 부분

### 1. 로그인한 사용자만 필요한 기능을 사용할 수 있도록 처리
Spring Security를 적용해서 로그인한 사용자만 글 작성, 댓글 작성, 수정, 삭제를 할 수 있도록 했습니다.

### 2. 작성자 권한 확인
게시글과 댓글은 단순히 프론트에서 버튼만 숨기는 것이 아니라, 백엔드에서도 현재 로그인한 사용자와 작성자를 비교해서 권한을 확인하도록 구현했습니다.

### 3. REST API 형태로 정리
초기에는 기능 중심 경로를 사용했지만, 이후 `/api/boards`, `/api/boards/{id}`, `/api/boards/{boardId}/comments`처럼 리소스 중심 경로로 정리했습니다.

### 4. 댓글 기능 확장 가능하도록 설계
현재는 일반 댓글만 사용하고 있지만, 댓글 엔티티에 부모 댓글을 참조할 수 있는 구조를 두어 대댓글 기능으로 확장할 수 있도록 설계했습니다.

### 5. 예외 상황 처리
게시글이나 댓글이 없는 경우, 권한이 없는 경우 등을 전역 예외 처리로 분리해서 상태코드를 다르게 응답하도록 했습니다.

## 어려웠던 점

### 1. 세션 로그인과 CSRF 처리
처음에는 로그인 이후에도 `POST`, `PUT`, `DELETE` 요청에서 403 에러가 발생했습니다.  
원인을 확인해보니 세션 기반 인증 환경에서는 CSRF 토큰 처리가 필요했고, 프론트와 백엔드 양쪽 설정을 함께 맞춰야 한다는 점을 알게 되었습니다.  
이후 CSRF 토큰 발급 API와 Axios 설정을 연결해서 정상적으로 요청이 처리되도록 수정했습니다.

### 2. 작성자 권한 검증
처음에는 프론트에서 수정/삭제 버튼만 숨기면 된다고 생각했지만, 실제로는 백엔드에서 현재 로그인 사용자와 작성자를 비교해 권한을 확인해야 한다는 점을 배웠습니다.  
이후 게시글과 댓글 모두 서비스 계층에서 작성자 검증 로직을 두고, 프론트에서는 사용자 경험을 위해 버튼을 조건부로 보여주도록 수정했습니다.

### 3. 댓글 수정 기능 처리
댓글 수정 기능을 다른 페이지로 이동해서 처리할지, 현재 화면에서 바로 수정할지 고민했습니다.  
최종적으로는 게시글 상세 페이지 안에서 바로 수정할 수 있도록 구현했고, 수정 후 댓글 목록을 다시 불러와 화면이 최신 상태를 유지하도록 처리했습니다.

### 4. API 경로 정리
처음에는 `/list`, `/create`, `/update`처럼 기능 중심으로 경로를 작성했지만, 진행하면서 REST API 방식에 맞게 `/api/boards`, `/api/boards/{id}`, `/api/boards/{boardId}/comments` 형태로 수정했습니다.  
이 과정을 통해 URL도 기능보다 리소스 중심으로 설계하는 것이 더 자연스럽다는 점을 알게 되었습니다.

### 5. 배포 환경에서의 DB연결
로컬에서는 바로 연결되던 데이터베이스를 AWS환경에서 다시 연결하는 과정이 쉽지 않았습니다.
EC2에 올린 SpringBoot 서버와 RDS PostgreSQL을 연결하면서 다양한 환경 설정, 접속 정보를 맞추어보는 과정을 경험했습니다.

## 프로젝트 구조

backend  
├─ config  
├─ controller  
│  ├─ api  
│  └─ web  
├─ domain  
├─ dto  
├─ exception  
├─ repository  
├─ security  
└─ service  

frontend  
├─ api  
├─ components  
│  ├─ comment  
│  └─ layout  
├─ context  
├─ pages  
└─ styles  

## 실행 방법 (로컬에서 실행시킬 떄)
### Backend
cd backend  
./gradlew bootRun  

### Frontend
cd frontend  
npm install  
npm start  

## 배포 환경
- SpringBoot 서버는 AWS EC2에 배포했습니다.
- 데이터베이스는 AWS RDS PostgreSQL을 사용했습니다.
- 서버와 데이터베이스를 연동해 외부 환경에서도 동작할 수 있도록 구성했습니다.
- 링크: http://3.39.11.162:8080/

![React App - Chrome 2026-04-05 17-28-53](https://github.com/user-attachments/assets/e5060c18-c097-40c4-9553-5fd59bf79455)
![React App - Chrome 2026-04-05 17-28-53 (1)](https://github.com/user-attachments/assets/584c9ad3-069c-4bf6-9f3e-2ae76af64d5a)

