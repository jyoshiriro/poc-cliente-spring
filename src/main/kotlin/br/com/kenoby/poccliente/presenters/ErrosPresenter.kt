package br.com.kenoby.poccliente.presenters

class ErrosPresenter {

    val erros = mutableListOf<Erro>()

    fun addErro(campo: String, erro: String) {
        this.erros.add(Erro(campo, erro))
    }

}
data class Erro(val campo:String, val erro:String)

