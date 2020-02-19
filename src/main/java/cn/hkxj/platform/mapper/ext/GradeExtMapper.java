package cn.hkxj.platform.mapper.ext;

import cn.hkxj.platform.mapper.GradeMapper;
import cn.hkxj.platform.pojo.Grade;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author JR Chan
 */
@Mapper
@Repository
public interface GradeExtMapper extends GradeMapper {

    void insertBatch(List<Grade> gradeList);
}
