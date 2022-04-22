package com.vaccineReview.login.mapper;

import org.apache.ibatis.annotations.Mapper;
import java.util.Map;

@Mapper
public interface loginMapper {

    Map<String, Object> getUserInfo(int i);
}
