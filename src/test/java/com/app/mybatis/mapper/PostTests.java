package com.app.mybatis.mapper;

import com.app.mybatis.domain.MemberVO;
import com.app.mybatis.domain.PostDTO;
import com.app.mybatis.domain.PostVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@SpringBootTest
@Slf4j
public class PostTests {

    @Autowired
    private PostMapper postMapper;
    @Autowired
    private MemberMapper memberMapper;

    @Test
    public void insertTest() {
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("geon1234@gmail.com");
        memberVO.setMemberPassword("1234");
        memberMapper.select(memberVO)
                .map(MemberVO::getId)
                .ifPresent((memberId) -> {
                    PostVO postVO = new PostVO();
                    postVO.setMemberId(memberId);
                    postVO.setPostTitle("테스트 작성 제목3");
                    postVO.setPostContent("테스트 작성 내용3");
                    postMapper.insert(postVO);
                });
    }

    @Test
    public void selectAllTest() {
        List<PostDTO> posts = postMapper.selectAll();
//        posts.forEach(PostDTO -> {
//            log.info(PostDTO.toString());
//        });

        posts.stream().map(PostDTO::toString).forEach(log::info);
    }

    @Test
    public void selectTest() {
        Optional<PostDTO> post = postMapper.select(2L);
        post.map(PostDTO::toString).ifPresent(log::info);
    }

    @Test
    public void updateTest(){
//        1. select 정보를 들고온다.
        postMapper.select(2L).ifPresent((postDTO) -> {
//            2. VO 가져온다
            PostVO postVO = new PostVO();
//            3. 수정한다
            postDTO.setPostTitle("수정된 제목2");
            postDTO.setPostContent("수정된 내용2");
//            4. update 쿼리 날린다.
            postVO.setId(postDTO.getId()); // 임의로 받았다고 생각하고 VO에 담기
            postVO.setPostTitle(postDTO.getPostTitle());
            postVO.setPostContent(postDTO.getPostContent());
            postMapper.update(postVO);
        });
    }

    @Test
    public void deleteTest() {
        postMapper.select(4L)
                .map(PostDTO::getId)
                .ifPresent(postMapper::delete);
    }

//  한 사람이 50개 글을 작성하도록 코딩
    @Test
    public void insertPostTest(){
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("geon1234@gmail.com");
        memberVO.setMemberPassword("1234");
        memberMapper.select(memberVO)
                .map(MemberVO::getId)
                .ifPresent((memberId) -> {
                    for(int i = 0; i < 50; i++){
                        PostVO postVO = new PostVO();
                        postVO.setMemberId(memberId);
                        postVO.setPostTitle("테스트 작성글" + (i + 1));
                        postVO.setPostContent("테스트 컨텐츠" + (i + 1));
                        postMapper.insert(postVO);
                    }
                });
    }

    @Test
    public void updateReadTest(){
        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            Long id = Long.valueOf(random.nextInt(50));
            postMapper.select(id)
                    .map(PostDTO::getId)
                    .ifPresent(postMapper::updateReadCount);
        }
    }

//    동적쿼리 정렬
//    아무것도 전달하지 않을 때
    @Test
    public void selectWithOrderTest(){
        String order = null;
        if(order == null){ order = ""; }
        postMapper.selectAllWithOrder(order)
                .stream().map(PostVO::toString).forEach(log::info);
    }

//    동적쿼리 정렬
//    아무것도 전달하지 않을 때
    @Test
    public void selectWithOrderTestPopular(){
        String order = "popular";
        if(order == null){ order = ""; }
        postMapper.selectAllWithOrder(order)
                .stream().map(PostVO::toString).forEach(log::info);
    }

    @Test
    public void selectWithParamTest(){
        HashMap<String,Object> params = new HashMap<>();
        params.put("order","popular");
        params.put("cursor",1);
//        if(cursor == null){
//            cursor = 1;
//        }
        params.put("direction","desc");

        postMapper.selectAllWithParams(params)
                .stream().map(PostVO::toString).forEach(log::info);
    }

}
