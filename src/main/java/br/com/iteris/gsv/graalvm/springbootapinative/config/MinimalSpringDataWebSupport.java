package br.com.iteris.gsv.graalvm.springbootapinative.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

// Adiciona suporte a parâmetros do tipo Pageable e Sort nos métodos do controller. Trata incompatibilidade já identificada
// no módulo spring-data-commons com GraalVM (https://jira.spring.io/browse/DATACMNS-1736), a ser corrigida na versão 2.4.0.
@Configuration(proxyBeanMethods = false)
public class MinimalSpringDataWebSupport implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        SortHandlerMethodArgumentResolver sortResolver = new SortHandlerMethodArgumentResolver();
        PageableHandlerMethodArgumentResolver pageableResolver = new PageableHandlerMethodArgumentResolver(sortResolver);

        resolvers.add(sortResolver);
        resolvers.add(pageableResolver);
    }
}
