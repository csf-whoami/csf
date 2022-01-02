package com.csf.whoami.service.impl;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.csf.base.domain.response.QuestionTemplate;
import com.csf.base.utilities.StringUtils;
import com.csf.whoami.service.TemplateService;

@Service
public class TemplateServiceImpl implements TemplateService {

	@Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private String port;

    @Value("${spring.mail.username}")
    private String email;

    @Value("${spring.mail.password}")
    private String password;

    private static final String CONTENT_TYPE_TEXT_HTML = "text/html;charset=\"utf-8\"";
    private static final String MAIL_TEMPLATE_BASE_NAME = "mail/MailMessages";
    private static final String MAIL_TEMPLATE_PREFIX = "/templates/html/";
    private static final String MAIL_TEMPLATE_SUFFIX = ".html";
    private static final String UTF_8 = "UTF-8";

    private static TemplateEngine templateEngine;
    static {
        templateEngine = emailTemplateEngine();
    }

	@Override
	public QuestionTemplate tempQuestion(String questionType) {
//		templateService.getContent("sidebar-channels", params)
		// decode.
		String quizType = new String(Base64.getDecoder().decode(questionType));
		if(!StringUtils.isNullOrEmpty(quizType)) {
			Map<String, Object> params = new HashMap<>();
			params.put("quizType", quizType);
			String htmpContent = getContent("quizFinalContent", params);
			QuestionTemplate questionTmp = new QuestionTemplate();
			questionTmp.setTempContent(exportHtmlBody(htmpContent));
			// TODO: Settings
			return questionTmp;
		}
		return null;
	}

	@Override
	public String tempQuestionDetail(Long questionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String tempQuizContent(String questionType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String tempQuizPreview(String questionType, Long questionId) {
		// TODO Auto-generated method stub
		return null;
	}

    private static TemplateEngine emailTemplateEngine() {
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(htmlTemplateResolver());
        templateEngine.setTemplateEngineMessageSource(emailMessageSource());
        return templateEngine;
    }

    private static ResourceBundleMessageSource emailMessageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(MAIL_TEMPLATE_BASE_NAME);
        return messageSource;
    }

    private static ITemplateResolver htmlTemplateResolver() {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix(MAIL_TEMPLATE_PREFIX);
        templateResolver.setSuffix(MAIL_TEMPLATE_SUFFIX);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding(UTF_8);
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    /**
     * Get HTML content from template file.
     * 
     * @param filePath
     * @param params
     * @return
     */
    public String getContent(String templatePath, Map<String, Object> params) {
        final Context context = new Context();
        for (String key : params.keySet()) {
            context.setVariable(key, params.get(key));
        }
        return templateEngine.process(templatePath, context);
    }

    /**
     * Export body content from HTML
     * 
     * @param htmlContent
     * @return
     */
    private String exportHtmlBody(String htmlContent) {
        String startTag = "<body>";
        String endTag = "</body>";
        if (htmlContent != null && htmlContent.length() > 0) {
            int start = htmlContent.indexOf(startTag);
            int last = htmlContent.lastIndexOf(endTag);
            if (start > 0 && last > 0) {
                htmlContent = htmlContent.substring(start + startTag.length(), last);
            }
        }
        return htmlContent;
    }

    /**
     * Send mail to user with title and contents.
     * 
     * @param emailAddress
     * @param tempFile
     * @param title
     * @param params
     * @return
     */
    public boolean sendMail(String emailAddress, String tempFile, String title, Map<String, Object> params) {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props,
            new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(email, password);
                }
            });
        Message message = new MimeMessage(session);
        try {
            message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(emailAddress)});
            message.setFrom(new InternetAddress(email));
            message.setSubject(title);
            String content = getContent(tempFile, params == null ? new HashMap<>() : params);
            message.setContent(content, CONTENT_TYPE_TEXT_HTML);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
