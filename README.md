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