/*
 * -----------------------------------------------------------------------
 * @(#)Message.java
 *
 * Copyright 2003 Seino Information Service Co.,Ltd. All rights reserved.
 *
 * -----------------------------------------------------------------------
 * �V�X�e�����@�@�FCORE
 * �T�u�V�X�e�����F����
 * �v���O�������@�F���b�Z�[�W�̍쐬
 * -----------------------------------------------------------------------
 * �N���X���F FormValidity
 * �쐬�����F2003.12.12 T03-129 Y.Kanamori �V�K�쐬
 * �C�������F2012.06.06 N12-041 Y.Kanamori ��������Ή�
 * �C�������F2012.02.28 T12-176 T.Kondo    ����ҌŗL�̍��ږ����擾���郁�b�Z�[�W���\�b�h�ɏC��
 * �C�������F2003.99.99 XXX-XXX ���O       �C�����e
 * �C�������F
 *com.csf.base.message---------------------------------------------
 */
package com.csf.base.message;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;

public final class Message {

	private MessageResources resource;

	private static Message _inst = null;
	private static Map<String, MultipleResourceBundle> mutiBundleMap;

	private Message(MessageResources res) {
		super();
		resource = res;
	}

	public static Message getInstance(MessageResources res) {
		if (_inst == null) {
			_inst = new Message(res);
		}
		return _inst;
	}

	public static Message getInstance() {
		if (_inst == null) {
			_inst = new Message(new MyMessageResources());
		}
		return _inst;
	}

	public static String getMessage(String key) {
		return Message.getInstance().resource.getMessage(key);
	}

	public static String getMessage(String key, String... args) {
		return Message.getInstance().resource.getMessage(key, args);
	}

	public static String getMessage(User user, String key) {
		Locale loc = Locale.getDefault();
		String message = null;
		if (user != null) {
			loc = user.getLocale();
			String altKey = key + "." + user.getKitakushaCode();
			message = Message.getInstance().resource.getMessage(loc, altKey);
		}

		if (message == null) {
			message = Message.getInstance().resource.getMessage(loc, key);
		}
		return message;
	}

	public static String getMessage(User user, String key, String... args) {
		Locale loc = Locale.getDefault();
		String message = null;
		if (user != null) {
			loc = user.getLocale();
			String altKey = key + "." + user.getKitakushaCode();
			message = Message.getInstance().resource.getMessage(loc, altKey, args);
		}
		if (message == null) {
			message = Message.getInstance().resource.getMessage(loc, key, args);
		}
		return message;
	}

	public static String getMessage(Locale local, String key) {
		return Message.getInstance().resource.getMessage(local, key);
	}

	public static String getMessage(Locale local, String key, String... args) {
		return Message.getInstance().resource.getMessage(local, key, args);
	}

	public static String getMessage(IModel form, String key) {
		return Message.getInstance().resource.getMessage(form.getLocale(), key);
	}

	public static String getMessage(IModel form, String key, String... args) {
		return Message.getInstance().resource.getMessage(form.getLocale(), key, args);
	}

	public static class MyMessageResources implements MessageResources {

		public MyMessageResources() {
			mutiBundleMap = new HashMap<>();
		}

		@Override
		public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
			return null;
		}

		public String getMessage(Locale locale, String key) {
			if (locale == null) {
				locale = Locale.getDefault();
			}

			try {
				if (!mutiBundleMap.containsKey(locale.toString())) {
					mutiBundleMap.put(locale.toString(), new MultipleResourceBundle(locale, "CoreApplicationResources", "ApplicationResources"));
				}
				return mutiBundleMap.get(locale.toString()).getString(key);
			} catch (MissingResourceException e) {
				return null;
			}
		}

		public String getMessage(Locale locale, String key, User user) {
			if (locale == null) {
				locale = Locale.getDefault();
			}
			if (!mutiBundleMap.containsKey(locale.toString())) {
				mutiBundleMap.put(locale.toString(), new MultipleResourceBundle(locale, "CoreApplicationResources", "ApplicationResources"));
			}
			if (user == null) {
				return mutiBundleMap.get(locale.toString()).getString(key);
			}
			String altKey = key + "." + user.getKitakushaCode();
			String message = mutiBundleMap.get(locale.toString()).getString(altKey);

			if (message == null) {
				message = mutiBundleMap.get(locale.toString()).getString(altKey);
			}

			return message;
		}

		@Override
		public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
			if (locale == null) {
				locale = Locale.getDefault();
			}

			if (!mutiBundleMap.containsKey(locale.toString())) {
				mutiBundleMap.put(locale.toString(), new MultipleResourceBundle(locale, "CoreApplicationResources", "ApplicationResources"));
			}
			try {
				String message = mutiBundleMap.get(locale.toString()).getString(code);
				message = MessageFormat.format(message, args);

				return message;
			} catch (MissingResourceException e) {
				return null;
			}
		}

		@Override
		public String getMessage(Locale locale, String key, String... args) {
			return getMessage(key, args, locale);
		}

		@Override
		public String getMessage(String key) {
			return getMessage(key, null, Locale.getDefault());
		}

		@Override
		public String getMessage(String key, String... args) {
			return getMessage(key, args, Locale.getDefault());
		}

		@Override
		public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
			return null;
		}
	}

	public MessageResources getResource() {
		return resource;
	}

	public void setResource(MessageResources resources) {
		resource = resources;
	}

	public static String getMessage(User user, IModel form, String key) {
		if (user == null) {
			return Message.getInstance().resource.getMessage(form.getLocale(), key);
		}
		String altKey = key + "." + user.getKitakushaCode();
		String message = Message.getInstance().resource.getMessage(form.getLocale(), altKey);
		if (message == null) {
			message = Message.getInstance().resource.getMessage(form.getLocale(), key);
		}
		return message;
	}

	public static String getMessage(User user, IModel form, String key, String... args) {
		if (user == null) {
			return Message.getInstance().resource.getMessage(form.getLocale(), key, args);
		}
		String altKey = key + "." + user.getKitakushaCode();
		String message = Message.getInstance().resource.getMessage(form.getLocale(), altKey, args);
		if (message == null) {
			message = Message.getInstance().resource.getMessage(form.getLocale(), key, args);
		}
		return message;
	}
}
