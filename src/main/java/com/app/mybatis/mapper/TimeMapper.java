package com.app.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TimeMapper {
    public String getTime();

//  메서드의 결과물이 있을때 바로 @으로 값을 가져올 수 있다.
    @Select("SELECT CURRENT_TIMESTAMP FROM DUAL")
    public String getTime2();
}
