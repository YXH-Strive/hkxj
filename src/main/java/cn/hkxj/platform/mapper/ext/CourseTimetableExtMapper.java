package cn.hkxj.platform.mapper.ext;

import cn.hkxj.platform.mapper.CourseTimetableMapper;
import cn.hkxj.platform.pojo.ClassCourseTimetable;
import cn.hkxj.platform.pojo.CourseTimetable;
import cn.hkxj.platform.pojo.StudentCourseTimeTable;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author JR Chan
 */
@Mapper
@Repository
public interface CourseTimetableExtMapper extends CourseTimetableMapper {
    /**
     * 根据学生课程关系表查询课程信息
     * @param relative  关系对象
     * @return 学生对应课程信息
     */
    List<CourseTimetable> selectByStudentRelative(StudentCourseTimeTable relative);

    /**
     * 根据班级课程关系表查询课程信息
     * @param relative  关系对象
     * @return 班级对应课程信息
     */
    List<CourseTimetable> selectByClassRelative(ClassCourseTimetable relative);
}
