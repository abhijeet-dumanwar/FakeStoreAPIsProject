package projects.javasampleproject.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import projects.javasampleproject.dto.SendEmailEventDTO;

@Service
public class KafkaMessagePublisherService {
    private KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper objectMapper;

    KafkaMessagePublisherService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper){
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public String publishMessage(SendEmailEventDTO message) throws JsonProcessingException {
        System.out.println("Message published: "+message);
        kafkaTemplate.send("send-email-event", objectMapper.writeValueAsString(message));
        return null;
    }
}
