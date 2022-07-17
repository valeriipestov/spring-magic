package com.vpestov.training.springmagic.bpp;

import com.vpestov.training.springmagic.annotation.Trimmed;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

public class TrimmedAnnotationBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        var beanType = bean.getClass();
        if (beanType.isAnnotationPresent(Trimmed.class)) {
            var enhancer = new Enhancer();
            enhancer.setSuperclass(beanType);
            MethodInterceptor interceptor = (obj, method, args, methodProxy) -> {
                for (int i = 0; i < args.length; i++) {
                    if (args[i].getClass().equals(String.class)) {
                        args[i] = args[i].toString().trim();
                    }
                }
                var result = methodProxy.invokeSuper(obj, args);
                if (method.getReturnType().equals(String.class)) {
                    return result.toString().trim();
                }
                return result;
            };
            enhancer.setCallback(interceptor);
            return enhancer.create();
        }
        return bean;
    }
}
