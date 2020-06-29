package br.com.kenoby.poccliente.helpers

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import spock.lang.Specification

class BeanUtilsTest extends Specification {

    def "Método copiarNaoNulosDePara() deve copiar somente os valores não nulos"() {
        expect:
        def destinoRecuperado = new TalCoisa(destino)
        BeanUtils.copiarNaoNulosDePara(new TalCoisa(origem), destinoRecuperado)
        destinoRecuperado == esperado

        where:
        origem                | destino               | esperado
        [id:11, nome:'AAA']   | [id:null, nome:'XXA'] | new TalCoisa(id:11, nome: 'AAA')
        [id:null, nome:'WWW'] | [id:20, nome:null]    | new TalCoisa(id:20, nome: 'WWW')
        [id:33, nome:'ABC']   | [id:77, nome:'ZYX']   | new TalCoisa(origem)
        [id:null, nome:null]  | [id:44, nome:null]    | new TalCoisa(destino)
    }
}

@EqualsAndHashCode(allProperties = true)
@ToString
class TalCoisa {
    Integer id
    String nome
}