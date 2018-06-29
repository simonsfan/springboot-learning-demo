package com.simons.cn.springbootdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * 项目名称：springbootdemo
 * 类名称：com.simons.cn.springbootdemo
 * 类描述：
 * 创建人：simonsfan
 * 创建时间：2018/6/27 19:41
 */
@Aspect
@Component
public class HttpRequestAspect {

    private static final Logger log = LoggerFactory.getLogger(HttpRequestAspect.class);

    public static long startTime;
    public static long endTime;

    @Pointcut("execution(public * com.simons.cn.springbootdemo.controller.*.*(..))")
    public void print() {

    }

    @Before(value="print()")
    public void before(JoinPoint joinPoint) {
        log.info("前置切面before……");
        startTime = System.currentTimeMillis();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String requestURI = request.getRequestURI();
        String remoteAddr = request.getRemoteAddr();
        String requestMethod = request.getMethod();
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("请求url=" + requestURI + ",客户端ip=" + remoteAddr + ",请求方式=" + requestMethod + ",请求的类名=" + declaringTypeName + ",方法名=" + methodName + ",入参=" + args);
    }

    @After("print()")
    public void after() {
        endTime = System.currentTimeMillis() - startTime;
        log.info("后置切面after……");
    }

    @AfterReturning(pointcut = "print()", returning = "object")
    public void getAfterReturn(Object object) {
        log.info("本次接口耗时={}ms", endTime);
        log.info("afterReturning={}", object.toString());
    }

}
