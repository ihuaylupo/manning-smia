package com.optimagrowth.license.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.optimagrowth.license.model.License;
import com.optimagrowth.license.service.LicenseService;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="v1/organization/{organizationId}/license")
public class LicenseController {

	private final LicenseService licenseService;

	@GetMapping(value="/{licenseId}")
	public ResponseEntity<License> getLicense( @PathVariable("organizationId") String organizationId,
			@PathVariable("licenseId") String licenseId) {

		License license = licenseService.getLicense(licenseId, organizationId, "");
		license.add( 
				linkTo(methodOn(LicenseController.class).getLicense(organizationId, license.getLicenseId())).withSelfRel(),
				linkTo(methodOn(LicenseController.class).createLicense(license)).withRel("createLicense"),
				linkTo(methodOn(LicenseController.class).updateLicense(license)).withRel("updateLicense"),
				linkTo(methodOn(LicenseController.class).deleteLicense(license.getLicenseId())).withRel("deleteLicense")
				);

		return ResponseEntity.ok(license);
	}

	@GetMapping(value="/{licenseId}/{clientType}")
	public License getLicensesWithClient( @PathVariable("organizationId") String organizationId,
			@PathVariable("licenseId") String licenseId,
			@PathVariable("clientType") String clientType) {

		return licenseService.getLicense(licenseId, organizationId, clientType);
	}

	@PutMapping
	public ResponseEntity<License> updateLicense(@RequestBody License request) {
		return ResponseEntity.ok(licenseService.updateLicense(request));
	}

	@PostMapping
	public ResponseEntity<License> createLicense(@RequestBody License request) {
		return ResponseEntity.ok(licenseService.createLicense(request));
	}

	@DeleteMapping(value="/{licenseId}")
	public ResponseEntity<String> deleteLicense(@PathVariable("licenseId") String licenseId) {
		return ResponseEntity.ok(licenseService.deleteLicense(licenseId));
	}

	@GetMapping(value="/")
	public List<License> getLicenses( @PathVariable("organizationId") String organizationId) {
		return licenseService.getLicensesByOrganization(organizationId);
	}

}
