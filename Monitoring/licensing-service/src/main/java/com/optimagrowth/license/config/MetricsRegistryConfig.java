package com.optimagrowth.license.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.prometheus.PrometheusMeterRegistry;

@Configuration
public class MetricsRegistryConfig {

	@Value("${spring.application.name}")
	String appName;

	@Bean
	MeterRegistryCustomizer<PrometheusMeterRegistry> configureMetricsRegistry(){
		return registry -> registry.config().commonTags("appName", appName);
	}

}