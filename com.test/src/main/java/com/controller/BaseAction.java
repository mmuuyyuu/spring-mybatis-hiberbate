package com.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.web.bind.annotation.ModelAttribute;

import com.alibaba.fastjson.JSONObject;


//import com.studyshen.publish.dao.ReslibrarysDao;
//import com.studyshen.publish.entity.Resources;

public class BaseAction<T> {

	public static final String STATUS = "status";
	public static final String WARN = "warn";
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	public static final String MESSAGE = "message";

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	public static Map<String,String> ParameterMap;


	public BaseAction() {

	}

	@SuppressWarnings("unchecked")
	@ModelAttribute
	public void setReqAndResp(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		
		ParameterMap=getParameterMap(request);
	}

	// AJAX输出，返回null
	public String ajax(String content, String type) {
		try {
			response.setContentType(type + ";charset=UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// AJAX输出文本，返回null
	public String ajaxText(String text) {
		return ajax(text, "text/plain");
	}

	// 根据字符串输出JSON，返回null
	public String ajaxJson(String jsonString) {
		return ajax(jsonString, "text/html");
	}

	// 输出JSON警告消息，返回null
	public String ajaxJsonWarnMessage(String message) {
		JSONObject jsonMap = new JSONObject();
		jsonMap.put(STATUS, WARN);
		jsonMap.put(MESSAGE, message);
		return ajax(jsonMap.toString(), "text/html");
	}

	// 输出JSON成功消息，返回null
	public String ajaxJsonSuccessMessage(Object message) {
		JSONObject jsonMap = new JSONObject();
		jsonMap.put(STATUS, SUCCESS);
		jsonMap.put(MESSAGE, message);
		return ajax(jsonMap.toString(), "text/html");
	}

	// 输出JSON错误消息，返回null
	public String ajaxJsonErrorMessage(String message) {
		JSONObject jsonMap = new JSONObject();
		jsonMap.put(STATUS, ERROR);
		jsonMap.put(MESSAGE, message);
		return ajax(jsonMap.toString(), "text/html");
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getParameterMap(HttpServletRequest request) {
		// 参数Map
		Map properties = request.getParameterMap(); // 返回值Map
		Map returnMap = new HashMap();
		Iterator entries = properties.entrySet().iterator();
		Map.Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		return returnMap;
	}

}
