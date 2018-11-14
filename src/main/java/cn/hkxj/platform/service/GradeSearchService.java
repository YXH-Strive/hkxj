package cn.hkxj.platform.service;

import cn.hkxj.platform.mapper.CourseMapper;
import cn.hkxj.platform.mapper.GradeMapper;
import cn.hkxj.platform.pojo.AllGradeAndCourse;
import cn.hkxj.platform.pojo.Course;
import cn.hkxj.platform.pojo.Grade;
import cn.hkxj.platform.spider.AppSpider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author junrong.chen
 * @date 2018/9/16
 */
@Slf4j
@Service("gradeSearchService")
public class GradeSearchService {
	@Resource
	private CourseMapper courseMapper;
	@Resource
	private GradeMapper gradeMapper;

	/**
	 * 只返回本学期的成绩，这个数据暂时不存在数据库
	 *
	 * @param account 教务网账号
	 * @param password 密码
	 */
	public void getCurrentGrade(int account, String password){
		//暂定只要是半学期的都应该直接查询最新的数据
		//先查询数据库中有没有这个数据，有就返回（如果要查本学期的数据，怎么判断知道数据有没有更新完）
		//如果没有从App中进行抓取，要先判断这个他的app账号是否正确，不正确从校务网抓
		//抓到的数据保存到数据并且返回结果（并行执行）在密集查成绩的期间要考虑是否需要存库这个功能
		AppSpider appSpider = new AppSpider(account);
		appSpider.getToken();
		AllGradeAndCourse gradeAndCourse = appSpider.getGradeAndCourse();
		for (AllGradeAndCourse.GradeAndCourse andCourse : gradeAndCourse.getCurrentTermGrade()) {
//			gradeMapper.insert(andCourse.getGrade());
//			saveCourse(account, andCourse.getCourse());
			if (!courseMapper.ifExistCourse(andCourse.getCourse().getUid()))
				saveCourse(account,andCourse.getCourse());
			if(!gradeMapper.ifExistGrade(andCourse.getGrade().getAccount(),andCourse.getGrade().getCourseId()))
				saveGrade(account,andCourse.getGrade());
		}
	}

	public List<Grade> getStudentGrades(int account, String password){
		getCurrentGrade(account, password);
		List<Grade> studentGrades=gradeMapper.selectByAccount(account);
		return studentGrades;
	}

	private void saveCourse(int account, Course course){
		courseMapper.insert(course);
		courseMapper.insertStudentAndCourse(account, course.getUid());
	}

	private void saveGrade(int account,Grade grade){
		gradeMapper.insert(grade);
	}

	public String toText(List<Grade> studentGrades){
		StringBuffer buffer = new StringBuffer();
		int i=1;
		for (Grade grade:studentGrades){
			if(i==1){
				buffer.append("- - - - - - - - - - - - - -\n");
				buffer.append("|"+grade.getYear()+"学年，第"+grade.getTerm()+"学期|\n");i--;
				buffer.append("- - - - - - - - - - - - - -\n\n");
			}
			buffer.append("考试名称："+courseMapper.selectNameByUid(grade.getCourseId())+"\n")
			.append("成绩："+grade.getScore()/10).append("   学分："+grade.getPoint()/10+"\n\n");
			System.out.println(buffer);
		}
		return buffer.toString();
	}
}