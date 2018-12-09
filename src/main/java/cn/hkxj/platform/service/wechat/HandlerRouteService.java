package cn.hkxj.platform.service.wechat;

import cn.hkxj.platform.interceptor.WechatOpenIdInterceptor;
import cn.hkxj.platform.service.wechat.handler.messageHandler.CourseMessageHandler;
import cn.hkxj.platform.service.wechat.handler.messageHandler.EmptyRoomHandler;
import cn.hkxj.platform.service.wechat.handler.messageHandler.ExamMessageHandler;
import cn.hkxj.platform.service.wechat.handler.messageHandler.GradeMessageHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author JR Chan
 * @date 2018/6/11 17:19
 * 所有handle的路由规则都在这个service里配置
 */
@Service
public class HandlerRouteService {
	@Resource
	private WxMessageRouter router;

	@Resource
    private CourseMessageHandler courseMessageHandler;

	@Resource
	private GradeMessageHandler gradeMessageHandler;

	@Resource
	private EmptyRoomHandler emptyRoomHandler;

	@Resource
    private ExamMessageHandler examMessageHandler;

	@Resource
	private WechatOpenIdInterceptor wechatOpenIdInterceptor;


	public void handlerRegister() {
		router
				.rule()
					.async(false)
					.rContent("(课表|课程|今日课表)")
					.interceptor(wechatOpenIdInterceptor)
					.handler(courseMessageHandler)
				.end()
				.rule()
					.async(false)
					.interceptor(wechatOpenIdInterceptor)
					.content("成绩")
					.handler(gradeMessageHandler)
				.end()
                .rule()
                    .async(false)
                    .rContent(".*?考试.*?")
                .interceptor(wechatOpenIdInterceptor)
                    .handler(examMessageHandler)
                .end()
                .rule()
                    .async(false)
                    .rContent("空教室.*?")
                    .handler(emptyRoomHandler)
                .end();

	}
}
