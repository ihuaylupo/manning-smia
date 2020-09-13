package com.optimagrowth.organization.service;

import java.util.Optional;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.optimagrowth.organization.events.source.SimpleSourceBean;
import com.optimagrowth.organization.model.Organization;
import com.optimagrowth.organization.repository.OrganizationRepository;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrganizationService {
	
    private final OrganizationRepository repository;
    private final SimpleSourceBean simpleSourceBean;

    public Organization findById(String organizationId) {
    	Optional<Organization> opt = repository.findById(organizationId);
    	simpleSourceBean.publishOrganizationChange("GET", organizationId);
        return opt.orElse(null);
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
			log.error(e.getMessage());
		}
	}
}