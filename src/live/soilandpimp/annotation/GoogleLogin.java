package live.soilandpimp.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Qualifier;

import live.soilandpimp.service.ApiLoginService;
import live.soilandpimp.service.GoogleLoginService;

/**
 * @see GoogleLoginService
 * @see ApiLoginService
 * 
 * @author NYPD
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface GoogleLogin {}
