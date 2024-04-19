package edu.bistu.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bistu.annotation.SystemLog;
import edu.bistu.domain.entity.WebLog;
import edu.bistu.service.WebLogService;
import edu.bistu.utils.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

@Component
@Aspect
@Slf4j
public class LogAspect {

    @Autowired
    private WebLogService webLogService;

    @Autowired
    private ObjectMapper objectMapper;

    @Pointcut("@annotation(edu.bistu.annotation.SystemLog)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object pointAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        beforeProceed(joinPoint);
        Object result = joinPoint.proceed();
        afterProceed(joinPoint, result);

        return result;
    }

    private SystemLog getSystemLog(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        return methodSignature.getMethod().getAnnotation(SystemLog.class);
    }

    private void beforeProceed(ProceedingJoinPoint joinPoint) {
    }

    private void afterProceed(ProceedingJoinPoint joinPoint, Object result) throws JsonProcessingException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String url = request.getRequestURL().toString();
        String businessName = getSystemLog(joinPoint).businessName();
        String httpMethod = request.getMethod();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = ((MethodSignature) joinPoint.getSignature()).getName();
        String ip = request.getRemoteHost();
        String args = objectMapper.writeValueAsString(joinPoint.getArgs());
        String r = objectMapper.writeValueAsString(result);
        String log = """
                URL          : %s
                Business Name: %s
                HTTP Method  : %s
                Class Method : %s
                IP           : %s
                Args         : %s
                Result       : %s
                """.formatted(url, businessName, httpMethod, className + "." + methodName, ip, args, r);

        Integer userId;
        try {
            userId = SecurityUtils.getUserId();
        } catch (Exception exception) {
            userId = -1;
        }

        WebLog webLog = new WebLog()
                .setUserid(userId)
                .setLog(log)
                .setCreateTime(new Date());
        webLogService.save(webLog);
    }

}
