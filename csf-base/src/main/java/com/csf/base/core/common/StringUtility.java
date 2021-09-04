package com.csf.base.core.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 *
 * @(#) StringUtility.java
 *
 * NOTICE !      You can copy or redistribute this code freely,
 * but you should not remove the information about the copyright notice
 * and the author.
 *
 * @author  WonYoung Lee, javaservice@hanmail.net
 * @author  SooKyung Lim, carpe@channeli.net
 */
public final class StringUtility {

	/**
	 * do not instanciate
	 */
	private StringUtility() {}

	/**
	 *
	 * @return java.lang.String
	 * @param s java.lang.String
	 */
	public static String head(String s, int size) {
		if ( s == null ) return "";
		String value = null;
		if ( s.length() > size ) value = s.substring(0, size) + "...";
		else value = s;
		return value;
	}

	/**
	 *
	 * @return java.lang.String
	 * @param s java.lang.String
	 */
	public static String tail(String s, int size) {
		if ( s == null ) return "";
		String value = null;
		if ( s.length() > size ) value =  "..." + s.substring(s.length()-size);
		else value = s;
		return value;
	}
    /**
     * 문자열을 15자 만큼만 보여주고
     * 그 길이에 초과되는 문자열일 경우 "..."를 덧붙여 보여준다.
     * @return the translated string.
     * @param s String 변환할 문자열
     */
	public static String fixLength(String input) {
		return fixLength(input, 15, "...");
	}

    /**
     * 문자열을 일정길이 만큼만 보여주고
     * 그 길이에 초과되는 문자열일 경우 "..."를 덧붙여 보여준다.
     * @return the translated string.
     * @param s String 변환할 문자열
     * @param limitLength int 문자열의 제한 길이
     */
	public static String fixLength(String input, int limit) {
		return fixLength(input, limit, "...");
	}

    /**
     * 문자열을 일정길이 만큼만 보여주고
     * 그 길이에 초과되는 문자열일 경우 특정문자를 덧붙여 보여준다.
     * @return the translated string.
     * @param s String 변환할 문자열
     * @param limitLength int 문자열의 제한 길이
     * @param postfix String 덧붙일 문자열
     */
	public static String fixLength(String input, int limit, String postfix) {

		String buffer = "";
		char[] charArray = input.toCharArray();
		if (limit >= charArray.length)
			return input;

		for (int j=0; j<limit; j++)
			buffer += charArray[j];

		buffer += postfix;
		return buffer;
	}

    /**
     * 문자열에서 특정 문자열을 치환한다.
     * @return the translated string.
     * @param source String 변환할 문자열
     * @param keyStr String 치환 대상 문자열
     * @param toStr String 치환될 문자열
     * 예를 들어, 123456-7890123라는 문자열 str을 1234567890123 형식으로 바꾸고 싶다면,
     * replaceStr( str, "-", "") 로 호출한다.
     */
    public static String replaceStr(String source, String keyStr, String toStr) {
        if (source == null) return null;
    	int startIndex = 0;
	    int curIndex = 0;
	    StringBuffer result = new StringBuffer();

	    while ( ( curIndex = source.indexOf(keyStr, startIndex) ) >= 0) {
    		result.append(source.substring(startIndex, curIndex))
	    	      .append(toStr);
    		startIndex = curIndex + keyStr.length();
	    }

    	if (startIndex <= source.length() )
	    	result.append(source.substring(startIndex, source.length()));

	    return result.toString();

    }

    /**
     * 문자열에서 특정 문자열을 치환한다.
     * 문자열 배열의 차례대로 치환하되
     * 더 이상 배열 값이 없으면 space 1칸으로 치환한다.
     * @return the translated string.
     * @param source String 변환할 문자열
     * @param keyStr String 치환 대상 문자열
     * @param toStr String[] 치환될 문자열 배열
     */
    public static String replaceStr(String source, String keyStr, String[] toStr) {
        if (source == null) return null;
    	int startIndex = 0;
	    int curIndex = 0;
	    int i = 0;
	    StringBuffer result = new StringBuffer();
		String specialString = null;

	    while ( ( curIndex = source.indexOf(keyStr, startIndex) ) >= 0) {
		    if (i < toStr.length )
		        specialString = toStr[i++];
		    else
		        specialString = " ";
    		result.append(source.substring(startIndex, curIndex))
	    	      .append(specialString);
    		startIndex = curIndex + keyStr.length();
	    }

    	if (startIndex <= source.length() )
	    	result.append(source.substring(startIndex, source.length()));

	    return result.toString();
    }

    /**
     * 문자열을 특정 형식으로 출력한다.
     * @return the translated string.
     * @param source String 변환할 문자열
     * @param format String 형식
     * 단, source가 null이라면 빈 문자열(empty string)을 리턴한다.
     * 형식: #은 문자열을 나타낸다. # 외의 문자는 그대로 출력된다.
     * 예를 들어, 1234567890123라는 문자열 str을 123456-7890123 형식으로 바꾸고 싶다면,
     * printStr( str, "######-#######") 로 호출한다.
     */
    public static String printStr(String source, String format) {
        if (source == null) return "";

		StringBuffer buf = new StringBuffer();
		char[] f = format.toCharArray();
		char[] s = source.toCharArray();

		int len = f.length;
        int h = 0;
		for ( int i=0; i < len; i++) {
            if ( h >= s.length ) break;
			if ( f[i] == '#' ) buf.append(s[h++]);
			else buf.append(f[i]);
		}
		return buf.toString();
    }

    /**
     * 문자열에 0을 추가해서 리턴한다.
     * @return the translated string.
     * @param source String 변환할 문자열
     * @param resultLen 결과 길이
     * @param isFront 0이 앞에 붙는지 뒤에 붙는지
     */
    public static String fillZero(String source, int resultLen, boolean isFront) {
		if (source == null) return "";
		source = source.trim();

		char[] s = source.toCharArray();
		if(resultLen <= s.length) return source;

		char[] result = new char[resultLen-s.length];
		for (int i=0; i < (resultLen - s.length); i++) {
			result[i] = '0';
		}

		if(isFront) return (new String(result) + source);
		else return source + (new String(result));
	}

    /**
     * 문자열에 포함된 특정한 형태의 String을 다른 String으로 변환한다.
     * @return the translated string.
     * @param source String 변환할 문자열
     * @param format String 형식
     * 단, source가 null이라면 빈 문자열(empty string)을 리턴한다.
     * 예를 들어, abc======라는 문자열에서 ===부분을 =로 바꾸고 싶다면
     * printStr("abc======", "===", "=")와 같은 형식으로 사용한다.
     */
	public static String replaceString(String source, String from, String to) {
	        if (source == null || to == null || from == null) return "";

	        int index;
	        if(to.indexOf(from) == -1)
	        {
	        	String result;
		        while((index = source.indexOf(from)) != -1)
		        {
		        	source = source.substring(0, index) + to + source.substring(index + from.length(), source.length());
		        }

		        return source;
		}else {
			StringBuffer sb = new StringBuffer();
			int beforeIndex = 0;
		        while((index = source.indexOf(from, beforeIndex)) != -1)
		        {
		        	sb.append(source.substring(beforeIndex, index) + to);
		        	beforeIndex = index + from.length();
		        }
		        return sb.toString();
		}
	}

	public static String getSortString(String str, int length)
	{
		byte[] byteString = str.getBytes();

	    if (byteString.length <= length)
	    {
	      return str;
	    }
	    else
	    {
	      int minusByteCount = 0;
	      for (int i = 0; i < length; i++)
	      {
	        minusByteCount += (byteString[i] < 0) ? 1 : 0;
	      }

	      if (minusByteCount % 2 != 0)
	      {
	        length--;
	      }

	      return new String(byteString, 0, length) + "..";
	    }

	}

	public static void main(String[] args)throws Exception{
		System.out.println(StringUtility.printStr("2304893024809328403284093289", "###,###"));
	}

	public static String encodeURIComponent(String s){
		String result = null;

		try{
			result = URLEncoder.encode(s, "UTF-8")
			.replaceAll("\\+", "%20")
			.replaceAll("\\%21", "!")
			.replaceAll("\\%27", "'")
			.replaceAll("\\%28", "(")
			.replaceAll("\\%29", ")")
			.replaceAll("\\%7E", "~");
		}catch (UnsupportedEncodingException e){
			result = s;
		}
		return result;
	}

/*	public static String dirToXml(String xml, File directory, String rootTmp) throws Exception{
	    File[] found = directory.listFiles();
	    String comment = "";
	    InetAddress localIp = InetAddress.getLocalHost();

	    Arrays.sort(found, new Comparator<File>(){
	        public int compare(File f1, File f2)     {
	         return f1.getName().compareTo(f2.getName());
	        }
	    });


	    if( found != null ){
	    	for( File f : found ){

	    		if( f.isDirectory() ) rootTmp = f.getName();
	    		if( f.isDirectory() ){

	    			xml += "<menu title=\""+f.getName()+"\">\n";
	    			xml = dirToXml(xml, f, rootTmp);
	    			xml += "</menu>\n";
	    		}else{
	    			comment = f.getName().replaceAll(".jpg", "");
	    			comment = comment.substring(5);

	    		    String rootFd = encodeURIComponent(rootTmp);
	    		    String getName = encodeURIComponent(f.getName());

	    		    URL sImgRoute = new URL("http://"+localIp.getHostAddress()+"/50anniversary/swf/theme_Gal/thumb/"+rootFd+"/"+getName);
	    		    URL dImgRoute = new URL("http://"+localIp.getHostAddress()+"/50anniversary/swf/theme_Gal/image/"+rootFd+"/"+getName);

	    			xml += "<img sImgRoute=\""+sImgRoute+"\" dImgRoute=\""+dImgRoute+"\"  comment=\""+comment+"\" />\n";
	    		}
	        }
	    }
	    return xml;
	}*/



}