package com.example.demo;

import com.example.demo.config.domain.entity.MemberLogin;
import com.example.demo.config.repository.MemberLoginRepository;
import com.example.demo.members.domain.entity.Member;
import com.example.demo.members.domain.entity.QMember;
import com.example.demo.members.domain.request.LoginRequest;
import com.example.demo.members.domain.response.LoginResponse;
import com.example.demo.members.repository.MemberRepository;
import com.example.demo.members.service.MemberService;
import com.example.demo.todos.domain.dto.TodoCondition;
import com.example.demo.todos.domain.entity.QTodo;
import com.example.demo.todos.domain.entity.Todo;
import com.example.demo.todos.repository.TodoRepository;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@Transactional
class DemoApplicationTests {


	@Test @Transactional
	void contextLoads() {
		memberLoginRepository.findFirstByMemberIdAndEndAtAfterOrderByEndAtDesc(1l, LocalDateTime.now());
	}
	@Test
	void test(){
		QMember member = new QMember("m");
		JPAQueryFactory queryFactory =
				new JPAQueryFactory(entityManager);
		// select m from member m
		// where name = null
		String name = "a";
		BooleanExpression nameEq = name != null ? member.name.eq(name) : null;
		member.name.like("%"+name+"%");
		Integer age = 20;
		BooleanExpression ageLoe = age == null ? null : member.age.loe(age);
		JPAQuery<Member> from = queryFactory
				.select(member)
				.from(member)
				.where(
						nameEq, ageLoe
				);
		List<Member> fetch = from.fetch();
		System.out.println();

	}

	@Test
	void test2(){
//		select member from member where age <= 10 and age > 5 and name != "na"
	}

