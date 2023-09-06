package com.manong.service.impl;

import com.manong.entity.Comment;
import com.manong.dao.CommentMapper;
import com.manong.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author KazuGin
 * @since 2021-03-03
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
