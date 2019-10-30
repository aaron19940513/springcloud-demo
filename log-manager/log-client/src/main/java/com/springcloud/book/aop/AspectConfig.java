package com.springcloud.book.aop;
import java.lang.reflect.Method;

import com.springcloud.book.common.annotation.ApiInformation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author sam
 * @date 10/29/19 16:25
 */
@Aspect
@Component
public class AspectConfig {
    @Autowired
    private RestTemplate restTemplate;


    @Pointcut("execution(public * com.springcloud.book.*.controller.*.*(..))")
    public void actionLog() {
    }

    @Before("actionLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        Class clazz = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();
        Method method = clazz.getMethod(methodName);
        if (null != method && method.getAnnotation(ApiInformation.class) != null) {
            ApiInformation apiInformation = method.getAnnotation(ApiInformation.class);
            System.out.println(apiInformation.applicationName());
            System.out.println(apiInformation.methodType());
        }
        System.out.println(joinPoint);
    }


}
