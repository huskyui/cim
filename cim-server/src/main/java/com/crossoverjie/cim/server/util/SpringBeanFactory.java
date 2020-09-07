package com.crossoverjie.cim.server.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 可以在util里面通过调用getBean来获取bean对象
 */
@Component
public final class SpringBeanFactory implements ApplicationContextAware{
	private static ApplicationContext context;
	
	public static <T> T getBean(Class<T> c){
		return context.getBean(c);
	}


	public static <T> T getBean(String name,Class<T> clazz){
		return context.getBean(name,clazz);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}
	
	
}
