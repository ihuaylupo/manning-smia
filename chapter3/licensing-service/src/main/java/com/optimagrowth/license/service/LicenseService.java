package com.optimagrowth.license.service;

import java.util.Locale;
import java.util.Random;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.optimagrowth.license.model.License;

@RequiredArgsConstructor
@Service
public class LicenseService {
	
	private final MessageSource messages;

	public License getLicense(String licenseId, String organizationId){
		License license = new License();
		license.setId(new Random().nextInt(1000));
		license.setLicenseId(licenseId);
		license.setOrganizationId(organizationId);
		license.setDescription("Software product");
		license.setProductName("Ostock");
		license.setLicenseType("full");

		return license;
	}

	public String createLicense(License license, String organizationId, Locale locale){
		String responseMessage = null;
		if(!StringUtils.isEmpty(license)) {
			license.setOrganizationId(organizationId);
			responseMessage = String.format(messages.getMessage("license.create.message",null,locale), license.toString());
		}

		return responseMessage;
	}

	public String updateLicense(License license, String organizationId){
		String responseMessage = null;
		if(!StringUtils.isEmpty(license)) {
			license.setOrganizationId(organizationId);
			responseMessage = String.format(messages.getMessage("license.update.message", null, null), license.toString());
		}

		return responseMessage;
	}

	public String deleteLicense(String licenseId, String organizationId){
		return String.format(messages.getMessage("license.delete.message", null, null),licenseId, organizationId);
	}
}
