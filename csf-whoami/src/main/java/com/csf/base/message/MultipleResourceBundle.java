package com.csf.base.message;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class MultipleResourceBundle extends ResourceBundle {

	/**
	 * The bundle names for the ResourceBundles to load in.
	 */
	private String[] bundleNames;

	private Locale locale;

	/**
	 * A Map containing the combined resources of all parts building this
	 * MultipleResourceBundle.
	 */
	private Map<String, String> combined;

	/**
	 * Construct a <code>MultipleResourceBundle</code> for the passed in bundle-names.
	 * 
	 * @param bundleNames bundle name file list.
	 */
	public MultipleResourceBundle(String... bundleNames) {
		this.bundleNames = bundleNames;
		loadBundlesOnce();
	}

	/**
	 * Construct a <code>MultipleResourceBundle</code> for the passed in bundle-names.
	 * 
	 * @param bundleNames bundle name file list.
	 */
	public MultipleResourceBundle(Locale locale, String... bundleNames) {
		this.locale = locale;
		this.bundleNames = bundleNames;
		loadBundlesOnce();
	}

	@Override
	public Object handleGetObject(String key) {
		if (key == null) {
			throw new NullPointerException();
		}
		return combined.get(key);
	}

	@Override
	public Enumeration<String> getKeys() {
		return Collections.enumeration(combined.keySet());
	}

	/**
	 * Load the resources once.
	 */
	private void loadBundlesOnce() {
		if (combined == null) {
			combined = new HashMap<String, String>();

			for (String bundleName : bundleNames) {
				ResourceBundle bundle = locale == null ? ResourceBundle.getBundle(bundleName) : ResourceBundle.getBundle(bundleName, locale);
				Enumeration<String> keys = bundle.getKeys();
				String key = null;
				while (keys.hasMoreElements()) {
					key = keys.nextElement();
					combined.put(key, bundle.getString(key));
				}
			}
		}
	}

}
