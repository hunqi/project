package com.raysun.spring.config;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.TimeUnit;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.ConfigurationBuilderEvent;
import org.apache.commons.configuration2.builder.ReloadingFileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.reloading.PeriodicReloadingTrigger;
import org.apache.commons.configuration2.spring.ConfigurationPropertiesFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReloadablePropertiesConfiguration {

    private static final String PROPERTIES_FILE_EXTENSION = ".properties";

    private ReloadingFileBasedConfigurationBuilder<PropertiesConfiguration>[] builders;

    @Autowired
    private String testConfigPath;

    /*
     * Inject org.apache.commons.configuration2.spring.
     * ConfigurationPropertiesFactoryBean into Spring PropertiesFactoryBean
     */
    @Bean("commonsPropertiesFactoryBean")
    public ConfigurationPropertiesFactoryBean getConfigurationPropertiesFactoryBean() throws ConfigurationException {
        // create
        // org.apache.commons.configuration2.spring.ConfigurationPropertiesFactoryBean
        ConfigurationPropertiesFactoryBean commonsFactoryBean = new ConfigurationPropertiesFactoryBean();
        
        // set org.apache.commons.configuration2.PropertiesConfiguration
        commonsFactoryBean.setConfigurations(getReloadableConfiguration());
        
        // add event listener for property file change
        for (ReloadingFileBasedConfigurationBuilder<PropertiesConfiguration> builder : builders) {
            builder.addEventListener(ConfigurationBuilderEvent.RESET,
                    new ReloadablePropertiesEventListener(builders, commonsFactoryBean));
        }
        return commonsFactoryBean;
    }

    private PropertiesConfiguration[] getReloadableConfiguration() throws ConfigurationException {
        // get properties files
        File path = new File(testConfigPath);

        FileFilter fileFilter = new FileFilter() {
//            @Override
            public boolean accept(File file) {
                if (file.getName().endsWith(PROPERTIES_FILE_EXTENSION)) {
                    return true;
                }
                return false;
            }
        };
        File[] propertiesFiles = path.listFiles(fileFilter);

        PropertiesConfiguration[] configs = new PropertiesConfiguration[propertiesFiles.length];
        builders = new ReloadingFileBasedConfigurationBuilder[propertiesFiles.length];
        for (int i = 0; i < propertiesFiles.length; i++) {
            Parameters params = new Parameters();

            builders[i] = new ReloadingFileBasedConfigurationBuilder<PropertiesConfiguration>(
                    PropertiesConfiguration.class).configure(params.fileBased().setFile(propertiesFiles[i]));
            PeriodicReloadingTrigger trigger = new PeriodicReloadingTrigger(builders[i].getReloadingController(), null,
                    30, TimeUnit.SECONDS);
            trigger.start();

            configs[i] = builders[i].getConfiguration();
        }
        return configs;
    }

}