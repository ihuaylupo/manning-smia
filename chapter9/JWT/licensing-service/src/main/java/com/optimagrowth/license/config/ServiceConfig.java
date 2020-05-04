package com.optimagrowth.license.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceConfig{

  @Value("${example.property}")
  private String exampleProperty;
  
  @Value("${signing.key}")
  private String jwtSigningKey="";
  
  public String getExampleProperty(){
    return exampleProperty;
  }
  
  public String getJwtSigningKey() {
	    return jwtSigningKey;
	  }
  
}