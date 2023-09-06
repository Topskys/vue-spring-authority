package com.manong.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.manong.dao.SubjectMapper;
import com.manong.entity.Subject;
import com.manong.entity.excel.ExcelSubjectData;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class ExcelSubjectListener extends AnalysisEventListener<ExcelSubjectData> {

    private SubjectMapper subjectMapper;

    @Override
    public void invoke(ExcelSubjectData data, AnalysisContext analysisContext) {
        //获取一级分类
        String levelFirstTitle = data.getLevelFirstTitle();
        //获取二级分类
        String levelSecondTitle = data.getLevelSecondTitle();
        //调用根据一级分类名称查询分类信息
        QueryWrapper<Subject> queryWrapperOne = new QueryWrapper<Subject>();
        queryWrapperOne.eq("title",levelFirstTitle);//一级分类
        Subject levelFirstSubject = subjectMapper.selectOne(queryWrapperOne);
        //父分类ID编号
        Integer parentId = null;
        //判断对象是否为空
        if(levelFirstSubject==null){//为空则表示不存在，不存在插入到数据库
            //创建科目分类对象
            Subject subject = new Subject();
            subject.setParentId(0);//一级分类父ID均为0
            subject.setTitle(levelFirstTitle);
            //执行新增
            subjectMapper.insert(subject);
            //获取当前分类的ID
            parentId = subject.getId();
        }else{
            //对象存在，获取到当前一级分类ID
            parentId = levelFirstSubject.getId();
        }

        //判断二级分类是否存在
        QueryWrapper<Subject> queryWrapperTwo = new QueryWrapper<Subject>();
        queryWrapperTwo.eq("title",levelSecondTitle);//二级分类
        queryWrapperTwo.eq("parentId",parentId);//分类ID
        Subject levelSecondSubject = subjectMapper.selectOne(queryWrapperTwo);
        //判断对象是否为空，为空则执行新增操作
        if(levelSecondSubject==null){
            //创建科目分类对象
            Subject subject = new Subject();
            subject.setParentId(parentId);//父ID
            subject.setTitle(levelSecondTitle);
            //执行新增
            subjectMapper.insert(subject);
        }

    }



    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("所有数据均解析完毕！");
    }
}
