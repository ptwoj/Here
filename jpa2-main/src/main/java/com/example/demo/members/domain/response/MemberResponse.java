package com.example.demo.members.domain.response;


import com.example.demo.config.domain.dto.MemberDto;
import com.example.demo.config.domain.dto.TodoDto;
import com.example.demo.members.domain.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
public class MemberResponse extends MemberDto {
    private List<TodoDto> todos;
    public MemberResponse(Member member) {
        super(member);
        todos = member.getTodos()
                .stream()
                .map(TodoDto::new)
                .toList();
        // todos = List<TodoDto>
    }

}

/*
1. MemberResponse안에 member 대신 memberdto를 안되는 이유
2. super 안에 member를 받아와야 하는 이유
3. member.getTodos()를 했는데 어떻게 List<Todo>가 되는거지?
4. .stream()를 했는데 Stream<Todo>가 되는지?
5. .map(TodoDto::new) Stream<TodoDto>가 되는지?
6. 마지막 .toList();을 하면 List<TodoDto>가 되는거지?

1. MemberResponse에 Member를 넣는 이유: MemberResponse 클래스가 MemberDto를 상속받았기 때문에
  MemberResponse는 MemberDto의 모든 필드와 메서드를 상속받는다. 이렇게 함으로써 MemberResponse 클래스에서
  Member 객체를 활용하여 필요한 데이터를 추출하고 초기화할 수 있다. 따라서 코드를 간결하게 유지하고 중복 코드를 피할 수 있다.
2. super가 MemberDto를 의미이다. super(member)는 MemberDto 클래스의 생성자를 호출하며, 인자로 member를 전달하고 있다.
  이로써 MemberResponse 객체는 MemberDto 클래스의 필드를 초기화할 수 있다.
3. member.getTodos()를 쓰면 todos는 List<TodoDto>인데, Member안에 todos가 List<Todo>이기 호출하면 List<Todo>가 되는 것이
4. .stream()를 했는데 어떻게 Stream<Todo>가 되었는을까?
    Stream<Todo> todoStream = todos.stream(); 이 코드가 .stream() 이거 하나로 대처가 된다
5. .map(TodoDto::new) 에서 map은 한 파일을 맵이라고 생각하고 내가 원하는 클래스를 바꿔주는 역할인데, .... 더 공부하자
6. 그 다음 .toList(); 를 사용하면 자동적으로 stream에서 자동으로 list로 바꿔주는 건가?








member.getTodos이 자체가 List<Todo>인거야 왜냐하면 멤버 클래스 안에서 List<Todo>를 todos로 해놔서 그러니까 여기 안에 todos랑 다른거다.
그래서 stream을 이용해서 List안을 바꿔주는 역할이고, map을 통해서



이거 하기 전에 MemberResponse 클래스를 왜 만들걸까?
회원 정보 변환: Member 객체를 MemberDto로 변환하여 클라이언트에게 전달합니다. 이를 통해 클라이언트는 회원의 필요한 정보만을 받아 사용할 수 있습니다.
회원 정보를 노출하지 않고 필요한 정보만을 제공하는 것이 보안과 성능 측면에서 유리합니다.

할 일(Todo) 목록 변환: Member 객체의 할 일(Todo) 목록을 TodoDto로 변환하여 클라이언트에게 전달합니다.
클라이언트는 회원 정보와 함께 할 일(Todo) 목록도 함께 받아 볼 수 있습니다.

이렇게 MemberResponse 클래스는 Member 객체와 관련된 정보를 적절하게 변환하여 클라이언트에게 전달하는 역할을 담당합니다.
이를 통해 클라이언트는 필요한 정보만을 받아와서 처리할 수 있으며, 불필요한 정보의 노출을 방지할 수 있습니다. 또한,
코드의 재사용성과 유지보수성을 높여주는 객체지향적인 설계 방식을 사용하고 있습니다







 왜 값을 받아야 하잖아
근데 그거를 Member클래스를 받아왔어 왜 받았어?




 */