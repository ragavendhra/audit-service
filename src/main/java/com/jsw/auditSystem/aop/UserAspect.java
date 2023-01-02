package com.jsw.auditSystem.aop;

import com.jsw.auditSystem.model.UserInfoAudit;
import com.jsw.auditSystem.repository.UserInfoMongoRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Configuration
public class UserAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserInfoMongoRepository userInfoMongoRepository;

  /*  @Autowired
    private UserInfoAudit userInfoAudit;*/

    //What kind of method calls I would intercept
    //execution(* PACKAGE.*.*(..))
    //Weaving & Weaver
    @Before("execution(* com.jsw.auditSystem.controller.*.*(..))")
    public void before(JoinPoint joinPoint){
        //Advice
        logger.info(" Check for user access ");
        logger.info(" Allowed execution for {}", joinPoint.getClass().getFields());
    }

  /*  @After("execution(* com.jsw.auditSystem.controller.*.createAccount(..))")
    public void beforeCreateAccount(JoinPoint joinPoint){
        //receiving the user info object within the objects array
        Object[] objects = joinPoint.getArgs();

        //type-casting the object into UserInfo
        UserInfo userInfo = (UserInfo) objects[0];

        //setting up the data for the userInfoAudit object
      *//*  userInfoAudit.setEmail(userInfo.getEmail());
        userInfoAudit.setPassword(userInfo.getPassword());
        userInfoAudit.setFirstname(userInfo.getFirstname());
        userInfoAudit.setLastname(userInfo.getLastname());
        userInfoAudit.setAccountSetupFinished(userInfo.isAccountSetupFinished());*//*

        //saving the object to the mongo repository
        userInfoMongoRepository.insert(userInfoAudit);
    }
  */
  @Around("execution(* com.jsw.auditSystem.controller.UserController.*(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint){
        Object result = null;
        long beginTime = System.currentTimeMillis();
        try{
            //execution method
            result = proceedingJoinPoint.proceed();
        }
        catch(Throwable exception){
            exception.printStackTrace();
        }
        //saving the log
        saveLog(proceedingJoinPoint, beginTime);
        return result;
    }

    private void saveLog(ProceedingJoinPoint proceedingJoinPoint, long time){

        //getting the method signature
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        //retrieving the method
        Method method = methodSignature.getMethod();



        String className = proceedingJoinPoint.getTarget().getClass().getName();

        String methodName = methodSignature.getName();
     //   AuditCode value();
       // applicationLog.setMethodName(className + "." + methodName + "()");

        Object[] arguments = proceedingJoinPoint.getArgs();

        StandardReflectionParameterNameDiscoverer standardReflectionParameterNameDiscoverer = new StandardReflectionParameterNameDiscoverer();

        String[] parameterNames = standardReflectionParameterNameDiscoverer.getParameterNames(method);

        if(arguments != null && parameterNames != null){
            String params = "";
            for(int i = 0; i < arguments.length; i++){
                params += " " + parameterNames[i] + ": " + arguments[i];
            }
          //  applicationLog.setInputValue(params);
        }

        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //applicationLog.(httpServletRequest.getServletPath());
       // applicationLog.setUserName("shreyash");
       // applicationLog.setRequestTime(Instant.ofEpochMilli(time).atZone(ZoneId.of("Africa/Tunis")).toLocalDateTime());

        UserInfoAudit applicationLog = new UserInfoAudit();
        applicationLog.setOperation(httpServletRequest.getMethod());
        userInfoMongoRepository.insert(applicationLog);
    }

}
