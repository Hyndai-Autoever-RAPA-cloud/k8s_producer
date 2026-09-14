package com.hyundai.producer.global.service;

import com.hyundai.producer.domain.book.dto.BookDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class KafkaProducer{
	private static final String TOPIC = "cqrs-topic";
	private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper;

	public void sendMessage(BookDTO bookDTO){
		try {
			String message = objectMapper.writeValueAsString(bookDTO);
			kafkaTemplate.send(TOPIC, message);
			log.info("Kafka message sent: topic={}, message={}", TOPIC, message);
		} catch (JacksonException e) {
			throw new IllegalArgumentException("도서 정보를 Kafka 메시지로 변환하지 못했습니다.", e);
		}
	}
}
