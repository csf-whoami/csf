package com.csf.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import com.csf.base.constant.ConstantsCommon;
import com.csf.base.utilities.StringUtils;

public class UTBrowserCheck {

//	private static Logger log = new Logger(UTBrowserCheck.class);
	public static final String COOKIE_SMARTPHONE_FLAG = "smartphoneFlagCommon";

	public static final String USER_AGENT = "user-agent";

	public static final String MAKER_DOCOMO = "DoCoMo";

	public static final String MAKER_FOMA = "FOMA";

	public static final String MAKER_PC = "PC";

	public static final String MAKER_ETC = "ETC";

	public static final boolean ISMOBILE_TRUE = true;

	public static final boolean ISMOBILE_FALSE = false;

	public static final boolean ISSMARTPHONE_TRUE = true;

	public static final boolean ISSMARTPHONE_FALSE = false;

	public static Hashtable<String, Object> getBrowserData (HttpServletRequest req) {

		String agent = "";
		String maker = "";
		String seri = "";
		boolean ismobile;
		boolean isSmartPhone = ISSMARTPHONE_FALSE;

		Hashtable<String, Object> table = new Hashtable<>();

		agent = req.getHeader("User-Agent");
		if (agent == null) agent = "";

		if (agent.indexOf("Mozilla/") != -1) {
			maker = MAKER_PC;
			ismobile = ISMOBILE_FALSE;
		} else {
			maker = MAKER_ETC;
			ismobile = ISMOBILE_FALSE;
		}

		if (maker.equals(MAKER_DOCOMO)) {
			if (agent.indexOf("ser") != -1) {
				seri = agent.substring(agent.indexOf("ser") + 3, agent.indexOf("ser") + 14);
			}
		} else if (maker.equals(MAKER_FOMA)) {
			if (agent.indexOf("ser") != -1) {
				seri = agent.substring(agent.indexOf("ser") + 3, agent.indexOf("ser") + 18);
			}
		} else if (seri == null) seri = "";

		ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
		String filePath = bundle.getString(BrowserConstant.CONFIG_SPN_NAME_FILE_PATH);
		String encSjis = "UTF-8";
		FileInputStream fisI = null;
		InputStreamReader isrI = null;
		BufferedReader brI = null;
		try {
			if (BrowserConstant.SMARTPHONE_USE_FLG_YES.equals(BrowserConstant.PN_SMARTPHONEUSEFLG)) { //TODO UTPropMngr.getProperty()
//				Cookies cookies = new Cookies(req, null);
//				String smartphoneFlag = cookies.getCookieValue(COOKIE_SMARTPHONE_FLAG);
				String smartphoneFlag = "";
				if (!StringUtils.isNullOrEmpty(smartphoneFlag)) {

					if (ConstantsCommon.FLAG_ON.equals(smartphoneFlag)) {
						agent = req.getHeader(USER_AGENT);;
						maker = "";
						seri = "";
						ismobile = ISMOBILE_FALSE;
						isSmartPhone = ISSMARTPHONE_TRUE;
					}
				} else {
					fisI = new FileInputStream(new File(filePath));
					isrI = new InputStreamReader(fisI, encSjis);
					brI = new BufferedReader(isrI);

					String data = null;
					while ((data = brI.readLine()) != null) {

						if (agent.indexOf(data) != -1) {

							agent = data;
							maker = "";
							seri = "";
							ismobile = ISMOBILE_FALSE;
							isSmartPhone = ISSMARTPHONE_TRUE;
							break;
						}
					}
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			if (fisI != null) {
				try {
					fisI.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (isrI != null) {
				try {
					isrI.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (brI != null) {
				try {
					brI.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		table.put("AGENT", agent);
		table.put("MAKER", maker);
		table.put("SERIAL", seri);
		table.put("ISMOBILE", ismobile);
		table.put("ISSMARTPHONE", isSmartPhone);

//		log.debug("<< UTBrowserCheck.GetBrowserData >>  " +
//						   "AGENT  : " + (String)table.get("AGENT") + "  " +
//						   "MAKER  : " + (String)table.get("MAKER") + "  " +
//						   "SERIAL : " + (String)table.get("SERIAL") + "  " +
//						   "ISMOBILE : " + table.get("ISMOBILE") + "  " +
//						   "ISSMARTPHONE : " + table.get("ISSMARTPHONE"));

		return table;
	}

}
