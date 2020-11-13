package br.com.iteris.gsv.graalvm.springbootapinative;

import nativeimage.Reflection;
import nativeimage.Reflections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

// Excluída configuração automática de SpringDataWeb, por incompatibilidade com compilação nativa do GraalVm.
@SpringBootApplication(proxyBeanMethods = false, exclude = SpringDataWebAutoConfiguration.class)

// Adicionando configurações de reflection para as classe POJO da aplicação e para algumas classes do módulo spring-data-commons
@Reflections({
    @Reflection(declaredConstructors = true, declaredFields = true, publicMethods = true, scanPackage = "br.com.iteris.gsv.graalvm.springbootapinative.requests"),
    @Reflection(declaredConstructors = true, declaredFields = true, publicMethods = true, scanPackage = "br.com.iteris.gsv.graalvm.springbootapinative.responses"),
    @Reflection(declaredFields = true, declaredConstructors = true, publicMethods = true, scanClass = Page.class),
    @Reflection(declaredFields = true, declaredConstructors = true, publicMethods = true, scanClass = PageImpl.class),
    @Reflection(declaredFields = true, declaredConstructors = true, publicMethods = true, scanClass = PageRequest.class),
})
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