	@Test
	void test3(){
//		select member from member where age <= 10 and age > 5 and name != "na"
		QMember qMember = QMember.member;
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		JPAQuery<Member> query = queryFactory.selectFrom(qMember)
				.leftJoin(qMember.todos)
				.fetchJoin()
				.where(qMember.age.loe(10)
						, qMember.age.gt(5)
						, qMember.name.ne("na"))
				.offset(0)
				.limit(20)
				.orderBy(qMember.age.desc());


		queryFactory.select(qMember.count()).from(qMember);
		List<Member> fetch = query.fetch();

		for (Member m:fetch) {
			System.out.println(m.getTodos().size());
		}
		System.out.println();

	}
	@Test
	void test5(){
		QMember qMember = QMember.member;
		JPAQueryFactory queryFactory =
				new JPAQueryFactory(entityManager);
		List<String> vv = queryFactory
				.select(qMember.name
						.concat("님 ")
						.concat(qMember.age.stringValue())
						.concat("살 입니다.")
				)

				.from(qMember).fetch();
		System.out.println(vv);
	}
	@Test
	void test6(){
		QMember qMember = QMember.member;
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		Member member1 = entityManager.find(Member.class, this.member.getId());
		Member member2 = memberRepository.findById(this.member.getId()).get();
		// select member from member where id = ?
		Member member3 = queryFactory
				.selectFrom(qMember)
				.where(qMember.id.eq(this.member.getId()))
				.fetchOne();
		Assertions.assertEquals(member1, member2);
		Assertions.assertEquals(member1, member3);
		Assertions.assertEquals(member2, member3);
		Assertions.assertNotEquals(member1, this.member);
	}
	@Test
	void test7(){
//		작성자 이름이 name 이고,
//		좋아요를 10 개 이상 받았고, 내용에 t 가 들어간 게시물들
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		QTodo qTodo = QTodo.todo;
//		select * from todo
//		where todo.member.name = "name"
//		and todo.likeCount >= ?
//		and todo.content like %t%
		List<Todo> fetch = queryFactory.selectFrom(qTodo)
				.where(
						qTodo.member.name.eq("name"),
						qTodo.likeCount.goe(10),
						qTodo.content.contains("t")
				).fetch();
//		result 30
		Assertions.assertEquals(fetch.size(), 30);
	}
	@Test
	void test8(){
//		작성자 이름이 name 이고,
//		좋아요를 10 개 이상 받았고, 내용에 t 가 들어간 게시물들
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		QMember qMember = QMember.member;
//		select * from members m
//		left join todos t on t.member_id = m.id
//		where m.name = "name"
//		and t.like_count >= 10
//		and t.content like %t%


//		select case when then else end
		QTodo qTodo = QTodo.todo;
		List<Member> fetch = queryFactory.
				select(qMember)
				.from(qMember)
				.leftJoin(qMember.todos, qTodo)
				.fetchJoin()
				.where(
						qMember.name.eq("name"),
						qTodo.content.contains("t"),
						qTodo.likeCount.goe(10)
				)
				.fetch();
		/*			select member,todos from member
		 * 			left join member.todos
		 * 			where m.name = name and t.content like %t% and t.likeCount >= 10
		 */
//		result 30

		Assertions.assertEquals(fetch.get(0).getTodos().size(), 30);
	}
	@Test
	void test9(){
		QMember qMember = QMember.member;

// 상태 정지 가는중 대기중
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		queryFactory.select(
						qMember.age.sum(),
						qMember.age.avg())
				.from(qMember);
		queryFactory
				.select(qMember)
				.from(qMember)
				.where(qMember.id.in(
						JPAExpressions.select(QTodo.todo.member.id)
								.from(QTodo.todo)
								.where(QTodo.todo.content.contains("t"))
				))
		;
//		select case when m.age >=10 and m.age< 20 then '10대'
		String s = queryFactory.select(
				new CaseBuilder()
						.when(qMember.age.between(10, 20))
						.then("10대")
						.otherwise("노인")
						.as("hi")
		).from(qMember).fetchOne();
		System.out.println(s);


	}
	QMember qMember = QMember.member;
	QTodo qTodo = QTodo.todo;
	@Test
	void test10(){
		// init data , given
		JPAQueryFactory queryFactory =
				new JPAQueryFactory(entityManager);
		TodoCondition condition = TodoCondition.builder()
				.build();
		PageRequest request = PageRequest.of(0,20);


		JPAQuery<Todo> limit = queryFactory
				.select(qTodo)
				.from(qTodo)
				.leftJoin(qTodo.member, qMember)
				.fetchJoin()
				.where(
						contentContains(condition.getContent()),
						titleEq(condition.getTitle())
				)
				.offset(request.getPageNumber())
				.limit(request.getPageSize());
		List<Todo> fetch = limit.fetch();
		Assertions.assertEquals(fetch.size(),20);
	}

	private BooleanExpression contentContains(String content) {
		return content == null
				? null
				: qTodo.content.contains(content);
	}

	private BooleanExpression titleEq(String title) {
		return title == null
				? null
				: qTodo.title.eq(title);
	}

	@Autowired
	MemberRepository memberRepository;
	@Autowired
	TodoRepository todoRepository;
	@Autowired
	MemberLoginRepository memberLoginRepository;
	String email = "1111";
	String password = "1234";
	Member member;
	@Autowired
	EntityManager entityManager;
	@Autowired
	MemberService memberService;
	String token;
	Todo todo;
	@BeforeEach
	void init(){
		Member member =
				new Member(null, email, password
						, "name", 10, new ArrayList<>(), null);

		this.member = memberRepository.save(member);
		this.todo = todoRepository.save(
				new Todo(null, "a", "a"
						, false, 0, member)
		);
		for (int i = 0; i < 40; i++) {
			todoRepository.save(
					new Todo(null, "t" + i,"t" + i
							, false, i, member)
			);
		}

		MemberLogin entity = new MemberLogin(this.member, LocalDateTime.now());
		memberLoginRepository.save(entity);
		entityManager.flush();
		entityManager.clear();
		LoginResponse login = memberService.login(new LoginRequest(email, password));
		token = login.token();

	}
	@AfterEach
	void clean(){
		todoRepository.deleteAll();
		memberLoginRepository.deleteAll();
		memberRepository.deleteAll();
	}



}