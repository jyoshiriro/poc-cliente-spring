package br.com.kenoby.poccliente.handlers


import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import spock.lang.Shared
import spock.lang.Specification

import javax.validation.ConstraintViolationException

class ExceptionsHandlersAnotacoesTest extends Specification {

    @Shared
    def classe = ExceptionsHandlers

    def 'ExceptionHandler c/ @ControllerAdvice'() {
        expect: 'A classe deve possuir a anotação ControllerAdvice'
        classe.isAnnotationPresent(ControllerAdvice)
    }

    def 'O método ExceptionHandler.handleConstraintViolationException() deve estar anotado com @Bean'() {
        setup: 'Recuperando o método "handleConstraintViolationException(ConstraintViolationException)"'
        def metodo = classe.getDeclaredMethod('handleConstraintViolationException', ConstraintViolationException)

        expect: 'A anotação ExceptionHandler deve estar presente'
        metodo.isAnnotationPresent(ExceptionHandler)

        and: 'A anotação ExceptionHandler deve ter o tipo ConstraintViolationException indicado'
        def anotacao = metodo.getDeclaredAnnotation(ExceptionHandler)
        anotacao.value().contains(ConstraintViolationException)
    }
}
