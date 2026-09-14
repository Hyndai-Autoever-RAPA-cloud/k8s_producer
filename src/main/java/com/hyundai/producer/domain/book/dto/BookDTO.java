package com.hyundai.producer.domain.book.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "도서 등록 요청")
public class BookDTO {

	private Long bid;

	@Schema(description = "도서명", example = "채식주의자")
	private String title;

	@Schema(description = "저자", example = "한강")
	private String author;

	@Schema(description = "카테고리", example = "소설")
	private String category;

	@Schema(description = "페이지 수", example = "224")
	private Integer pages;

	@Schema(description = "가격", example = "15000")
	private Integer price;

	@Schema(description = "출판일", example = "2007-10-30", format = "date")
	private String publiched_date;

	@Schema(description = "도서 설명", example = "한 여성의 삶을 다룬 장편소설")
	private String description;
}
