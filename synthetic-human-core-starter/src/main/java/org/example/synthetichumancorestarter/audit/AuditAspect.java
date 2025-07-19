package org.example.synthetichumancorestarter.audit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Aspect
@Component
public class AuditAspect {
    @Around("@annotation(org.example.synthetichumancorestarter.audit.WeylandWatchingYou)")
    public Object aroundAudit(ProceedingJoinPoint joinPoint) throws Throwable {
        AuditEvent auditEvent = new AuditEvent();
        Object result = joinPoint.proceed();

        auditEvent.setClassName(joinPoint.getSignature().getDeclaringTypeName());
        auditEvent.setMethodName(joinPoint.getSignature().getName());
        auditEvent.setArguments(joinPoint.getArgs());
        auditEvent.setResult(result);
        auditEvent.setTimestamp(Instant.now());

        System.out.println("Audit: " + auditEvent);

        return result;
    }
}
