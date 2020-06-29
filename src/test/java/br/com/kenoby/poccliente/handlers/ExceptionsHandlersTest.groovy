package br.com.kenoby.poccliente.handlers

import org.springframework.http.HttpStatus
import spock.lang.Specification

import javax.validation.ConstraintViolation
import javax.validation.ConstraintViolationException
import javax.validation.Path

class ExceptionsHandlersTest extends Specification {

    def "Erros gerados devem ter mesmo tamanho e com valores corretos de acordo c/ a ConstraintViolationException"() {
        given: 'Numa nova instância de ExceptionsHandlers'
        def handler = new ExceptionsHandlers()

        and: 'Mock de ConstraintViolationException'
        ConstraintViolationException exception = Mock()
        ConstraintViolation violation1 = Mock()
        ConstraintViolation violation2 = Mock()
        Path path1 = Mock()
        Path path2 = Mock()

        def paths = ["campo1", "campo2"]
        def mensagens = ["Erro AAA", "Erro BBB"]
        path1.toString() >> "metodox.${paths[0]}"
        path2.toString() >> "metodox.${paths[1]}"
        violation1.getPropertyPath() >> path1
        violation2.getPropertyPath() >> path2
        violation1.getMessage() >> mensagens[0]
        violation2.getMessage() >> mensagens[1]

        exception.constraintViolations >> [violation1, violation2]

        when: 'Quando o handleConstraintViolationException() é invocado com uma ConstraintViolationException'
        def resposta = handler.handleConstraintViolationException(exception)

        then: 'O status de resposta deve ser 400'
        resposta.statusCode == HttpStatus.BAD_REQUEST

        and: 'O corpo de resposta deve estar totalmente alinhado com a ConstraintViolationException'
        resposta.body.erros.size() == 2
        resposta.body.erros.eachWithIndex { item,i ->
            assert item.campo == paths[i]
            assert item.erro == mensagens[i]
        }

    }
}
