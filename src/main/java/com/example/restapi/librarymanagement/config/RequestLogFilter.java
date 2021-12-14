package com.example.restapi.librarymanagement.config;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

@Component
public class RequestLogFilter  implements Filter {

    private final String REQUEST_ID = "request-id";
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //generates a random id per request
        UUID uniqueId = UUID.randomUUID();
        try {
            MDC.put(REQUEST_ID,uniqueId.toString());
            filterChain.doFilter(servletRequest,servletResponse);
        }finally {
            MDC.remove(REQUEST_ID);
        }
    }

    @Override
    public void destroy() { }
}
