## 주제
이 과제의 목적은 간단한 결제 시스템의 백엔드 구현을 통해 지원자의 API 개발, 데이터
모델링, 비즈니스 로직 처리 및 테스트 작성 능력을 평가하는 것입니다.

## 요구사항
 - API 구현: 아래 정의된 세 개의 RESTful API 엔드포인트를 구현하세요.
 - Request & Response: 각 API의 요청과 응답 형식은 아래에 명시된 대로 구현해야
합니다.
 - 데이터베이스 연동: 이 시스템은 사용자와 결제 정보를 저장할 수 있는
데이터베이스(H2)와 연동되어야 합니다. 관련 스키마 설계 및 구현이 필요합니다.
 - 단위 테스트: 각 API에 대한 단위 테스트를 작성하여 기능이 올바르게 동작하는지
검증하세요.


---
## 기술 스택
- Gradle
- Java 17
- Spring Boot 3.2.3
- Spring Data JPA (Hibernate)
- H2 Database (in-memory)
- Swagger (OpenAPI 3.0)
- Junit5

---
## 실행 방법
 - 프로젝트 루트 디렉토리에서 다음 명령어를 실행합니다.
    ```shell
    ./gradlew bootRun
    ```
## [API 명세](http://localhost:8080/swagger-ui/index.html)
1. 잔액 조회: /api/payment/balance
2. 결제 예상 결과 조회: /api/payment/estimate
3. 결제 승인 요청: /api/payments/approval
---
## 테스트 케이스  
 > data.sql에 초기 데이터가 적용되어 있습니다.
- test_1 : 잔액 조회
   - test_1_2 : userId가 없는 경우
- test_2 : 결제 예상 결과 조회
  - test_2_2 : merchantId가 없는 경우
- test_3 : 결제 승인 요청
  - test_3_2 : 잘못된 CVV인 경우
---