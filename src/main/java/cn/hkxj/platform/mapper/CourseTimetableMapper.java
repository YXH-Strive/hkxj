package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.CourseTimetable;
import cn.hkxj.platform.pojo.example.CourseTimetableExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CourseTimetableMapper {
    int countByExample(CourseTimetableExample example);

    int deleteByExample(CourseTimetableExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CourseTimetable record);

    int insertSelective(CourseTimetable record);

    List<CourseTimetable> selectByExample(CourseTimetableExample example);

    List<CourseTimetable> selectCourseTimeTable(@Param("idList") List<Integer> idList);

    List<CourseTimetable> selectcourse_timetable(@Param("courseTimetableId") List<Integer> courseTimetableId);

    CourseTimetable selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CourseTimetable record, @Param("example") CourseTimetableExample example);

    int updateByExample(@Param("record") CourseTimetable record, @Param("example") CourseTimetableExample example);

    int updateByPrimaryKeySelective(CourseTimetable record);

    int updateByPrimaryKey(CourseTimetable record);
}