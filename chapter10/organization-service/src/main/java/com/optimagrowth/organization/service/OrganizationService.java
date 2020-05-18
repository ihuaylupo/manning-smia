package com.optimagrowth.organization.service;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optimagrowth.organization.events.source.SimpleSourceBean;
import com.optimagrowth.organization.model.Organization;
import com.optimagrowth.organization.repository.OrganizationRepository;

@Service
public class OrganizationService {
	
	private static final Logger logger = LoggerFactory.getLogger(OrganizationService.class);
	
    @Autowired
    private OrganizationRepository repository;
    
    @Autowired
    SimpleSourceBean simpleSourceBean;

    public Organization findById(String organizationId) {
    	Optional<Organization> opt = repository.findById(organizationId);
    	simpleSourceBean.publishOrganizationChange("GET", organizationId);
        return (opt.isPresent()) ? opt.get() : null;
    }	

    public Organization create(Organization organization){
    	organization.setId( UUID.randomUUID().toString());
        organization = repository.save(organization);
        simpleSourceBean.publishOrganizationChange("SAVE", organization.getId());
        return organization;

    }

    public void update(Organization organization){
    	repository.save(organization);
        simpleSourceBean.publishOrganizationChange("UPDATE", organization.getId());
    }

    public void delete(String organizationId){
    	repository.deleteById(organizationId);
    	simpleSourceBean.publishOrganizationChange("DELETE", organizationId);
    }
    
    @SuppressWarnings("unused")
	private void sleep(){
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
		}
	}
}