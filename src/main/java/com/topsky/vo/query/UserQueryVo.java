package com.topsky.vo.query;

import com.topsky.entity.User;
import lombok.Data;

@Data
public class UserQueryVo extends User {
    private Long pageNo=1L;//当前页码
    private Long pageSize=10L;//每页显示数量
}
