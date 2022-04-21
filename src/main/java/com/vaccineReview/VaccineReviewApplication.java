package com.vaccineReview;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value={"com.vaccineReview.login.mapper"})
public class VaccineReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaccineReviewApplication.class, args);
	}

}
