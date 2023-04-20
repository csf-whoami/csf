package com.csf.base.core;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZValue extends ListOrderedMap implements Serializable {

	private static final long serialVersionUID = 2206001979803073944L;

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	protected String name = null;

	public ZValue() {
	}

	public ZValue(String name) {
		this.name = name;
	}

	public Object getId(){
		return getLong("id");
	}

	public boolean getBoolean(String s) {
		String s1 = getString(s);
		boolean flag = false;
		try {
			flag = Boolean.parseBoolean (s1);
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}

		return flag;
	}

	public double getDouble(String s) {
		String s1 = removeComma(getString(s));
		if (s1.equals(""))
			return 0.0D;
		double d = 0.0D;
		try {
			d = Double.valueOf(s1).doubleValue();
		} catch (Exception exception) {
			d = 0.0D;
		}

		return d;
	}

	public float getFloat(String s) {
		return (float) getDouble(s);
	}

	public int getInt(String s) {
		double d = getDouble(s);
		return (int) d;
	}

	public int getInt(String s, int initVal) {
		int ret = getInt(s);
		if (ret == 0)
			ret = initVal;
		return ret;
	}

	public long getLong(String s) {
		String s1 = removeComma(getString(s));
		if (s1.equals(""))
			return 0L;
		long l = 0L;
		try {
			l = Long.valueOf(s1).longValue();
		} catch (Exception exception) {
			l = 0L;
		}

		return l;
	}

	public String getString(String s) {
		String s1 = null;
		try {
			Object obj = super.get(s);
			if (obj == null) {
				return "";
			}

			Class<?> class1 = obj.getClass();
			if (class1.isArray()) {
				int i = Array.getLength(obj);
				if (i == 0) {
					s1 = "";
				} else {
					Object obj1 = Array.get(obj, 0);
					if (obj1 == null)
						s1 = "";
					else
						s1 = obj1.toString();
				}
			} else {
				s1 = obj.toString();
			}
		} catch (Exception exception) {
			s1 = "";
		}

		return s1.trim();
	}

	public String getString(String s, String initVal) {
		String result = getString(s);
		if ("".equals(result))
			result = initVal;
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<String> getList(String s) {
		Object obj = super.get(s);
		if(obj == null) {
			ArrayList<String> al = new ArrayList<>();
			putObject(s, al);
			return al;
		}
		if(obj instanceof List){
			return (List<String>)obj;
		}
		List<String> al = new ArrayList<>();
		try {
			Class<?> class1 = obj.getClass();
			if (obj != null) {
				if (class1.isArray()) {
					int i = Array.getLength(obj);
					if (i != 0) {
						for (int j = 0; j < i; j++) {
							Object obj1 = Array.get(obj, j);
							if (obj1 != null){
								al.add(obj1.toString());
							}
						}
					}
				}
				else {
					al.add(obj.toString());
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return al;
	}

	@SuppressWarnings("unchecked")
	public List<String> getStartWithList(String s){
		List<String> al = new ArrayList<>();
		Iterator<String> it = keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			if( key.startsWith(s) ){
				al.add(getString(key));
			}
		}
		return al;
	}

	@Override
	public Object put(Object key, Object value) {
		return super.put(convert2CamelCase((String) key), value);
	}

	public void putObject(Object key, Object value) {
		super.put(key, value);
	}

	public void put(String s, List<?> al) {
		String as[] = new String[al.size()];
		for (int i = 0; i < al.size(); i++) {
			if (al.get(i) != null)
				as[i] = (String) al.get(i);
			else
				as[i] = "";
		}
		super.put(s, as);
	}

	@SuppressWarnings("rawtypes")
	public void putAllObject(Map map) {
		for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			putObject(entry.getKey(), entry.getValue());
		}
	}

	protected static String removeComma(String s) {
		if (s == null)
			return null;
		if (s.indexOf(",") != -1) {
			StringBuffer stringbuffer = new StringBuffer();
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (c != ',')
					stringbuffer.append(c);
			}

			return stringbuffer.toString();
		} else {
			return s;
		}
	}

	public void setValue(String s, String s1) {
		super.put(s, s1);
	}

	public void set(String s, String s1) {
		super.put(s, s1);
	}

	public void set(String s, List<?> al) {
		String as[] = new String[al.size()];
		for (int i = 0; i < al.size(); i++) {
			if (al.get(i) != null)
				as[i] = (String) al.get(i);
			else
				as[i] = "";
		}
		put(s, as);
	}

	public String convert2CamelCase(String underScore) {
		if (underScore == null)
			return "";
		if (underScore.indexOf('_') < 0 && Character.isLowerCase(underScore.charAt(0)))
			return underScore;
		StringBuilder result = new StringBuilder();
		boolean nextUpper = false;
		int len = underScore.length();
		for (int i = 0; i < len; i++) {
			char currentChar = underScore.charAt(i);
			if (currentChar == '_') {
				nextUpper = true;
				continue;
			}
			if (nextUpper) {
				result.append(Character.toUpperCase(currentChar));
				nextUpper = false;
			} else {
				result.append(Character.toLowerCase(currentChar));
			}
		}

		return result.toString();
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> getStartWithParam(String s){
		Map<String, String> result = new HashMap<String, String>();
		Iterator<String> it = this.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			if( key.startsWith(s) ){
				result.put(key, this.getString(key));
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("{");
		Iterator<Object> it = keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			String value = getString(key);
			result.append("(" + key + "," + value + ")");
		}
		result.append("}");
		return result.toString();
	}

	/**
	 * 배열로 넘어온 파라미터는 "Arr" 를 붙혀 관리하고 해당 값을 리턴한다.
	 * @param s
	 * @return
	 */
	public String[] getStringArr(String s){
		try {
			if (this.get(s+"Arr")==null){
				return new String[] {this.getString(s+"Arr")};
			} else {
				return (String[])this.get(s+"Arr");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getStringArr(String s, int indexNo){
		try {
			String[] tmp = getStringArr(s);
			if (tmp.length>indexNo){
				return tmp[indexNo];
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}