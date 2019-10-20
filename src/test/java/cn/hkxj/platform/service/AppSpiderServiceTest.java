package cn.hkxj.platform.service;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.pojo.AllGradeAndCourse;
import cn.hkxj.platform.pojo.GradeAndCourse;
import cn.hkxj.platform.pojo.timetable.ExamTimeTable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author junrong.chen
 * @date 2018/11/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
public class AppSpiderServiceTest {
    @Resource
    private AppSpiderService appSpiderService;

    @Test
    public void getExamByAccount() throws PasswordUncorrectException {
        List<ExamTimeTable> examByAccount = appSpiderService.getExamByAccount(2017023523);
        for (ExamTimeTable table : examByAccount) {
            System.out.println(examByAccount.toString());
        }

    }

    @Test
    public void getGrade(){
        AllGradeAndCourse gradeAndCourseByAccount = appSpiderService.getGradeAndCourseByAccount(2017023523);
        for (GradeAndCourse course : gradeAndCourseByAccount.getCurrentTermGrade()) {
            System.out.println(course.toString());
        }

    }
}