package com.netty.send.iservice.domain;

import java.io.Serializable;

/**
 * @author Jerry
 * @Date 2020/10/19 11:17 下午
 */
public class ServiceDoMain implements Serializable {
    private String className;

    private String methodName;

    private Class<?> [] paramTypes;

    private Object[] args;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Class<?>[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
