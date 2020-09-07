package com.peace.myblog.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author YR#
 * @create 2020-08-10 19:00
 * 日志记录切面
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    // 切面
    @Pointcut("execution(* com.peace.myblog.webController.*.*(..))")
    public void log() {}

    // 在请求前构建 RequestLog
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." +joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        log.info("RequestLog : {}", requestLog);
    }

    @After("log()")
    public void doAfter() {

        log.info("_____doAfter______");
    }


    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturn(Object result) {
        log.info("result:{}",result);

    }


    /**
     * 记录博客访问信息
     * 记录内容：访问URL，访客IP，调用方法，参数
     */
    private class RequestLog {

        private String RequestURL;
        private String visitorIp;
        private String classMethod;
        private Object[] args;

        public RequestLog(String requestURL, String visitorIp, String classMethod, Object[] args) {
            RequestURL = requestURL;
            this.visitorIp = visitorIp;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "Log {" +
                    "RequestURL='" + RequestURL + '\'' +
                    ", visitorIp='" + visitorIp + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
