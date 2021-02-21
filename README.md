# JPA-quick-start

## JPA 퀵스타트 도서의 스터디/실습 진행. (저자: 채규태 님)

* [yes24 도서 판매 페이지 링크](http://www.yes24.com/Product/Goods/92287236)

## 책 진행에 부가적으로 진행하는하는 부분
* h2 대신 MySQL 8.0.21 DB, OracleXE 18c 사용 
  * Hyper-V의 CentOS 8.3 가상머신에 설치, 내부 네트워크로 통신
	* OracleXE는 CentOS 7.10에 설치
  * 4장 이전까지 MySQL, 4장중반부 부터 OracleXE 에서 테스트
* Gradle Build 환경으로 사용
* Slf4j & log4j2 를 사용하고 책의 Sysout 사용한 부분은 로거로서 출력.
* MyBatis 파트는 SqlSeesion만 만들어서 아래 /mybatis-dynamic-sql 라이브러리로 테스트
  * https://mybatis.org/mybatis-dynamic-sql/docs/quickStart.html  


## 의견
* main 테스트로 테스트 대신 JUnit으로 DAO를 테스트하는 식으로 진행하면 좋을 것 같습니다.
  * 프로젝트 설정시 JUnit 4 또는 5가 기본 사용가능한 상태가 되어, 사용이 어렵지는 않을 것 같습니다.
  * 나중에 Scanner로 입력을 받는 예제가 등장하는데.. 이부분은은 JUnit5의 @ParameterizedTest 로 처리하긴했습니다.
* Java 8 이상의 Lamda 식이나, 최신 날짜 클래스를 사용하면 좋을 것 같습니다.
   * 하이버네이트 요즘 버전에서는 Java 8의 LocalDate, LocalDateTime을 그대로 사용할 수 있음.
   * 반복 부분들을 람다 식으로 바꿔봐도 좋을것 같습니다. 
* p362~365 까지 log4j 추가가 있는데, 아래 내용이 들어가거나 변경되면 좋을 것 같습니다.
  * log4j2 slf4j기반으로 가이드가 되면 좋을 것 같음.
  * 로거 이름을 org.hibernate.type 까지보다는 org.hibernate.type.descriptor.sql.BasicBinder 까지 지정하고 레빌을 TRACE 로 유지했을 때, 바인딩로그만 보여서, 괜찮음.
* p471 :
  * 부서정보가 없는 직원을 검색하는 것에 대해서는, dept(s_dept)에 대한 fetch 없이 s_emp.dept_id 가 NULL인 것만 확인하면 되기 때문에, 
    일부러 FETCH문을 포함할 필요가 없을 것 같습니다.
* p490 : trim 함수 사용부분에 Character.valueOf('부')로 되어있는데.. '부'로만 써도 될 것 같습니다.
* p495 : 컬렉션 함수 이용하기 도입부 설명에서 GROUP BY 절과 HAVING 절을 이용했다고 적혀있는데, 실제로 JOIN하고 서브쿼리로 쿼리가 실행되어서, 조금 설명이 해깔리는 것 같습니다.
* p526 ~ 529
  * failed to lazily initialize a collection of role 관련 예외에 대해서, 
    부서 조회후 트랜젝션이 끝난 상태에서, 거기에 속한 직원을 조회하려해서 저런 예외가 난 것 같습니다.
    부서 조회와 내부의 직원 컬렉션을 조회하는 부분을 트랜젝션 범위에 둔뒤에 실행할 때는 예외가 발생하지 않았습니다.
    부서가 소유한 직원컬렉션을 EAGER 전략으로 사용하는 것외에도 트랜젝션 관련 설명이 추가되면 좋을 것 같습니다.

## 정오표  
* p175 : 동기화 잡업 -> 동기화 작업
* p470 : 
  * le(), lessThanOrEqualTo() 항목
    * 메서드는 맞는데, 값만 25400.00 에서 121500.00 으로 변경필요.
  * lessThan() 항목
    * `builder.graterThan(emp.<Double>.get("salary"), 25400.00)` -> `builder.lessThan(emp.<Double>.get("salary"), 121500.00)`
* p474 : 주석 부분에서  `AND emp.salary >= 5000/00` 의 우변 값 부분을 `35000.00` 으로 변경
* p507 : 공퉁으로 -> 공통으로
* p609, p636, 637 : 영속성 유닛 설정 이름 부분 "RubbyShopping" -> "RubyShopping", "RubbyShoppingClient" -> "RubyShoppingClient"
  * rubby 란 단어가 알콜중독자란 뜻이 있어서 왠만하면 고치는게 낫겠습니다.
* p624 : Hibernate 쿼리실행결과에서, Item엔티티의 테이블 명을 S_ITEM으로 설정했다면..
  Item 엔티티의 테이블 생성구문, 외래키 설정 구문에 나타난 테이블 이름 들이 S_ITEM으로 나와야할텐데, S_ORDER_PRODUCT 로 표시되어있습니다.

## 기타
* Gradle
  * Windows 8.1 과 Gradle 6.4.1 이상에서 빌드 문제가 있다.
    * 빌드시 계속 데몬을 실행시켜, Java프로세스가 무한정 늘어난다. 
    * 6.4 버전에서는 이슈 없음.
* IntelliJ
  * JPA Console로 SELECT조회하면 persistence.xml의 
    hibernate.hbm2ddl.auto=create 설정을 다시 읽어서 조회를 하는지?
    DB의 데이터가 삭제된다.
    * DB에서 S_EMP와 S_DEPT를 제거하고 JPA Console로 Employee를 조회했을 때,
  테이블이 생기는것을 확인함, 그동안 못느꼈던 원인은 아마도 update되어있던 상태였태였었을 것 같다.
    
