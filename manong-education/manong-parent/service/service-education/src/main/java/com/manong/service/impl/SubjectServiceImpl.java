package com.manong.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.manong.entity.Subject;
import com.manong.dao.SubjectMapper;
import com.manong.entity.excel.ExcelSubjectData;
import com.manong.listener.ExcelSubjectListener;
import com.manong.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manong.vo.SubjectVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author KazuGin
 * @since 2021-02-28
 */
@Service
@Transactional
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public void batchImport(InputStream inputStream) {
        EasyExcel.read(inputStream, ExcelSubjectData.class,new ExcelSubjectListener(baseMapper)).excelType(ExcelTypeEnum.XLS).sheet().doRead();
    }

    @Override
    public List<SubjectVo> selectNestedListByParentId(Integer parentId) {
        return baseMapper.selectNestedListByParentId(parentId);
    }
}
