package com.jsw.auditSystem.aop;


import com.jsw.auditSystem.model.EmployeeInfo;
import com.jsw.auditSystem.model.Logger;
import com.jsw.auditSystem.repository.EmployeeInfoMangoRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Aspect
@Configuration
public class Log {

    @Autowired
    private EmployeeInfoMangoRepository employeeInfoMangoRepository;
    static java.util.logging.Logger logger = java.util.logging.Logger.getLogger("Aspect Logger");

    @AfterReturning(pointcut = "@annotation(com.jsw.auditSystem.model.Logger)", returning = "object")
    private  void log(JoinPoint joinPoint, Object object) throws IllegalAccessException {

        String methodMessage = getMethodMessage(joinPoint);
        Class<?> objectClass = object.getClass();
        Map<String, String> logElements = new HashMap<>();
        Set<String> displayFields = new HashSet<>();
        for (Field field : objectClass.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Logger.class)) {
                if (checkIsShowDataEnabled(field)) {
                    logElements.put(getFieldValue(field), String.valueOf(field.get(object)));
                } else {
                    displayFields.add(getFieldValue(field));
                }
            }
        }
if("Employee created.".equals(methodMessage) || "Employee updated.".equals(methodMessage)){
    employeeInfoMangoRepository.insert(EmployeeInfo.builder().logElements(logElements).build());
}
        logger.info("method message: " + methodMessage);
        logger.info("displayed fields: " + String.join(", ", displayFields)+"  "+logElements.toString());
        logger.info("displayed fields with data: " + logElements.toString());

    }//save the data in  map

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
}
