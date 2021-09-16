package jpql;

import javax.lang.model.SourceVersion;
import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            //insert. 멤버셋팅
//            Member member1 = new Member("장성용", 37);
//            em.persist(member1);
//            Member member2 = new Member("권다애", 36);
//            em.persist(member2);

            //1. 조회쿼리 예시 (엔티티 프로젝션)
//            List<Member> resultList  = em.createQuery("select m from Member as m where m.username = :username", Member.class)
//                    .setParameter("username", "장성용")
//                    .getResultList();
//
//            for (Member member : resultList) {
//                System.out.println("member.getUsername() = " + member.getUsername());
//                System.out.println("member.getAge() = " + member.getAge());
//                member.setAge(20); // 영속성 컨텍스트에서 관리된다는 것을 알 수 있음
//            }

            // insert. 팀 입력 + 업뎃팀

//            Team team  = new Team("에이브로스");
//            em.persist(team);
//
//            em.createQuery("select m from Member m", Member.class).getResultList().forEach(member -> member.setTeam(team));
//
//            Team team2 = new Team("어글리고릴라");
//            em.persist(team2);

            //3. 엔티티 프로젝션 예시
//            List<Team> resultProjection = em.createQuery("select distinct t from Member m join m.team t", Team.class).getResultList();
//
//            for (Team team : resultProjection) {
//                System.out.println("team.getName() = " + team.getName());
//            }


            // insert. order
//            Member member = em.find(Member.class, 1L);
//            Product product = new Product("포스트잇", 1000, 100);
//            em.persist(product);
//
//            Order order = new Order(2, new Address("세종시", "조치원읍", "64012"), member, product);
//            em.persist(order);

            //4. 임베디드 타입 프로젝션
//            List<Address> resultList = em.createQuery("select o.address from Order as o", Address.class).getResultList();
//            for (Address address : resultList) {
//                System.out.println("address.getCity() = " + address.getCity());
//            }

            //5. 스칼라 타입 프로젝션 (타입변환)
//            List resultList = em.createQuery("select m.username, m.age from Member m").getResultList();
//
//            for (Object o : resultList) {
//                Object[] obj = (Object[]) o;
//                System.out.println("o[0] = " + obj[0]);
//                System.out.println("o[0] = " + obj[1]);
//            }

            //6. 스칼라 타입 프로젝션 (제네릭)
//            List<Object[]> resultList = em.createQuery("select m.username, m.age from Member m").getResultList();
//
//            for (Object[] o : resultList) {
//                System.out.println("o[0] = " + o[0]);
//                System.out.println("o[0] = " + o[1]);
//            }

            //7. 스칼라 타입 프로젝션 (DTO) 꼭 new 패키지명(인자1, 인자2)오퍼레이션을 사용해야 합니다. 마치 생성자를 호출하듯이!!
//            List<MemberDTO> resultList = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class).getResultList();
//
//            for (MemberDTO dto : resultList) {
//                System.out.println("dto.getUsername = " + dto.getUsername());
//                System.out.println("dto.getAge() = " + dto.getAge());
//            }


            // insert. Member 대량 입력
//            for (int i=0; i < 100; i++){
//                Member member = new Member("맴버"+i, i);
//                em.persist(member);
//            }

            //8. 페이징
//            List<Member> result = em.createQuery("select m from Member as m order by m.id", Member.class)
//                    .setFirstResult(10)
//                    .setMaxResults(10)
//                    .getResultList();
//
//            for (Member member : result) {
//                System.out.println("member.getId() = " + member.getId());
//                System.out.println("member.getAge() = " + member.getAge());
//                System.out.println("member.getUsername() = " + member.getUsername());
//            }

            //9. 내부 조인 (inner join 또는 join)
//            List<Member> resultList = em.createQuery("select m from Member as m inner join m.team t where t.name=:teamName", Member.class)
//                    .setParameter("teamName", "에이브로스")
//                    .getResultList();
//
//            for (Member member : resultList) {
//                System.out.println("member.getUsername() = " + member.getUsername());
//            }

            //10. 외부 조인 (left outer join 또는 left join)
//            List<Member> resultList = em.createQuery("select m from Member as m left outer join m.team", Member.class)
//                    .getResultList();
//
//            for (Member member : resultList) {
//                System.out.println("member.getUsername() = " + member.getUsername());
//            }


            // insert 세타 조인 확인을 위한 맴버 추가 입력
//            Team team = new Team("뾰로롱");
//            em.persist(team);
//
//            Member member1 = new Member("에이브로스", 7);
//            member1.setTeam(team);
//            em.persist(member1);


            //11. 세타 조인 (모든 데이터 조인 후 조건에 맞는 경우를 필터링)
//            List<Member> resultList = em.createQuery("select m from Member m, Team t where m.username = t.name", Member.class)
//                    .getResultList();
//            for (Member member : resultList) {
//                System.out.println("member.getUsername() = " + member.getUsername());
//                System.out.println(" ================================================");
//                System.out.println("member.getTeam().getName() = " + member.getTeam().getName());
//            }

            //12. 조인 대상 필터링 
            // on 절을 이용한 조인 가능 / JPA 5.1부터 지원 / on은 조인하기 전 조건
//
//            List<Member> resultList = em.createQuery("select m from Member m left join m.team t on t.name='에이브로스'", Member.class)
//                    .getResultList();
//            for (Member member : resultList) {
//                System.out.println("member.getUsername() = " + member.getUsername());
//            }

            //12. 연관관계 없는 엔티티 외부 조인
            // on 절을 이용한 조인 가능 / JPA 5.1부터 지원 / on은 조인하기 전 조건
//
//            List<Member> resultList = em.createQuery("select m from Member m left join Team t on m.username = t.name", Member.class)
//                    .getResultList();
//            System.out.println("resultList.size() = " + resultList.size());

            //13. 서브쿼리 : 나이가 평균보다 많은 회원
            List<Member> memberList = em.createQuery("select m from Member as m  where m.age > (select avg(m2.age) from Member as m2)", Member.class)
                    .getResultList();
            System.out.println("resultList.size() = " + memberList.size());



            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
