package br.com.kenoby.poccliente.entities

import br.com.caelum.stella.bean.validation.CPF
import org.hibernate.validator.constraints.Length
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class ClienteAnotacoesTest extends Specification {

    @Shared
    def classe = Cliente

    def 'Cliente c/ @Entity'() {
        setup: 'Recuperando os atributos'
        def id = classe.getDeclaredField('id')
        def nome = classe.getDeclaredField('nome')
        def cpf = classe.getDeclaredField('cpf')
        def dataNascimento = classe.getDeclaredField('dataNascimento')
    }

    @Unroll
    def 'Atributo "#nomeAtributo" deveria estar anotado com #anotacoes'() {
        expect: 'Os atributos devem ter as anotações com as opções esperadas'
        def atributo = classe.getDeclaredField(nomeAtributo)
        for (anotacao in anotacoes) {
            assert atributo.isAnnotationPresent(anotacao[0])
            if (anotacao.size()>0) {
                def anotacaoNoMetodo = atributo.getDeclaredAnnotation(anotacao[0])
                for (atributoEsperado in anotacao[1]) {
                    assert anotacaoNoMetodo."${atributoEsperado.key}"() == atributoEsperado.value
                }
            }
        }

        where:
        nomeAtributo     | anotacoes
        'id'             | [[Id], [GeneratedValue, [strategy: GenerationType.IDENTITY]]]
        'nome'           | [[NotEmpty], [Length, [min: 5, max: 50]], [Column, [length: 50, nullable: false]]]
        'cpf'            | [[NotEmpty], [CPF], [Column, [length: 11, nullable: false]]]
        'dataNascimento' | [[NotNull], [Column, [nullable: false]]]
    }
}
