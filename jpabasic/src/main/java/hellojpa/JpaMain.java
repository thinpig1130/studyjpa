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
            /** 새로운 Member 저장 **/
            Member member1 = new Member("Hello M", 30);
            Member member2 = new Member("Hello 2", 31);
            Member member3 = new Member("Hello 3", 32);

            System.out.println("============================");
            em.persist(member1);
            em.persist(member2);
            em.persist(member3);
            System.out.println("============================");


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
