package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager entityManager = emf.createEntityManager();

        // 데이터를 변경하는 모든 작업은 트랜잭션 안에서 해야한다.
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Member member = new Member();
        member.setId(2L);
        member.setName("HelloB");

        entityManager.persist(member);

        entityTransaction.commit();


        entityManager.close();

        emf.close();
    }
}
