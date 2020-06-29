package br.com.kenoby.poccliente.handlers;

import br.com.kenoby.poccliente.presenters.ErrosPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionsHandlers {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrosPresenter> handleConstraintViolationException(ConstraintViolationException exception) {

        ErrosPresenter erros = new ErrosPresenter();
        erros.getErros();

        exception.getConstraintViolations().forEach(violation -> {
            String path = violation.getPropertyPath().toString();
            String campo = path.substring(path.lastIndexOf(".")+1);

            erros.addErro(campo, violation.getMessage());
        });

        return ResponseEntity.badRequest().body(erros);
    }
}
