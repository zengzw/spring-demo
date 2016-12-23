package com.test.springmvc.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;



public class HttpUtils {

	private static Logger logger = Logger.getLogger(HttpUtils.class.getName());
	private static final String charset = "utf-8";	
	public static ThreadLocal<String> ipLocal = new ThreadLocal<String>();
	

	public static String post(String path, String method, String params,String contentType,boolean isSSL,String authCode) {

		HttpURLConnection conn = null; 
		try {
			logger.info(String.format("request-->Path[%s], RequestMethod[%s],RequestBody[%s]", path, method,
					URLDecoder.decode(params, charset)));
			boolean isPost = true;
			if (method.equals("GET")) {
				isPost = false;
				if (params != null && !params.equals(""))
					path += "?" + params;
			}

			//auto add ssl request
			if(isSSL){
				initSSL();
			}

			URL url = new URL(path);

			InetAddress address = InetAddress.getByName(url.getHost());
			String ip = address.getHostAddress();
			ipLocal.set(ip);
			conn = (HttpURLConnection) url.openConnection();

			// TODO 连接超时的设置
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			conn.setRequestMethod(method);
			conn.setUseCaches(false);
			conn.setDoOutput(true);
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Charset", charset);
			if(authCode != null && !authCode.isEmpty())
				conn.setRequestProperty("Authorization", authCode);

			if (isPost) {
				byte[] data = params.getBytes(charset);
				conn.setRequestProperty("Content-Length", String.valueOf(data.length));
				conn.setRequestProperty("Content-Type", contentType);
				DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
				outStream.write(data);
				outStream.flush();
			}

			InputStream in = conn.getInputStream();
			StringBuffer sb = new StringBuffer();
			InputStreamReader reader = new InputStreamReader(in, charset);

			char[] buff = new char[1024];
			int len;
			while ((len = reader.read(buff)) > 0) {
				sb.append(buff, 0, len);
			}

			logger.info(String.format("response-->Path[%s], requestParma[%s],ResponseCode[%s], ResponseBody[%s]", path,
					URLDecoder.decode(params, charset), conn.getResponseCode(), sb.toString().replace("\n", "")));			
			if (conn.getResponseCode() == 200) {
				if (!sb.toString().equals("")) {
					return sb.toString();
				} else {
					logger.error("Response empty string.");
				}
			} else {
				logger.warn("Response not 200 - " + conn.getResponseCode() + ", " + conn.getResponseMessage());
			}
		} catch (IOException ioe) {

			ioe.printStackTrace();
		
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(String.format("Path[%s], Error[%s]", path, e.getMessage()));
		}
		return null;
	}

	public static String doPost(String path, Map<String, Object> params) {
		return post(path, "POST", parse(params),ContentTypeEnum.Formurlencoded.value(),false,null);
	}

	public static String doGet(String path, Map<String, Object> params) {
		return post(path, "GET", parse(params),ContentTypeEnum.Formurlencoded.value(),false,null);
	}

	public static String doGet(String path, Map<String, Object> params,boolean isSSL) {
		return post(path, "GET", parse(params),ContentTypeEnum.Formurlencoded.value(),true,null);
	}

	public static String doGet(String path, Map<String, Object> params,boolean isSSL,String authCode) {
		return post(path, "GET", parse(params),ContentTypeEnum.Formurlencoded.value(),true,authCode);
	}


	public static String doPostJson(String path,String jsonParam){

		return post(path, "POST",jsonParam,ContentTypeEnum.Json.value(),false,null);
	}


	private static String parse(Map<String, Object> params) {
		if (params == null || params.equals(""))
			return "";

		StringBuilder builder = new StringBuilder();
		for (String key : params.keySet()) {
			try {
				builder.append(key + "=" + URLEncoder.encode(String.valueOf(params.get(key)), charset) + "&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return builder.toString();
	}

	public static String[] httpGet(String url, boolean isContent) {
		ClientConnectionManager connManager = new PoolingClientConnectionManager();
		HttpClient client = new DefaultHttpClient(connManager);
		try {
			client = getSecuredHttpClient(client);

		} catch (Exception e) {
			e.printStackTrace();
		}
		String result[] = new String[] { "200", "" };
		HttpGet get = new HttpGet(url);
		/*	String base = String.valueOf(Base64.encode("57b9ef19d4be5de08df12aa0:13ac09b17715bd117163d8a1".getBytes()));
		System.err.println(base);
		get.setHeader("Authorization", "Basic "+base);*/
		HttpResponse response = null;
		try {
			response = client.execute(get);
			int statusCode = response.getStatusLine().getStatusCode();	
			logger.info("url = " + url + " #statusCode = " + statusCode);

			if (statusCode != HttpStatus.SC_OK) {
				result[0] = String.valueOf(statusCode);
				return result;
			}
			if (isContent) {
				HttpEntity entity = response.getEntity();
				result[1] = EntityUtils.toString(entity, charset);
			}

		} catch (Exception e) {
			e.printStackTrace();
			result[0] = "500";
			result[1]="";

			logger.error(e.getMessage(), e);
		} finally {
			get.abort();
		}
		return result;

	}


	public static byte[] getNetFileBtye(String fileUrl) {
		if(StringUtils.isSSL(fileUrl)){
			initSSL();
		}	

		InputStream inStream = null;
		BufferedInputStream bis = null;
		try {
			URL	url = new URL(fileUrl);
			URLConnection conn = url.openConnection();
			conn.setConnectTimeout(3000);
			inStream = conn.getInputStream();
			bis = new BufferedInputStream(inStream);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int readLength = 0;
			while ((readLength = bis.read()) != -1) {
				baos.write(readLength);
			}
			return baos.toByteArray();

		} catch (Exception e) {		
			logger.error("get net file error:"+fileUrl,e);
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (inStream != null) {
					inStream.close();
				}
				if (bis != null) {
					bis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


	}
	/** 添加ssl认证 **/
	protected static void initSSL() {

		try {

			TrustManager[] tmCerts = new javax.net.ssl.TrustManager[1];
			tmCerts[0] = new SimpleTrustManager();
			javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
			sc.init(null, tmCerts, new java.security.SecureRandom());

			javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			HostnameVerifier hv = new SimpleHostnameVerifier();
			HttpsURLConnection.setDefaultHostnameVerifier(hv);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static DefaultHttpClient getSecuredHttpClient(HttpClient httpClient) throws Exception {
		final X509Certificate[] _AcceptedIssuers = new X509Certificate[] {};
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {
				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return _AcceptedIssuers;
				}
				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) {
				}
				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType) {
				}
			};
			ctx.init(null, new TrustManager[] { tm }, new SecureRandom()); 
			SSLSocketFactory ssf = new SSLSocketFactory(ctx);
			ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			ClientConnectionManager ccm = httpClient.getConnectionManager();
			SchemeRegistry sr = ccm.getSchemeRegistry();
			sr.register(new Scheme("https", ssf, 443));
			return new DefaultHttpClient(ccm, httpClient.getParams());
		} catch (Exception e) {
			throw e;
		}
	}

	private static String getContentCharSet(final HttpEntity entity) throws ParseException {
		if (entity == null) {
			throw new IllegalArgumentException("HTTP entity may not be null");
		}
		String charset = null;
		if (entity.getContentType() != null) {
			HeaderElement values[] = entity.getContentType().getElements();
			if (values.length > 0) {
				NameValuePair param = values[0].getParameterByName("charset");
				if (param != null) {
					charset = param.getValue();
				}
			}
		}
		return charset;
	}

}
