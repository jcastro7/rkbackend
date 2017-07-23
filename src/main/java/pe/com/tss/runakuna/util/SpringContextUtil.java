package pe.com.tss.runakuna.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext ctx;

	@Autowired
	public void setApplicationContext(ApplicationContext appContext) throws BeansException {
		ctx = appContext;

	}

	public static ApplicationContext getApplicationContext() {
		return ctx;
	}

	public static <T> T getBean(String beanName, Class<T> clazz) {
		return ctx.getBean(beanName, clazz);
	}
	
}
