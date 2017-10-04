package io.github.vort2014.spring.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Checks if annotated parameter is {@link java.lang.String} and equals login of user that calls this method.
 * If user is a member of admin groups then login check will be skipped.
 *
 * @author vort
 * @see io.github.vort2014.spring.aspect.OwnerAspect
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface OwnerIdentity {
}
