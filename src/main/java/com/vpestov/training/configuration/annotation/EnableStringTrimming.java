package com.vpestov.training.springmagic.annotation;

import com.vpestov.training.configuration.bpp.StringTrimmingConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(StringTrimmingConfiguration.class)
public @interface EnableStringTrimming {
}
