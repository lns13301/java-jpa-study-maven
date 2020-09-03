# java-jpa-study-maven

## 학습 목표

- JPA를 실전으로 써보자

## 용어 정리

- 트랜잭션 : 데이터베이스의 상태를 변환시키는 하나의 논리적 기능을 수행하기 위한 작업의 단위
- 영속성 컨텍스트 : "엔티티를 영구 저장하는 환경", JPA를 이해하는데 가장 중요한 용어
    - 이점들...
    - 1차 캐시
    - 동일성(identity) 보장
    - 트랜잭션을 지원하는 쓰기 지연
    - 변경 감지 (Dirty Checking)
    - 지연 로딩 (Lazy Loading)
- 플러시 : 영속성 컨텍스트의 변경내용을 데이터베이스에 반영
    - 다음과 같은 상황에서 플러시가 동작함
    - entityManager.flush();
    - entityTransaction.commit();
    - JPQL 쿼리 실행
- 준영속 상태 : 영속 상태의 엔티티가 영속성 컨텍스트에서 분리
    - entityManager.detach(entity);
    - entityManager.clear();  // 테스트 코드 작성 시 눈으로 확인할 때 용이하게 사용함
    - entityManager.close();
- DDL 생성 기능
    - 제약조건 추가 : 회원 이름은 필수, 10자 초과 X
        - @Column(nullable = false, length = 10)
    - DDL 생성 기능은 DDL 을 자동 생성 할 때만 사용되고 JPA의 실행 로직에는 영향을 주지 않는다.
        - 이와 다르게 @Table(name = "MBR") 과 같은 테이블 명을 바꾸는 것은 런타임에 영향을 줌
- 기본키 매핑 방법
    - 직접 할당 : @Id만 사용
    - 자동 생성(@GeneratedValue)
        - IDENTITY: 데이터베이스에 위임, MYSQL
        - SEQUENCE: 데이터베이스 시퀀스 오브젝트 사용, ORACLE
            - @SequenceGenerator 필요
        - TABLE: 키 생성용 테이블 사용, 모든 DB에서 사용
            - @TableGenerator 필요
        - AUTO: 방언에 따라 자동 지정, 기본값

## 내용 정리

- 권장하는 식별자 전략
    - 기본 키 제약 조건: null 아님, 유일, 변하면 안됨
    - 미래까지 이 조건을 만족하는 자연키는 찾기 어렵다. 대리키(대체키)를 사용하자.
    - 예를 들어 주민등록번호도 기본 키로 적절하지 않다.
    - 권장: Long형 + 대체키(예: Auto Increment, Sequence Object, UUID, 랜덤 값 조합) + 키 생성 전략 사용
- Identity 전략
    - 기본 키 생성을 데이터베이스에 위임
    - Identity 일 때만 특이하게 insert 쿼리를 entityManager.persist() 할 때 날린다.
        - PK 값이 필요한데 Identity 는 DB에 쿼리가 보내지고 나서 PK 가 생성되기 때문에 예외로 처리한다.
    - 모아서 Insert 하는게 Identity 전략의 단점 이다.
- Sequence 전략
    - allocationSize 기본 값 50, 시퀀스 한 번 호출에 증가하는 수(성능 최적화에 사용됨)
    - nextCall 한 번할 때, DB에 50개를 미리 호출해서 세팅해두고 메모리에서 1씩 사용, 50개를 다 쓰고나서 호출하면 다시 50개를 미리 호출
    - 여러 웹 서버가 있어도 동시성 이슈없이 다양한 문제를 해결 가능함
    