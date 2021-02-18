package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository; // <- 생성자 주입 변경 : private final MemberRepository memberRepository = new MemoryMemberRepository();
    private DiscountPolicy discountPolicy; // final을 제거하면서, DIP 위반 요소 제거

    // 생성자 주입 이전 : private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // 생성자 주입 이전 : private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 할인정책으로 바뀌면서 추가


    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); // DiscountPolicy에 정보를 넘김
                                                                         // 단일 체계의 원칙을 준수하게 설계, member와 itemPrice를 매개변수로 discountPrice 값을 얻을 수 있음

        return new Order(memberId, itemName, itemPrice, discountPrice); // 주문서 작성 및 반환
    }
}
