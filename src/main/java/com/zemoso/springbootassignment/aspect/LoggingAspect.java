package com.zemoso.springbootassignment.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;
import java.util.logging.Logger;

@Aspect
@Configuration
public class LoggingAspect {

    private Logger myLogger =
            Logger.getLogger(getClass().getName());

    @Around("within(com.zemoso.springbootassignment..*)")
    public Object logging(
            ProceedingJoinPoint theProceedingJointPoint) throws Throwable{
        String method = theProceedingJointPoint.getSignature().toShortString();
        myLogger.info("\n====>>>> Method called:"+method);

        Object result;
        try{
            result = theProceedingJointPoint.proceed();
        }
        catch (Exception e){
            myLogger.warning("\n!!!!!EXCEPTION!!!!!!\n" +
                    "method: "+method+"\nerror: "+e.getMessage());
            result= null;
        }
        myLogger.info("\n====>>>> Method returned: "+method);
        return result;
    }
}
