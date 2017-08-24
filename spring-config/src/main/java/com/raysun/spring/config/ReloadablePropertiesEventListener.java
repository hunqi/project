package com.raysun.spring.config;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.ConfigurationBuilderEvent;
import org.apache.commons.configuration2.builder.ReloadingFileBasedConfigurationBuilder;
import org.apache.commons.configuration2.event.EventListener;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.spring.ConfigurationPropertiesFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;

public class ReloadablePropertiesEventListener implements EventListener<ConfigurationBuilderEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ReloadablePropertiesEventListener.class);

    PropertiesFactoryBean propertiesFactoryBean;

    ReloadingFileBasedConfigurationBuilder<PropertiesConfiguration>[] builders;

    ConfigurationPropertiesFactoryBean commonsPropertiesFactoryBean;

    //    @Override
    public void onEvent(ConfigurationBuilderEvent event) {
        logger.info("Start to reload configuration.");
        Configuration[] configurations = new Configuration[builders.length];
        for (int i = 0; i < builders.length; i++) {
            try {
                configurations[i] = builders[i].getConfiguration();
            } catch (ConfigurationException e) {
                logger.error("Get reloadConfiguration failed.", e);
            }
        }
        commonsPropertiesFactoryBean.getConfiguration().clear();
        commonsPropertiesFactoryBean.setConfigurations(configurations);
        try {
            commonsPropertiesFactoryBean.afterPropertiesSet();
        } catch (Exception e1) {
            logger.error("Set Configurations failed.", e1);
        }
        try {
            propertiesFactoryBean = (PropertiesFactoryBean) SpringContext.getContext()
                    .getBean(PropertiesFactoryBean.class);
            propertiesFactoryBean.setProperties(commonsPropertiesFactoryBean.getObject());
            propertiesFactoryBean.afterPropertiesSet();
        } catch (Exception e) {
            logger.error("Set propertiesFactoryBean failed.", e);
        }
        logger.info("Reload configuration successfully.");
    }

    public ReloadablePropertiesEventListener(ReloadingFileBasedConfigurationBuilder<PropertiesConfiguration>[] builders,
                                             ConfigurationPropertiesFactoryBean commonsPropertiesFactoryBean) {
        super();
        this.builders = builders;
        this.commonsPropertiesFactoryBean = commonsPropertiesFactoryBean;
    }

}