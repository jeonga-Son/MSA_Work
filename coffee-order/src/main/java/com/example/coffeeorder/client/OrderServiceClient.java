package com.example.coffeeorder.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "coffee-member")
// coffee-member 프로젝트로 요청한다.
// 요청하는 쪽이라 client 가 된다.
public interface OrderServiceClient {
    @GetMapping("/coffee-member/existMember/{memberName}")
    boolean getCoffeeMember(@PathVariable String memberName); // 이 메소드를 호출하면 위의 url이 호출되고 해당되는 메소드가 호출된다.
}
