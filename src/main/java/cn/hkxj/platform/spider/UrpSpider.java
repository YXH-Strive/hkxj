package cn.hkxj.platform.spider;

import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.exceptions.ReadTimeoutException;
import cn.hkxj.platform.pojo.Student;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
public class UrpSpider {
	private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	private String account;
	private String password;
	private final static Gson GSON = new Gson();
	private static final String INFORMATION_URL = "http://spider.hackerda.com/information";
	private static final String GRADE_URL = "http://spider.hackerda.com/grade";
	private static final String CURRENT_GRADE_URL = GRADE_URL+"/current";
	private static final String EVER_GRADE_URL = GRADE_URL+"/ever";
	private static OkHttpClient client = new OkHttpClient.Builder()
			.connectTimeout(15, TimeUnit.SECONDS)
			.build();

	public UrpSpider(String account, String password) {
		this.account = account;
		this.password = password;
	}

	public Student getInformation() throws PasswordUncorrectException, ReadTimeoutException {
		Map result;
		try {
			log.info("urp spider start get student info account{}", this.account);
			result = getResult(INFORMATION_URL);
		} catch (IOException e) {
			log.error("read spider server timeout");
			throw new ReadTimeoutException("本地服务器读取超时", e);
		}

		Object information = result.get("information");
		if (Objects.isNull(information)){
			log.info("read school server timeout account{}", this.account);
			throw new ReadTimeoutException("学校服务器读取超时");
		}

		Student student = new Student();
		try {
			BeanUtils.populate(student, (Map)information);
		} catch (IllegalAccessException | InvocationTargetException e) {
			log.error(e.getMessage());
			throw new RuntimeException("个人信息json解析出错", e);
		}

		return student;
	}

	public Map getGrade() throws IOException, PasswordUncorrectException {
		return getResult(GRADE_URL);
	}

	public void getCurrentGrade() throws IOException, PasswordUncorrectException {
		Map result = getResult(CURRENT_GRADE_URL);
	}

	public void getEverGrade() throws IOException, PasswordUncorrectException {
		Map result = getResult(EVER_GRADE_URL);
	}

	private Map getResult(String url) throws IOException, PasswordUncorrectException {
		RequestBody requestBody = getRequestBody();
		Request request = new Request.Builder()
				.url(url)
				.post(requestBody)
				.build();
		Response response = client.newCall(request).execute();

		String result = response.body().string();

		log.debug(result);

		HashMap resultMap = GSON.fromJson(result, HashMap.class);
		Double statu = (Double) resultMap.get("statu");

		if(statu.intValue() == 400) {
			log.info("password uncorrect account{} password{}", account, password);
			throw new PasswordUncorrectException("账号: "+account+"  密码："+password);
		}
		else if(statu.intValue() == 500) {
			throw new ReadTimeoutException("学校服务器连接超时");
		}
		return resultMap;
	}

	private RequestBody getRequestBody() {
		HashMap<String, String> postData = Maps.newHashMapWithExpectedSize(2);
		postData.put("account", account);
		postData.put("password", password);
		String json = GSON.toJson(postData);

		return RequestBody.create(JSON, json);
	}

}
