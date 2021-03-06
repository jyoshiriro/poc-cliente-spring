package br.com.kenoby.poccliente.repositories;

import br.com.kenoby.poccliente.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Page<Cliente> findByCpfAndNome(String cpf, String nome, Pageable pageable); // não precisava, apenas para constar

    @Query("select c from Cliente c where c.cpf = ?1 and c.nome = ?2")
    Page<Cliente> findByCpfAndNome2(String cpf, String nome, Pageable pageable); // não precisava, apenas para constar

    @Query("select c from Cliente c where c.cpf = :cpf and c.nome = :nome")
    Page<Cliente> findByCpfAndNome3(@Param("cpf") String cpf, @Param("nome") String nome, Pageable pageable); // não precisava, apenas para constar
}
