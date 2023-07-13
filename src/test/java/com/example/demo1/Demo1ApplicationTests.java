package com.example.demo1;

import com.example.demo1.hobby.domain.entity.Hobby;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.swing.text.html.parser.Entity;

@SpringBootTest
@Transactional
@Commit
class Demo1ApplicationTests {
	@Autowired
	EntityManager em;

	@Test
	void contextLoads() {
		Hobby 축구 = new Hobby(1l, "축구");
		// 축구 취미를 영속화 (INSERT)
		em.persist(축구);

		//em.flush();는 업데이트기능을 했을 때 업데이트 농구 전에 테이블을 보여주고 그 다음 농구
		// 영속성 컨텍스트를 플러시하여 쿼리 실행 (UPDATE)
		em.flush();

		//em.clear();는 기존 테이블을 없애고 바로 농구
		// 영속성 컨텍스트 초기화
		em.clear();
		축구.setName("농구");

//		아이디는 뭐!, 네임은 뭐! 빌드를 해버린다? 여기서 l은 private Long id;여기서 long임
		Hobby 배구 = Hobby.builder().id(2l).name("배구").build();
		em.persist(배구);

		Hobby hobby = em.find(Hobby.class, 2l);
		System.out.println(hobby);
		System.out.println(hobby == 배구);

		Hobby hobby2 = em.find(Hobby.class, 1l);
		Hobby hobby3 = em.find(Hobby.class, 3l);
		System.out.println("축구 == hobby2" + (축구 == hobby2));
		System.out.println(hobby3);
	}
}

//	@Test
//	@Transactional
//	void 데이터많이넣기() throws Exception{
//		for (int i = 0; i < 10; i++) {
//			Hobby 축구 = new Hobby((long) i, "축구");
//			em.persist(축구);
//			if(i == 7) throw new Exception();
//			if(i % 2 == 0) em.flush();
//			if(i == 5) em.clear();
//		}
//	}

