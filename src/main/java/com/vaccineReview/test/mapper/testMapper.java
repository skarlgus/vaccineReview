package com.vaccineReview.test.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
public interface testMapper {
    Map<String, Object> test();
}
