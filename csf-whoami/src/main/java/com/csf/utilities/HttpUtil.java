package com.csf.utilities;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.util.StringUtils;


public class HttpUtil {

	/**
	 * 창을 메세지 출력후 창 닫음
	 * MethodName : winClose
	 * @return void
	 * @exception
	 * @param response
	 * @param msg
	 * @desc
	 */
	public static void winClose(HttpServletResponse response, String msg){
		try {
			response.reset();
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("Cache-Control","no-cache");

			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta HTTP-EQUIV=\"Content-Type\" CONTENT=\"text/html; charset=UTF-8\">");
			out.println("<script language='JavaScript'>");
			if (msg!=null && !msg.equals("")){
				out.println("alert('" + msg+ "');");
			}
			out.println("self.close();");
			out.println("</script>");
			out.println("</head>");
			out.println("</html>");
			out.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 창을 메세지 출력후 창 닫고 부모창 새로고침
	 * MethodName : winCloseReload
	 * @return void
	 * @exception
	 * @param response
	 * @param msg
	 * @desc
	 */
	public static void winCloseReload(HttpServletResponse response, String msg){
		try {
			response.reset();
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("Cache-Control","no-cache");

			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta HTTP-EQUIV=\"Content-Type\" CONTENT=\"text/html; charset=UTF-8\">");
			out.println("<script language='JavaScript'>");
			out.println("opener.location.reload();");
			if (msg!=null){
			out.println("alert('" + msg+ "');");
			}
			out.println("self.close();");
			out.println("</script>");
			out.println("</head>");
			out.println("</html>");
			out.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 창을 닫은 후 보무창을 goUrl로 보냄
	 * MethodName : winCloseLocation
	 * @return void
	 * @exception
	 * @param response
	 * @param goUrl
	 * @param msg
	 * @desc
	 */
	public static void winCloseLocation(HttpServletResponse response, String goUrl, String msg){
		try {
			response.reset();
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("Cache-Control","no-cache");

			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta HTTP-EQUIV=\"Content-Type\" CONTENT=\"text/html; charset=UTF-8\">");
			out.println("<script language='JavaScript'>");
			if (msg!=null && !msg.equals("")){
			out.println("alert('" + msg+ "');");
			}
			out.println("opener.location.href='" + goUrl + "';");
			out.println("self.close();");
			out.println("</script>");
			out.println("</head>");
			out.println("</html>");
			out.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 해당 페이지(goUrl)로 보냄
	 * MethodName : goUrl
	 * @return void
	 * @exception
	 * @param response
	 * @param goUrl
	 * @param msg
	 * @desc
	 */
	public static void goUrl(HttpServletResponse response, String goUrl, String msg){
		goUrl(response, goUrl, msg, false);
	}

	public static void goUrl(HttpServletResponse response, String goUrl, String msg, boolean nocache){
		try {

			response.reset();
			response.setContentType("text/html;charset=UTF-8");
			if (nocache==true){
				response.setHeader("Cache-Control","no-cache");
			}

			PrintWriter out = response.getWriter();
			out.println("<!doctype html>");
			out.println("<html lang=\"ko\">");
			out.println("<head>");
			out.println("<title>빈페이지</title>");
			out.println("<meta HTTP-EQUIV=\"Content-Type\" CONTENT=\"text/html; charset=UTF-8\">");
			out.println("<script type='text/javascript'>");
			if (msg!=null){
			out.println("alert('" + msg + "');");
			}
			out.println("location.href='" + goUrl + "';");
			out.println("</script>");
			out.println("</head>");
			out.println("<body></body></html>");
			out.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 이전 페이지로 history back
	 * MethodName : back
	 * @return void
	 * @exception
	 * @param response
	 * @param msg
	 * @desc
	 */
	public static void back(HttpServletResponse response, String msg){
		back(response, msg, true);
	}

	public static void back(HttpServletResponse response, String msg, boolean nocache){
		try {
			response.reset();
			response.setContentType("text/html;charset=UTF-8");
			if (nocache==true){
				response.setHeader("Cache-Control","no-cache");
			}

			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta HTTP-EQUIV=\"Content-Type\" CONTENT=\"text/html; charset=UTF-8\">");
			out.println("<script language='JavaScript'>");
			out.println("alert('" + msg + "');");
			out.println("history.back();");
			out.println("</script>");
			out.println("</head>");
			out.println("</html>");
            out.flush();
			out.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 창을 닫음
	 * MethodName : realName_winClose
	 * @return void
	 * @exception
	 * @param response
	 * @param action
	 * @param msg
	 * @desc
	 */
	public static void winCloseRealNameLocation(HttpServletResponse response, String goUrl, String msg){
		try {
			response.reset();
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("Cache-Control","no-cache");

			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta HTTP-EQUIV=\"Content-Type\" CONTENT=\"text/html; charset=UTF-8\">");
			out.println("<script language='JavaScript'>");
			if (msg!=null && !msg.equals("")){
			out.println("alert('" + msg+ "');");
			}
			out.println("opener.location.href='" + goUrl + "';");
			out.println("window.close();");
			out.println("</script>");
			out.println("</head>");
			out.println("</html>");
			out.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 해당 URL로 POST로 보냄
	 * MethodName : goUrl
	 * @return void
	 * @exception
	 * @param response
	 * @param url
	 * @param map
	 * @param msg
	 * @desc
	 */
	public static void goUrl(HttpServletResponse response, String url, Map<String, String> map, String msg) {
		try {

			response.reset();
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("Cache-Control","no-cache");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta HTTP-EQUIV=\"Content-Type\" CONTENT=\"text/html; charset=UTF-8\">");
			out.println("</head>");

			out.println("<form name=\"gofrm\" action='" + url + "' method='post'>");
			if( MapUtils.isNotEmpty(map) ){
				Iterator<String> it = map.keySet().iterator();
				while(it.hasNext()){
					String key = it.next();
					String val = map.get(key);
					if (StringUtils.hasText(val)){
						out.println("<input type='hidden' name='" + key.toLowerCase() + "' value='" + val + "' />");
					}
				}
			}
			out.println("<script language='JavaScript'>");
			out.println("<!--");
			if (msg!=null && msg.length()>0){
			out.println("alert('" + msg + "');");
			}
			out.println("document.gofrm.submit();");
			out.println("-->");
			out.println("</script>");
			out.println("</form>");


			out.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param resultMsg : 메인 메제지 alert창에 뛰우는 메세지
	 * @param infoMsg : noscript에 대한 설명 메세지
	 * @param rurl : 바로이동하기 위한 URL
	 * @param target : -1: 창닫기-닫고 사용을 권장, 0=링크, 1:새창, 2 : submit
	 * @param paramMap : Post로 넘길 파라미터가 있을 경우
	 * @return
	 */
	public static String getNoScriptHtml(String resultMsg, String infoMsg, String rurl, int target){
		StringBuffer sb = new StringBuffer();
		sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"/korea/css/common.css\" />");
		sb.append("<div id=\"no_script\">");
		sb.append("<ul class=\"bg\">");
		if (resultMsg==null || resultMsg.equals("")){
			sb.append("<li class=\"tt\"></li>");
		} else {
			sb.append("<li class=\"tt\">"+resultMsg+"</li>");
		}
		sb.append("<li class=\"txt\">"+infoMsg+"</li>");

		rurl = (rurl==null || rurl.equals("")|| rurl.equals("/")) ? "/" : rurl;
		if (target==-1){ //창닫기-닫고 사용을 권장
			sb.append("<li class=\"btn\"></li>");
		} else if (target==0){ //링크
			sb.append("<li class=\"btn\"><a href=\""+rurl+"\" title=\"페이지 이동하기\"><img src=\"/korea/images/etc/btn_move.gif\" alt=\"바로 이동하기 버튼\" /></a></li>");
		} else if (target==1){ //새창
			sb.append("<li class=\"btn\"><a href=\""+rurl+"\" title=\"페이지 이동하기\" target+\"_blank\"><img src=\"/korea/images/etc/btn_move.gif\" alt=\"바로 이동하기 버튼\" /></a></li>");
		} else if (target==2){ //submit
			sb.append("<li class=\"btn\"><input type=\"image\" src=src=\"/korea/images/etc/btn_move.gif\" alt=\"바로 이동하기 버튼\"/></li>");
		}
		sb.append("</ul>");
		sb.append("</div>");
		return sb.toString();
	}

	public static void goConfirmUrl(HttpServletResponse response, String goUrl, String msg) throws Exception {
		response.reset();
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control","no-cache");
		PrintWriter out = response.getWriter();

		out.print("<script>\n");
		out.print("if(confirm('"+msg+"'))\n");
		out.print("location.href='"+goUrl+"'\n");
		out.print("else history.back();\n");
		out.print("</script>");
		out.flush();
		out.close();
		return;
	}

	public static String getClientOS(HttpServletRequest request) {

		String os = "";
		String userAgent = request.getHeader("User-Agent");

			userAgent = userAgent.toLowerCase();

			if (userAgent.indexOf("windows nt 6.1") > -1) {
				os = "Windows7";
			}
			else if (userAgent.indexOf("windows nt 10.") > -1) {
				os = "Windows10";
			}
			else if (userAgent.indexOf("windows nt 9.") > -1) {
				os = "Windows9";
			}
			else if (userAgent.indexOf("windows nt 6.2") > -1 || userAgent.indexOf("windows nt 6.3") > -1 ) {
				os = "Windows8";
			}
			else if (userAgent.indexOf("windows nt 6.0") > -1) {
				os = "WindowsVista";
			}
			else if (userAgent.indexOf("windows nt 5.1") > -1) {
				os = "WindowsXP";
			}
			else if (userAgent.indexOf("windows nt 5.0") > -1) {
				os = "Windows2000";
			}
			else if (userAgent.indexOf("windows nt 4.0") > -1) {
				os = "WindowsNT";
			}
			else if (userAgent.indexOf("windows 98") > -1) {
				os = "Windows98";
			}
			else if (userAgent.indexOf("windows 95") > -1) {
				os = "Windows95";
			}
			//window 외
			else if (userAgent.indexOf("iphone") > -1) {
				os = "iPhone";
			}
			else if (userAgent.indexOf("ipad") > -1) {
				os = "iPad";
			}
			else if (userAgent.indexOf("android") > -1) {
				os = "android";
			}
	                   else if (userAgent.indexOf("mac") > -1) {
				os = "mac";
			}
			else if (userAgent.indexOf("linux") > -1) {
				os = "Linux";
			}
			else {
				os = "Etc";
			}

		return os;
	}

	public static String getClientBrowser(HttpServletRequest request) {
		String browser = "";
		String userAgent = request.getHeader("User-Agent");

		if (userAgent.indexOf("Trident/7.0") > -1) {
				browser = "ie11";
			}
		else if (userAgent.indexOf("MSIE 10") > -1) {
				browser = "ie10";
			}
			else if (userAgent.indexOf("MSIE 9") > -1) {
				browser = "ie9";
			}
			else if (userAgent.indexOf("MSIE 8") > -1) {
				browser = "ie8";
			}
			else if (userAgent.indexOf("Chrome/") > -1) {
				browser = "Chrome";
			}
			else if (userAgent.indexOf("Safari/") > -1) {
				browser = "Safari";
			}

			/*else if (userAgent.indexOf("Chrome/") == -1 && userAgent.indexOf("Safari/") >= -1) {
				browser = "Safari";
			}*/
			else if (userAgent.indexOf("Firefox/") > -1) {
				browser = "Firefox";
			}
			else {
				browser ="Etc";
			}
		return browser;
	}

	public static String getPcMobileSe(HttpServletRequest request) {
		String userAgent = request.getHeader("user-agent");
		boolean mobile1 = userAgent.matches(".*(iPhone|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mini|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson).*");
		boolean mobile2 = userAgent.matches(".*(LG|SAMSUNG|Samsung).*");
		if(mobile1 || mobile2) {
			return "M";
		}
		return "P";
	}

	public static String getUserIpBrowserCheck(HttpServletRequest request) {
		String ipaddr = request.getRemoteAddr()==null ? "" : request.getRemoteAddr();
		return ipaddr +"|"+ getClientBrowser(request);
	}

	public static String extractFullFilenameFromUrlPath(String urlPath) {
		int end = urlPath.indexOf(';');
		if (end == -1) {
			end = urlPath.indexOf('?');
			if (end == -1) {
				end = urlPath.length();
			}
		}
		int begin = urlPath.lastIndexOf('/', end) + 1;
		return urlPath.substring(begin, end);
	}
}
