package com.hyundai.producer.domain.book.controller;

import com.hyundai.producer.domain.book.dto.BookDTO;
import com.hyundai.producer.domain.book.entity.Book;
import com.hyundai.producer.domain.book.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Tag(name = "Books", description = "도서 관리 API")
public class BookController {

	private final BookService bookService;

	@GetMapping("/health")
	@Operation(summary = "상태 확인", description = "도서 API의 동작 상태를 확인합니다.")
	@ApiResponse(responseCode = "200", description = "정상 동작")
	public String healthCheck() {
		return "success";
	}

	@PostMapping
	@Operation(summary = "도서 등록", description = "새 도서를 등록합니다.")
	@ApiResponses({
			@ApiResponse(
					responseCode = "201",
					description = "도서 등록 성공",
					content = @Content(schema = @Schema(implementation = Book.class))),
			@ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content)
	})
	public ResponseEntity<Book> saveBook(@RequestBody BookDTO bookDTO) {
		Book savedBook = bookService.saveBook(bookDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
	}
}
