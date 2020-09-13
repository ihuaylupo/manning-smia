package com.optimagrowth.organization.service;

import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.optimagrowth.organization.model.Organization;
import com.optimagrowth.organization.repository.OrganizationRepository;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrganizationService {
	
    private final OrganizationRepository repository;

    public Organization findById(String organizationId) {
        return repository.findById(organizationId).orElse(null);
    }

    public Organization create(Organization organization){
    	organization.setId( UUID.randomUUID().toString());
        return repository.save(organization);
    }

    public void update(Organization organization){
    	repository.save(organization);
    }

    public void delete(String organizationId){
    	repository.deleteById(organizationId);
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