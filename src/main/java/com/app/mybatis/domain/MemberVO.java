package com.app.mybatis.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

@Component
@Data
public class MemberVO {
    @EqualsAndHashCode.Include
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
}
