package com.optimagrowth.organization.service;

import java.util.Optional;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optimagrowth.organization.events.source.SimpleSourceBean;
import com.optimagrowth.organization.model.Organization;
import com.optimagrowth.organization.repository.OrganizationRepository;

import brave.ScopedSpan;
import brave.Tracer;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrganizationService {

    private final OrganizationRepository repository;
    private final SimpleSourceBean simpleSourceBean;
	private final Tracer tracer;

    public Organization findById(String organizationId) {
    	Optional<Organization> opt = null;
    	ScopedSpan newSpan = tracer.startScopedSpan("getOrgDBCall");
    	try {
    	opt = repository.findById(organizationId);
    	simpleSourceBean.publishOrganizationChange("GET", organizationId);
    	if (!opt.isPresent()) {
    		String message = String.format("Unable to find an organization with the Organization id %s", organizationId);
			log.error(message);
			throw new IllegalArgumentException(message);	
		}
    	log.debug("Retrieving Organization Info: " + opt.get().toString());
    	}finally {
    		newSpan.tag("peer.service", "postgres");
			newSpan.annotate("Client received");
			newSpan.finish();
    	}
    	return opt.get();
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