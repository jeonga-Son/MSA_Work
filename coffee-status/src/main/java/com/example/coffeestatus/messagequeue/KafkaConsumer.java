package com.example.coffeestatus.messagequeue;

import com.example.coffeestatus.mapper.CoffeeMapper;
import com.example.coffeestatus.vo.CoffeeStatusVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class KafkaConsumer {
    @Autowired
    private CoffeeMapper coffeeMapper;

    @KafkaListener(topics = "kosa-kafka-test")
    public void processMessage(String kafkaMessage) {
        System.out.println("kafka Message => " + kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            // 문자열을 객체로 변환해야 한다.
            // TypeReference : TypeReference<T> 를 상속한 익명클래스를 Key 로 저장. 이를통해 value 를 따로 가져오는 코드이다.
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        CoffeeStatusVO vo = new CoffeeStatusVO();
        vo.setOrderNumber((String)map.get("orderNumber"));
        vo.setCoffeeName((String)map.get("coffeeName"));
        vo.setCoffeeCount((Integer) map.get("coffeeCount"));
        vo.setCustomerName((String)map.get("customerName"));

        coffeeMapper.insertCoffeeOrderStatus(vo);
    }

}
