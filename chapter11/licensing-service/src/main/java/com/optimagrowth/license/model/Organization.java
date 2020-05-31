package com.optimagrowth.license.model;

import javax.persistence.Id;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@RedisHash("organization")
public class Organization extends RepresentationModel<Organization> {

	@Id
	String id;
    String name;
    String contactName;
    String contactEmail;
    String contactPhone;
    
}
