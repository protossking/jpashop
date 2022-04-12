package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;



@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional // 엔티티매니저를 통한 데이터변경은 트랜잭션안에서 이루어져야 한다.
//    @Rollback(false)
    public void testMember() throws Exception {
        //g
        Member member = new Member();
        member.setUsername("memberA");

        //w
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);
        //t
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member);
        System.out.println("findMember : " + findMember);
        System.out.println("member :" + member);
        //영속성컨텍스트에서 식별자가같으면 같은 엔티티로 인식한다 1차캐시에서 관리하던게 나옴
        //./gradlew clean build

    }

}