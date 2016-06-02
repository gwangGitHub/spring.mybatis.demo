package com.gwang.spring.mybatis.demo.datasource;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MultipleDataSourceAspect {

	private static final Logger LOG = LoggerFactory.getLogger(MultipleDataSourceAspect.class);

    public Object doAround(ProceedingJoinPoint jp) throws Throwable {
        if (LOG.isDebugEnabled()) {
            LOG.debug("MultipleDataSourceAspectAdvice invoked!");
        }
        Signature signature = jp.getSignature();
        DataSources dataSourceKey = getDataSourceKey(signature);

        if (dataSourceKey != null) {
            DataSourceTypeManager.set(dataSourceKey);
        }

        Object jpVal = jp.proceed();
        return jpVal;
    }

    private DataSources getDataSourceKey(Signature signature) {
        if (signature == null) return null;

        if (signature instanceof MethodSignature) {

            //检测方法级注解
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();
            if (method.isAnnotationPresent(DataSource.class)) {
                return dsSettingInMethod(method);
            }

            //类级注解
            Class declaringClazz = method.getDeclaringClass();
            if (declaringClazz.isAnnotationPresent(DataSource.class)) {
                try {
                    return dsSettingInConstructor(declaringClazz);
                } catch (Exception e) {
                    LOG.error("获取构造方法的DataSource注解失败", e);
                }
            }

            //包级注解,为了配置方便，包注解和类以及方法注解方式不同
            Package pkg = declaringClazz.getPackage();
            dsSettingInPackage(pkg);

        }

        return null;
    }

    private DataSources dsSettingInMethod(Method method) {
        DataSource dataSource = method.getAnnotation(DataSource.class);
        return dataSource.value();
    }

    private DataSources dsSettingInConstructor(Class clazz) {
        DataSource dataSource = (DataSource) clazz.getAnnotation(DataSource.class);
        return dataSource.value();
    }

    private void dsSettingInPackage(Package pkg) {
        if (LOG.isDebugEnabled()) {
            LOG.debug(pkg.getName());
        }
//        MultipleDataSource.usePackageDataSource(pkg.getName());
//        DataSourceTypeManager.usePackageDataSource();
    }

}
