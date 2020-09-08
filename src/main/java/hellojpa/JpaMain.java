package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 쓰레드간 공유하면 안된다.
        EntityManager entityManager = emf.createEntityManager();

        // 데이터를 변경하는 모든 작업은 트랜잭션 안에서 해야한다.
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
/*//            Member findMember = entityManager.find(Member.class, 1L);
//            findMember.setName("HelloJPA");

            //전체 데이터를 조회하고 싶을 때, JPQL(엔티티 객체를 대상으로 하는 객체 지향 쿼리 언어로 보면 됨) 사용
            List<Member> result = entityManager.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }

            // 비영속
            Member member = new Member();
            member.setId(100L);
            member.setName("HelloJPA");

            Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");

            // 영속
            entityManager.persist(member);

            entityManager.persist(member1);
            entityManager.persist(member2);
            System.out.println("==== 영속성 컨텍스트 전에 쿼리가 나가는지 확인하는 선 ====");

            // 영속 된다고 DB에 저장 되는게 아님, 커밋을 해야 저장됨
            entityTransaction.commit();*/

/*            Member member = new Member();
            member.setUsername("C");

            entityManager.persist(member);

            entityTransaction.commit();*/

            Team team = new Team();
            team.setName("TeamA");
            entityManager.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeamId(team.getId());
            entityManager.persist(member);

            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }

        emf.close();
    }
}
