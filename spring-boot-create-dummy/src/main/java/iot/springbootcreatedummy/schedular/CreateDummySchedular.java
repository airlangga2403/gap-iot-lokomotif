package iot.springbootcreatedummy.schedular;

import com.github.javafaker.Faker;
import iot.springbootcreatedummy.dto.DummyData;
import iot.springbootcreatedummy.kafka.MessageProducer;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
@Log4j2
public class CreateDummySchedular {

    @Autowired
    private MessageProducer messageProducer;

    @Value("${lokomotif.iot.topic.message}")
    private String topicKafka;

    private final List<String> allowedStatus = Arrays.asList("Poor", "Good", "Excellent");

    @Scheduled(fixedRate = 10000) // runs every 10 second
    public void createDummy() {
        Faker faker = new Faker();
        String kodeLoko = faker.lorem().characters(8);
        String namaLoko = faker.lorem().word();
        String dimensiLoko = faker.number().digits(5);
        String status = getRandomStatus();
        Date dateAndTime = faker.date().birthday();
        DummyData dummyData = new DummyData(kodeLoko, namaLoko, dimensiLoko, status, dateAndTime);

        messageProducer.sendMessage(topicKafka, dummyData);
    }

    private String getRandomStatus() {
        Random random = new Random();
        int index = random.nextInt(allowedStatus.size());
        return allowedStatus.get(index);
    }
}
