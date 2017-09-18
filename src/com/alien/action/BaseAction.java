package com.alien.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.parser.Entity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.bouncycastle.jce.provider.JDKDSASigner.noneDSA;
import org.hibernate.criterion.Criterion;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.alien.page.Pager;
import com.alien.util.ComplexPropertyPreFilter;
import com.alien.util.DESPlusUtil;
import com.alien.util.DateJsonValueProcessor;
import com.alien.util.JsonFilter;
import com.alien.util.JsonUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * ACTION 客户端父类
 */
@SuppressWarnings("unchecked")
public class BaseAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	private static final String HEADER_ENCODING = "UTF-8";
	private static final boolean HEADER_NO_CACHE = true;
	public Map<String, Object> session = new HashMap<String, Object>();// 定义session变量
	public static final String LOGIN = "login";
	public static final String LIST = "list";
	public static final String QUERY = "query";
	private String successResultValue;
	protected final String JSON_SUCCESS = "{\"status\":success,\"message\":\"操作成功\"}";
	protected final String JSON_FALSE = "{\"status\":error,\"message\":\"操作失败\"}";
	public String jsonStr;
	private static final long serialVersionUID = 1L;
	private static final String HEADER_JSON_CONTENT_TYPE = "text/plain";
	public static final String title = "<?xml version=\"1.0\"  encoding=\"UTF-8\"?>";
	protected String id;
	protected String[] ids;
	protected Pager pager = new Pager();
	protected String redirectUrl;// 跳转URL
	protected Log log = LogFactory.getLog(this.getClass());
	public final List<Criterion> parmsList = new ArrayList<Criterion>();
	public final List<String> lists = new ArrayList<String>();
	private String message;
	
	public String callbackType="forward",forwardUrl="",navTabId="1";

	public static final String STATUS_PARAMETER_NAME = "status";// 操作状态参数名称
	public static final String MESSAGE_PARAMETER_NAME = "message";// 操作消息参数名称

	// 操作状态（警告、错误、成功）
	public enum Status {
		warn, error, success
	}

	public BaseAction() {
		setSuccessResultValue("/json.jsp");
	}

	/*
	 * 消息提示
	 */
	public String SUCCESS(String redrcit, String message) {
		this.redirectUrl = redrcit;
		this.message = message;
		return "success";
	}

	public String ERROR(String redrcit, String message) {
		this.redirectUrl = redrcit;
		this.message = message;
		return "error";
	}

	// 判断是否为添加
	public Boolean getIsAddAction() {
		if (id == null) {
			return true;
		} else {
			return false;
		}
	}

	// 判断是否为编辑
	public Boolean getIsEditAction() {
		if (id != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * date类型转String
	 */
	public String turnStr(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(date);
		return time;
	}

	/**
	 * string类型转date
	 * 
	 * @throws ParseException
	 */
	public Date turnDate(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date dates = (Date) sdf.parse(date);
		return dates;
	}

	/**
	 * 字符串加密
	 * 
	 * @param str
	 * @return
	 */

	@SuppressWarnings("static-access")
	public String encrypt(String str) {

		String encryptedStr = null;
		DESPlusUtil desPlus;
		try {
			desPlus = new DESPlusUtil();
			encryptedStr = desPlus.encrypt(str);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return encryptedStr;
	}

	/**
	 * 字符串解密
	 * 
	 * @param str
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String decrypt(String str) {
		String decryptedStr = null;
		DESPlusUtil desPlus;
		try {
			desPlus = new DESPlusUtil();
			decryptedStr = desPlus.decrypt(str);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return decryptedStr;
	}

	/**
	 * 根据两个日期求天数
	 */
	public static int daysBetween(Date date1, Date date2) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 根据操作状态、消息内容输出AJAX(servlet输出方式)
	 * 
	 * @param status
	 * @param message
	 * @return
	 */
	protected String ajax(Status status, String message) {
		HttpServletResponse response = initResponse(HEADER_JSON_CONTENT_TYPE);
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put(STATUS_PARAMETER_NAME, status.toString());
		jsonMap.put(MESSAGE_PARAMETER_NAME, message);
		JsonUtil.toJson(response, jsonMap);
		return NONE;
	}

	/**
	 * 输出json(struts2输出方式)
	 * 
	 * @param json
	 *            json字符串
	 * @return
	 */
	protected String ajax(String json) {
		jsonStr = "{\"status\":\"" + Status.success.toString() + "\",\"message\":\"操作成功\",\"toObject\":" + json + "}";
		return "json";
	}

	/**
	 * 根据操作状态、消息内容输出AJAX(struts2输出方式)
	 * 
	 * @param status
	 * @param message
	 * @return
	 */
	protected String ajaxJson(Status status, String message) {
		jsonStr = "{\"status\":\"" + status.toString() + "\",\"message\":\"" + message + "\"}";
		return "json";
	}

	// 根据Object输出AJAX
	protected String ajax(Object object) {
		HttpServletResponse response = initResponse(HEADER_JSON_CONTENT_TYPE);
		JsonUtil.toJson(response, object);
		return NONE;
	}

	// 根据list输出ajax
	protected String ajax(List<Object> object) {
		HttpServletResponse response = initResponse(HEADER_JSON_CONTENT_TYPE);
		JsonUtil.toJson(response, object);
		return NONE;
	}

	
	protected String toJson(Object object, boolean flag) {
		String json = JSON.toJSONString(object, flag);
		jsonStr = "{\"status\":\"" + Status.success.toString() + "\",\"message\":\"操作成功\",\"toObject\":" + json + "}";
		return "json";

	}
	
	
	public String fastJson(Object object) {
		getResponse().setContentType("application/json;charset=utf-8");
		try {
			getResponse().getWriter().write(JSON.toJSONString(object,SerializerFeature.WriteDateUseDateFormat));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return NONE;

	}

	public String fastJson( final Class<?> t, Object object,  final String[] fileName) {
		getResponse().setContentType("application/json;charset=utf-8");
		try {
			// 过滤不需要解析的字段（1.其中对字段与实体对象适用 2.*@JSONField(serialize=false)适用于set对象）
			
			if (fileName != null) {
				ComplexPropertyPreFilter filter = new ComplexPropertyPreFilter();

				filter.setExcludes(new HashMap<Class<?>, String[]>() {

					private static final long serialVersionUID = -8411128674046835592L;

					{
						put(t, fileName);
					}
				});
				

				getResponse().getWriter()
						.write(JSON.toJSONString(object, filter, SerializerFeature.WriteDateUseDateFormat));
			}
			else {
				getResponse().getWriter().write(JSON.toJSONString(object, SerializerFeature.WriteDateUseDateFormat));
			}
			// 转为为json

		} catch (IOException e) {

			e.printStackTrace();
		}
		return NONE;

	}
	
	/**
	 * 成功提示页
	 * @param message
	 * @return
	 */
	public String success(String message,String navTabId, String callbackType, String forwardUrl) {
		
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("statusCode",200);
		map.put("message",message);
		map.put("navTabId",navTabId);
		map.put("rel","success");
		map.put("callbackType",callbackType);
		map.put("forwardUrl",forwardUrl);
		map.put("confirmMsg","");
		return fastJson(map);
	}
	/**
	 * 成功提示页
	 * @param message
	 * @return
	 */
	public String success(String message) {
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("statusCode",200);
		map.put("message",message);
		map.put("navTabId","1");
		map.put("rel","success");
		map.put("callbackType","forward");
		map.put("forwardUrl","");
		map.put("confirmMsg","");
		return fastJson(map);
	}
	/**
	 * 错误信息提示页
	 * @param message
	 * @return
	 */
	public String error(String message,String callback, String forward) {
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("statusCode",300);
		map.put("message",message);
		map.put("navTabId","1");
		map.put("rel","error");
		map.put("callbackType",callbackType);
		map.put("forwardUrl",forwardUrl);
		map.put("confirmMsg","");
		return fastJson(map);
	}
	/**
	 * 错误提示页
	 * @param message
	 * @return
	 */
	public String error(String message) {
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("statusCode",300);
		map.put("message",message);
		map.put("navTabId","1");
		map.put("rel","error");
		map.put("callbackType","forward");
		map.put("forwardUrl","");
		map.put("confirmMsg","");
		return fastJson(map);
	
	}

	/***
	 * #####try
	 * 
	 * @param contentType
	 * @return
	 * 
	 * 		protected String tojsonobj(List list) { Gson gson = new
	 *         GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	 *         return gson.toJson(list); }
	 */

	private HttpServletResponse initResponse(String contentType) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType(contentType + ";charset=" + HEADER_ENCODING);
		if (HEADER_NO_CACHE) {
			response.setDateHeader("Expires", 1L);
			response.addHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
		}
		return response;
	}

	/**
	 * 将parmsList转换成Criterion[]数组
	 * 
	 * @return
	 */
	protected Criterion[] parmsListToCriterion(List<Criterion> parmsList) {
		return parms(parmsList);
	}

	// 以下三个方式在排查错误时使用()
	/*
	 * public void addActionError(String anErrorMessage){
	 * System.err.println(anErrorMessage); } public void addActionMessage(String
	 * aMessage){ System.err.println(aMessage); } public void
	 * addFieldError(String fieldName, String errorMessage){
	 * System.err.println(errorMessage); }
	 */

	/**
	 * 将查询条件parmsList转换成数组
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	protected Criterion[] parms(List<Criterion> list) {
		Criterion[] criterions = new Criterion[list.size()];
		int i = 0;
		for (Criterion criterion : list) {
			criterions[i] = criterion;
			i++;
		}
		return criterions;
	}

	/**
	 * 重定向到某个地址
	 * 
	 * @param urlpath
	 * @return
	 */
	protected String redirecturl(String urlpath) {
		try {
			String url = new String(urlpath);
			getResponse().sendRedirect(url);
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 获取ServletContext
	protected ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}

	// 获取ContextPath
	protected String getContextPath() {
		return ServletActionContext.getRequest().getContextPath();
	}

	// 获取真实路径
	protected String getRealPath(String path) {
		return ServletActionContext.getServletContext().getRealPath(path);
	}

	public String getSuccessResultValue() {
		return successResultValue;
	}

	public String getJsonStr() {
		return jsonStr;
	}

	public void setSuccessResultValue(String successResultValue) {
		this.successResultValue = successResultValue;
	}

	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}

	public void setSession(Map arg0) {
		this.session = arg0;
	}

	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	protected HttpSession getSession() {
		return getRequest().getSession();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletResponse getResopnse() {
		return response;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
