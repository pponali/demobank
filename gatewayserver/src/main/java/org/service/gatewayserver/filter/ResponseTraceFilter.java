package org.service.gatewayserver.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


/**
 * @Author Prakash Ponali (@pponali)
 * @Date 15/12/23
 * @Description
 */

@Component
@Slf4j
public class ResponseTraceFilter {

    @Autowired
    org.service.gatewayserver.filter.FilterUtility filterUtility;

    @Bean
    GlobalFilter postGlobalFilter() {
        return (exchange, chain) -> chain.filter(exchange).then(Mono.fromRunnable(() -> {
            HttpHeaders httpHeaders = exchange.getRequest().getHeaders();
            httpHeaders.add("X-Request-Trace-Id", filterUtility.getCorrelationId(httpHeaders));
        }));
    }
}
