package com.csf.base.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Repository implements Serializable {

	private static final long serialVersionUID = 7391588908354557774L;

	private static Repository _inst = null;

//	private static Logger log = new Logger("Repository");

	private Properties configProperties;

	private String unyoDate;

	/**
	 * Constructor for Repository.
	 */
	private Repository() {
		super();
		setConfigProperties(new Properties());
		try {
			createConfig();
		} catch(IOException e) {
//			log.warn(Message.getMessage("error.AE8035"));
		}
	}

	private void createConfig() throws IOException {

		String propertyPath = "/config.properties"; //Default
//		String profileActive = ApplicationProfile.getEnvironment().getActiveProfiles()[0];
//		if (profileActive != null) {
//			propertyPath = "/config_" + profileActive + ".properties";
////			log.info(Message.getMessage("info.AI7001", profileActive));
//		} else {
////			log.info(Message.getMessage("warn.AW8005"));
//		}
		InputStream in = Repository.class.getResourceAsStream(propertyPath);
		if (in == null) {
//			log.warn(Message.getMessage("error.AE8002"));
			return ;
		}
		//Core config properties
		InputStream coreIn = Repository.class.getResourceAsStream("/CoreConfig.properties");
		getConfigProperties().load(coreIn);
		getConfigProperties().load(in);
	}

	public static Repository getInstance() {
		if(_inst == null) {
			_inst = new Repository();
		}
		return _inst;
	}

	public static String getConfig(String key) {
		return Repository.getInstance().getConfigProperties().getProperty(key);
	}

	public static String getUnyoDate(HttpServletRequest request) {
		//HttpSession session = request.getSession();
		//User user = (User) session.getAttribute(Certification.ATTRIBUTE_USER_INFOMATION);
		//if(user != null) {
		//	return user.getUnyoDate();
		//}
//		User user = AuthenticationUtil.getUserInfo(request);
//		return ApplDateFormat.getCurrentTime(user, "yyyy/MM/dd", ApplDateFormat.TZ_OFF, ApplDateFormat.CNV_G);
		return "2021/11/11";
	}
}
