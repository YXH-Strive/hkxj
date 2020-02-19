package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.ext.GradeExtMapper;
import cn.hkxj.platform.pojo.Grade;
import cn.hkxj.platform.pojo.GradeExample;
import cn.hkxj.platform.pojo.SchoolTime;
import cn.hkxj.platform.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GradeDao {
    @Resource
    private GradeExtMapper gradeExtMapper;

    public int insertSelective(Grade grade) {
        try {
            return gradeExtMapper.insertSelective(grade);
        } catch (Exception e) {
            log.error("error data {}", grade, e);
            throw e;
        }

    }

    public void insertBatch(List<Grade> gradeList) {
        if(CollectionUtils.isEmpty(gradeList)){
            return;
        }
        try {
            gradeExtMapper.insertBatch(gradeList);
        } catch (Exception e) {
            log.error("error data {}", gradeList, e);
            throw e;
        }

    }

    public List<Grade> selectByPojo(Grade grade) {
        GradeExample gradeExample = new GradeExample();
        GradeExample.Criteria criteria = gradeExample.createCriteria();
        if (grade.getAccount() != null) {
            criteria.andAccountEqualTo(grade.getAccount());
        }
        if (grade.getTermYear() != null) {
            criteria.andTermYearEqualTo(grade.getTermYear());
        }
        if (grade.getTermOrder() != null) {
            criteria.andTermOrderEqualTo(grade.getTermOrder());
        }

        return gradeExtMapper.selectByExample(gradeExample);
    }


    public List<Grade> getCurrentTermGradeByAccount(int account) {
        SchoolTime schoolTime = DateUtils.getCurrentSchoolTime();
        return selectByPojo(new Grade()
                .setTermYear(schoolTime.getTerm().getTermYear())
                .setTermOrder(schoolTime.getTerm().getOrder())
                .setAccount(account));
    }


    public List<Grade> getEverTermGradeByAccount(int account) {
        List<Grade> gradeList = getGradeByAccount(account);

        return gradeList.stream().filter(grade -> !grade.isCurrentTermGrade())
                .collect(Collectors.toList());
    }


    public List<Grade> getGradeByAccount(int account) {
        return selectByPojo(new Grade()
                .setAccount(account));
    }

    public void updateByPrimaryKeySelective(Grade grade) {
        gradeExtMapper.updateByPrimaryKeySelective(grade);
    }


    public void updateByUniqueIndex(Grade grade) {
        GradeExample example = new GradeExample();
        example.createCriteria()
                .andAccountEqualTo(grade.getAccount())
                .andTermYearEqualTo(grade.getTermYear())
                .andTermOrderEqualTo(grade.getTermOrder())
                .andCourseOrderEqualTo(grade.getCourseOrder())
                .andCourseNumberEqualTo(grade.getCourseNumber());

        gradeExtMapper.updateByExampleSelective(grade, example);
    }
}
