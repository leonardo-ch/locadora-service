package com.routes;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelRoutes extends RouteBuilder {
    @Override
    public void configure() {
        CamelContext context = new DefaultCamelContext();

//        rest().post("")
//        from("direct:remoteService")...
    }
}
