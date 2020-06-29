package br.com.kenoby.poccliente.presenters

import br.com.kenoby.poccliente.entities.Cliente
import org.springframework.data.domain.PageImpl
import spock.lang.Shared
import spock.lang.Specification

import java.time.LocalDate

class ClientePresenterTest extends Specification {

    @Shared
    //def nascimento = LocalDate.parse('2000-01-01')
    def nascimento = LocalDate.parse('1981-07-06')
    def cliente = new Cliente(id:100, nome: 'Zé Ramalho', cpf: '123', dataNascimento: nascimento)

    def 'Instância criada com Entidade deve ter os valores corretos'() {
        when: 'Criar uma nova instância do presenter com um cliente'
        def presenter = new ClientePresenter(cliente)

        then: 'Todos os atributos de cliente devem ser iguais aos que deveriam ser'
        presenter.nome == cliente.nome
        presenter.cpf == cliente.cpf
        presenter.dataNascimento == cliente.dataNascimento
    }

    def 'A idade deve ser calculada corretamente'() {
        when: 'Criar uma nova instância do presenter com esse cliente'
        def presenter = new ClientePresenter(cliente)

        then: 'Todos os atributos de cliente devem ser iguais aos que deveriam ser'
        presenter.dataNascimento.until(LocalDate.now()).years == presenter.idade
    }

    def 'Método criarLista() deve criar lista com tamanho e valores certos'() {
        given: 'Lista original de clientes'
        def clientesOriginal = [cliente, new Cliente(id:22, nome: 'Ana', cpf: '999', dataNascimento: LocalDate.now())]
        def entidades = new PageImpl<Cliente>(clientesOriginal)

        when: 'Solicitando a lista de presenters'
        def presenters = ClientePresenter.criarLista(entidades)

        then: 'A lista de presenters deve ter o mesmo tamanho da de entidades'
        presenters.size == entidades.size

        and: 'Os valores de todos os itens devem estar corretos'
        presenters.eachWithIndex {presenter,i ->
            def entidade = entidades[i]
            assert presenter.id == entidade.id
            assert presenter.nome == entidade.nome
            assert presenter.cpf == entidade.cpf
            assert presenter.dataNascimento == entidade.dataNascimento
        }

    }
}
