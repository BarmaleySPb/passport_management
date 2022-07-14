package ru.job4j.passport.management.service.emailservice;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.job4j.passport.management.model.Passport;
import ru.job4j.passport.management.service.PassportService;

import java.util.concurrent.TimeUnit;

@Service
public class EmailService {

    private final PassportService passportService;
    private final KafkaTemplate<Integer, Passport> kafkaTemplate;

    public EmailService(PassportService passportService,
                        KafkaTemplate<Integer, Passport> kafkaTemplate) {
        this.passportService = passportService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    public void send() {
        Iterable<Passport> passports = passportService.findExpiredPassports();
        passports.iterator().forEachRemaining(passport ->
                kafkaTemplate.send("expired_passports", passport));
    }
}