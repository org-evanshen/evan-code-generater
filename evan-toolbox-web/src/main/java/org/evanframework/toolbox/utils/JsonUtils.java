package org.evanframework.toolbox.utils;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

/**
 * Json 工具
 * 
 * @author <a href="mailto:shenwei@qq.com">ShenWei</a>
 * @version Date: 2010-5-7 下午02:12:21
 * @since
 */
public class JsonUtils {
	// private static final Logger logger =
	// LoggerFactory.getLogger(JsonUtils.class);

	private static final ObjectMapper mapper = new ObjectMapper();

	/**
	 * 将javaBean转换成Json字符串
	 * 
	 * @param o
	 * @return Json字符串
	 */
	public static String beanToJSON(Object o) {
		if (o == null) {
			return null;
		}
		String json = null;
		try {
			json = mapper.writeValueAsString(o);
		} catch (IOException e) {
			throw new UnsupportedOperationException("Can not convert [" + o + "] to JSON", e);
		}

		return json;
	}

	/**
	 * 将Json字符串转换成javaBean
	 * 
	 * @param json
	 * @param c
	 * @return javaBean
	 */
	public static <T> T jsonToBean(String json, Class<T> c) {
		if (StringUtils.isBlank(json)) {
			return null;
		}
		T returnO = null;
		try {
			returnO = mapper.readValue(json, c);
		} catch (IOException e) {
			throw new UnsupportedOperationException("Can not convert JSON [" + json + "] to [" + c + "]", e);
		}
		return returnO;
	}
}

// if (logger.isErrorEnabled()) {
// logger.error(e.getMessage(), e);
// }
