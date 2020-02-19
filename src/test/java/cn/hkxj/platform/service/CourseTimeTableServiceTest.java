package cn.hkxj.platform.service;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.dao.CourseTimeTableDao;
import cn.hkxj.platform.dao.StudentDao;
import cn.hkxj.platform.dao.UrpClassRoomDao;
import cn.hkxj.platform.pojo.CourseTimetable;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.UrpClassroom;
import cn.hkxj.platform.pojo.vo.CourseTimeTableVo;
import cn.hkxj.platform.spider.newmodel.coursetimetable.UrpCourseTimeTableForSpider;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yuki
 * @date 2019/9/2 16:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
@Slf4j
public class CourseTimeTableServiceTest {

    @Resource
    private CourseTimeTableService courseTimeTableService;
    @Resource
    private StudentDao studentDao;
    @Resource
    private CourseTimeTableDao courseTimeTableDao;
    @Resource
    private UrpClassRoomDao urpClassRoomDao;


    @Test
    public void getCourseTimeTableByStudent() {
        Student student = studentDao.selectStudentByAccount(2017021881);
        for (CourseTimeTableVo courseTimeTableVo : courseTimeTableService.getCurrentTermCourseTimeTableByStudent(student)) {
            System.out.println(courseTimeTableVo);
        }

    }
    @Test
    public void getCourseTimeTableByStudent2() {
        Student student = studentDao.selectStudentByAccount(2017025717);
        for (CourseTimeTableVo courseTimeTableVo : courseTimeTableService.getCourseTimeTableByStudent2(student)) {
            System.out.println(courseTimeTableVo);
        }

    }

    @Test
    public void fix() {
        for (CourseTimetable courseTimetable : courseTimeTableDao.selectByCourseTimetable(new CourseTimetable())) {
            List<UrpClassroom> classroomList = urpClassRoomDao.selectByClassroom(new UrpClassroom().setName(courseTimetable.getRoomName()));

            if (classroomList.size() == 0) {
                log.error("{} size 0", courseTimetable.getRoomName());
            } else if (classroomList.size() == 1) {
                if (!classroomList.get(0).getNumber().equals(courseTimetable.getRoomNumber())) {
                    log.error("{} number error", courseTimetable);
                }
            } else {
                log.error("{} size more than 1", classroomList);
            }
        }

    }



    @Test
    public void getCourseTimeTableByStudentFromSpider() {
        Student student = studentDao.selectStudentByAccount(2016024986);
        for (CourseTimeTableVo vo : courseTimeTableService.getCourseTimeTableByStudentFromSpider(student)) {
            System.out.println(vo);
        }

    }

    @Test
    public void getCourseTimeTableByStudentFromSpider2() {
        Student student = studentDao.selectStudentByAccount(2016024986);
        for (CourseTimeTableVo vo : courseTimeTableService.getCourseTimeTableByStudentFromSpider2(student)) {
            System.out.println(vo);
        }

    }

    @Test
    public void testUpdate() {
        for (CourseTimeTableVo vo : courseTimeTableService.getCurrentTermCourseTimeTableByStudent(2017023437)) {
            System.out.println(vo);
        }

        System.out.println("####################");

        for (CourseTimeTableVo vo : courseTimeTableService.updateCourseTimeTableByStudent(2017023437)) {
            System.out.println(vo);
        }


    }


    @Test
    public void fixErrorData() {
        // 按学生分好组，然后再进行抓取
        UrpCourseTimeTableForSpider details = courseTimeTableService.getCourseTimeTableDetails(studentDao.selectStudentByAccount(2017026003));
        List<CourseTimetable> list = courseTimeTableService.getCourseTimetableList(details);
        courseTimeTableService.getCourseTimetableIdList(list);

    }

    @Test
    public void getCurrentTermCourseTimetableByClass() {
        // 按学生分好组，然后再进行抓取
        List<CourseTimetable> timetable = courseTimeTableService.getCurrentTermCourseTimetableByClass("2017040012");
        System.out.println(timetable.size());
    }


}