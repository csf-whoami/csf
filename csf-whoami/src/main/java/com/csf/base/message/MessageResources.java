package com.csf.base.message;

import java.util.Locale;

import org.springframework.context.MessageSource;

public interface MessageResources extends MessageSource {
	/**
	 * 
	 * @param locale
	 * @param key
	 * @return
	 */
	String getMessage(Locale locale, String key);

	/**
	 * 
	 * @param locale
	 * @param key
	 * @param args
	 * @return
	 */
	String getMessage(Locale locale, String key, String... args);

	/**
	 * 
	 * @param key
	 * @return
	 */
	String getMessage(String key);

	/**
	 * 
	 * @param key
	 * @param args
	 * @return
	 */
	String getMessage(String key, String... args);

}
