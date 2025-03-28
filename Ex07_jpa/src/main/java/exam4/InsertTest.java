package exam4;

import java.time.LocalDate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class InsertTest {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JpaEx01");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			
			Member4 user;
			user = new Member4("test1@test.com","홍길동", LocalDate.now());
			em.persist(user);
			user = new Member4("test2@test.com","홍길순", LocalDate.now());
			em.persist(user);
			user = new Member4("test3@test.com","김길동", LocalDate.now());
			em.persist(user);
			user = new Member4("test4@test.com","김길순", LocalDate.now());
			em.persist(user);
			user = new Member4("test5@test.com","김남제", LocalDate.now());
			em.persist(user);
			user = new Member4("test6@test.com","이순신", LocalDate.now());
			em.persist(user);
			user = new Member4("test7@test.com","김여제", LocalDate.now());
			em.persist(user);
			
			em.getTransaction().commit();
			System.out.println("가입 요청을 처리했습니다");
		} catch(Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		em.clear();
		emf.close();

	}

}
