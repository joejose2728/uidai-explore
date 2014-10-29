package uidai.explore.util;

import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {

	public static String encode(Object data) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(data);
		} catch (Exception ex) {
			return null;
		}
	}

	public static <T> T decode(String data, Class<T> theClass) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(data.getBytes(), theClass);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}