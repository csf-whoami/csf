package com.csf.base.core.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.util.StringUtils;

import com.csf.base.core.ZValue;

public abstract class UnpCollectionUtils {

	public static Object getProperty(Object bean, String name) {
		Object property = null;
		try {
			property = PropertyUtils.getProperty(bean, name);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return property;
	}

	public static void setProperty(Object bean, String name, Object value) {
		try {
			PropertyUtils.setProperty(bean, name, value);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	public static <T> Map<String, T> toMap(List<T> list, String prefix, String key) {
		if( CollectionUtils.isEmpty(list) ){
			return Collections.emptyMap();
		}

		Map<String, T> map = new LinkedHashMap<String, T>();
		for ( T val : list ) {
			String keyVal = prefix + getProperty(val, key);
			map.put(keyVal, val);
		}
		return map;
	}

	public static <T> Map<String, List<T>> convertMap(List<T> list, String prefix, String key) {
		if( CollectionUtils.isEmpty(list) ){
			return Collections.emptyMap();
		}

		Map<String, List<T>> map = new LinkedHashMap<String, List<T>>();
		for ( T t : list ) {
			String keyVal = prefix + getProperty(t, key);
			List<T> value = map.get(keyVal);
			if ( value == null ) {
				value = new ArrayList<T>();
				value.add( t );
				map.put(keyVal, value);
			}
			else {
				value.add( t );
			}
		}
		return map;
	}

	public static <T> Map<String, T> convertOneMap(List<T> list, String prefix, String key) {
		if( CollectionUtils.isEmpty(list) ){
			return null;
		}

		Map<String, T> map = new LinkedHashMap<String, T>();
		for ( T t : list ) {
			String keyVal = prefix + getProperty(t, key);
			map.put(keyVal, t);
		}
		return map;
	}

	public static <T> void setFirstFile(List<T> list, Map<String, List<ZValue>> fileMap, String fieldName, String imgFieldName) {
		if (CollectionUtils.isNotEmpty(list)) {
			if (MapUtils.isNotEmpty(fileMap)) {
				for (T val : list) {
					List<ZValue> files = fileMap.get(getProperty(val, fieldName));
					if (CollectionUtils.isNotEmpty(files)) {
						if (StringUtils.hasText(imgFieldName)) {
							ZValue file = null;
							for (ZValue f : files) {
								if (f.getString("fileFieldNm").equals(imgFieldName)) {
									file = f;
									break;
								}
							}
							setFirstFile(val, file);
						}
						else {
							setFirstFile(val, files.get(0));
						}
					}
				}
			}
		}
	}

	public static <T> void setFirstImgFile(List<T> list, Map<String, List<ZValue>> fileMap, String fieldName, String imgFieldName) {
		if (CollectionUtils.isNotEmpty(list)) {
			if (MapUtils.isNotEmpty(fileMap)) {
				for (T val : list) {
					List<ZValue> files = fileMap.get(getProperty(val, fieldName));
					if (CollectionUtils.isNotEmpty(files)) {
						ZValue file = null;
						if (StringUtils.hasText(imgFieldName)) {
							for (ZValue f : files) {
								if (f.getString("fileFieldNm").equals(imgFieldName)) {
									file = f;
									break;
								}
							}
						}
						else {
							for (ZValue f : files) {
								String extsn = f.getString("streFileNm");
								if (extsn.matches("([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)")) {
									file = f;
									break;
								}
							}
						}
						setFirstFile(val, file);
					}
				}
			}
		}
	}
	
	private static <T> void setFirstFile(T val, ZValue file) {
		if (file == null) {
			return;
		}
		setProperty(val, "fileSn", file.getString("fileSn"));
		setProperty(val, "fileCn", file.getString("fileCn"));
		setProperty(val, "fileStreCours", file.getString("fileStreCours"));
		setProperty(val, "streFileNm", file.getString("streFileNm"));
		setProperty(val, "thumbImgUrl", file.getString("thumbImgUrl"));
		setProperty(val, "imgUrl", file.getString("imgUrl"));
		String extsn = file.getString("fileExtsnNm");
		if ("jpg,jpeg,png.gif,bmp".indexOf(extsn.toLowerCase()) > -1) {
			setProperty(val, "imageAt", "Y");
		}
	}

	public static <T> void setFirstFile(List<T> list, Map<String, List<ZValue>> fileMap, String fieldName) {
		setFirstFile(list, fileMap, fieldName, null);
	}

	public static <T> void setFileToList(List<T> list, Map<String, List<ZValue>> fileMap, String fieldName, String matchingfileFieldNm) {
		if (CollectionUtils.isNotEmpty(list)) {
			if (MapUtils.isNotEmpty(fileMap)) {
				for (T val : list) {

					List<ZValue> files = fileMap.get(getProperty(val, fieldName));
					if (CollectionUtils.isNotEmpty(files)) {
						for(ZValue file : files){
							String fileFieldNm = file.getString("fileFieldNm");
							if( fileFieldNm.equals(matchingfileFieldNm) ){
								setProperty(val, "fileSn", file.getString("fileSn"));
								setProperty(val, "fileCn", file.getString("fileCn"));
								setProperty(val, "fileStreCours", file.getString("fileStreCours"));
								setProperty(val, "streFileNm", file.getString("streFileNm"));
								setProperty(val, "thumbImgUrl", file.getString("thumbImgUrl"));
								break;
							}
						}
					}
				}
			}
		}
	}

	public static String listToDelimToString(Collection<?> coll, String property, String delim) {
		if (CollectionUtils.isEmpty(coll)) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Iterator<?> it = coll.iterator();
		while (it.hasNext()) {
			Object obj = it.next();
			String s = (String)getProperty(obj, property);
			sb.append(s);
			if (it.hasNext()) {
				sb.append(delim);
			}
		}
		return sb.toString();
	}

	@SuppressWarnings("serial")
	public static <K,V> Map<K,V> lruCache(final int maxSize) {
	    return new LinkedHashMap<K,V>(maxSize*4/3, 0.75f, true) {
	        @Override
	        protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
	            return size() > maxSize;
	        }
	    };
	}

	public static void doWithIterateMap(Map<?, ?> map, MapIterateCallback callback) {
		if (MapUtils.isNotEmpty(map)) {
			Iterator<?> it = map.keySet().iterator();
			while (it.hasNext()) {
				Object key = it.next();
				Object value = map.get(key);
				callback.doWith(key, value);
			}
		}
	}

	public static List<String> extractPropertyList(List<ZValue> list, String property) {
		List<String> resultList = new ArrayList<String>();
		if (CollectionUtils.isNotEmpty(list)) {
			for (ZValue val : list) {
				if (StringUtils.hasText(val.getString(property))) {
					resultList.add(val.getString(property));
				}
			}
		}
		return resultList;
	}

	public static interface MapIterateCallback {

		public void doWith(Object key, Object value);
	}
}
