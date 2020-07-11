package br.com.kenoby.poccliente.repositories;

import br.com.kenoby.poccliente.entities.Cliente;
import br.com.kenoby.poccliente.entities.ClienteResumido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteResumidoRepository extends JpaRepository<ClienteResumido, Long> {

}
