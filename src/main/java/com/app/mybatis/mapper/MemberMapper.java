package com.app.mybatis.mapper;

import com.app.mybatis.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {
    public void insert(MemberVO memberVO);

    public Optional<MemberVO> select(MemberVO memberVO);

    public List<MemberVO> selectAll();

    public void update(MemberVO memberVO);

    public void delete(Long id);
}
