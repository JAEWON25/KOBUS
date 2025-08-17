# KOBUS 고속버스 예매 시스템 🚍

고속버스 노선 및 배차 조회부터 예매 전 과정까지 구현한 **JSP + Spring MVC (Legacy)** + **MyBatis** + **Oracle** 기반 학습 프로젝트입니다.  
출발지/도착지 선택 → 배차 조회 → 시간 선택 → 예약 생성까지의 흐름을 연결하여 구현했습니다.

(src/main/webapp -> main.do 로 실행)

---

## 📌 핵심 기능
[역할]
- **지역/터미널 선택 모달**
  - `sidoCode` 기반 AJAX 요청으로 터미널 목록 조회
  - 출발/도착지 선택 시 hidden form에 자동 반영
- **배차 조회**
  - 선택한 **출발지, 도착지, 날짜**를 기반으로 배차 리스트 조회
- **소요시간 표시**
  - `route` 테이블의 `durMin` 값을 AJAX로 조회하여 노선 소요시간 표시
- **예매 흐름**
  - 배차 선택 → 좌석/예약 화면으로 이동 (좌석 잔여 수량 관리 기능은 추후 확장 예정)

---

## 🛠 기술 스택

- **Backend**: Spring MVC (Legacy), MyBatis
- **Frontend**: JSP, jQuery, jQuery UI (Calendar)
- **DB**: Oracle 
- **Build/Run**: Tomcat, Java 17
- **기타**: Gson (AJAX JSON 응답), Lombok

---

## 📂 프로젝트 구조

<img width="544" height="348" alt="image" src="https://github.com/user-attachments/assets/365336f6-23e1-43a5-8d8d-1c0f1c7db0c2" />


---

## 🔗 주요 API 엔드포인트

| 메서드 | 경로                | 설명                     | 파라미터 예시 |
|--------|----------------------|--------------------------|--------------|
| GET    | `/getTerminals.do`   | 시/도 코드로 터미널 조회 | `sidoCode=11` |
| GET    | `/getDuration.ajax`  | 소요시간 조회            | `deprCd=11&arvlCd=22` |
| GET    | `/getDuration.ajax`  | 배차 조회 (동일 URL 분기) | `deprCd=11&arvlCd=22&rideDate=2025-06-19` |
| POST   | `/mrs/alcnSrch.do`   | 배차 검색 폼 전송        | hidden 필드 다수 |

---

## 🗄 DB 개요

- **region**: 시/도/터미널 정보
- **route**: 노선 정보 + `durMin`(소요시간)
- **busschedule**: 배차 스케줄
- **company, bus, seat**: 운행사/버스/좌석 정보
- **reservation**: 예약 정보
- **kobususer**: 사용자 정보


