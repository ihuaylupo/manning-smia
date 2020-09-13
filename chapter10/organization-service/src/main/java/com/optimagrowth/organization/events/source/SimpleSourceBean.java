package com.optimagrowth.organization.events.source;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.optimagrowth.organization.events.model.OrganizationChangeModel;
import com.optimagrowth.organization.utils.UserContext;

@Slf4j
@RequiredArgsConstructor
@Component
public class SimpleSourceBean {

    private final Source source;

    public void publishOrganizationChange(String action, String organizationId){
        log.debug("Sending Kafka message {} for Organization Id: {}", action, organizationId);
        OrganizationChangeModel change =  new OrganizationChangeModel(
                OrganizationChangeModel.class.getTypeName(),
                action,
                organizationId,
                UserContext.getCorrelationId());

        source.output().send(MessageBuilder.withPayload(change).build());
    }
}
