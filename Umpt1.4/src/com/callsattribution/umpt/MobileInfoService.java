package com.callsattribution.umpt;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

public class MobileInfoService {
	/**
	 * 获得电话号码归属地信息
	 * 
	 * @param inStream
	 * @param mobile
	 * @return
	 * @throws Exception
	 */
	public static String getMobileAddress(InputStream inStream, String mobile)
			throws Exception {
		// 定义输入流
		String soap = readSoapFile(inStream, mobile);
		byte[] data = soap.getBytes();
		//使用URL连接网络;
		URL url = new URL(
				"http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setConnectTimeout(5 * 1000);
		// 如果通过post提交数据，必须设置允许对外输出数据
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type",
				"application/soap+xml; charset=utf-8");
		conn.setRequestProperty("Content-Length", String.valueOf(data.length));
		OutputStream outStream = conn.getOutputStream();
		outStream.write(data);
		outStream.flush();
		outStream.close();
		if (conn.getResponseCode() == 200) {
			return parseResponseXML(conn.getInputStream());
		}
		return null;
	}

	/**
	 * 读取Soap文件
	 * 
	 * @param inStream
	 * @param mobile
	 * @return
	 * @throws Exception
	 */
	private static String readSoapFile(InputStream inStream, String mobile)
			throws Exception {
		// 读取输入流
		byte[] data = StreamTool.readInputStream(inStream);
		String soapxml = new String(data);
		Map<String, String> params = new HashMap<String, String>();
		params.put("mobile", mobile);
		return replace(soapxml, params);
	}

	/**
	 * 替换占位符方法
	 * 
	 * @param xml
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String replace(String xml, Map<String, String> params)
			throws Exception {
		String result = xml;
		if (params != null && !params.isEmpty()) {
			// 循环替换掉所有占位符
			for (Map.Entry<String, String> entry : params.entrySet()) {
				// 需要对$进行转义
				String name = "\\$" + entry.getKey();
				// 使用正则表达式替换
				Pattern pattern = Pattern.compile(name);
				Matcher matcher = pattern.matcher(result);
				if (matcher.find()) {
					result = matcher.replaceAll(entry.getValue());
				}
			}
		}
		return result;
	}

	/**
	 * 解析返回的XML字符串数据
	 * 
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	private static String parseResponseXML(InputStream inStream)
			throws Exception {
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(inStream, "UTF-8");
		// 产生第一个事件
		int eventType = parser.getEventType();
		// 只要不是文档结束事件
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_TAG:
				// 获取解析器当前指向的元素的名称
				String name = parser.getName();
				if ("getMobileCodeInfoResult".equals(name)) {
					return parser.nextText();
				}
				break;
			}
			eventType = parser.next();
		}
		return null;
	}
}