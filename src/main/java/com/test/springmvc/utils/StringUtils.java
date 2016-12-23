package com.test.springmvc.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;


public class StringUtils {
	private StringUtils(){}
	
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat format_cn = new SimpleDateFormat("yyyy年MM月dd日  HH时mm分ss秒"); 
	private static  Pattern pat = Pattern.compile("[u4e00-u9fa5]");   

	public static boolean isEmpty(Object obj){
		return obj == null ? true : String.valueOf(obj).trim().length() == 0;
	}

	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String toMD5(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");

			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes()));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resultString;
	}

	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static String getRandomChars(int length) {
		String uuid = getUUID();
		return uuid.substring(8, 8 + length);
	}

	public static String convertToUnicode(String str) {
		str = (str == null ? "" : str);
		String tmp;
		StringBuffer sb = new StringBuffer(1000);
		char c;
		int i, j;
		sb.setLength(0); 
		for (i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			sb.append("\\u");
			j = (c >>>8);
			tmp = Integer.toHexString(j);
			if (tmp.length() == 1) sb.append("0");
			sb.append(tmp);
			j = (c & 0xFF);
			tmp = Integer.toHexString(j);
			if (tmp.length() == 1)  sb.append("0");
			sb.append(tmp);
		}
		return sb.toString();
	}

	public static String encodeString(String param){
		String encodeParam = "";
		try {
			encodeParam = URLEncoder.encode(param,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encodeParam;
	}

	public static String encodeHtmlScriptTag(String str) {
		return str.replace("<", "&lt;")
		.replace(">", "&gt;");
	}

	/* 请求参数 */
	public static String getRequestParams(HttpServletRequest request){
		StringBuffer paramBuffer = new StringBuffer();
		Enumeration enu=request.getParameterNames();

		while(enu.hasMoreElements()){	
			String paraName=(String)enu.nextElement();
			paramBuffer.append(paraName).append("=").append(request.getParameter(paraName));
			paramBuffer.append("&");
		} 
		if(paramBuffer.length() > 0){
			paramBuffer = paramBuffer.insert(0, "?");
		}
		return paramBuffer.toString();
	}

	/**
	 * 
	 * 
	 * @date 2012-12-17
	 * @desc  生产验证文件（验证开发者url）
	 * @param devId
	 * @return
	 */
	public static String getValidateKey(int devId){
		String key = devId+"rd5s"+new Date().getTime();
		key = toMD5(key);

		return key;
	}

	/**
	 * 
	 * 
	 * @date 2012-12-20
	 * @desc  获取顶级域名或者参数
	 * @param url
	 * @param rootUrl true:顶级域名,false:获取参数
	 * @param params  true:获取参数
	 * @return
	 */
	public static String getRootUrl(String url,boolean rootUrl){
		if(url == null || url.equals("")) return null;

		StringBuffer buffer = new StringBuffer();
		for(int i = 0; i<url.length(); i++){
			if(i > 2) break;
			int index = url.indexOf("/");
			if(index == -1 && i == 2){
				buffer.append(url).append("/");
			}else{
				buffer.append(url.substring(0, index+1));
			}
			String netxUrl = url.substring(index+1);
			url = netxUrl;
		}
		if(rootUrl) return buffer.toString();
		else  return url;
	}



	public static String splitSuffix(String url){
		if(url == null){
			return null;
		}
		return url.substring(url.lastIndexOf("/")+1);
	}



	public static String splitPrefix(String url){
		if(url == null){
			return null;
		}
		return url.substring(0,url.lastIndexOf("/")+1);
	}


	public static String getListToString(List<String> data){
		StringBuffer buffer = new StringBuffer(10);
		for(int i = 0; i<data.size(); i++){
			buffer.append(data.get(i));
			if(i+1 < data.size()) buffer.append(",");
		}
		return buffer.toString();
	}

	public static String replaceFastDFSUrl(String url){
		String resultUrl = getRootUrl(url, false);
		return "/"+resultUrl;
	}

	public static boolean isSSL(String url){

		String host = url.substring(0,url.indexOf(":"));
		if(host != null && host.equals("https"))
			return true;
		else 
			return false;
	}

	public static String getRmoteIpAddr(HttpServletRequest request) { 
		String ip = request.getHeader("x-forwarded-for"); 

		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
			ip = request.getHeader("Proxy-Client-IP"); 

		} 
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
			ip = request.getHeader("WL-Proxy-Client-IP"); 

		} 
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
			ip = request.getRemoteAddr(); 

		} 
		//如果使用透明代理的话，得到的x-forwarded-for规则是，客户端真实 ip,经过的多台代理ip。如：111.111.111.11,2222.222.22.22
		if(ip != null){
			ip = ip.split(",")[0];
		}
		return ip; 
	} 

	public static String getDateFormate(Date date){
		return format.format(date);
	}
	public static String getCNDateFormate(Date date){
		return format_cn.format(date);
	}

	public static Date getStringToDate(String date){
		Date newDate = null;
		try {
			newDate = format.parse(date);
		} catch (ParseException e) {		
			e.printStackTrace();
		}
		return newDate;

	}

	public static String getPushListRedisKey(String appId,String type,String send_source){		
		return appId+"_"+type+"_"+send_source;
	}


	public static boolean isChinessage(String content){
		Matcher matcher = pat.matcher(content);      
		boolean flg = false;   

		if (matcher.find()){  
			flg = true;    
		}      
		return flg;  
	}

	public static String parseLocaleToString(Locale locale){
		String language ="zh_CN";
		if(locale == null)
			return language;
		
		String lg = locale.getLanguage();
		String cn = locale.getCountry();
		
		return language = lg+"_"+cn;

	}
	
	//generate emial code
	public static String getEmailToken(String emial){
		String token = getRandomChars(3) + "-" + getRandomChars(3) + "-" + toMD5(StringUtils.getUUID() + emial).substring(2, 22);
		return token;
	}
	
	public static void main(String[] args) {
		/*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:SS:MM");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, 200);

		System.out.println(format.format(calendar.getTime()));

		System.out.println("system1:"+calendar.getTime().getTime()/1000);
		System.out.println("system:"+System.currentTimeMillis() / 1000); */


	}
}
