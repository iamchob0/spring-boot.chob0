package com.iam.chobo.global.response;

import com.iam.chobo.global.error.ErrorResponse;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class HttpResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    // response 타입 통일되게 변환
    @Override
    public Object beforeBodyWrite(
        Object body, 
        MethodParameter returnType, 
        MediaType selectedContentType, 
        Class<? extends HttpMessageConverter<?>> converterType,
        ServerHttpRequest request,
        ServerHttpResponse response) {
        if(body instanceof ErrorResponse) {
            return body;
        }
        if(body instanceof Response) {
            return body;
        }
        return new Response.Builder<>().data(body).build();
    }
}