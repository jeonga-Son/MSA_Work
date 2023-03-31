package com.example.coffeeorder.messagequeue;

import com.example.coffeeorder.vo.CoffeeOrderVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public CoffeeOrderVO send(String kafkaTopic, CoffeeOrderVO vo) {  // 여기서 kafkaTopic은 키값이라고 생각하면 된다.
        // 메세지 값을 json으로 만들어준다.
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";

        try {
            jsonString = mapper.writeValueAsString(vo); // json 값으로 얻어온다.
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        kafkaTemplate.send(kafkaTopic, jsonString); // 메세지가 공급이 되어졌다.
        System.out.println("메세지 발행 : " + vo);

        return vo;
    }
}
