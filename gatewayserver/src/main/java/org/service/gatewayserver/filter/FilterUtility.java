package org.service.gatewayserver.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

/**
 * @Author Prakash Ponali (@pponali)
 * @Date 15/12/23
 * @Description
 */
@Component
public class FilterUtility {


    public static final String X_REQUEST_TRACE_ID = "X-Request-Trace-Id";

    public String getCorrelationId(HttpHeaders headers) {
        headers.getFirst("X-Request-Trace-Id");
         if(headers.get("X-Request-Trace-Id") != null){
             List<String> correlationId = headers.get(X_REQUEST_TRACE_ID);
             return  correlationId.stream().findFirst().get();
         } else {
             return null;
         }
    }

    public ServerWebExchange setRequestHeader(ServerWebExchange exchange, String name, String value) {
        return exchange.mutate().request(exchange.getRequest().mutate().header(name, value).build()).build();
    }

    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
        return this.setRequestHeader(exchange, X_REQUEST_TRACE_ID, correlationId);
    }
}
