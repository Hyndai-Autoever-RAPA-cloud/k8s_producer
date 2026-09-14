package com.hyundai.producer.domain.book.repository;

import com.hyundai.producer.domain.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
