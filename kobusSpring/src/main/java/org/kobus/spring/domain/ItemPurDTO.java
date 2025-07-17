package org.kobus.spring.domain;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
// 쿠폰 구매내역 페이지 DTO
public class ItemPurDTO {
	private int couponID; // 프리패스, 정기권 식별번호(시퀀스)
	private String couponName; // 프리패스, 정기권인지 나태내는 변수
	private String payStatus; // 결제상태
	private Date startDate; // 사용시작일
	private int amount; // 결제금액
}
