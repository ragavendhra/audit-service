package com.jsw.auditSystem.aop;


import com.jsw.auditSystem.model.AddressInfo;
import com.jsw.auditSystem.model.EmployeeInfo;
import com.jsw.auditSystem.model.Logger;
import com.jsw.auditSystem.model.UserInfoAudit;
import com.jsw.auditSystem.repository.AddressMangoRepository;
import com.jsw.auditSystem.repository.EmployeeInfoMangoRepository;
import com.jsw.auditSystem.repository.UserInfoMongoRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Aspect
@Configuration
public class Log {

    @Autowired
    private EmployeeInfoMangoRepository employeeInfoMangoRepository;

    @Autowired
    private AddressMangoRepository addressMangoRepository;

    @Autowired
    private UserInfoMongoRepository userInfoMongoRepository;

    static java.util.logging.Logger logger = java.util.logging.Logger.getLogger("Aspect Logger");

    @AfterReturning(pointcut = "@annotation(com.jsw.auditSystem.model.Logger)", returning = "object")
    private void log(JoinPoint joinPoint, Object object) throws IllegalAccessException {
        String methodMessage = getMethodMessage(joinPoint);
        Class<?> objectClass = object.getClass();
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String> logElements = new HashMap<>();
        Set<String> displayFields = new HashSet<>();
        String operation = httpServletRequest.getMethod();
        for (Field field : objectClass.getDeclaredFields()) {
            field.setAccessible(true);
            if ("POST".equals(operation)) {
                logElements.put(field.getName(), String.valueOf(field.get(object)));
            } else if (field.isAnnotationPresent(Logger.class)) {
                if (checkIsShowDataEnabled(field)) {
                    logElements.put(getFieldValue(field), String.valueOf(field.get(object)));
                } else {
                    displayFields.add(getFieldValue(field));
                    //  }
                }
            }
        }

        switch(methodMessage){

            //cases for the employee class
            case "Employee updated.":
            case "Employee created.":
                insertingAdditionalInformationIntoMap(logElements, operation);
                employeeInfoMangoRepository.insert(EmployeeInfo.builder().empId(logElements.get("id")).logElements(logElements).build());
                break;

            //cases for the address class
            case "Address updated.":
            case "Address created.":
                insertingAdditionalInformationIntoMap(logElements, operation);
                addressMangoRepository.insert(AddressInfo.builder().addressId(logElements.get("id")).logElements(logElements).build());
                break;

            //cases for user class
            case "User created.":
            case "User updated.":
                insertingAdditionalInformationIntoMap(logElements, operation);
                userInfoMongoRepository.insert(UserInfoAudit.builder().userId(logElements.get("id")).logElements(logElements).build());
                break;

        }

    }

    private static String getMethodMessage(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Logger aspectLogger = method.getAnnotation(Logger.class);
        return aspectLogger.value();
    }

    private static String getFieldValue(Field field) {
        String fieldValue = field.getAnnotation(Logger.class).value();
        return fieldValue.isEmpty() ? field.getName() : fieldValue;
    }

    private static boolean checkIsShowDataEnabled(Field field) {
        return field.getAnnotation(Logger.class).showData();
    }

    private void insertingAdditionalInformationIntoMap(Map logElements, String operation){
        logElements.put("operation", operation);
        logElements.put("createdBy", "shreyash");
        logElements.put("createdDate", String.valueOf(Instant.now()));
    }
}
