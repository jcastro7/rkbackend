package pe.com.tss.runakuna.util;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

public class ReadDbPropertiesPostProcessor extends PropertySourcesPlaceholderConfigurer{
	
	/**
     * Name of the custom property source added by this post processor class
     */
    private static final String PROPERTY_SOURCE_NAME = "databaseProperties";

    /**
     * Adds Spring Environment custom logic. This custom logic fetch properties from database and setting highest precedence
     */
    
    @Autowired
    DbPlaceholderRepository dbPlaceholderRepository;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    	DataSource dataSource = beanFactory.getBean(DataSource.class);
    	CustomProperties dbProps = new CustomProperties(dataSource);
    	setProperties(dbProps);
    	super.postProcessBeanFactory(beanFactory);    	
    }	

}
