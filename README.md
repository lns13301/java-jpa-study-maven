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
