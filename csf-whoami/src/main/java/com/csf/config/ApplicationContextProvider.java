package com.csf.config;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public final class ApplicationContextProvider implements ApplicationContextAware {

	private static final ApplicationContextProvider INSTANCE = new ApplicationContextProvider();
	private static ApplicationContext applicationContext;

	private ApplicationContextProvider() {
	}

	public static void autowire(Object classToAutowire) {
		ApplicationContextProvider.applicationContext.getAutowireCapableBeanFactory().autowireBean(classToAutowire);
	}

	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) {
		ApplicationContextProvider.applicationContext = applicationContext;
	}

	public static ApplicationContextProvider getInstance() {
		return INSTANCE;
	}

	public ApplicationContext getApplicationContext() {
		return ApplicationContextProvider.applicationContext;
	}

	public static <T> T getBean(Class<T> beanClass) {
		return applicationContext.getBean(beanClass);
	}

	public static <T> T getBean(String beanName, Class<T> beanClass) {
		return applicationContext.getBean(beanName, beanClass);
	}

	/**
	 * It's same inject bean with @Autowired(required = false)
	 * @param <T>
	 * @param beanClass
	 * @return
	 */
	public static <T> T getBeanNotRequired(Class<T> beanClass) {
		try {
			return applicationContext.getBean(beanClass);
		} catch (NoSuchBeanDefinitionException e) { 
			return null;
		}
	}

	/**
	 * It's same inject bean with @Autowired(required = false)
	 * @param <T>
	 * @param beanClass
	 * @return
	 */
	public static <T> T getBeanNotRequired(String beanName, Class<T> beanClass) {
		try {
			return applicationContext.getBean(beanName, beanClass);
		} catch (NoSuchBeanDefinitionException e) { 
			return null;
		}
	}
}
