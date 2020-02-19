package cn.hkxj.platform.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;

public final class StudentExamTimetableDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final StudentExamTimetable studentExamTimetable = new StudentExamTimetable();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = studentExamTimetable.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> account = studentExamTimetable.account;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> examTimetableId = studentExamTimetable.examTimetableId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> termYear = studentExamTimetable.termYear;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> termOrder = studentExamTimetable.termOrder;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> gmtCreate = studentExamTimetable.gmtCreate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> gmtModify = studentExamTimetable.gmtModify;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class StudentExamTimetable extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> account = column("account", JDBCType.VARCHAR);

        public final SqlColumn<Integer> examTimetableId = column("exam_timetable_id", JDBCType.INTEGER);

        public final SqlColumn<String> termYear = column("term_year", JDBCType.VARCHAR);

        public final SqlColumn<Integer> termOrder = column("term_order", JDBCType.INTEGER);

        public final SqlColumn<Date> gmtCreate = column("gmt_create", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> gmtModify = column("gmt_modify", JDBCType.TIMESTAMP);

        public StudentExamTimetable() {
            super("student_exam_timetable");
        }
    }
}