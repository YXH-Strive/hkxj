package cn.hkxj.platform.service;

import cn.hkxj.platform.MDCThreadPool;
import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.dao.GradeDao;
import cn.hkxj.platform.dao.StudentDao;
import cn.hkxj.platform.exceptions.UrpEvaluationException;
import cn.hkxj.platform.exceptions.UrpException;
import cn.hkxj.platform.pojo.Grade;
import cn.hkxj.platform.pojo.GradeDetail;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.UrpGradeAndUrpCourse;
import cn.hkxj.platform.pojo.vo.GradeVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Yuki
 * @date 2019/8/1 20:15
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
public class NewGradeSearchServiceTest {

    @Resource
    private NewGradeSearchService newGradeSearchService;
    @Resource
    private StudentDao studentDao;
    @Resource
    private SubscribeService subscribeService;
    @Resource
    private GradeDao gradeDao;

    @Test
    public void test() {
        Student student = studentDao.selectStudentByAccount(2018022512);
        for (UrpGradeAndUrpCourse course : newGradeSearchService.getCurrentGrade(student).getData()) {
            System.out.println(course);
        }

    }


    @Test
    public void getCurrentTermGradeFromSpider() {
        Student student = studentDao.selectStudentByAccount(2017021554);
        List<GradeDetail> gradeDetailList = newGradeSearchService.getCurrentTermGradeFromSpider(student);
        List<Grade> gradeList = gradeDetailList.stream().map(GradeDetail::getGrade).collect(Collectors.toList());

        for (Grade grade : gradeList) {
            System.out.println(grade);
        }


    }

    @Test
    public void checkUpdate() {
        Student student = studentDao.selectStudentByAccount(2018022512);
        List<GradeDetail> gradeDetailList = newGradeSearchService.getCurrentTermGradeFromSpider(student);
        List<Grade> gradeList = gradeDetailList.stream().map(GradeDetail::getGrade).collect(Collectors.toList());

        List<Grade> updateList = newGradeSearchService.checkUpdate(student, gradeList);

        for (Grade update : updateList) {
            if (update.isUpdate()) {
                System.out.println(update);
            }

        }
        newGradeSearchService.saveUpdateGrade(updateList);

    }

    @Test
    public void getCurrentTermGradeVoSync() {
        Student student = studentDao.selectStudentByAccount(2018022512);

        List<GradeVo> gradeVoList = newGradeSearchService.getCurrentTermGradeVoSync(student);

        for (GradeVo vo : gradeVoList) {
            System.out.println(vo);
        }

    }

    @Test
    public void getCurrentTermGrade() {
        Student student = studentDao.selectStudentByAccount(2019030404);
        for (GradeVo grade : newGradeSearchService.getCurrentTermGrade(student)) {
            System.out.println(grade);
        }
    }

    @Test
    public void getSchemeGrade() {
        Student student = studentDao.selectStudentByAccount(2018022512);
        for (Grade grade : newGradeSearchService.getSchemeGrade(student)) {
            System.out.println(grade);
        }
    }

    @Test
    public void getSchemeGradeFromSpider() {
        Student student = studentDao.selectStudentByAccount(2018025144);

        for (Grade grade : newGradeSearchService.getSchemeGradeFromSpider(student)) {
            gradeDao.updateByUniqueIndex(grade);
        }
    }


    @Test
    public void getGrade() {
        Student student = studentDao.selectStudentByAccount(2016021728);

    }


    @Test
    public void testProxy() {

        for (int x = 0; x < 10; x++) {
            try {
                Student student = studentDao.selectStudentByAccount(2017021517);
                newGradeSearchService.getCurrentTermGradeVoSync(student);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(x);
            }

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void updateAllGrade() {
        Set<Student> studentList = subscribeService.getGradeUpdateSubscribeStudent();
        MDCThreadPool pool = new MDCThreadPool(8, 8,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), r -> new Thread(r, "gradeSearch"));

        CountDownLatch latch = new CountDownLatch(studentList.size());

        for (Student student : studentList) {

            pool.submit(() -> {
                long l = System.currentTimeMillis();
                try {
                    log.info("{} start", student.getAccount());
                    List<GradeDetail> gradeDetailList = newGradeSearchService.getCurrentTermGradeFromSpider(student);
                    List<Grade> gradeList = gradeDetailList.stream().map(GradeDetail::getGrade).collect(Collectors.toList());

                    List<Grade> updateList = newGradeSearchService.checkUpdate(student, gradeList);
                    for (Grade update : updateList) {
                        if (update.isUpdate()) {
                            System.out.println(update);
                        }

                    }

                } catch (Exception e) {
                    if (e instanceof UrpException) {
                        log.error("error {}", e.getMessage());
                    } else if (e instanceof UrpEvaluationException) {
                        log.error("error {}", e.getMessage());
                    } else {
                        log.error("error", e);
                    }

                } finally {
                    latch.countDown();
                    log.info("{} finish in {}ms", student.getAccount(), (System.currentTimeMillis() - l));
                }

            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}