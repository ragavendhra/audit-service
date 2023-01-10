/*
package com.jsw.auditSystem.aop;

import com.jsw.auditSystem.model.*;
import com.jsw.auditSystem.repository.AddressMangoRepository;
import com.jsw.auditSystem.repository.UserInfoMongoRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Aspect
@Configuration
public class UserAspect {

    @Autowired
    private UserInfoMongoRepository userInfoMongoRepository;

    @Autowired
    private AddressMangoRepository addressMangoRepository;


  @Around("execution(* com.jsw.auditSystem.controller.*.*(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint){
        Object result = null;
        long beginTime = System.currentTimeMillis();
        try{
            //execution method
          //  request ; ladelinfo chnade ladle 150 changed commonJSOn
            // object type ,method name, opetione, object
            //annotation based on the @anotation
            result = proceedingJoinPoint.proceed();
        }
        catch(Throwable exception){
            exception.printStackTrace();
        }
        //saving the log
        saveLog(proceedingJoinPoint, beginTime, result);
        return result;
    }

    private void saveLog(ProceedingJoinPoint proceedingJoinPoint, long time, Object result){

        //getting the method signature
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String path = httpServletRequest.getServletPath();

        //checking whether the api contains the object name and pushing the built object to the respective repository
        buildingObject(result, httpServletRequest, path);

    }

    private void buildingObject(Object result, HttpServletRequest httpServletRequest, String path) {
        if(path.contains("user")){
            UserInfoAudit userInfoAudit;
            if(!httpServletRequest.getMethod().equals("GET")){//post,put
                ResponseEntity<Response<UserInfo>> resultantObject = (ResponseEntity<Response<UserInfo>>) result;
                UserInfo userInfo = resultantObject.getBody().getContent();
                userInfoAudit = UserInfoAudit.builder().firstname(userInfo.getFirstname())
                        .lastname(userInfo.getLastname())
                        .email(userInfo.getEmail())
                        .password(userInfo.getPassword())
                        .operation(httpServletRequest.getMethod())
                        .build();
                userInfoMongoRepository.insert(userInfoAudit);
            }

            else{
                // auditing the GET ALL we need add one more condition for getById
                ResponseEntity<Response<List<UserInfo>>> resultantObject = (ResponseEntity<Response<List<UserInfo>>>) result;
                List<UserInfo> usersInfoList = resultantObject.getBody().getContent();
                userInfoAudit = UserInfoAudit.builder().userInfoList(usersInfoList)
                        .operation(httpServletRequest.getMethod())
                        .build();
                userInfoMongoRepository.insert(userInfoAudit);
            }
        }

        else if(path.contains("address")){
            AddressInfo addressInfo;
            if(!httpServletRequest.getMethod().equals("GET"))
            {
                ResponseEntity<Address> resultantObject = (ResponseEntity<Address>) result;
                addressInfo = AddressInfo.builder().addressName(resultantObject.getBody().getAddressName())
                        .operation(httpServletRequest.getMethod())
                        .build();
                addressMangoRepository.insert(addressInfo);
            }
        }
    }

}
*/
