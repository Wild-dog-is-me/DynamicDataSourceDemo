package org.dog.server.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.dog.server.annotation.DataSource;
import org.dog.server.datasource.DynamicDataSourceContextHolder;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

/**
 * @Author: Odin
 * @Date: 2023/6/2 12:59
 * @Description:
 */

@Component
@Aspect
@Slf4j
public class DataSourceAspect {

    @Pointcut("@annotation(org.dog.server.annotation.DataSource)" +
            "||@within(org.dog.server.annotation.DataSource)")
    public void pc() {
    }

    @Around("pc()")
    public Object around(ProceedingJoinPoint point) {

        System.out.println("executed");

        DataSource dataSource = getDataSource(point);
        if (dataSource != null) {
            String value = dataSource.value();
            log.error("[value]:{}",dataSource.value());

            DynamicDataSourceContextHolder.setDataSourceType(value);
        }
        Object o = null;
        try {
            o = point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            DynamicDataSourceContextHolder.clearDataSourceType();
        }
        return o;
    }

    public DataSource getDataSource(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        DataSource annotation = AnnotationUtils.findAnnotation(signature.getMethod(), DataSource.class);
        if (annotation != null) {
            return annotation;
        }
        return AnnotationUtils.findAnnotation(signature.getDeclaringType(), DataSource.class);
    }
}
