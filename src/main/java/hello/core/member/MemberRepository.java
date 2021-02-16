package hello.core.member;

public interface MemberRepository {

    void save(Member member); // 가입 기능

   Member findById(Long memberId); // 조회 기능
}
