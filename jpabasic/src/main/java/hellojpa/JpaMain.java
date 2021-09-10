package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
//            /** 새로운 Member 저장 **/
//            Member member = new Member();
//            member.setId(4L);
//            member.setName("HelloA");
//            em.persist(member);

//            /** Member 조회 **/
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());

//            /** Member 삭제  (조회 후, 조회한 객체 삭제 실행) **/
//            em.remove(findMember);

//            /** 기존의 Member 수정  (조회 후 객체 내 데이터 변경) **/
//            findMember.setName("HelloJPA");
            // 저장하지 않아도 자동으로 update 쿼리 전송

//            // 비영속 상태
//            Member member = new Member();
//            member.setId(100L);
//            member.setName("LOSA");
//
//            System.out.println("persist 이전");
//            em.persist(member); // 영속화
//            System.out.println("persist 이후");
//
//            // 조회
//            Member findMember = em.find(Member.class, 100L);
//
//            // 조회 결과 출력
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());


//            Member findMember1 = em.find(Member.class, 100L);
//            Member findMember2 = em.find(Member.class, 100L);
//
//            System.out.println("findMember1 = " + findMember1);
//            System.out.println("findMember2 = " + findMember2);
//            System.out.printf("findMember1 == findMember2 -> %b\n", findMember1 == findMember2);


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
