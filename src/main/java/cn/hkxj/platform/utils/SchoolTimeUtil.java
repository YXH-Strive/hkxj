package cn.hkxj.platform.utils;

import cn.hkxj.platform.pojo.LessonOrder;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalTime;

/**
 * @author junrong.chen
 * @date 2018/10/31
 */
public class SchoolTimeUtil {
	/**
	 * 开学日期
	 */
	private static final DateTime TERM_START = new DateTime(2018, 8, 24, 0, 0);

	/**
	 * 教学周
	 */
	public static int getSchoolWeek(){
		return (int) new Duration(TERM_START, new DateTime()).getStandardDays() / 7 + 1;
	}

	/**
	 * 星期几
	 */
	public static int getDayOfWeek(){
		return new DateTime().getDayOfWeek();
	}

	public static String getDayOfWeekChinese() {
		switch (getDayOfWeek()){
			case 1:
				return "星期一";
			case 2:
				return "星期二";
			case 3:
				return "星期三";
			case 4:
				return "星期四";
			case 5:
				return "星期五";
			case 6:
				return "星期六";
			case 7:
				return "星期日";
			default:
				throw new IllegalArgumentException("never happen");
		}
	}

	/**
	 * 查询现在为第几节课
	 */
	public static int getOrderOfLesson(){
		return LessonOrder.getLessonOrder(new LocalTime()).getOrder();
	}

	public static int getWeekDistinct() {
		 if(getSchoolWeek() % 2 == 0){
		 	return 2;
		 }
		 else {
		 	return 1;
		 }
	}

	public static void main(String[] args) {
		System.out.println(StringUtils.upperCase("科s108"));
	}
}
