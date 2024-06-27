package iot.springbootcreatedummy.controller;

import iot.springbootcreatedummy.dto.DummyData;
import iot.springbootcreatedummy.kafka.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Value("${lokomotif.iot.topic.message}")
    private String topicKafka;

    @Autowired
    private MessageProducer messageProducer;

    @PostMapping("")
    public String testKafka(@RequestBody DummyData dummyData) {
        Boolean produceKafka = messageProducer.sendMessage(topicKafka,dummyData);
        if (produceKafka){
            return "success";
        }
        return "false";
    }
}
