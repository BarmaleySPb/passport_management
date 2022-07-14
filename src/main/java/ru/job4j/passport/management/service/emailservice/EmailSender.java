package ru.job4j.passport.management.service.emailservice;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.job4j.passport.management.model.Passport;

@Component
public class EmailSender {
    @KafkaListener(topics = {"expired_passports"})
    public void sendEmail(ConsumerRecord<Integer, Passport> input) {
        Passport passport = input.value();
        System.out.println("Passport: "
                + passport.getSeries()
                + " "
                + passport.getNumber()
                + " is expired and need replace.");
    }
}