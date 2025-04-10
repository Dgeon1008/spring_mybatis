package com.app.mybatis.mapper;

import com.app.mybatis.domain.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Member;
import java.sql.Time;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class MapperTests {

    @Autowired
    private TimeMapper timeMapper;

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MemberVO memberVO;

    @Test
    public void getTimeTest() {
        log.info("----------------------");
//        log.info("{}", timeMapper.getTime());
        log.info("{}", timeMapper.getTime2());
        log.info("----------------------");
    }

    @Test
    public void memberInsertTest() {
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("geon1234@gmail.com");
        memberVO.setMemberPassword("1234");
        memberVO.setMemberName("홍길동");

        memberMapper.insert(memberVO);
    }

    @Test
    public void selectTest() {
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("geon3238@gmail.com");
        memberVO.setMemberPassword("1234");
        memberVO.setMemberName("김동건");

        Optional<MemberVO> foundMember = memberMapper.select(memberVO);

        foundMember.ifPresent((MemberVO member) -> {
            log.info("{}", member);
        });

        foundMember.map(MemberVO::toString).ifPresentOrElse(log::info, () -> {
            new RuntimeException("로그인 실패");
        });
    }

    @Test
    public void selectAllTest() {
        memberMapper.selectAll().forEach(memberVO -> {
            log.info("{}", memberVO);
        });
    }

    @Test
    public void memberUpdateTest() {
//        회원 조회
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("geon1234@gmail.com");
        memberVO.setMemberPassword("1234");
//        Optional<MemberVO> foundUser = memberMapper.select(memberVO); // 옵셔널로 감싸진 memberVO
        memberMapper.select(memberVO).ifPresent((member) -> {
            // 회원 수정
            member.setMemberName("강감찬");
            memberMapper.update(member);
        });
    }

    @Test
    public void deleteTest() {
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("1");
        memberVO.setMemberPassword("1");
        memberMapper.select(memberVO).map(MemberVO::getId).ifPresent((Long id) -> {
            memberMapper.delete(1L);
        });
    }

}
