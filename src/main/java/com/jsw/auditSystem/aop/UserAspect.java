package com.jsw.auditSystem.aop;

import com.jsw.auditSystem.model.UserInfo;
import com.jsw.auditSystem.model.UserInfoAudit;
import com.jsw.auditSystem.repository.UserInfoMongoRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class UserAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserInfoMongoRepository userInfoMongoRepository;

    @Autowired
    private UserInfoAudit userInfoAudit;

    //What kind of method calls I would intercept
    //execution(* PACKAGE.*.*(..))
    //Weaving & Weaver
    @Before("execution(* com.jsw.auditSystem.controller.*.*(..))")
    public void before(JoinPoint joinPoint){
        //Advice
        logger.info(" Check for user access ");
        logger.info(" Allowed execution for {}", joinPoint.getClass().getFields());
    }

    @After("execution(* com.jsw.auditSystem.controller.*.createAccount(..))")
    public void beforeCreateAccount(JoinPoint joinPoint){
        //receiving the user info object within the objects array
        Object[] objects = joinPoint.getArgs();

        //type-casting the object into UserInfo
        UserInfo userInfo = (UserInfo) objects[0];

        //setting up the data for the userInfoAudit object
        userInfoAudit.setEmail(userInfo.getEmail());
        userInfoAudit.setPassword(userInfo.getPassword());
        userInfoAudit.setFirstname(userInfo.getFirstname());
        userInfoAudit.setLastname(userInfo.getLastname());
        userInfoAudit.setAccountSetupFinished(userInfo.isAccountSetupFinished());

        //saving the object to the mongo repository
        userInfoMongoRepository.insert(userInfoAudit);
    }
}
