package com.example.demo.members.repository;

import com.example.demo.members.domain.entity.Member;
import com.example.demo.todos.domain.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/*쿼리를 편하게 치기 위해서 extends JpaRepository를 Spring Data JPA의 강력한 기능을 활용하여
  데이터베이스와 상호작용하는데 매우 편리한 방법
  1. JpaRepository는 기본적인 CRUD (Create, Read, Update, Delete) 작업을 지원
     예를 들어, save(), findById(), deleteById() 등의 메서드를 사용하여
     쉽게 데이터를 생성, 조회, 수정, 삭제할 수 있다.
  2. @Query 어노테이션을 사용하여 메서드에 JPQL 쿼리를 작성하고 실행할 수 있다.
  3. 자동으로 해당 인터페이스의 메서드들의 구현체를 생성

  <T, Id>이렇게 써주는 거다 그래서 t는 엔티티클래스, Id는 primary key 그래서 Long으로 해준거 Member클래스에서 Long id로 선언해줘서
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
    // select * from members where email = ? and password = ?;

    // findByEmailAndPassword 메서드는 주어진 email과 password에 해당하는 Member를 조회하는 메서드
    // 이 메서드는 email과 password를 기준으로 데이터베이스에서 해당하는 Member를 찾습니다.
    Optional<Member> findByEmailAndPassword(String email, String password);
    Optional<Member> findByEmail(String email);
    @Query("select m from Member m left join fetch m.todos t order by m.age desc")
    Page<Member> findAllFetch(Pageable request);
}