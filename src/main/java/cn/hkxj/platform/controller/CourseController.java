package cn.hkxj.platform.controller;

import cn.hkxj.platform.elasticsearch.CourseTimeTableSearchService;
import cn.hkxj.platform.elasticsearch.document.CourseTimeTableDocument;
import cn.hkxj.platform.pojo.WebResponse;
import cn.hkxj.platform.pojo.constant.ErrorCode;
import cn.hkxj.platform.pojo.vo.CourseTimeTableVo;
import cn.hkxj.platform.service.CourseTimeTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * @author junrong.chen
 * @date 2018/10/13
 */
@Slf4j
@RestController
@RequestMapping("/course")
public class CourseController {


    @Resource
    private CourseTimeTableService courseTimeTableService;
    @Resource
    private CourseTimeTableSearchService courseTimeTableSearchService;

    @Resource
    private HttpSession httpSession;
    private static final int ACCOUNT_LENGTH = 10;
    private static final String ACCOUNT_PREFIX = "201";


    @GetMapping("/timetableV2")
    public WebResponse getTimeTableV2(@RequestParam(value = "account", required = false) String account) {

        if (Objects.isNull(account)) {
            account = (String) httpSession.getAttribute("account");
        }

        if (!isAccountValid(account)) {
            return WebResponse.fail(ErrorCode.ACCOUNT_OR_PASSWORD_INVALID.getErrorCode(), "账号无效");
        }

        List<CourseTimeTableVo> courseTimeTableVoList = courseTimeTableService.getCurrentTermCourseTimeTableByStudent(Integer.parseInt(account));
        return WebResponse.success(courseTimeTableVoList);
    }

    @GetMapping("/timetable/search")
    public WebResponse<List<CourseTimeTableDocument>> searchTimeTable(@RequestParam(value = "q") String query,
                                                                      @RequestParam(value = "page", required = false) Integer page,
                                                                      @RequestParam(value = "size", required = false) Integer size
                                       ) {

        if(page== null || size == null){
            page = 0;
            size = 10;
        }

        return WebResponse.success(courseTimeTableSearchService.searchCourseTimeTable(page, size ,query));
    }

    private boolean isAccountValid(String account) {
        return !Objects.isNull(account) && account.length() == ACCOUNT_LENGTH && account.startsWith(ACCOUNT_PREFIX);
    }


}
