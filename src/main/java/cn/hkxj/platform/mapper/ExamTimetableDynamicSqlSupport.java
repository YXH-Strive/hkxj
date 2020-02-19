package cn.hkxj.platform.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;

public final class ExamTimetableDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final ExamTimetable examTimetable = new ExamTimetable();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = examTimetable.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = examTimetable.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> roomName = examTimetable.roomName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> courseNum = examTimetable.courseNum;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> courseOrder = examTimetable.courseOrder;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> termYear = examTimetable.termYear;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> termOrder = examTimetable.termOrder;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> examDate = examTimetable.examDate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> startTime = examTimetable.startTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> endTime = examTimetable.endTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> day = examTimetable.day;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> schoolWek = examTimetable.schoolWek;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> gmtCreate = examTimetable.gmtCreate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> gmtModify = examTimetable.gmtModify;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class ExamTimetable extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> roomName = column("room_name", JDBCType.VARCHAR);

        public final SqlColumn<String> courseNum = column("course_num", JDBCType.VARCHAR);

        public final SqlColumn<String> courseOrder = column("course_order", JDBCType.VARCHAR);

        public final SqlColumn<String> termYear = column("term_year", JDBCType.VARCHAR);

        public final SqlColumn<Integer> termOrder = column("term_order", JDBCType.INTEGER);

        public final SqlColumn<Date> examDate = column("exam_date", JDBCType.TIMESTAMP);

        public final SqlColumn<String> startTime = column("start_time", JDBCType.VARCHAR);

        public final SqlColumn<String> endTime = column("end_time", JDBCType.VARCHAR);

        public final SqlColumn<String> day = column("day", JDBCType.VARCHAR);

        public final SqlColumn<String> schoolWek = column("school_wek", JDBCType.VARCHAR);

        public final SqlColumn<Date> gmtCreate = column("gmt_create", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> gmtModify = column("gmt_modify", JDBCType.TIMESTAMP);

        public ExamTimetable() {
            super("exam_timetable");
        }
    }
}