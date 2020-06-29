package br.com.kenoby.poccliente.presenters

import spock.lang.Specification

class ErrosPresenterTest extends Specification {

    def "Nova instância de ErrosPresenter deve nascer sem erros"() {
        given: 'Nova intância'
        def presenter = new ErrosPresenter()

        when: 'Solicitar imediatamente a lista de erros'
        def erros = presenter.getErros()

        then: 'A lista deve estar vazia'
        erros.isEmpty()
    }

    def "Todo erro adicionado deve ser recuperado quando solicitado"() {
        given: 'Nova instância'
        def presenter = new ErrosPresenter()

        when: 'Adicionar 3 erros'
        def errosEsperados = [campo1:'AAA', campo2:'BBBB', campo3:'CCCC']
        errosEsperados.each {item ->
            presenter.addErro(item.key, item.value)
        }

        and: 'Solicitar os erros do presenter'
        def errosRecuperados = presenter.getErros()

        then:
        errosEsperados.size() == errosRecuperados.size()
        errosEsperados.eachWithIndex { item, i ->
            assert item.key == errosRecuperados[i].campo
            assert item.value == errosRecuperados[i].erro
        }
    }
}
