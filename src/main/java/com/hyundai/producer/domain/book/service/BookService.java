package com.hyundai.producer.domain.book.service;

import com.hyundai.producer.domain.book.dto.BookDTO;
import com.hyundai.producer.domain.book.entity.Book;
import com.hyundai.producer.domain.book.repository.BookRepository;
import com.hyundai.producer.global.service.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
@RequiredArgsConstructor
public class BookService {

	private final BookRepository bookRepository;
	private final KafkaProducer kafkaProducer;

	@Transactional
	public Book saveBook(BookDTO bookDTO) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);

		Book book = Book.builder()
				.title(bookDTO.getTitle())
				.author(bookDTO.getAuthor())
				.category(bookDTO.getCategory())
				.page(bookDTO.getPages())
				.publishedDate(parsePublishedDate(dateFormat, bookDTO.getPubliched_date()))
				.description(bookDTO.getDescription())
				.build();

		bookRepository.save(book);
		bookDTO.setBid(book.getBid());
		kafkaProducer.sendMessage(bookDTO);
		
		return book;
	}

	private java.util.Date parsePublishedDate(SimpleDateFormat dateFormat, String publishedDate) {
		try {
			return dateFormat.parse(publishedDate);
		} catch (ParseException e) {
			throw new IllegalArgumentException("출판일은 yyyy-MM-dd 형식이어야 합니다.", e);
		}
	}
}
