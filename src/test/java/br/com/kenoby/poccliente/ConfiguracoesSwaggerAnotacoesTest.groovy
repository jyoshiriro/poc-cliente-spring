package br.com.kenoby.poccliente

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import spock.lang.Shared
import spock.lang.Specification
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration
import springfox.documentation.swagger2.annotations.EnableSwagger2

class ConfiguracoesSwaggerAnotacoesTest extends Specification {

    @Shared
    def classe = ConfiguracoesSwagger

    def 'ConfiguracoesSwagger c/ @Configuration, @EnableSwagger2 e @Import(BeanValidatorPluginsConfiguration.class)'() {
        expect: 'A classe deve possuir as anotações Configuration, EnableSwagger2 e Import'
        classe.isAnnotationPresent(Configuration)
        classe.isAnnotationPresent(EnableSwagger2)
        classe.isAnnotationPresent(Import)

        and: 'A anotação Import deve ter o tipo BeanValidatorPluginsConfiguration indicado'
        def anotacao = classe.getDeclaredAnnotation(Import)
        anotacao.value().contains(BeanValidatorPluginsConfiguration)
    }

    def 'O método ConfiguracoesSwagger.api() deve estar anotado com @Bean'() {
        setup: 'Recuperando o método "api()"'
        def metodo = classe.getDeclaredMethod('api')

        expect: 'A anotação Bean deve estar presente'
        metodo.isAnnotationPresent(Bean)
    }
}
