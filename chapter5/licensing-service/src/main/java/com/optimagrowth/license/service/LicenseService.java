package com.optimagrowth.license.service;

import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.optimagrowth.license.config.ServiceConfig;
import com.optimagrowth.license.model.License;
import com.optimagrowth.license.repository.LicenseRepository;

@RequiredArgsConstructor
@Service
public class LicenseService {

	private final MessageSource messages;

	private final LicenseRepository licenseRepository;

	private final ServiceConfig config;

	public License getLicense(String licenseId, String organizationId){
		License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
		if (null == license) {
			throw new IllegalArgumentException(String.format(messages.getMessage("license.search.error.message", null, null),licenseId, organizationId));	
		}
		return license.withComment(config.getExampleProperty());
	}

	public License createLicense(License license){
		license.setLicenseId(UUID.randomUUID().toString());
		return licenseRepository.save(license.withComment(config.getExampleProperty()));
	}

	public License updateLicense(License license){
		return licenseRepository.save(license.withComment(config.getExampleProperty()));
	}

	public String deleteLicense(String licenseId){
		licenseRepository.deleteById(licenseId);
		return String.format(messages.getMessage("license.delete.message", null, null),licenseId);
	}
}
