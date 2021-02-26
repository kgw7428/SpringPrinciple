package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component// AutoAppConfig class 생성에 따른 어노테이션 추가
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Autowired // ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도 - configuration과 싱글톤 확인을 위한 소스 코드
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
