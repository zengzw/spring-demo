package com.test.commom.web.source.spring;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  Implementation of LocaleResolver that simply uses the primary locale
 * specified in the "accept-language" header of the HTTP request (that is,
 * the locale sent by the client browser, normally that of the client's OS).
 *
 * <p>Note: Does not support <code>setLocale</code>, since the accept header
 * can only be changed through changing the client's locale settings.
 * @author sunkey
 * @date Apr 3, 2013 9:16:39 AM
 * @version 1.0.0
 * @copyright fpx.com
 */
public class AcceptHeaderLocaleResolver extends org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver {
	
	private Locale locale = null;

	public Locale resolveLocale(HttpServletRequest request) {
		if(locale == null){
			return request.getLocale();
		}
		return locale;
	}

	public void setLocale(HttpServletRequest request,	HttpServletResponse response, Locale locale) {
		this.locale = locale;
	}
}
