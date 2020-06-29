package br.com.kenoby.poccliente.presenters

import br.com.kenoby.poccliente.entities.Cliente
import org.springframework.data.domain.Page
import java.time.LocalDate
import java.time.Period

data class ClientePresenter (val id:Long, val nome:String, val cpf:String, val dataNascimento:LocalDate) {

    val idade: Int
        get() = Period.between(dataNascimento, LocalDate.now()).getYears()

    constructor(entidade: Cliente) : this(entidade.id, entidade.nome, entidade.cpf, entidade.dataNascimento)

    companion object {
        @JvmStatic
        fun criarLista(listaEntidade: Page<Cliente>): Page<ClientePresenter> = listaEntidade.map{ ClientePresenter(it) }
    }

}