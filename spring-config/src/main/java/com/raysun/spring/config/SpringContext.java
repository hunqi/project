package com.raysun.spring.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/*
 * We can get Spring Application Context from this class
 * 
 * */
@Service
@Lazy(false)
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

//    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContext.applicationContext = applicationContext;
    }

    public static ApplicationContext getContext() {
        return applicationContext;
    }

}
