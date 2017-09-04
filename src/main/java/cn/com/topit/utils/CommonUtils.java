package cn.com.topit.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

/**
 * get请求以及返回错误信息工具类
 * 
 * @author zzf
 * @version 2017-05-04
 *
 */
public class CommonUtils {
	// 字符串以逗号分隔
	public static String stringSplit(String str) {
		String[] strArray = str.split(",|，");
		String strs = "";
		for (int i = 0; i < strArray.length; i++) {
			strs += "'" + strArray[i] + "'";
			if (i < strArray.length - 1) {
				strs += ",";
			}
		}
		return strs;
	}

	// token验证
	public static String verifyToken(String authorization, String version) {
		PropertiesLoader prop = new PropertiesLoader("project.properties");
		String unauthorized = prop.getProperty("unauthorized");
		String ParaVersion = prop.getProperty("version");
		String url = prop.getProperty("ucenter.url");
		Integer tokenErrorState = prop.getInteger("tokenErrorState");
		String tokenErrorMessage = prop.getProperty("tokenErrorMessage");
		// 判断authorization是否为空
		if (authorization.equals("")) {
			return unauthorized;
		}
		// 比较版本号
		if (!version.toUpperCase().equals(ParaVersion)) {
			return CommonUtils.errorVersionMessage();
		}
		// 截取前面7位获取authorization
		String str = authorization.substring(7, authorization.length());
		// 通过token进行验证，可获取到手机号。
		String json_String = CommonUtils.sendGet(url + "/user.php",
				"access_token=" + str);
		JSONObject jsonObj = JSONObject.fromObject(json_String);
		if (jsonObj.getString("status").equals("4000")) {
			return CommonUtils.message(tokenErrorState, tokenErrorMessage);
		}
		return "";
	}

	// 返回错误信息
	public static String message(int status, String message) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("errcode", status);
		jsonObject.put("message", message);
		// map.put("Data",data);
		return jsonObject.toString();
	}

	// 返回版本错误
	public static String errorVersionMessage() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("errcode", "请求未知的API版本");
		return jsonObject.toString();
	}

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			/*
			 * // 获取所有响应头字段 Map<String, List<String>> map =
			 * connection.getHeaderFields(); // 遍历所有的响应头字段 for (String key :
			 * map.keySet()) { System.out.println(key + "--->" + map.get(key));
			 * }
			 */
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	// post表单请求
	public static String sendPost(String urlParam, Map<String, String> params,
			String charset) {
		StringBuffer resultBuffer = null;
		// 构建请求参数
		StringBuffer sbParams = new StringBuffer();
		if (params != null && params.size() > 0) {
			for (Entry<String, String> e : params.entrySet()) {
				sbParams.append(e.getKey());
				sbParams.append("=");
				sbParams.append(e.getValue());
				sbParams.append("&");
			}
		}
		HttpURLConnection con = null;
		OutputStreamWriter osw = null;
		BufferedReader br = null;
		// 发送请求
		try {
			URL url = new URL(urlParam);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			if (sbParams != null && sbParams.length() > 0) {
				osw = new OutputStreamWriter(con.getOutputStream(), charset);
				osw.write(sbParams.substring(0, sbParams.length() - 1));
				osw.flush();
			}
			// 读取返回内容
			resultBuffer = new StringBuffer();
			int contentLength = Integer.parseInt(con
					.getHeaderField("Content-Length"));
			if (contentLength > 0) {
				br = new BufferedReader(new InputStreamReader(
						con.getInputStream(), charset));
				String temp;
				while ((temp = br.readLine()) != null) {
					resultBuffer.append(temp);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (osw != null) {
				try {
					osw.close();
				} catch (IOException e) {
					osw = null;
					throw new RuntimeException(e);
				} finally {
					if (con != null) {
						con.disconnect();
						con = null;
					}
				}
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					br = null;
					throw new RuntimeException(e);
				} finally {
					if (con != null) {
						con.disconnect();
						con = null;
					}
				}
			}
		}

		return resultBuffer.toString();
	}

}
