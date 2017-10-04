package io.github.vort2014.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

/**
 * <p>Security aspect that checks if user that requested code execution is trying to work with own data.
 * Only admins allowed to modify data related to other users.</p>
 *
 * @author vort
 */
@Aspect
@Configurable
public class OwnerAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(OwnerAspect.class);

    /**
     * Triggers on method with param that is annotated with {@link io.github.vort2014.spring.aspect.OwnerIdentity}
     * annotation and checks if user's login equals this parameter. If user is an admin then method execution
     * is allowed even if login name do not match.
     *
     * @param joinPoint aspect's join point
     * @throws RuntimeException if parameter isn't String
     *                            or doesn't equals user's login (and requester is not an admin)
     *                            or more then one parameters are annotated
     * @see io.github.vort2014.spring.aspect.OwnerIdentity
     * @see org.aspectj.lang.JoinPoint
     */
    @Before("execution(* *(.., @OwnerIdentity (*), ..))")
    public void checkOwnerIdentity(JoinPoint joinPoint) {
        // get login from
        final String login = null;;
        final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        final Method method = methodSignature.getMethod();
        final String fullMethodName = method.getDeclaringClass() + "." + method.getName() + "()";
        LOGGER.debug("Call that requires owner identification was made by login: {}, method = {}",
                login, fullMethodName);

        // get user's groups
        final List<String> userGroups = null;
        LOGGER.debug("User is a member of groups: {}", userGroups);
        if (userGroups.contains("admin_role") || userGroups.contains("super_admin")) {
            LOGGER.debug("User is an admin so we don't perform any checks and exit from aspect");
            return;
        }

        LOGGER.debug("User with login: '{}' is not an admin, going to check login match", login);

        Object[] args = joinPoint.getArgs();
        LOGGER.debug("args.length={}", args.length);

        final Class<?>[] parameterTypes = method.getParameterTypes();
        final Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        int count = 0;
        for(int i = 0; i < parameterAnnotations.length; i++) {
            for (final Annotation annotation : parameterAnnotations[i]) {
                if (annotation instanceof OwnerIdentity) {
                    if (++count > 1) {
                        throw new RuntimeException(
                                "More than one parameter annotated with OwnerIdentity annotation within "
                                        +
                                        fullMethodName);
                    }
                    if (parameterTypes[i] == String.class) {
                        if (!login.equals(args[i])) {
                            throw new RuntimeException("Login \"" + login +
                                    "\" doesn't match method parameter value \"" + args[i] + "\"" +
                                    " that was annotated with OwnerIdentity within "
                                    + fullMethodName);
                        }
                    } else {
                        throw new RuntimeException(
                                "Method parameter annotated with OwnerIdentity must be String"
                                        +
                                        fullMethodName);
                    }
                }
            }
        }
    }
}

