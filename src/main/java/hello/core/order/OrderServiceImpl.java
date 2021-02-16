package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.disscount(member, itemPrice); // DiscountPolicy에 정보를 넘김
                                                                         // 단일 체계의 원칙을 준수하게 설계, member와 itemPrice를 매개변수로 discountPrice 값을 얻을 수 있음

        return new Order(memberId, itemName, itemPrice, discountPrice); // 주문서 작성 및 반환
    }
}
