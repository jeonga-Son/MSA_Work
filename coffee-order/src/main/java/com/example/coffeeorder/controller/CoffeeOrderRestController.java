package com.example.coffeeorder.controller;

import com.example.coffeeorder.client.OrderServiceClient;
import com.example.coffeeorder.messagequeue.KafkaProducer;
import com.example.coffeeorder.service.CoffeeOrderService;
import com.example.coffeeorder.vo.CoffeeOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coffee-order")
public class CoffeeOrderRestController {
    @Autowired
    CoffeeOrderService coffeeOrderService;

    @Autowired
    private OrderServiceClient orderServiceClient;

    @Autowired
    private KafkaProducer kafkaProducer;

    @GetMapping("/")
    public String coffeeOrder() {
        return "Coffee Order";
    }

    @PostMapping("/coffeeOrder")
    public ResponseEntity<CoffeeOrderVO> coffeeOrder(@RequestBody CoffeeOrderVO vo) {
        // 회원 유무 체크
        if(!orderServiceClient.getCoffeeMember(vo.getCustomerName())) {
            System.out.println(vo.getCustomerName() + " is not a member");

            return new ResponseEntity<CoffeeOrderVO>(vo, HttpStatus.BAD_REQUEST);
        }

        System.out.println(vo.getCustomerName() + " is a member");

        // 커피 주문
        coffeeOrderService.coffeeOrder(vo);

        // kafka send
        // 이 정보가 parsing되어서 status에 전달되고, db에 저장된다.
        kafkaProducer.send("kosa-kafka-test", vo);

        return new ResponseEntity<CoffeeOrderVO>(vo, HttpStatus.OK);
    }
}
