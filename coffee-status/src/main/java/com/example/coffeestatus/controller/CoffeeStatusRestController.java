package com.example.coffeestatus.controller;

import com.example.coffeestatus.service.CoffeeStatusService;
import com.example.coffeestatus.vo.CoffeeStatusVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@RequestMapping("/coffee-status")
public class CoffeeStatusRestController {
    @Autowired
    private CoffeeStatusService coffeeStatusService;

    @GetMapping("/")
    public String coffeeStatus() {
        return "Coffee status";
    }

    @GetMapping("/createStatusTable")
    public void createStatusTable() {
        coffeeStatusService.createStatusTable();
    }

    @GetMapping("/coffeeOrderStatus")
    // 주문이 다되면 해당 주문 상태가 status로 dispatch 된다.
    // 따라서 insert 작업이 필요하지 않다.
    public ResponseEntity<List<CoffeeStatusVO>> coffeeOrderStatus() {
        List<CoffeeStatusVO> list = coffeeStatusService.selectCoffeeOrderStatus();

        return new ResponseEntity<List<CoffeeStatusVO>>(list,HttpStatus.OK);
    }

}
