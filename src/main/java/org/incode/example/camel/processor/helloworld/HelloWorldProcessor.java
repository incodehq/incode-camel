package org.incode.example.camel.processor.helloworld;

import org.apache.camel.Exchange;

import org.apache.isis.schema.ixn.v1.InteractionDto;

import org.incode.example.camel.processor.util.MessageUtil;
import org.incode.example.camel.processor.ProcessorAbstract;

public class HelloWorldProcessor extends ProcessorAbstract {

    @Override
    public void process(final Exchange exchange) throws Exception {
        final InteractionDto interactionDto = MessageUtil.interactionFrom(exchange.getIn());

        System.out.println(toXml(interactionDto));
    }
}
