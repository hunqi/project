package com.raysun.spring.config;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.stereotype.Service;

@Service
public class ReloadableProperties {

    private static final String[] EMPTY_ARRAY = new String[0];

    private static final Logger logger = LoggerFactory.getLogger(ReloadableProperties.class);

    @Autowired
    public PropertiesFactoryBean propertiesFactoryBean;

    public String getString(String key) {
        return getProperties().getProperty(key);
    }

    public int getInt(String key) {
        return getInt(key, 0);
    }

    public int getInt(String key, int value) {
        String valueStr = getProperties().getProperty(key);
        if (valueStr == null) {
            return value;
        }
        try {
            return Integer.valueOf(valueStr);
        } catch (Exception e) {
            return value;
        }
    }

    public String[] getStringArray(String key) {
        String result = getString(key);
        if (result == null) {
            return EMPTY_ARRAY;
        }
        String[] resultArray = result.split(",");
        return (resultArray == null) ? EMPTY_ARRAY : resultArray;
    }

    public boolean getBoolean(String key) {
        String flag = "false";

        try {
            String propflag = getString(key);

            if (StringUtils.isNotEmpty(propflag) && Boolean.TRUE.toString().equalsIgnoreCase(propflag)) {
                flag = propflag;
            }
        } catch (IllegalStateException e) {
            logger.error("unable to get property {} from configuration. ", key);
        }

        return Boolean.valueOf(flag);
    }

    private Properties getProperties() {
        Properties prop;
        try {
            prop = propertiesFactoryBean.getObject();
        } catch (IOException e) {
            throw new RuntimeException("Can not get the system propertes.", e);
        }
        return prop;
    }
}