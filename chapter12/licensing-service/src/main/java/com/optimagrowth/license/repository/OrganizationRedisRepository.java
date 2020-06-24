package com.optimagrowth.license.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.optimagrowth.license.model.Organization;

@Repository
public interface OrganizationRedisRepository extends CrudRepository<Organization,String>  {
}
