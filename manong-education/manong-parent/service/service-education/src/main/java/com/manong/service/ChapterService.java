package com.manong.service;

import com.manong.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.manong.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author KazuGin
 * @since 2021-03-03
 */
public interface ChapterService extends IService<Chapter> {

    /**
     * 删除章节信息
     * @param id
     * @return
     */
    boolean deleteChapterById(Integer id);

    /**
     * 查询章节列表
     * @param courseId
     * @return
     */
    List<ChapterVo> nestedList(Integer courseId);
}
