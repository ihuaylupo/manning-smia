package com.optimagrowth.organization.events.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter @Setter @ToString
public class OrganizationChangeModel {

	private String type;
	private String action;
	private String organizationId;
	private String correlationId;

}
