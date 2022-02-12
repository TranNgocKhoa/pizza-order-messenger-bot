package com.khoa.bot.connector.facebook.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khoa.bot.connector.facebook.entity.WebHookRequest;
import com.khoa.bot.connector.facebook.repository.mapper.WebHookRequestMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Component
public class RequestFilter implements Filter {
    private final ObjectMapper customObjectMapper;
    private final WebHookRequestMapper webHookRequestMapper;

    public RequestFilter(ObjectMapper customObjectMapper, WebHookRequestMapper webHookRequestMapper) {
        this.customObjectMapper = customObjectMapper;
        this.webHookRequestMapper = webHookRequestMapper;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest wrappedRequest = new RequestWrapper((HttpServletRequest) request);

        if (wrappedRequest.getRequestURI().contains("messenger") && wrappedRequest.getMethod().equals(RequestMethod.POST.name())) {
            CompletableFuture.runAsync(() -> {
                try {
                    webHookRequestMapper.insertRequest(WebHookRequest.builder()
                            .header(wrappedRequest.getHeader("X-Hub-Signature"))
                            .event(customObjectMapper.readTree(wrappedRequest.getInputStream()).toString())
                            .build());
                } catch (Exception e) {
                    // Do nothing
                }
            });
        }

        chain.doFilter(wrappedRequest, response);
    }

}