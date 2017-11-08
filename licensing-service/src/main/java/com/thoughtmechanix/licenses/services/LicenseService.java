package com.thoughtmechanix.licenses.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.thoughtmechanix.licenses.clients.OrganizationDiscoveryClient;
import com.thoughtmechanix.licenses.clients.OrganizationFeignClient;
import com.thoughtmechanix.licenses.clients.OrganizationRestTemplateClient;
import com.thoughtmechanix.licenses.config.ServiceConfig;
import com.thoughtmechanix.licenses.model.License;
import com.thoughtmechanix.licenses.model.Organization;
import com.thoughtmechanix.licenses.repository.LicenseRepository;
import com.thoughtmechanix.licenses.utils.UserContextHolder;

@Service
public class LicenseService {
	
	private static final Logger logger = LoggerFactory.getLogger(LicenseService.class);
	
	@Autowired
	private LicenseRepository licenseRepository;

	@Autowired
	ServiceConfig config;
	
	@Autowired
    OrganizationFeignClient organizationFeignClient;

    @Autowired
    OrganizationRestTemplateClient organizationRestClient;
    
    @Autowired
    OrganizationDiscoveryClient organizationDiscoveryClient;
	
    @HystrixCommand
	private Organization retrieveOrgInfo(String organizationId, String clientType){
        Organization organization = null;

        switch (clientType) {
            case "feign":
                System.out.println("I am using the feign client");
                organization = organizationFeignClient.getOrganization(organizationId);
                break;
            case "rest":
                System.out.println("I am using the rest client");
                organization = organizationRestClient.getOrganization(organizationId);
                break;
            case "discovery":
                System.out.println("I am using the discovery client");
                organization = organizationDiscoveryClient.getOrganization(organizationId);
                break;
            default:
                organization = organizationRestClient.getOrganization(organizationId);
        }

        return organization;
    }

	public License getLicense(String organizationId, String licenseId) {
		License license = licenseRepository.findByOrganizationIdAndLicenseId(
				organizationId, licenseId);
		return license.withComment(config.getExampleProperty());
	}
	
	public License getLicense(String organizationId,String licenseId, String clientType) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);

        Organization org = retrieveOrgInfo(organizationId, clientType);

        return license
                .withOrganizationName( org.getName())
                .withContactName( org.getContactName())
                .withContactEmail( org.getContactEmail() )
                .withContactPhone( org.getContactPhone() )
                .withComment(config.getExampleProperty());
    }
	
	private void randomlyRunLong() {
		Random rand = new Random();
		int randomNum = rand.nextInt((3 - 1) + 1) + 1;
		if (randomNum == 3)
			sleep();
	}

	private void sleep() {
		try {
			Thread.sleep(11000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@HystrixCommand(fallbackMethod="buildFallbackLicenseList",
			threadPoolKey="licenseByOrgThreadPool",
			threadPoolProperties={
				@HystrixProperty(name="coreSize", value="30"),
				@HystrixProperty(name="maxQueueSize", value="10")},
			commandProperties={
				@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="10"),
				@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="75"),
				@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="7000"),
				@HystrixProperty(name="metrics.rollingStats.timeInMilliseconds", value="15000"),
				@HystrixProperty(name="metrics.rollingStats.numBuckets", value="5")
	})
	public List<License> getLicensesByOrg(String organizationId) {
		logger.debug("LicenseService Correlation id: {}", UserContextHolder.getContext().getCorrelationId());
		randomlyRunLong();
		return licenseRepository.findByOrganizationId(organizationId);
	}
	
	private List<License> buildFallbackLicenseList(String organizationId){
		List<License> fallbackList = new ArrayList<>();
		License license = new License()
		.withId("0000000-00-00000")
		.withOrganizationId( organizationId )
		.withProductName(
		"Sorry no licensing information currently available");
		fallbackList.add(license);
		return fallbackList;
	}
	
	public void saveLicense(License license) {
		license.withId(UUID.randomUUID().toString());

		licenseRepository.save(license);

	}

	public void updateLicense(License license) {
		licenseRepository.save(license);
	}

	public void deleteLicense(License license) {
		licenseRepository.delete(license.getLicenseId());
	}
}
