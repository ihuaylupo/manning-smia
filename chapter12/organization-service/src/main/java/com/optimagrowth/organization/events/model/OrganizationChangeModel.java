package com.optimagrowth.organization.events.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class OrganizationChangeModel {
	private String type;
	private String action;
	private String organizationId;
	private String correlationId;

	public OrganizationChangeModel(String type, String action, String organizationId, String correlationId) {
		super();
		this.type = type;
		this.action = action;
		this.organizationId = organizationId;
		this.correlationId = correlationId;
	}
}
