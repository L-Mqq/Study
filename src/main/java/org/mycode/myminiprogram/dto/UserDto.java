package org.mycode.myminiprogram.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //生成getter setter方法
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long userId;

    private String username;

    private String nickname;
}
