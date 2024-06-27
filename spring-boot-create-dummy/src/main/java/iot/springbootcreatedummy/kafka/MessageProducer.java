package iot.springbootcreatedummy.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import iot.springbootcreatedummy.dto.DummyData;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class MessageProducer {

    @Autowired
    private KafkaTemplate<String, DummyData> kafkaTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Boolean sendMessage(String topic, DummyData message) {
        try {
//            String jsonMessage = objectMapper.writeValueAsString(message);
            kafkaTemplate.send(topic, message);
            log.info("Successfully sent message to Kafka topic: " + topic);
            return true;
        } catch (KafkaException e) {
            log.error("Failed to send message to Kafka: " + e.getMessage());
            return false;
        }
    }
}
