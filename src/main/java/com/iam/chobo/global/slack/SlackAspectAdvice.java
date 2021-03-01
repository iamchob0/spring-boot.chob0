package com.iam.chobo.global.slack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class SlackAspectAdvice {

    @Autowired
    private SlackSenderManager slackSenderManager;

    @Around("execution(* com.iam.chobo.domain.board.*.*(..))")
    public Object logger(ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }

    @AfterThrowing(pointcut = "execution(* com.iam.chobo..*.*(..))", throwing = "exception")
    public void handleExceptionWithSlack(JoinPoint joinPoint, Exception exception) throws RuntimeException {
        // exception 으로 해당 메서드에서 발생한 예외를 가져올 수 있다.
        log.info(exception.getMessage());
        String exceptionMessage = exception.getMessage();
        // StringWriter sw = new StringWriter();
        // PrintWriter pw = new PrintWriter(sw);
        // exception.printStackTrace(pw);
        // String sStackTrace = sw.toString();
 
        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        final HttpUtils utils = new HttpUtils();

        String method = request.getMethod();
        String headers = utils.getHeadersJsonString(request);
        String requestQuery = utils.getRequestQueryJsonString(request);
        String requestBody = utils.getRequestBodyJsonString(request);
    
        SlackMessageDto.Store message = new SlackMessageDto.Store(method, headers, requestQuery, requestBody, exceptionMessage);
        slackSenderManager.send(message);
    }

    @NoArgsConstructor
    public static class HttpUtils {

        public String getHeadersJsonString(HttpServletRequest request) {
            // 헤더 파싱
            String headers = "";
            HttpHeaders httpHeaders = Collections.list(request.getHeaderNames()).stream()
                    .collect(Collectors.toMap(Function.identity(), h -> Collections.list(request.getHeaders(h)),
                            (oldValue, newValue) -> newValue, HttpHeaders::new));
            try {
                Map<String, String> result = httpHeaders.entrySet().stream()
                    .collect(HashMap::new, (m,e)->e.getValue().forEach(k->m.put(e.getKey(),k)), Map::putAll);
    
                headers = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(result);
            } catch(JsonProcessingException e) {
                log.error(e.getMessage());
            }
            return headers;
        }
    
        public String getRequestQueryJsonString(HttpServletRequest request) {
            String requestQuery = "";
            HashMap<String, Object> map = new HashMap<String, Object>();
            Enumeration<String> enumber = request.getParameterNames();
            
            while (enumber.hasMoreElements()) {
                String key = enumber.nextElement().toString();
                String value = request.getParameter(key);
        
                map.put(key, value);  
            }
            try {
                requestQuery = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(map);
            } catch(JsonProcessingException e) {
                log.error(e.getMessage());
            }
            return requestQuery;
        }
    
        public String getRequestBodyJsonString(HttpServletRequest request) {
            String requestBody = "";
            try {
                StringBuilder builder = new StringBuilder();
                BufferedReader input = new BufferedReader(new InputStreamReader(request.getInputStream()));
                String buffer;
                while ((buffer = input.readLine()) != null) {
                    if (builder.length() > 0) {
                        builder.append("\n");
                    }
                    builder.append(buffer);
                }
                String requestBodyString = builder.toString();
                ObjectMapper mapper = new ObjectMapper();
                Map<String, String> result = mapper.readValue(requestBodyString, new TypeReference<Map<String,String>>(){});
                requestBody = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(result);
            } catch(JsonGenerationException e) {
                log.error(e.getMessage());
            } catch (IOException e) {
                log.error(e.getMessage());
            }
            return requestBody;
        }

    }
    
}
