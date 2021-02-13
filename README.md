# JPA-quick-start

## JPA 퀵스타트 도서의 스터디/실습 진행. (저자: 채규태 님)

* [yes24 도서 판매 페이지 링크](http://www.yes24.com/Product/Goods/92287236)

## 책 진행과 다르게 하는 부분
* h2 대신 MySQL 8.0.21 DB, OracleXE 18c 사용 
  * Hyper-V의 CentOS 8.3 가상머신에 설치, 내부 네트워크로 통신
  * 4장 이전까지 MySQL, 4장중반부 부터 OracleXE 에서 테스트
* Gradle Build 환경으로 사용
* Slf4j & log4j2 를 사용하고 책의 Sysout 사용한 부분은 로거로서 출력.
* MyBatis 파트는 SqlSeesion만 만들어서 아래 /mybatis-dynamic-sql 라이브러리로 테스트
  * https://mybatis.org/mybatis-dynamic-sql/docs/quickStart.html  



## 의견
* main 테스트로 테스트 대신 JUnit으로 DAO를 테스트하는 식으로 진행하면 좋을 것 같습니다.
  * 프로젝트 설정시 JUnit 4 또는 5가 기본 사용가능한 상태가 되어, 사용이 어렵지는 않을 것 같습니다.
* p362~365 까지 log4j 추가가 있는데, 아래 내용이 들어가거나 변경되면 좋을 것 같습니다.
  * log4j2 slf4j기반으로 가이드가 되면 좋을 것 같음.
  * 로거 이름을 org.hibernate.type 까지보다는 org.hibernate.type.descriptor.sql.BasicBinder 까지 지정하고 레빌을 TRACE 로 유지했을 때, 바인딩로그만 보여서, 괜찮음.
    

## 정오표  
* p175 : 동기화 잡업 -> 동기화 작업
