package com.magic.crius.dt.core;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * User: joey
 * Date: 2017/6/9
 * Time: 22:06
 * 数据源切换
 */
//@Component
//@Aspect
public class MultipleDataSourceAspectAdvice {
    @Around("execution(* com.magic.crius.dao.crius.db.*.*(..))")
    public Object criusDtAround(ProceedingJoinPoint jp) throws Throwable {
        MultipleDataSource.setDataSourceKey("criusDataSource");
        return jp.proceed();
    }

    @Around("execution(* com.magic.crius.dao.tethys.db.*.*(..))")
    public Object tethysDtAround(ProceedingJoinPoint jp) throws Throwable {
        MultipleDataSource.setDataSourceKey("tethysDataSource");
        return jp.proceed();
    }
}
