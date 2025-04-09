package com.app.mybatis.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

@Component
@Data
public class PostVO {
    @EqualsAndHashCode.Include
    private Long id;
    private String postTitle;
    private String postContent;
    private Long memberId;
    private Long postReadCount;
}
