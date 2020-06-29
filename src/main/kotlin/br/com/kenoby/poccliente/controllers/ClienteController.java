package br.com.kenoby.poccliente.controllers;

import br.com.kenoby.poccliente.entities.Cliente;
import br.com.kenoby.poccliente.helpers.BeanUtils;
import br.com.kenoby.poccliente.presenters.ClientePresenter;
import br.com.kenoby.poccliente.presenters.ErrosPresenter;
import br.com.kenoby.poccliente.repositories.ClienteRepository;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/clientes")
@CrossOrigin
@Validated
public class ClienteController {

    private ClienteRepository repository;

    private final static String mensagemCamposInvalidos = "Um ou mais campos inválidos";

    @Autowired
    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiResponses(@ApiResponse(code = 400, response = ErrosPresenter.class, message = mensagemCamposInvalidos))
    public ResponseEntity postCliente(@RequestBody Cliente novoCliente) {
        this.repository.save(novoCliente);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoCliente.getId())
                .toUri();

        return created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientePresenter> getCliente(@PathVariable Long id) {
        Optional<Cliente> clienteOptional = this.repository.findById(id);
        return clienteOptional.isPresent()
                ? ok(new ClientePresenter(clienteOptional.get()))
                : notFound().build();
    }

    @GetMapping
    public ResponseEntity<Page<ClientePresenter>> getCliente(
            @RequestParam(required = false) final String nome,
            @RequestParam(required = false) final String cpf,
            final Pageable paginacao) {

        Page<Cliente> clientes = this.repository.findAll(Example.of(new Cliente(nome, cpf)), paginacao);

        return clientes.isEmpty() ? noContent().build() : ok(ClientePresenter.criarLista(clientes));
    }

    @PutMapping("/{id}")
    @ApiResponses(@ApiResponse(code = 400, response = ErrosPresenter.class, message = mensagemCamposInvalidos))
    public ResponseEntity putCliente(@PathVariable Long id, @RequestBody Cliente clienteAtualizado) {
        if (this.repository.existsById(id)) {
            clienteAtualizado.setId(id);
            this.repository.save(clienteAtualizado);
            return ok().build();
        } else {
            return notFound().build();
        }
    }

    @PatchMapping("/{id}")
    @ApiResponses(@ApiResponse(code = 400, response = ErrosPresenter.class, message = mensagemCamposInvalidos))
    public ResponseEntity patchCliente(@PathVariable Long id, @RequestBody Cliente clienteParcialmenteAtualizado) {

        Optional<Cliente> clienteOptional = this.repository.findById(id);

        if (clienteOptional.isPresent()) {
            Cliente clienteAtualizado = clienteOptional.get();
            clienteParcialmenteAtualizado.setId(id);
            BeanUtils.copiarNaoNulosDePara(clienteParcialmenteAtualizado, clienteAtualizado);

            this.repository.save(clienteAtualizado);
            return ok().build();
        } else {
            return notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCliente(@PathVariable Long id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return ok().build();
        } else {
            return notFound().build();
        }
    }

}
