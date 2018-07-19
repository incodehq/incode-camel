package org.incode.example.camel.processor;

import java.util.HashMap;
import java.util.UUID;

import com.google.common.collect.Maps;

import org.junit.Before;
import org.junit.Test;

import org.apache.isis.schema.ixn.v1.InteractionDto;
import org.apache.isis.schema.ixn.v1.MemberExecutionDto;

import org.isisaddons.module.publishmq.dom.servicespi.PublisherServiceUsingActiveMq;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assume.assumeThat;

public class E2e_Test {

    PublisherServiceUsingActiveMq service;

    @Before
    public void setUp() throws Exception {

        assumeThat(System.getProperty("e2e"), is(notNullValue()));

        service = new PublisherServiceUsingActiveMq();
        final HashMap<String, String> props = Maps.newHashMap();

        props.put(PublisherServiceUsingActiveMq.KEY_VM_TRANSPORT_URL, "tcp://localhost:61616");
        service.init(props);
    }

    @Test
    public void publish() throws Exception {

        final InteractionDto x = new InteractionDto();
        x.setTransactionId(UUID.randomUUID().toString());
        x.setExecution(new MemberExecutionDto() {
            @Override
            public int getSequence() {
                return 1;
            }
        });

        service.republish(x);
    }


}